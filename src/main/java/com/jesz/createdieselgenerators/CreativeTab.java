package com.jesz.createdieselgenerators;

import com.jesz.createdieselgenerators.blocks.BlockRegistry;
import com.jesz.createdieselgenerators.fluids.FluidRegistry;
import com.jesz.createdieselgenerators.items.ItemRegistry;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllCreativeModeTabs;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.compress.utils.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CreativeTab {

    public static final ResourceKey<CreativeModeTab> CREATIVE_TAB =
            ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(CreateDieselGenerators.ID, "group"));

    public static final AllCreativeModeTabs.TabInfo MAIN_TAB = register("base",
            () -> FabricItemGroup.builder()
                    .title(Component.translatable("itemGroup.create.base"))
                    .icon(() -> AllBlocks.COGWHEEL.asStack())
                    .displayItems(new AllCreativeModeTabs.RegistrateDisplayItemsGenerator(true))
                    .build());

    private static AllCreativeModeTabs.TabInfo register(String name, Supplier<CreativeModeTab> supplier) {
        ResourceLocation id = CreateDieselGenerators.asResource(name);
        ResourceKey<CreativeModeTab> key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, id);
        CreativeModeTab tab = supplier.get();
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key, tab);
        return new AllCreativeModeTabs.TabInfo(key, tab);
    }

    public static void register() {
        List<Item> ITEMS = Lists.newArrayList();
        ITEMS.add(ItemRegistry.ENGINEPISTON.get());
        ITEMS.add(BlockRegistry.DIESEL_ENGINE.get().asItem());
        ITEMS.add(BlockRegistry.MODULAR_DIESEL_ENGINE.get().asItem());
        ITEMS.add(BlockRegistry.BASIN_LID.get().asItem());
        ITEMS.add(FluidRegistry.BIODIESEL.getBucket().get());
        ITEMS.add(FluidRegistry.ETHANOL.getBucket().get());
        ITEMS.add(FluidRegistry.PLANT_OIL.getBucket().get());

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CREATIVE_TAB, FabricItemGroup.builder()
                .icon(() -> new ItemStack(BlockRegistry.DIESEL_ENGINE))
                .title(Component.translatable(CreateDieselGenerators.ID + ".group.main"))
                .build());

        ItemGroupEvents.modifyEntriesEvent(CREATIVE_TAB).register(entries -> {
            entries.acceptAll(ITEMS.stream().map(Item::getDefaultInstance).toList());
        });
    }

}
