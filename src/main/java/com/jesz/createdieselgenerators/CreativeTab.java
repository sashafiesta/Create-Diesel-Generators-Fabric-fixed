package com.jesz.createdieselgenerators;

import com.jesz.createdieselgenerators.blocks.BlockRegistry;
import com.jesz.createdieselgenerators.fluids.FluidRegistry;
import com.jesz.createdieselgenerators.items.ItemRegistry;
import com.simibubi.create.AllCreativeModeTabs;
import com.simibubi.create.foundation.utility.Components;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreativeTab {

    private static List<ItemLike> items = List.of(
            ItemRegistry.ENGINE_PISTON.get(),
            ItemRegistry.ENGINE_SILENCER.get(),
            ItemRegistry.ENGINE_TURBO.get(),
            BlockRegistry.DIESEL_ENGINE.get(),
            BlockRegistry.MODULAR_DIESEL_ENGINE.get(),
            BlockRegistry.HUGE_DIESEL_ENGINE.get(),
            ItemRegistry.DISTILLATION_CONTROLLER.get(),
            ItemRegistry.OIL_SCANNER.get(),
            BlockRegistry.PUMPJACK_BEARING.get(),
            BlockRegistry.PUMPJACK_CRANK.get(),
            BlockRegistry.PUMPJACK_HEAD.get(),
            ItemRegistry.WOOD_CHIPS.get(),
            BlockRegistry.CHIP_WOOD_BEAM.get(),
            BlockRegistry.CHIP_WOOD_BLOCK.get(),
            BlockRegistry.CHIP_WOOD_STAIRS.get(),
            BlockRegistry.CHIP_WOOD_SLAB.get(),
            BlockRegistry.CANISTER.get(),
            BlockRegistry.OIL_BARREL.get(),
            BlockRegistry.BASIN_LID.get(),
            BlockRegistry.ASPHALT_BLOCK.get(),
            BlockRegistry.ASPHALT_STAIRS.get(),
            BlockRegistry.ASPHALT_SLAB.get(),
            //FluidRegistry.CRUDE_OIL.getBucket().get(),
            //FluidRegistry.BIODIESEL.getBucket().get(),
            //FluidRegistry.DIESEL.getBucket().get(),
            //FluidRegistry.GASOLINE.getBucket().get(),
            //FluidRegistry.PLANT_OIL.getBucket().get(),
            //FluidRegistry.ETHANOL.getBucket().get(),
            ItemRegistry.KELP_HANDLE.get(),
            ItemRegistry.LIGHTER.get(),
            ItemRegistry.CHEMICAL_SPRAYER.get(),
            ItemRegistry.CHEMICAL_SPRAYER_LIGHTER.get());

    public static void register(){

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, "main", BASE_CREATIVE_TAB);
        ItemGroupEvents.MODIFY_ENTRIES_ALL.register(CreativeTab::make);
    }

    private static void make(CreativeModeTab creativeModeTab, FabricItemGroupEntries entries) {
        if (creativeModeTab == BASE_CREATIVE_TAB) {

            entries.accept(ItemRegistry.ENGINE_PISTON);
            entries.accept(ItemRegistry.ENGINE_SILENCER);
            entries.accept(ItemRegistry.ENGINE_TURBO);
            entries.accept(BlockRegistry.DIESEL_ENGINE);
            entries.accept(BlockRegistry.MODULAR_DIESEL_ENGINE);
            entries.accept(BlockRegistry.HUGE_DIESEL_ENGINE);
            entries.accept(ItemRegistry.DISTILLATION_CONTROLLER);
            entries.accept(ItemRegistry.OIL_SCANNER);
            entries.accept(BlockRegistry.PUMPJACK_BEARING);
            entries.accept(BlockRegistry.PUMPJACK_CRANK);
            entries.accept(BlockRegistry.PUMPJACK_HEAD);
            entries.accept(ItemRegistry.WOOD_CHIPS);
            entries.accept(BlockRegistry.CHIP_WOOD_BEAM);
            entries.accept(BlockRegistry.CHIP_WOOD_BLOCK);
            entries.accept(BlockRegistry.CHIP_WOOD_STAIRS);
            entries.accept(BlockRegistry.CHIP_WOOD_SLAB);
            entries.accept(BlockRegistry.CANISTER);
            entries.accept(BlockRegistry.OIL_BARREL);
            entries.accept(BlockRegistry.BASIN_LID);
            entries.accept(BlockRegistry.ASPHALT_BLOCK);
            entries.accept(BlockRegistry.ASPHALT_STAIRS);
            entries.accept(BlockRegistry.ASPHALT_SLAB);
                    //FluidRegistry.CRUDE_OIL.getBucket().get(),
                    //FluidRegistry.BIODIESEL.getBucket().get(),
                    //FluidRegistry.DIESEL.getBucket().get(),
                    //FluidRegistry.GASOLINE.getBucket().get(),
                    //FluidRegistry.PLANT_OIL.getBucket().get(),
                    //FluidRegistry.ETHANOL.getBucket().get(),
            entries.accept(ItemRegistry.KELP_HANDLE);
            entries.accept(ItemRegistry.LIGHTER);
            entries.accept(ItemRegistry.CHEMICAL_SPRAYER);
            entries.accept(ItemRegistry.CHEMICAL_SPRAYER_LIGHTER);
        }
    }

    public static final CreativeModeTab BASE_CREATIVE_TAB = FabricItemGroup.builder()
                    .title(Components.translatable("itemGroup.create.base"))
                    .icon(BlockRegistry.DIESEL_ENGINE::asStack)
                    .build();
}
