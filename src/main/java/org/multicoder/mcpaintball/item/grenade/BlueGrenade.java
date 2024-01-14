package org.multicoder.mcpaintball.item.grenade;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.multicoder.mcpaintball.MCPaintballSounds;
import org.multicoder.mcpaintball.entity.grenade.BlueGrenadeEntity;
import org.multicoder.mcpaintball.world.PaintballMatchData;

public class BlueGrenade extends Item {
    public BlueGrenade() {
        super(new Settings());
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            PaintballMatchData data = PaintballMatchData.getServerState(world.getServer());
            if (data.Started) {
                ThrownItemEntity E = new BlueGrenadeEntity(user, world);
                E.setVelocity(user, user.getPitch(), user.getYaw(), user.getRoll(), 3, 0);
                world.spawnEntity(E);
                user.getItemCooldownManager().set(this, 30);
                world.playSound(null, user.getBlockPos(), MCPaintballSounds.GRENADE, SoundCategory.PLAYERS, 1, 1);
            }
        }
        return super.use(world, user, hand);
    }
}
