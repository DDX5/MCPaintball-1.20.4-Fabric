package org.multicoder.mcpaintball;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class MCPaintballSounds
{
    public static final SoundEvent SHOT = Registry.register(Registries.SOUND_EVENT,new Identifier(MCPaintball.MOD_ID,"shot"),SoundEvent.of(new Identifier(MCPaintball.MOD_ID,"shot")));
    public static final SoundEvent SPLAT = Registry.register(Registries.SOUND_EVENT,new Identifier(MCPaintball.MOD_ID,"splat"),SoundEvent.of(new Identifier(MCPaintball.MOD_ID,"splat")));
    public static final SoundEvent HIT = Registry.register(Registries.SOUND_EVENT,new Identifier(MCPaintball.MOD_ID,"hit"),SoundEvent.of(new Identifier(MCPaintball.MOD_ID,"hit")));
    public static final SoundEvent GRENADE = Registry.register(Registries.SOUND_EVENT,new Identifier(MCPaintball.MOD_ID,"grenade"),SoundEvent.of(new Identifier(MCPaintball.MOD_ID,"grenade")));
    public static final SoundEvent BAZOOKA = Registry.register(Registries.SOUND_EVENT,new Identifier(MCPaintball.MOD_ID,"bazooka"),SoundEvent.of(new Identifier(MCPaintball.MOD_ID,"bazooka")));

    public static void registerModSounds(){
        MCPaintball.LOGGER.info("Adding Sounds");
    }
}
