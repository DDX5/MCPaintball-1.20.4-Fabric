package org.multicoder.mcpaintball.item.weapons;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.multicoder.mcpaintball.MCPaintballSounds;
import org.multicoder.mcpaintball.item.MCPaintballItems;
import org.multicoder.mcpaintball.utility.PaintballTeam;
import org.multicoder.mcpaintball.utility.interfaces.IEntityDataSaver;
import org.multicoder.mcpaintball.utility.interfaces.IReloadableWeapon;
import org.multicoder.mcpaintball.world.PaintballMatchData;

public class RifleItem extends IReloadableWeapon {

    public RifleItem() {
        super(new Settings().maxDamage(64));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            ItemStack Held = user.getStackInHand(hand);
            IEntityDataSaver persistantData = ((IEntityDataSaver) user);
            NbtCompound compound = persistantData.getPersistentData();
            if (compound.contains("team")) {
                PaintballMatchData data = PaintballMatchData.getServerState(user.getServer());
                if (data.Started) {
                    if (Held.getDamage() != Held.getMaxDamage()) {
                        PaintballTeam team = PaintballTeam.values()[compound.getInt("team")];
                        PersistentProjectileEntity arrow = team.getPaintball(user, world);
                        arrow.setVelocity(user, user.getPitch(), user.getYaw(), user.getRoll(), 5f, 0f);
                        world.spawnEntity(arrow);
                        world.playSound(null, user.getBlockPos(), MCPaintballSounds.SHOT, SoundCategory.PLAYERS, 1f, 1f);
                        user.getItemCooldownManager().set(this, 10);
                        Held.setDamage((Held.getDamage() + 1));
                    } else {
                        user.sendMessage(Text.translatable("text.mcpaintball.reload").formatted(Formatting.BOLD).formatted(Formatting.DARK_RED));
                    }
                } else {
                    user.sendMessage(Text.translatable("text.mcpaintball.match_error").formatted(Formatting.BOLD).formatted(Formatting.DARK_RED), false);
                }
            }
        }
        return super.use(world, user, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (selected && world.isClient()) {
            if (entity instanceof PlayerEntity Player && Screen.hasShiftDown()) {
                Player.sendMessage(Text.translatable("text.mcpaintball.ammo", (stack.getMaxDamage() - stack.getDamage())), true);
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public ItemStack getReloadItem() {
        return new ItemStack(MCPaintballItems.BASIC_AMMO);
    }

}
