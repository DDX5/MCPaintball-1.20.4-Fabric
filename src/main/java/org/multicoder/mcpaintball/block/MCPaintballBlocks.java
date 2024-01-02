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

public class MCPaintballBlocks
{

    public static final Block AMMO_POD = registerBlock("ammo_pod",new AmmoPodBlock());

    private static Block registerBlock(String name, Block item)
    {
        Block B = Registry.register(Registries.BLOCK,new Identifier(MCPaintball.MOD_ID,name),item);
        MCPaintballItems.registerItem(name,new BlockItem(B,new Item.Settings()));
        return B;
    }

    private static void addToToolsCreativeTab(FabricItemGroupEntries entries)
    {
        entries.add(AMMO_POD.asItem());
    }
    public static void registerModBlocks()
    {
        MCPaintball.LOGGER.info("Adding Blocks");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(MCPaintballBlocks::addToToolsCreativeTab);
    }
}
