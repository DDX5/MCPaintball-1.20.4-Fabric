package org.multicoder.mcpaintball.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.multicoder.mcpaintball.item.MCPaintballItems;
import org.multicoder.mcpaintball.utility.IEntityDataSaver;
import org.multicoder.mcpaintball.utility.PaintballClass;
import org.multicoder.mcpaintball.world.PaintballMatchData;

public class AmmoPodBlock extends Block
{
    public static final BooleanProperty ENABLED = BooleanProperty.of("enabled");
    public AmmoPodBlock()
    {
        super(Settings.create().dropsNothing().pistonBehavior(PistonBehavior.BLOCK).nonOpaque().breakInstantly());
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
    {
        if(!world.isClient() && state.get(ENABLED))
        {
            if(PaintballMatchData.getServerState(world.getServer()).IsEnabled)
            {
                NbtCompound Data = ((IEntityDataSaver)player).getPersistentData();
                if(Data.contains("class"))
                {
                    PaintballClass paintballClass = PaintballClass.values()[Data.getInt("class")];
                    switch (paintballClass)
                    {
                        case STANDARD,SNIPER ->
                        {
                            ItemStack Ammo_1 = new ItemStack(MCPaintballItems.BASIC_AMMO,24);
                            ItemStack Ammo_2 = new ItemStack(MCPaintballItems.BASIC_AMMO,32);
                            player.dropItem(Ammo_2,true);
                            player.dropItem(Ammo_1,true);
                            world.setBlockState(pos,state.cycle(ENABLED));
                        }
                        case HEAVY ->
                        {
                            ItemStack Ammo_1 = new ItemStack(MCPaintballItems.ROCKET_AMMO,24);
                            ItemStack Ammo_2 = new ItemStack(MCPaintballItems.BASIC_AMMO,32);
                            player.dropItem(Ammo_2,true);
                            player.dropItem(Ammo_1,true);
                            world.setBlockState(pos,state.cycle(ENABLED));
                        }
                        case MEDIC, ENGINEER ->
                        {
                            ItemStack Ammo_1 = new ItemStack(MCPaintballItems.SHELL_AMMO,24);
                            ItemStack Ammo_2 = new ItemStack(MCPaintballItems.BASIC_AMMO,32);
                            player.dropItem(Ammo_2,true);
                            player.dropItem(Ammo_1,true);
                            world.setBlockState(pos,state.cycle(ENABLED));
                        }
                    }
                }
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random)
    {
        if(PaintballMatchData.getServerState(world.getServer()).IsEnabled)
        {
            if(!state.get(ENABLED))
            {
                world.setBlockState(pos,state.cycle(ENABLED));
            }
        }
        super.randomTick(state, world, pos, random);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx)
    {
        return super.getPlacementState(ctx).with(ENABLED,true);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        super.appendProperties(builder);
        builder.add(ENABLED);
    }
}
