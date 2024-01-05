package org.multicoder.mcpaintball.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import org.multicoder.mcpaintball.MCPaintball;
import org.multicoder.mcpaintball.network.packets.ReloadC2SPacket;

public class MCPaintballNetworking {
    public static final Identifier RELOAD_KEY_ID = new Identifier(MCPaintball.MOD_ID, "reload");

    public static void RegisterC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(RELOAD_KEY_ID, ReloadC2SPacket::receive);
    }
}
