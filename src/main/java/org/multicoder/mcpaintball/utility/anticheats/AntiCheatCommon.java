package org.multicoder.mcpaintball.utility.anticheats;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.multicoder.mcpaintball.config.MCPaintballConfig;

public class AntiCheatCommon
{
    public static class SafeExplosion extends ExplosionBehavior
    {
        @Override
        public boolean canDestroyBlock(Explosion explosion, BlockView world, BlockPos pos, BlockState state, float power)
        {
            if(!MCPaintballConfig.BLOCKS_BREAK)
            {
                return false;
            }
            return super.canDestroyBlock(explosion, world, pos, state, power);
        }

        @Override
        public boolean shouldDamage(Explosion explosion, Entity entity)
        {
            if(!(entity instanceof PlayerEntity)){
                return false;
            }
            return true;
        }
    }
}
