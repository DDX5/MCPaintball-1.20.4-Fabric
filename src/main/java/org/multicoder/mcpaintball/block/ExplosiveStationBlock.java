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
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.multicoder.mcpaintball.item.MCPaintballItems;
import org.multicoder.mcpaintball.utility.PaintballClass;
import org.multicoder.mcpaintball.utility.PaintballTeam;
import org.multicoder.mcpaintball.utility.interfaces.IEntityDataSaver;
import org.multicoder.mcpaintball.world.PaintballMatchData;

public class ExplosiveStationBlock extends Block {
    public static final BooleanProperty ENABLED = BooleanProperty.of("enabled");
    public static final IntProperty TEAM = IntProperty.of("team", 0, 8);

    public ExplosiveStationBlock() {
        super(Settings.create().dropsNothing().pistonBehavior(PistonBehavior.BLOCK).nonOpaque().hardness(2.5f));
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient() && state.get(ENABLED)) {
            if (PaintballMatchData.getServerState(world.getServer()).Started) {
                NbtCompound Data = ((IEntityDataSaver) player).getPersistentData();
                if (Data.contains("class")) {
                    if (Data.getInt("team") == state.get(TEAM).intValue()) {
                        PaintballClass paintballClass = PaintballClass.values()[Data.getInt("class")];
                        PaintballTeam T = PaintballTeam.values()[state.get(TEAM).intValue()];
                        ItemStack[] Items = T.GetTeamItems();
                        ItemStack EX_1 = Items[0];
                        ItemStack EX_2 = Items[1];
                        switch (paintballClass) {
                            case STANDARD, SNIPER ->
                            {
                                EX_1.setCount(12);
                                EX_2.setCount(6);
                                player.dropItem(EX_1,true);
                                player.dropItem(EX_2, true);
                                world.setBlockState(pos, state.cycle(ENABLED));
                            }
                            case HEAVY -> {
                                EX_1.setCount(16);
                                EX_2.setCount(9);
                                player.dropItem(EX_1, true);
                                player.dropItem(EX_2, true);
                                world.setBlockState(pos, state.cycle(ENABLED));
                            }
                            case MEDIC, ENGINEER -> {
                                EX_1.setCount(10);
                                EX_2.setCount(4);
                                player.dropItem(EX_1, true);
                                player.dropItem(EX_2, true);
                                world.setBlockState(pos, state.cycle(ENABLED));
                            }
                        }
                    }
                }
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (PaintballMatchData.getServerState(world.getServer()).Started) {
            if (!state.get(ENABLED)) {
                world.setBlockState(pos, state.cycle(ENABLED));
            }
        }
        super.randomTick(state, world, pos, random);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        PlayerEntity player = ctx.getPlayer();
        int T = ((IEntityDataSaver) player).getPersistentData().getInt("team");
        return super.getPlacementState(ctx).with(ENABLED, true).with(TEAM, T);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(ENABLED);
        builder.add(TEAM);
    }
}
