package org.multicoder.mcpaintball.world;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import org.multicoder.mcpaintball.MCPaintball;

public class PaintballMatchData extends PersistentState {
    private static final Type<PaintballMatchData> type = new Type<>(PaintballMatchData::new, PaintballMatchData::createFromNBT, null);
    public boolean Started;
    public boolean MatchSetup;
    public int[] Points;

    public static PaintballMatchData getServerState(MinecraftServer server) {
        PersistentStateManager manager = server.getOverworld().getPersistentStateManager();
        PaintballMatchData data = manager.getOrCreate(type, MCPaintball.MOD_ID);
        data.markDirty();
        return data;
    }

    public static PaintballMatchData createFromNBT(NbtCompound nbt) {
        PaintballMatchData data = new PaintballMatchData();
        data.Started = nbt.getBoolean("started");
        data.Points = nbt.getIntArray("points");
        data.MatchSetup = nbt.getBoolean("setup");
        return data;
    }


    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putBoolean("started", Started);
        nbt.putIntArray("points", Points);
        nbt.putBoolean("setup",MatchSetup);
        return nbt;
    }
}
