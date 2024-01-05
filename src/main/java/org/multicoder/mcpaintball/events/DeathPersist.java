package org.multicoder.mcpaintball.events;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import org.multicoder.mcpaintball.utility.interfaces.IEntityDataSaver;

public class DeathPersist implements ServerPlayerEvents.CopyFrom {

    @Override
    public void copyFromPlayer(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        NbtCompound Data = ((IEntityDataSaver) oldPlayer).getPersistentData();
        ((IEntityDataSaver) newPlayer).setPersistentData(Data);
    }
}
