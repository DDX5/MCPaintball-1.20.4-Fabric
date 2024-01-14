package org.multicoder.mcpaintball.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.GameRules;
import org.multicoder.mcpaintball.config.MCPaintballConfig;
import org.multicoder.mcpaintball.utility.PaintballTeam;
import org.multicoder.mcpaintball.world.PaintballMatchData;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.server.command.CommandManager.RegistrationEnvironment;
import static net.minecraft.server.command.CommandManager.literal;

public class MatchCommands {
    public static void registerMatchCommands(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess access, RegistrationEnvironment environment) {
        dispatcher.register(literal("mcpaintball").then(literal("match").then(literal("start").executes(MatchCommands::StartMatch)))).createBuilder().build();
        dispatcher.register(literal("mcpaintball").then(literal("match").then(literal("setup").executes(MatchCommands::StupMatch)))).createBuilder().build();
        dispatcher.register(literal("mcpaintball").then(literal("match").then(literal("end").executes(MatchCommands::EndMatch)))).createBuilder().build();
        dispatcher.register(literal("mcpaintball").then(literal("match").then(literal("winner").executes(MatchCommands::Winner)))).createBuilder().build();
    }

    private static int StupMatch(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        if(MCPaintballConfig.MATCH_SERVER_OP) {
            if (context.getSource().getPlayerOrThrow().hasPermissionLevel(2)) {
                PaintballMatchData.getServerState(context.getSource().getServer()).MatchSetup = true;
                context.getSource().getServer().getPlayerManager().broadcast(Text.translatable("mcpaintball.command.response.match.setup"),true);
                return 0;
            }
            else
            {
                context.getSource().getPlayerOrThrow().sendMessage(Text.translatable("mcpaintball.command.response.match.error").formatted(Formatting.BOLD).formatted(Formatting.DARK_RED));
                return -1;
            }
        }
        else{
            PaintballMatchData.getServerState(context.getSource().getServer()).MatchSetup = true;
            context.getSource().getServer().getPlayerManager().broadcast(Text.translatable("mcpaintball.command.response.match.setup"),true);
            return 0;
        }
    }

    private static int Winner(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        if (MCPaintballConfig.MATCH_SERVER_OP) {
            if (context.getSource().getPlayerOrThrow().hasPermissionLevel(2)) {
                PaintballMatchData data = PaintballMatchData.getServerState(context.getSource().getServer());
                List<Integer> Points = new ArrayList<>();
                for (int Point : data.Points) {
                    Points.add(Point);
                }
                int Winner = Points.indexOf(Points.stream().max(Integer::compare).orElseThrow());
                PaintballTeam WinningTeam = PaintballTeam.values()[Winner];
                context.getSource().getServer().getPlayerManager().broadcast(Text.translatable("mcpaintball.command.response.team.winner", WinningTeam.name()), false);
                data.Points = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
                return 0;
            } else
            {
                context.getSource().getPlayerOrThrow().sendMessage(Text.translatable("mcpaintball.command.response.match.error").formatted(Formatting.BOLD).formatted(Formatting.DARK_RED));
                return -1;
            }
        }
        else
        {
            PaintballMatchData data = PaintballMatchData.getServerState(context.getSource().getServer());
            List<Integer> Points = new ArrayList<>();
            for (int Point : data.Points) {
                Points.add(Point);
            }
            int Winner = Points.indexOf(Points.stream().max(Integer::compare).orElseThrow());
            PaintballTeam WinningTeam = PaintballTeam.values()[Winner];
            context.getSource().getServer().getPlayerManager().broadcast(Text.translatable("mcpaintball.command.response.team.winner", WinningTeam.name()), false);
            data.Points = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
            return 0;
        }
    }

    private static int EndMatch(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        if (MCPaintballConfig.MATCH_SERVER_OP) {
            if (context.getSource().getPlayerOrThrow().hasPermissionLevel(2)) {
                context.getSource().getServer().getGameRules().get(GameRules.KEEP_INVENTORY).set(false, context.getSource().getServer());
                PaintballMatchData data = PaintballMatchData.getServerState(context.getSource().getServer());
                data.Started = false;
                context.getSource().getServer().getPlayerManager().broadcast(Text.translatable("mcpaintball.command.response.match.end"), true);

            } else {
                context.getSource().getPlayerOrThrow().sendMessage(Text.translatable("mcpaintball.command.response.match.error").formatted(Formatting.BOLD).formatted(Formatting.DARK_RED));
            }
        } else {
            context.getSource().getServer().getGameRules().get(GameRules.KEEP_INVENTORY).set(false, context.getSource().getServer());
            PaintballMatchData data = PaintballMatchData.getServerState(context.getSource().getServer());
            data.Started = false;
            context.getSource().getServer().getPlayerManager().broadcast(Text.translatable("mcpaintball.command.response.match.end"), true);

        }
        return 0;
    }

    private static int StartMatch(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        if (MCPaintballConfig.MATCH_SERVER_OP) {
            if (context.getSource().getPlayerOrThrow().hasPermissionLevel(2)) {
                context.getSource().getServer().getGameRules().get(GameRules.KEEP_INVENTORY).set(true, context.getSource().getServer());
                PaintballMatchData data = PaintballMatchData.getServerState(context.getSource().getServer());
                data.Started = true;
                data.MatchSetup = false;
                context.getSource().getServer().getPlayerManager().broadcast(Text.translatable("mcpaintball.command.response.match.start"), true);

            } else {
                context.getSource().getPlayerOrThrow().sendMessage(Text.translatable("mcpaintball.command.response.match.error").formatted(Formatting.BOLD).formatted(Formatting.DARK_RED));
            }
        } else {
            context.getSource().getServer().getGameRules().get(GameRules.KEEP_INVENTORY).set(true, context.getSource().getServer());
            PaintballMatchData data = PaintballMatchData.getServerState(context.getSource().getServer());
            data.Started = true;
            data.MatchSetup = false;
            context.getSource().getServer().getPlayerManager().broadcast(Text.translatable("mcpaintball.command.response.match.start"), true);

        }
        return 0;
    }
}
