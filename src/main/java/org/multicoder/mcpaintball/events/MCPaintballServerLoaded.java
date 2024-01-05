package org.multicoder.mcpaintball.events;

import net.minecraft.server.MinecraftServer;
import org.multicoder.mcpaintball.config.MCPaintballConfig;
import org.multicoder.mcpaintball.world.PaintballMatchData;

public class MCPaintballServerLoaded {

    public static void onServerStarted(MinecraftServer server) {
        PaintballMatchData data = PaintballMatchData.getServerState(server);
        if (data.Points == null) {
            data.Points = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        }
        try {
            MCPaintballConfig.LoadOrCreate(server);
        } catch (Exception e) {
            MCPaintballConfig.MATCH_SERVER_OP = true;
        }

    }
}
