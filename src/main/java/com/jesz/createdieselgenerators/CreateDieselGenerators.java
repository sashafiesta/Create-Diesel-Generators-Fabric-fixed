package com.jesz.createdieselgenerators;

import com.jesz.createdieselgenerators.blocks.BlockRegistry;
import com.jesz.createdieselgenerators.blocks.entity.BlockEntityRegistry;
import com.jesz.createdieselgenerators.config.ConfigRegistry;
import com.jesz.createdieselgenerators.fluids.FluidRegistry;
import com.jesz.createdieselgenerators.items.ItemRegistry;
import com.jesz.createdieselgenerators.ponder.PonderIndex;
import com.jesz.createdieselgenerators.recipes.RecipeRegistry;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.utility.Components;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.config.ModConfig;

import static com.simibubi.create.foundation.utility.Lang.resolveBuilders;

public class CreateDieselGenerators implements ModInitializer, ClientModInitializer {
    public static String ID = "createdieselgenerators";
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create("createdieselgenerators");
    @Override
    public void onInitialize() {
        ItemRegistry.register();
        BlockRegistry.register();
        BlockEntityRegistry.register();

        RecipeRegistry.register();
        FluidRegistry.register();

        REGISTRATE.register();
        CreativeTab.register();
        ForgeConfigRegistry.INSTANCE.register("createdieselgenerators", ModConfig.Type.SERVER, ConfigRegistry.SERVER_SPEC, "createdieselgenerators-server.toml");
    }

    public static MutableComponent translate(String key, Object... args) {
        return Components.translatable(key, resolveBuilders(args));
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(ID, path);
    }

    @Override
    public void onInitializeClient() {
        //ItemBlockRenderTypes.setRenderLayer(FluidRegistry.ETHANOL.get(), RenderType.translucent());
        //TODO ItemBlockRenderTypes.setRenderLayer(FluidRegistry.ETHANOL.getSource(), RenderType.translucent());
        PonderIndex.register();
    }
}
