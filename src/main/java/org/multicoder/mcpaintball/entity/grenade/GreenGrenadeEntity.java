package org.multicoder.mcpaintball.entity.grenade;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.multicoder.mcpaintball.entity.MCPaintballEntities;
import org.multicoder.mcpaintball.item.MCPaintballItems;
import org.multicoder.mcpaintball.utility.IEntityDataSaver;
import org.multicoder.mcpaintball.utility.PaintballTeam;
import org.multicoder.mcpaintball.world.PaintballMatchData;

import java.util.List;

public class GreenGrenadeEntity extends ThrownItemEntity
{

    public GreenGrenadeEntity(EntityType<? extends ThrownItemEntity> entityType, World world)
    {
        super(entityType, world);
    }


    public GreenGrenadeEntity(LivingEntity livingEntity, World world)
    {
        super(MCPaintballEntities.GREEN_GRENADE, livingEntity, world);
    }

    @Override
    protected void onCollision(HitResult hitResult)
    {
        if(!this.getEntityWorld().isClient())
        {
            PaintballMatchData levelData = PaintballMatchData.getServerState(this.getServer());
            BlockPos Position = BlockPos.ofFloored(hitResult.getPos());
            Explosion E = this.getEntityWorld().createExplosion(this,Position.getX(),Position.getY(),Position.getZ(),5, World.ExplosionSourceType.TNT);
            List<PlayerEntity> Players = E.getAffectedPlayers().keySet().stream().toList();
            for(PlayerEntity player : Players)
            {
                NbtCompound data = ((IEntityDataSaver) player).getPersistentData();
                if(data.contains("team"))
                {
                    int Team = data.getInt("team");
                    if(Team != PaintballTeam.GREEN.ordinal())
                    {
                        int[] Cache = levelData.Points;
                        Cache[PaintballTeam.GREEN.ordinal()] += 1;
                        levelData.Points = Cache;
                    }
                }
            }
        }
        this.kill();
        this.discard();
        super.onCollision(hitResult);
    }

    @Override
    protected Item getDefaultItem()
    {
        return MCPaintballItems.GREEN_GRENADE;
    }


    @Override
    protected ItemStack getItem() {
        return new ItemStack(MCPaintballItems.GREEN_GRENADE);
    }
}
