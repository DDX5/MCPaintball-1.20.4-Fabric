package org.multicoder.mcpaintball.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.multicoder.mcpaintball.MCPaintball;
import org.multicoder.mcpaintball.item.ammo.BasicAmmoItem;
import org.multicoder.mcpaintball.item.ammo.RocketAmmoItem;
import org.multicoder.mcpaintball.item.ammo.ShellAmmoItem;
import org.multicoder.mcpaintball.item.grenade.*;
import org.multicoder.mcpaintball.item.utility.RemoteItem;
import org.multicoder.mcpaintball.item.weapons.*;

public class MCPaintballItems {
    public static final Item PISTOL = registerItem("pistol", new PistolItem());
    public static final Item RIFLE = registerItem("rifle", new RifleItem());
    public static final Item SHOTGUN = registerItem("shotgun", new ShotgunItem());
    public static final Item SNIPER = registerItem("sniper", new SniperItem());
    public static final Item BAZOOKA = registerItem("bazooka", new BazookaItem());

    public static final Item RED_GRENADE = registerItem("red_grenade", new RedGrenade());
    public static final Item GREEN_GRENADE = registerItem("green_grenade", new GreenGrenade());
    public static final Item BLUE_GRENADE = registerItem("blue_grenade", new BlueGrenade());
    public static final Item CYAN_GRENADE = registerItem("cyan_grenade", new CyanGrenade());
    public static final Item MAGENTA_GRENADE = registerItem("magenta_grenade", new MagentaGrenade());
    public static final Item LIME_GRENADE = registerItem("lime_grenade", new LimeGrenade());
    public static final Item LIGHT_BLUE_GRENADE = registerItem("light_blue_grenade", new LightBlueGrenade());
    public static final Item PINK_GRENADE = registerItem("pink_grenade", new PinkGrenade());
    public static final Item PURPLE_GRENADE = registerItem("purple_grenade", new PurpleGrenade());

    public static final Item BASIC_AMMO = registerItem("basic_ammo", new BasicAmmoItem());
    public static final Item ROCKET_AMMO = registerItem("rocket_ammo", new RocketAmmoItem());
    public static final Item SHELL_AMMO = registerItem("shell_ammo", new ShellAmmoItem());

    public static final Item REMOTE = registerItem("remote", new RemoteItem());

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(MCPaintball.MOD_ID, name), item);
    }

    private static void addToCreativeTab(FabricItemGroupEntries entries) {
        entries.add(PISTOL);
        entries.add(RIFLE);
        entries.add(SHOTGUN);
        entries.add(SNIPER);
        entries.add(BAZOOKA);
        entries.add(RED_GRENADE);
        entries.add(GREEN_GRENADE);
        entries.add(BLUE_GRENADE);
        entries.add(CYAN_GRENADE);
        entries.add(MAGENTA_GRENADE);
        entries.add(LIME_GRENADE);
        entries.add(LIGHT_BLUE_GRENADE);
        entries.add(PINK_GRENADE);
        entries.add(PURPLE_GRENADE);
    }

    private static void addToToolsCreativeTab(FabricItemGroupEntries entries) {
        entries.add(BASIC_AMMO);
        entries.add(ROCKET_AMMO);
        entries.add(SHELL_AMMO);
        entries.add(REMOTE);
    }

    public static void registerModItems() {
        MCPaintball.LOGGER.info("Adding Items");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(MCPaintballItems::addToCreativeTab);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(MCPaintballItems::addToToolsCreativeTab);
    }
}
