package com.jesz.createdieselgenerators;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jesz.createdieselgenerators.blocks.BlockRegistry;
import com.jesz.createdieselgenerators.blocks.ct.SpriteShifts;
import com.jesz.createdieselgenerators.blocks.entity.BlockEntityRegistry;
import com.jesz.createdieselgenerators.compat.EveryCompatCompat;
import com.jesz.createdieselgenerators.compat.computercraft.CCProxy;
import com.jesz.createdieselgenerators.config.ConfigRegistry;
import com.jesz.createdieselgenerators.entity.EntityRegistry;
import com.jesz.createdieselgenerators.fluids.FluidRegistry;
import com.jesz.createdieselgenerators.items.FluidStorageItem;
import com.jesz.createdieselgenerators.items.ItemRegistry;
import com.jesz.createdieselgenerators.other.CDGPartialModel;
import com.jesz.createdieselgenerators.other.SpoutCanisterFilling;
import com.jesz.createdieselgenerators.ponder.PonderIndex;
import com.jesz.createdieselgenerators.recipes.RecipeRegistry;
import com.jesz.createdieselgenerators.sounds.SoundRegistry;
import com.simibubi.create.AllTags;
import com.simibubi.create.api.behaviour.BlockSpoutingBehaviour;
import com.simibubi.create.compat.Mods;
import com.simibubi.create.foundation.data.CreateRegistrate;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.util.Mth;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.fml.config.ModConfig;
import java.util.Random;

public class CreateDieselGenerators implements ModInitializer {
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create("createdieselgenerators");
    public static final String ID = "createdieselgenerators";
    ResourceKey<CreativeModeTab> C_CREATIVE_TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(ID));


    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(ID, path);
    }

    @Override
    public void onInitialize() {

        ItemRegistry.register();
        BlockRegistry.register();
        FluidRegistry.register();
        BlockEntityRegistry.register();
        EntityRegistry.register();
        SoundRegistry.register();
        RecipeRegistry.register();

        FluidStorage.ITEM.registerFallback((itemStack, context) -> {
            if (itemStack.getItem() instanceof FluidStorageItem storageItem)
                return storageItem.getFluidStorage(itemStack, context);
            return null;
        });

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, C_CREATIVE_TAB, CreativeTab.CREATIVE_TAB);

        if(FabricLoader.getInstance().isModLoaded("moonlight")) {
            EveryCompatCompat.init();
        }

        Mods.COMPUTERCRAFT.executeIfInstalled(() -> CCProxy::register);

        BlockSpoutingBehaviour.addCustomSpoutInteraction(new ResourceLocation("createdieselgenerators:canister_filling"), new SpoutCanisterFilling());


        ForgeConfigRegistry.INSTANCE.register("createdieselgenerators-server.toml",  ModConfig.Type.SERVER, ConfigRegistry.SERVER_SPEC);
    }


    public static int getOilAmount(ServerLevel serverLevel, Holder<Biome> biome, int x, int z, long seed){
        Random random = new Random(new Random(seed).nextLong() + (long) x * z);
        int amount = Math.abs(random.nextInt());
        var reg = serverLevel.registryAccess().registry(Registries.BIOME);
        if (reg.isPresent()) {
            boolean isHighInOil = biome == null || biome.is(AllTags.optionalTag(reg.get(), new ResourceLocation("createdieselgenerators:oil_biomes")));
            if(biome != null && biome.is(AllTags.optionalTag(reg.get(), new ResourceLocation("createdieselgenerators:deny_oil_biomes"))))
                return 0;
            if(isHighInOil ? (random.nextFloat(0, 100) >= ConfigRegistry.HIGH_OIL_PERCENTAGE.get()) : (amount % 100 >= ConfigRegistry.OIL_PERCENTAGE.get()))
                return 0;
            if(ConfigRegistry.OIL_DEPOSITS_INFINITE.get())
                return Integer.MAX_VALUE;
            if(isHighInOil)
                return (int) (Mth.clamp(amount % 400000, 8000, 400000)*ConfigRegistry.HIGH_OIL_MULTIPLIER.get());
            return (int) (Mth.clamp(amount % 15000, 0, 12000)*ConfigRegistry.OIL_MULTIPLIER.get());
        }
        return 0;
    }


}
