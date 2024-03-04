package com.jesz.createdieselgenerators.sounds;

import com.simibubi.create.AllSoundEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class SoundRegistry {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "createdieselgenerators");

    public static RegistryObject<SoundEvent> DIESEL_ENGINE_SOUND = registerSoundEvent("diesel_engine_sound");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createFixedRangeEvent(new ResourceLocation("createdieselgenerators", name), 8));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
