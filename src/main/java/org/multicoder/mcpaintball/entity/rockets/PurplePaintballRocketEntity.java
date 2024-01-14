package org.multicoder.mcpaintball.entity.rockets;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.multicoder.mcpaintball.config.MCPaintballConfig;
import org.multicoder.mcpaintball.entity.MCPaintballEntities;
import org.multicoder.mcpaintball.utility.PaintballTeam;
import org.multicoder.mcpaintball.utility.anticheats.AntiCheatCommon;
import org.multicoder.mcpaintball.utility.interfaces.IEntityDataSaver;
import org.multicoder.mcpaintball.world.PaintballMatchData;

import java.util.List;

public class PurplePaintballRocketEntity extends PersistentProjectileEntity {

    public PurplePaintballRocketEntity(EntityType<? extends PersistentProjectileEntity> type, World world) {
        super(type, world, ItemStack.EMPTY);
    }

    public PurplePaintballRocketEntity(LivingEntity owner, World world) {
        super(MCPaintballEntities.PURPLE_PAINTBALL_ROCKET, owner, world, ItemStack.EMPTY);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if (!this.getEntityWorld().isClient()) {
            BlockPos Position = blockHitResult.getBlockPos();
            PaintballMatchData levelData = PaintballMatchData.getServerState(this.getServer());
            Explosion E;
            if(MCPaintballConfig.BLOCKS_BREAK)
            {
                E = this.getEntityWorld().createExplosion(this,this.getEntityWorld().getDamageSources().explosion(this,this.getOwner()), new AntiCheatCommon.SafeExplosion(),Position.getX(),Position.getY(),Position.getZ(),5f,false, World.ExplosionSourceType.TNT);
            }
            else
            {
                E = this.getEntityWorld().createExplosion(this,this.getEntityWorld().getDamageSources().explosion(this,this.getOwner()), new AntiCheatCommon.SafeExplosion(),Position.getX(),Position.getY(),Position.getZ(),5f,false, World.ExplosionSourceType.NONE);
            }
            List<PlayerEntity> Players = E.getAffectedPlayers().keySet().stream().toList();
            for (PlayerEntity player : Players) {
                NbtCompound data = ((IEntityDataSaver) player).getPersistentData();
                if (data.contains("team")) {
                    int Team = data.getInt("team");
                    if (Team != PaintballTeam.BLUE.ordinal()) {
                        int[] Cache = levelData.Points;
                        Cache[PaintballTeam.BLUE.ordinal()] += 1;
                        levelData.Points = Cache;
                    }
                }
            }
        }
        this.kill();
        this.discard();
        super.onBlockHit(blockHitResult);
    }
}
