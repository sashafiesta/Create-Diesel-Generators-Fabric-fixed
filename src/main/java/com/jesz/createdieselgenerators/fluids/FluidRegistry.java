package com.jesz.createdieselgenerators.fluids;

import com.simibubi.create.AllFluids;
import com.simibubi.create.Create;
import com.tterrag.registrate.fabric.SimpleFlowableFluid;
import com.tterrag.registrate.util.entry.FluidEntry;
import io.github.fabricators_of_create.porting_lib.core.PortingLib;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

import static com.jesz.createdieselgenerators.CreateDieselGenerators.REGISTRATE;

public class FluidRegistry {

    public static final FluidEntry<SimpleFlowableFluid.Flowing> PLANT_OIL =
            REGISTRATE.fluid("plant_oil", new ResourceLocation("createdieselgenerators:block/plant_oil_still"), new ResourceLocation("createdieselgenerators:block/plant_oil_flow"))
                    .lang("Plant Oil")
                    .fluidAttributes(() -> new AllFluids.CreateAttributeHandler("block.create.plant_oil", 1500, 500))
                    .fluidProperties(p -> p.levelDecreasePerBlock(2)
                            .tickRate(25)
                            .flowSpeed(3)
                            .blastResistance(100f))
                    .register();

    public static final FluidEntry<SimpleFlowableFluid.Flowing> BIODIESEL =
            REGISTRATE.fluid("biodiesel", new ResourceLocation("createdieselgenerators:block/biodiesel_still"), new ResourceLocation("createdieselgenerators:block/biodiesel_flow"))
                    .lang("Biodiesel")
                    .fluidAttributes(() -> new AllFluids.CreateAttributeHandler("block.create.biodiesel", 1500, 500))
                    .fluidProperties(p -> p.levelDecreasePerBlock(2)
                            .tickRate(25)
                            .flowSpeed(3)
                            .blastResistance(100f))
                    .register();
    public static final FluidEntry<SimpleFlowableFluid.Flowing> ETHANOL =
            REGISTRATE.fluid("ethanol", new ResourceLocation("createdieselgenerators:block/ethanol_still"), new ResourceLocation("createdieselgenerators:block/ethanol_flow"))
                    .lang("Ethanol")
                    .fluidAttributes(() -> new AllFluids.CreateAttributeHandler("block.create.ethanol", 1500, 500))
                    .fluidProperties(p -> p.levelDecreasePerBlock(2)
                            .tickRate(25)
                            .flowSpeed(5)
                            .blastResistance(100f))
                    .register();

    public static void register() {}

}
