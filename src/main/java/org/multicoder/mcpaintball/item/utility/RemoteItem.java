package org.multicoder.mcpaintball.item.utility;

import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtLong;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.multicoder.mcpaintball.MCPaintballSounds;
import org.multicoder.mcpaintball.block.ExplosiveBlock;
import org.multicoder.mcpaintball.config.MCPaintballConfig;
import org.multicoder.mcpaintball.utility.PaintballTeam;
import org.multicoder.mcpaintball.utility.interfaces.IEntityDataSaver;
import org.multicoder.mcpaintball.world.PaintballMatchData;

import java.util.List;

public class RemoteItem extends Item {
    public RemoteItem() {
        super(new Settings());
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            PaintballTeam T = PaintballTeam.values()[((IEntityDataSaver) user).getPersistentData().getInt("team")];
            int Team = T.ordinal();
            if (Screen.hasShiftDown()) {
                NbtCompound NBT = user.getStackInHand(hand).getOrCreateNbt();
                PaintballMatchData Data = PaintballMatchData.getServerState(world.getServer());
                if (!NBT.contains("Targets")) {
                    user.sendMessage(Text.translatable("text.mcpaintball.no_tag").formatted(Formatting.BOLD).formatted(Formatting.DARK_RED), true);
                    return TypedActionResult.fail(user.getStackInHand(hand));
                } else {
                    NbtList ListTag = NBT.getList("Targets", NbtElement.LONG_TYPE);
                    world.playSound(null, user.getBlockPos(), MCPaintballSounds.C4_EXPLODE, SoundCategory.PLAYERS, 1, 1);
                    ListTag.forEach(tag ->
                    {
                        NbtLong LongValue = (NbtLong) tag;
                        long PositionLong = LongValue.longValue();
                        BlockPos Position = BlockPos.fromLong(PositionLong);
                        if (T.getExplosive() == world.getBlockState(Position).getBlock()) {
                            world.removeBlock(Position, false);
                            Explosion E;
                            if (MCPaintballConfig.BLOCKS_BREAK) {
                                E = world.createExplosion(null, Position.getX(), Position.getY(), Position.getZ(), 5f, World.ExplosionSourceType.TNT);
                            } else {
                                E = world.createExplosion(null, Position.getX(), Position.getY(), Position.getZ(), 5f, World.ExplosionSourceType.NONE);
                            }
                            List<PlayerEntity> Players = E.getAffectedPlayers().keySet().stream().toList();
                            for (PlayerEntity player : Players) {
                                NbtCompound data = ((IEntityDataSaver) player).getPersistentData();
                                if (data.contains("team")) {
                                    int TeamT = data.getInt("team");
                                    if (Team != TeamT) {
                                        player.damage(world.getDamageSources().explosion(E), 2.5f);
                                        int[] Cache = Data.Points;
                                        Cache[Team] += 1;
                                        Data.Points = Cache;
                                    }
                                }
                            }
                        }
                    });
                    user.getStackInHand(hand).setNbt(new NbtCompound());
                }
            }
        }
        return super.use(world, user, hand);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (!world.isClient()) {
            BlockPos Position = context.getBlockPos();
            PlayerEntity Player = context.getPlayer();
            PaintballTeam User = PaintballTeam.values()[((IEntityDataSaver) Player).getPersistentData().getInt("team")];
            if (world.getBlockState(Position).getBlock() instanceof ExplosiveBlock) {
                Block Filter = User.getExplosive();
                if (Filter == world.getBlockState(Position).getBlock()) {
                    long PositionS = Position.asLong();
                    NbtCompound NBT = context.getStack().getOrCreateNbt();
                    if (NBT.contains("Targets")) {
                        NbtList ListTag = NBT.getList("Targets", NbtElement.LONG_TYPE);
                        if (ListTag.contains(NbtLong.of(PositionS))) {
                            world.playSound(null, Player.getBlockPos(), MCPaintballSounds.C4_REMOVED, SoundCategory.PLAYERS, 1, 1);
                            ListTag.remove(NbtLong.of(PositionS));
                        } else {
                            world.playSound(null, Player.getBlockPos(), MCPaintballSounds.C4_ADDED, SoundCategory.PLAYERS, 1, 1);
                            ListTag.add(NbtLong.of(PositionS));
                        }
                    } else {
                        NbtList ListTag = new NbtList();
                        ListTag.add(NbtLong.of(PositionS));
                        NBT.put("Targets", ListTag);
                        context.getStack().setNbt(NBT);
                        world.playSound(null, Player.getBlockPos(), MCPaintballSounds.C4_ADDED, SoundCategory.PLAYERS, 1, 1);
                    }
                } else {
                    Player.sendMessage(Text.translatable("text.mcpaintball.invalid_team").formatted(Formatting.BOLD).formatted(Formatting.DARK_RED), true);
                    return ActionResult.FAIL;
                }
            }
        }
        return super.useOnBlock(context);
    }
}
