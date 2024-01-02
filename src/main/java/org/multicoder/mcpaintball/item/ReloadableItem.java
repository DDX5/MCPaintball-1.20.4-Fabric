package org.multicoder.mcpaintball.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ReloadableItem extends Item
{

    public ReloadableItem(Settings settings)
    {
        super(settings);
    }

    public ItemStack GetAmmoType()
    {
        return ItemStack.EMPTY;
    }
}
