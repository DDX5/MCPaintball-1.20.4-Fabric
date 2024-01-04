package org.multicoder.mcpaintball.network.packets;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import org.multicoder.mcpaintball.utility.interfaces.IReloadable;

public class ReloadC2SPacket
{

    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buffer, PacketSender sender)
    {
        Item Held = player.getStackInHand(Hand.MAIN_HAND).getItem();
        ItemStack HeldStack = player.getStackInHand(Hand.MAIN_HAND);
        if(Held instanceof IReloadable)
        {
            ItemStack Filter = ((IReloadable) Held).getReloadItem();
            PlayerInventory Inventory = player.getInventory();
            if(Inventory.contains(Filter))
            {
                int Index = Inventory.indexOf(Filter);
                ItemStack PlayerAmmo = Inventory.getStack(Index);
                if(HeldStack.getDamage() < PlayerAmmo.getCount())
                {
                    PlayerAmmo.setCount(PlayerAmmo.getCount() - HeldStack.getDamage());
                    HeldStack.setDamage(0);
                }
                else
                {
                    int Remaining = HeldStack.getDamage() - PlayerAmmo.getCount();
                    HeldStack.setDamage(Remaining);
                    Inventory.removeStack(Index);
                }
                player.getItemCooldownManager().set(HeldStack.getItem(),60);
                player.sendMessage(Text.translatable("text.mcpaintball.reloading"),true);
                while(player.getItemCooldownManager().isCoolingDown(HeldStack.getItem())) {}
            }
        }
        else
        {
            return;
        }
    }
}
