package org.multicoder.mcpaintball;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.multicoder.mcpaintball.block.MCPaintballBlocks;
import org.multicoder.mcpaintball.commands.MatchCommands;
import org.multicoder.mcpaintball.commands.TeamCommands;
import org.multicoder.mcpaintball.commands.arguments.CommandArguments;
import org.multicoder.mcpaintball.entity.MCPaintballEntities;
import org.multicoder.mcpaintball.events.DeathPersist;
import org.multicoder.mcpaintball.events.MCPaintballServerLoaded;
import org.multicoder.mcpaintball.item.MCPaintballItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MCPaintball implements ModInitializer {
    public static final String MOD_ID = "mcpaintball";
    public static final Logger LOGGER = LoggerFactory.getLogger(MCPaintball.MOD_ID);

    @Override
    public void onInitialize()
    {
        MCPaintballItems.registerModItems();
        MCPaintballBlocks.registerModBlocks();
        MCPaintballEntities.registerModEntities();
        MCPaintballSounds.registerModSounds();
        CommandArguments.registerModCommandArguments();
        CommandRegistrationCallback.EVENT.register((TeamCommands::registerTeamCommands));
        CommandRegistrationCallback.EVENT.register((MatchCommands::registerMatchCommands));
        ServerLifecycleEvents.SERVER_STARTED.register(MCPaintballServerLoaded::onServerStarted);
        ServerPlayerEvents.COPY_FROM.register(new DeathPersist());
    }
}