package org.multicoder.mcpaintball.utility.interfaces;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class IReloadableWeapon extends Item {

    public IReloadableWeapon(Settings settings) {
        super(settings);
    }

    public ItemStack getReloadItem() {
        return ItemStack.EMPTY;
    }

    public void Reload(PlayerInventory Inventory, ItemStack Held, ItemStack AmmoType) {
        int Slot = Inventory.indexOf(AmmoType);
        ItemStack Ammo = Inventory.getStack(Slot);
        int Damage = Held.getDamage();
        int AmmoCount = Ammo.getCount();
        if (AmmoCount > Damage) {
            int Remainder = AmmoCount - Damage;
            Held.setDamage(0);
            Ammo.setCount(Remainder);
        } else {
            int Remainder = Damage - AmmoCount;
            Held.setDamage(Remainder);
            Inventory.removeStack(Slot);
        }
    }
}
