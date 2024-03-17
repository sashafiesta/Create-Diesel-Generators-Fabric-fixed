package com.jesz.createdieselgenerators;

import com.jesz.createdieselgenerators.blocks.BlockRegistry;
import com.jesz.createdieselgenerators.fluids.FluidRegistry;
import com.jesz.createdieselgenerators.items.ItemRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;

public class CreativeTab {


    public static final CreativeModeTab CREATIVE_TAB = CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                    .title(Component.translatable("itemGroup.cdg_creative_tab"))
                    .icon(BlockRegistry.DIESEL_ENGINE::asStack)
                    .displayItems((pParameters, output) -> {
                        output.accept(ItemRegistry.ENGINE_PISTON.get());
                        output.accept(ItemRegistry.ENGINE_SILENCER.get());
                        output.accept(ItemRegistry.ENGINE_TURBO.get());
                        output.accept(BlockRegistry.DIESEL_ENGINE.get());
                        output.accept(BlockRegistry.MODULAR_DIESEL_ENGINE.get());
                        output.accept(BlockRegistry.HUGE_DIESEL_ENGINE.get());
                        output.accept(ItemRegistry.DISTILLATION_CONTROLLER.get());
                        output.accept(ItemRegistry.OIL_SCANNER.get());
                        output.accept(BlockRegistry.PUMPJACK_BEARING.get());
                        output.accept(BlockRegistry.PUMPJACK_CRANK.get());
                        output.accept(BlockRegistry.PUMPJACK_HEAD.get());
                        output.accept(ItemRegistry.WOOD_CHIPS.get());
                        output.accept(BlockRegistry.CHIP_WOOD_BEAM.get());
                        output.accept(BlockRegistry.CHIP_WOOD_BLOCK.get());
                        output.accept(BlockRegistry.CHIP_WOOD_STAIRS.get());
                        output.accept(BlockRegistry.CHIP_WOOD_SLAB.get());
                        output.accept(BlockRegistry.CANISTER.get());
                        output.accept(BlockRegistry.OIL_BARREL.get());
                        output.accept(BlockRegistry.BASIN_LID.get());
                        output.accept(BlockRegistry.ASPHALT_BLOCK.get());
                        output.accept(BlockRegistry.ASPHALT_STAIRS.get());
                        output.accept(BlockRegistry.ASPHALT_SLAB.get());
                        output.accept(FluidRegistry.CRUDE_OIL.getBucket().get());
                        output.accept(FluidRegistry.BIODIESEL.getBucket().get());
                        output.accept(FluidRegistry.DIESEL.getBucket().get());
                        output.accept(FluidRegistry.GASOLINE.getBucket().get());
                        output.accept(FluidRegistry.PLANT_OIL.getBucket().get());
                        output.accept(FluidRegistry.ETHANOL.getBucket().get());
                        output.accept(ItemRegistry.KELP_HANDLE.get());
                        output.accept(ItemRegistry.LIGHTER.get());
                        output.accept(ItemRegistry.CHEMICAL_SPRAYER.get());
                        output.accept(ItemRegistry.CHEMICAL_SPRAYER_LIGHTER.get());
                    })
                    .build();
}
