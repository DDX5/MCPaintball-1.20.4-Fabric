package org.multicoder.mcpaintball.block;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.multicoder.mcpaintball.MCPaintball;
import org.multicoder.mcpaintball.item.MCPaintballItems;

public class MCPaintballBlocks {

    public static final Block AMMO_POD = registerBlock("ammo_pod", new AmmoPodBlock());
    public static final Block EXPLOSIVE_STATION = registerBlock("explosive_station", new ExplosiveStationBlock());
    public static final Block RED_EXPLOSIVE = registerBlock("red_explosive", new ExplosiveBlock());
    public static final Block GREEN_EXPLOSIVE = registerBlock("green_explosive", new ExplosiveBlock());
    public static final Block BLUE_EXPLOSIVE = registerBlock("blue_explosive", new ExplosiveBlock());
    public static final Block CYAN_EXPLOSIVE = registerBlock("cyan_explosive", new ExplosiveBlock());
    public static final Block MAGENTA_EXPLOSIVE = registerBlock("magenta_explosive", new ExplosiveBlock());
    public static final Block LIME_EXPLOSIVE = registerBlock("lime_explosive", new ExplosiveBlock());
    public static final Block LIGHT_BLUE_EXPLOSIVE = registerBlock("light_blue_explosive", new ExplosiveBlock());
    public static final Block PINK_EXPLOSIVE = registerBlock("pink_explosive", new ExplosiveBlock());
    public static final Block PURPLE_EXPLOSIVE = registerBlock("purple_explosive", new ExplosiveBlock());

    private static Block registerBlock(String name, Block item) {
        Block B = Registry.register(Registries.BLOCK, new Identifier(MCPaintball.MOD_ID, name), item);
        MCPaintballItems.registerItem(name, new BlockItem(B, new Item.Settings()));
        return B;
    }

    private static void addToToolsCreativeTab(FabricItemGroupEntries entries) {
        entries.add(AMMO_POD.asItem());
        entries.add(RED_EXPLOSIVE.asItem());
        entries.add(GREEN_EXPLOSIVE.asItem());
        entries.add(BLUE_EXPLOSIVE.asItem());
        entries.add(CYAN_EXPLOSIVE.asItem());
        entries.add(MAGENTA_EXPLOSIVE.asItem());
        entries.add(LIME_EXPLOSIVE.asItem());
        entries.add(LIGHT_BLUE_EXPLOSIVE.asItem());
        entries.add(PINK_EXPLOSIVE.asItem());
        entries.add(PURPLE_EXPLOSIVE.asItem());
        entries.add(EXPLOSIVE_STATION.asItem());
    }

    public static void registerModBlocks() {
        MCPaintball.LOGGER.info("Adding Blocks");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(MCPaintballBlocks::addToToolsCreativeTab);
    }
}
