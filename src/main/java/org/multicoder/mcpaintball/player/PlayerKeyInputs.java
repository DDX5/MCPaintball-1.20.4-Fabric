package org.multicoder.mcpaintball.player;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.multicoder.mcpaintball.network.MCPaintballNetworking;

public class PlayerKeyInputs {
    public static final String KEY_CATEGORY_MCPAINTBALL = "key.category.mcpaintball.gameplay";
    public static final String KEY_RELOAD = "key.mcpaintball.reload";

    public static KeyBinding Reload;

    public static void registerInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (Reload.wasPressed()) {
                ClientPlayNetworking.send(MCPaintballNetworking.RELOAD_KEY_ID, PacketByteBufs.create());
            }
        });
    }

    public static void Register() {
        Reload = KeyBindingHelper.registerKeyBinding(new KeyBinding(KEY_RELOAD, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, KEY_CATEGORY_MCPAINTBALL));
        registerInputs();
    }
}
