package com.jesz.createdieselgenerators;

import com.jesz.createdieselgenerators.blocks.BlockRegistry;
import com.jesz.createdieselgenerators.fluids.FluidRegistry;
import com.jesz.createdieselgenerators.items.ItemRegistry;
import com.simibubi.create.foundation.utility.Components;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTab;

public class CreativeTab {

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
            entries.accept(BlockRegistry.PUMPJACK_HOLE);
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
            entries.accept(FluidRegistry.CRUDE_OIL.get().getBucket());
            entries.accept(FluidRegistry.BIODIESEL.get().getBucket());
            entries.accept(FluidRegistry.DIESEL.get().getBucket());
            entries.accept(FluidRegistry.GASOLINE.get().getBucket());
            entries.accept(FluidRegistry.PLANT_OIL.get().getBucket());
            entries.accept(FluidRegistry.ETHANOL.get().getBucket());
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
