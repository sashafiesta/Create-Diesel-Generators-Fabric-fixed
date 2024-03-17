package com.jesz.createdieselgenerators.sounds;

import com.jesz.createdieselgenerators.CreateDieselGenerators;
import com.simibubi.create.AllSoundEvents;
import com.simibubi.create.Create;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

import java.util.HashMap;
import java.util.Map;

public class SoundRegistry {

    public static final Map<ResourceLocation, AllSoundEvents.SoundEntry> ALL = new HashMap<>();


    public static final AllSoundEvents.SoundEntry

            DIESEL_ENGINE_SOUND = create("diesel_engine_sound").noSubtitle()
            .category(SoundSource.NEUTRAL)
            .attenuationDistance(128)
            .build();

    private static AllSoundEvents.SoundEntryBuilder create(String name) {
        return create(CreateDieselGenerators.asResource(name));
    }

    public static AllSoundEvents.SoundEntryBuilder create(ResourceLocation id) {
        return new AllSoundEvents.SoundEntryBuilder(id);
    }

    public static void register() {
        for (AllSoundEvents.SoundEntry entry : ALL.values())
            entry.register();
    }
}
