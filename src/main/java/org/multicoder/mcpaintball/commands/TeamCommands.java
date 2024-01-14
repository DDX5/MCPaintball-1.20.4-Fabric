package org.multicoder.mcpaintball.commands;

import com.google.gson.JsonSerializationContext;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.BookScreen;
import net.minecraft.client.util.Clipboard;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Util;
import org.multicoder.mcpaintball.MCPaintball;
import org.multicoder.mcpaintball.commands.arguments.PaintballClassArgument;
import org.multicoder.mcpaintball.commands.arguments.PaintballTeamArgument;
import org.multicoder.mcpaintball.events.MCPaintballServerLoaded;
import org.multicoder.mcpaintball.utility.PaintballClass;
import org.multicoder.mcpaintball.utility.PaintballTeam;
import org.multicoder.mcpaintball.utility.interfaces.IEntityDataSaver;
import org.multicoder.mcpaintball.world.PaintballMatchData;

import java.awt.*;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class TeamCommands {
    public static void registerTeamCommands(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess access, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(literal("mcpaintball").then(literal("team").then(literal("set").then(argument("team", PaintballTeamArgument.teamArgument()).executes(TeamCommands::SetTeam))))).createBuilder().build();
        dispatcher.register(literal("mcpaintball").then(literal("class").then(literal("set").then(argument("class", PaintballClassArgument.classArgument()).executes(TeamCommands::SetClass))))).createBuilder().build();
        dispatcher.register(literal("mcpaintball").then(literal("tag").then(literal("copy").executes(TeamCommands::CloneTag)))).createBuilder().build();
        dispatcher.register(literal("mcpaintball").then(literal("help").executes(TeamCommands::Help))).createBuilder().build();
    }

    private static int Help(CommandContext<ServerCommandSource> context) throws CommandSyntaxException
    {
        ItemStack Book = new ItemStack(Items.WRITTEN_BOOK);
        Book.setNbt(MCPaintballServerLoaded.Book);
        if(context.getSource().getPlayerOrThrow().getInventory().insertStack(Book))
        {
            return 0;
        }
        context.getSource().getPlayerOrThrow().dropItem(Book,true);
        return 0;
    }

    private static int CloneTag(CommandContext<ServerCommandSource> context) throws CommandSyntaxException
    {
        PlayerEntity Player = context.getSource().getPlayerOrThrow();
        ItemStack Held = Player.getMainHandStack();
        if(Held.hasNbt())
        {
            NbtCompound Tag = Held.getNbt();
            MCPaintball.LOGGER.info("Tag: " + NbtHelper.toNbtProviderString(Tag));
        }
        return 0;
    }

    public static int SetTeam(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerCommandSource source = context.getSource();
        ServerPlayerEntity player = source.getPlayerOrThrow();
        PaintballMatchData Data = PaintballMatchData.getServerState(context.getSource().getServer());
        if(Data.MatchSetup && !Data.Started)
        {
            PaintballTeam team = PaintballTeamArgument.getTeam(context, "team");
            IEntityDataSaver persistantData = ((IEntityDataSaver) player);
            persistantData.getPersistentData().putInt("team", team.ordinal());
            player.sendMessage(Text.translatable("mcpaintball.command.response.team.set").append(" ").append(Text.translatable(team.getTranslationKey())));
            player.getDisplayName().getStyle().withColor(team.getColor());
            return 0;
        }
        player.sendMessage(Text.translatable("mcpaintball.command.response.settings").formatted(Formatting.BOLD,Formatting.DARK_RED),true);
        return -1;
    }

    public static int SetClass(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerCommandSource source = context.getSource();
        ServerPlayerEntity player = source.getPlayerOrThrow();
        PaintballMatchData Data = PaintballMatchData.getServerState(context.getSource().getServer());
        if(Data.MatchSetup && !Data.Started)
        {
            PaintballClass Type = PaintballClassArgument.getClass(context, "class");
            IEntityDataSaver persistantData = ((IEntityDataSaver) player);
            persistantData.getPersistentData().putInt("class", Type.ordinal());
            player.sendMessage(Text.translatable("mcpaintball.command.response.class.set").append(" ").append(Text.translatable(Type.getTranslationKey())));
            return 0;
        }
        player.sendMessage(Text.translatable("mcpaintball.command.response.settings").formatted(Formatting.BOLD,Formatting.DARK_RED),true);
        return -1;
    }
}
