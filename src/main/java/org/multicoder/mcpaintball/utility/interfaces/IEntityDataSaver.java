package org.multicoder.mcpaintball.utility.interfaces;

import net.minecraft.nbt.NbtCompound;

public interface IEntityDataSaver {
    NbtCompound getPersistentData();

    void setPersistentData(NbtCompound compound);
}

