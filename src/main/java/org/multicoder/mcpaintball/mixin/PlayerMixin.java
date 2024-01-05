package org.multicoder.mcpaintball.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.multicoder.mcpaintball.utility.interfaces.IEntityDataSaver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class PlayerMixin implements IEntityDataSaver {
    private NbtCompound persistentData;

    @Override
    public NbtCompound getPersistentData() {
        if (this.persistentData == null) {
            this.persistentData = new NbtCompound();
        }
        return persistentData;
    }

    @Override
    public void setPersistentData(NbtCompound persistentData) {
        this.persistentData = persistentData;
    }

    @Inject(method = "writeNbt", at = @At("HEAD"))
    protected void injectWriteMethod(NbtCompound nbt, CallbackInfoReturnable info) {
        if (persistentData != null) {
            nbt.put("mcpaintball.data", persistentData);
        }
    }

    @Inject(method = "readNbt", at = @At("HEAD"))
    protected void injectReadMethod(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains("mcpaintball.data", 10)) {
            persistentData = nbt.getCompound("mcpaintball.data");
        }
    }
}
