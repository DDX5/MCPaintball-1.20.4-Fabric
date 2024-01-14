package org.multicoder.mcpaintball.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.multicoder.mcpaintball.item.MCPaintballItems;
import org.multicoder.mcpaintball.utility.PaintballClass;
import org.multicoder.mcpaintball.utility.interfaces.IEntityDataSaver;
import org.multicoder.mcpaintball.world.PaintballMatchData;

public class ExplosiveBlock extends Block {
    public ExplosiveBlock() {
        super(Settings.create().pistonBehavior(PistonBehavior.BLOCK).hardness(2.5f).dropsNothing().nonOpaque());
    }

    public VoxelShape makeShape() {
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.25, 0, 0.25, 0.75, 0.3125, 0.75), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.625, 0.3125, 0.4375, 0.75, 0.4375, 0.5625), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.25, 0.3125, 0.4375, 0.375, 0.4375, 0.5625), BooleanBiFunction.OR);
        return shape;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return makeShape();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return makeShape();
    }
}
