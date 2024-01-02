package org.multicoder.mcpaintball.utility;

import net.minecraft.nbt.NbtCompound;

public interface IEntityDataSaver
{
    NbtCompound getPersistentData();

    void setPersistentData(NbtCompound compound);
}

