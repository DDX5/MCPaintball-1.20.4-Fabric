package org.multicoder.mcpaintball.entity.paintball;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.multicoder.mcpaintball.MCPaintballSounds;
import org.multicoder.mcpaintball.entity.MCPaintballEntities;
import org.multicoder.mcpaintball.utility.interfaces.IEntityDataSaver;
import org.multicoder.mcpaintball.world.PaintballMatchData;


public class LimePaintballEntity extends PersistentProjectileEntity {


    public LimePaintballEntity(EntityType<? extends PersistentProjectileEntity> type, World world) {
        super(type, world, ItemStack.EMPTY);
    }

    public LimePaintballEntity(LivingEntity owner, World world) {
        super(MCPaintballEntities.LIME_PAINTBALL, owner, world, ItemStack.EMPTY);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (!this.getEntityWorld().isClient()) {
            PaintballMatchData data = PaintballMatchData.getServerState(this.getServer());
            if (data.Started) {
                if (entityHitResult.getEntity() instanceof PlayerEntity Target) {
                    PlayerEntity Shooter = (PlayerEntity) this.getOwner();
                    NbtCompound ShooterData = ((IEntityDataSaver) Shooter).getPersistentData();
                    NbtCompound TargetData = ((IEntityDataSaver) Target).getPersistentData();
                    if (ShooterData.contains("team") && TargetData.contains("team")) {
                        if (ShooterData.getInt("team") != TargetData.getInt("team")) {
                            int Index = ShooterData.getInt("team");
                            int[] P = data.Points;
                            P[Index] += 1;
                            data.Points = P;
                            Target.damage(this.getEntityWorld().getDamageSources().arrow(this, this.getOwner()), 2.5f);
                            this.getEntityWorld().playSound(null, this.getOwner().getBlockPos(), MCPaintballSounds.HIT, SoundCategory.PLAYERS, 1, 1);
                        }
                    }
                }
                this.kill();
                this.discard();
            }
        }
    }

    @Override
    protected SoundEvent getHitSound() {
        return MCPaintballSounds.SPLAT;
    }

    @Override
    protected ItemStack asItemStack() {
        return ItemStack.EMPTY;
    }
}
