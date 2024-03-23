package com.jesz.createdieselgenerators;

import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.utility.Lang;
import io.github.fabricators_of_create.porting_lib.tags.Tags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

import static com.simibubi.create.AllTags.NameSpace.FORGE;
import static com.simibubi.create.AllTags.NameSpace.MOD;
import static com.simibubi.create.AllTags.optionalTag;

public class TagRegistry {

    public enum FluidTags {

        CRUDE_OIL(FORGE),
        BIODIESEL(FORGE),
        ETHANOL(FORGE),
        GASOLINE(FORGE),
        PLANT_OIL(FORGE)

        ;

        public final TagKey<Fluid> tag;
        public final boolean alwaysDatagen;

        FluidTags() {
            this(MOD);
        }

        FluidTags(AllTags.NameSpace namespace) {
            this(namespace, namespace.optionalDefault, namespace.alwaysDatagenDefault);
        }

        FluidTags(AllTags.NameSpace namespace, String path) {
            this(namespace, path, namespace.optionalDefault, namespace.alwaysDatagenDefault);
        }

        FluidTags(AllTags.NameSpace namespace, boolean optional, boolean alwaysDatagen) {
            this(namespace, null, optional, alwaysDatagen);
        }

        FluidTags(AllTags.NameSpace namespace, String path, boolean optional, boolean alwaysDatagen) {
            ResourceLocation id = new ResourceLocation(namespace.id, path == null ? Lang.asId(name()) : path);
            tag = optionalTag(BuiltInRegistries.FLUID, id);
            this.alwaysDatagen = alwaysDatagen;
        }

        @SuppressWarnings("deprecation")
        public boolean matches(Fluid fluid) {
            return fluid.is(tag);
        }

        public boolean matches(FluidState state) {
            return state.is(tag);
        }

        public static void init() {}
    }
}
