package com.jesz.createdieselgenerators.mixins;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.stream.Stream;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(at = @At("HEAD"),method = "tick()V")
    public void tick(CallbackInfo ci){
    }
}
