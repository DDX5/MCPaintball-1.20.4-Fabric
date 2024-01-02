package org.multicoder.mcpaintball.world;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import org.multicoder.mcpaintball.MCPaintball;

public class PaintballMatchData extends PersistentState
{
    public boolean IsEnabled;
    public int[] Points;

    private static Type<PaintballMatchData> type = new Type<>(PaintballMatchData::new,PaintballMatchData::createFromNBT,null);

    public static PaintballMatchData getServerState(MinecraftServer server)
    {
        PersistentStateManager manager = server.getOverworld().getPersistentStateManager();
        PaintballMatchData data = manager.getOrCreate(type, MCPaintball.MOD_ID);
        data.markDirty();
        return data;
    }
    public static PaintballMatchData createFromNBT(NbtCompound nbt)
    {
        PaintballMatchData data = new PaintballMatchData();
        data.IsEnabled = nbt.getBoolean("enabled");
        data.Points = nbt.getIntArray("points");
        return data;
    }


    @Override
    public NbtCompound writeNbt(NbtCompound nbt)
    {
        nbt.putBoolean("enabled",IsEnabled);
        nbt.putIntArray("points",Points);
        return nbt;
    }
}
