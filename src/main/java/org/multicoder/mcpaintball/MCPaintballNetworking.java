package org.multicoder.mcpaintball;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.util.Identifier;
import org.multicoder.packets.ReloadC2SPacket;

public class MCPaintballNetworking
{
    public static final Identifier RELOAD_KEY_ID = new Identifier(MCPaintball.MOD_ID,"reload");

    public static void RegisterC2SPackets()
    {
        ServerPlayNetworking.registerGlobalReceiver(RELOAD_KEY_ID, ReloadC2SPacket::receive);
    }
}
