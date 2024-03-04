package com.jesz.createdieselgenerators.other;

import net.minecraft.world.entity.Entity;

public class EntityTickEvent extends Event implements IModBusEvent {
    public EntityTickEvent(Object entity){
        this.entity = (Entity) entity;
    }
    public Entity entity;
}
