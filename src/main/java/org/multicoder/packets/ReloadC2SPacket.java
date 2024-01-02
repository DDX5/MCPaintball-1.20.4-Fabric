package org.multicoder.packets;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import org.multicoder.mcpaintball.item.ReloadableItem;

public class ReloadC2SPacket
{

    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buffer, PacketSender sender)
    {
        Item Held = player.getStackInHand(Hand.MAIN_HAND).getItem();
        ItemStack HeldStack = player.getStackInHand(Hand.MAIN_HAND);
        if(Held instanceof ReloadableItem)
        {
            ItemStack Filter = ((ReloadableItem) Held).GetAmmoType();
            PlayerInventory Inventory = player.getInventory();
            if(Inventory.contains(Filter)){
                int Index = Inventory.indexOf(Filter);
                ItemStack PlayerAmmo = Inventory.getStack(Index);
                int Damage = HeldStack.getDamage();
                int AmmoCount = PlayerAmmo.getCount();
                if((AmmoCount - Damage) >= 0)
                {
                    PlayerAmmo.setCount(AmmoCount - Damage);
                    player.getItemCooldownManager().set(HeldStack.getItem(),60);
                    while(player.getItemCooldownManager().isCoolingDown(HeldStack.getItem()))
                    {

                    }
                    HeldStack.setDamage(0);
                }
                else
                {
                    Inventory.removeStack(Index);
                    player.getItemCooldownManager().set(HeldStack.getItem(),60);
                    while(player.getItemCooldownManager().isCoolingDown(HeldStack.getItem()))
                    {

                    }
                    HeldStack.setDamage(Damage - AmmoCount);
                }
            }
        }
        else{
            return;
        }
    }
}
