package org.multicoder.mcpaintball.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.multicoder.mcpaintball.commands.arguments.PaintballClassArgument;
import org.multicoder.mcpaintball.utility.IEntityDataSaver;
import org.multicoder.mcpaintball.utility.PaintballClass;
import org.multicoder.mcpaintball.utility.PaintballTeam;
import org.multicoder.mcpaintball.commands.arguments.PaintballTeamArgument;

import static net.minecraft.server.command.CommandManager.*;

public class TeamCommands
{
    public static void registerTeamCommands(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess access, CommandManager.RegistrationEnvironment environment)
    {
        dispatcher.register(literal("mcpaintball").then(literal("team").then(literal("set").then(argument("team", PaintballTeamArgument.teamArgument()).executes(TeamCommands::SetTeam))))).createBuilder().build();
        dispatcher.register(literal("mcpaintball").then(literal("class").then(literal("set").then(argument("class", PaintballClassArgument.classArgument()).executes(TeamCommands::SetClass))))).createBuilder().build();
    }

    public static int SetTeam(CommandContext<ServerCommandSource> context) throws CommandSyntaxException
    {
        ServerCommandSource source = context.getSource();
        ServerPlayerEntity player = source.getPlayerOrThrow();
        PaintballTeam team = PaintballTeamArgument.getTeam(context,"team");
        IEntityDataSaver persistantData = ((IEntityDataSaver) player);
        persistantData.getPersistentData().putInt("team",team.ordinal());
        player.sendMessage(Text.translatable("mcpaintball.command.response.team.set",team.name().toLowerCase()));
        return 0;
    }
    public static int SetClass(CommandContext<ServerCommandSource> context) throws CommandSyntaxException
    {
        ServerCommandSource source = context.getSource();
        ServerPlayerEntity player = source.getPlayerOrThrow();
        PaintballClass Type = PaintballClassArgument.getClass(context,"class");
        IEntityDataSaver persistantData = ((IEntityDataSaver) player);
        persistantData.getPersistentData().putInt("class",Type.ordinal());
        player.sendMessage(Text.translatable("mcpaintball.command.response.class.set",Type.name().toLowerCase()));
        return 0;
    }
}
