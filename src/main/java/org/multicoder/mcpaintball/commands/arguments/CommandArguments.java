package org.multicoder.mcpaintball.commands.arguments;

import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;
import net.minecraft.util.Identifier;
import org.multicoder.mcpaintball.MCPaintball;

public class CommandArguments {
    public static void registerModCommandArguments() {
        ArgumentTypeRegistry.registerArgumentType(new Identifier(MCPaintball.MOD_ID, "team"), PaintballTeamArgument.class, ConstantArgumentSerializer.of(PaintballTeamArgument::teamArgument));
        ArgumentTypeRegistry.registerArgumentType(new Identifier(MCPaintball.MOD_ID, "class"), PaintballClassArgument.class, ConstantArgumentSerializer.of(PaintballClassArgument::classArgument));
    }
}
