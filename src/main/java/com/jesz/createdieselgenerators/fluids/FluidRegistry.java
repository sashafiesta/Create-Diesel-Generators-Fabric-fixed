package com.jesz.createdieselgenerators.fluids;

import com.tterrag.registrate.util.entry.FluidEntry;
import com.tterrag.registrate.fabric.SimpleFlowableFluid;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributeHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

import static com.jesz.createdieselgenerators.CreateDieselGenerators.REGISTRATE;

public class FluidRegistry {

    public static final FluidEntry<SimpleFlowableFluid.Flowing> PLANT_OIL =
            REGISTRATE.fluid("plant_oil", new ResourceLocation("createdieselgenerators:block/plant_oil_still"), new ResourceLocation("createdieselgenerators:block/plant_oil_flow"))
                    .lang("Plant Oil")
                    .fluidAttributes(() -> new CreateAttributeHandler("block.createdieselgenerators.plant_oil", 1500, 500))
                    .fluidProperties(p -> p.levelDecreasePerBlock(2)
                            .tickRate(25)
                            .flowSpeed(3)
                            .blastResistance(100f))
                    .register();
    public static final FluidEntry<SimpleFlowableFluid.Flowing> CRUDE_OIL =
            REGISTRATE.fluid("crude_oil", new ResourceLocation("createdieselgenerators:block/crude_oil_still"), new ResourceLocation("createdieselgenerators:block/crude_oil_flow"))
                    .lang("Crude Oil")
                    .fluidAttributes(() -> new CreateAttributeHandler("block.createdieselgenerators.crude_oil", 1500, 100))
                    .fluidProperties(p -> p.levelDecreasePerBlock(3)
                            .tickRate(25)
                            .flowSpeed(2)
                            .blastResistance(100f))
                    .register();

    public static final FluidEntry<SimpleFlowableFluid.Flowing> BIODIESEL =
            REGISTRATE.fluid("biodiesel", new ResourceLocation("createdieselgenerators:block/biodiesel_still"), new ResourceLocation("createdieselgenerators:block/biodiesel_flow"))
                    .lang("Biodiesel")
                    .fluidAttributes(() -> new CreateAttributeHandler("block.createdieselgenerators.biodiesel", 1500, 500))
                    .fluidProperties(p -> p.levelDecreasePerBlock(2)
                            .tickRate(25)
                            .flowSpeed(3)
                            .blastResistance(100f))
                    .register();
    public static final FluidEntry<SimpleFlowableFluid.Flowing> DIESEL =
            REGISTRATE.fluid("diesel", new ResourceLocation("createdieselgenerators:block/diesel_still"), new ResourceLocation("createdieselgenerators:block/diesel_flow"))
                    .lang("Diesel")
                    .fluidAttributes(() -> new CreateAttributeHandler("block.createdieselgenerators.diesel", 1500, 500))
                    .fluidProperties(p -> p.levelDecreasePerBlock(2)
                            .tickRate(25)
                            .flowSpeed(3)
                            .blastResistance(100f))
                    .register();
    public static final FluidEntry<SimpleFlowableFluid.Flowing> GASOLINE =
            REGISTRATE.fluid("gasoline", new ResourceLocation("createdieselgenerators:block/gasoline_still"), new ResourceLocation("createdieselgenerators:block/gasoline_flow"))
                    .lang("Gasoline")
                    .fluidAttributes(() -> new CreateAttributeHandler("block.createdieselgenerators.gasoline", 1500, 500))
                    .fluidProperties(p -> p.levelDecreasePerBlock(2)
                            .tickRate(25)
                            .flowSpeed(3)
                            .blastResistance(100f))
                    .register();
    public static final FluidEntry<SimpleFlowableFluid.Flowing> ETHANOL =
            REGISTRATE.fluid("ethanol", new ResourceLocation("createdieselgenerators:block/ethanol_still"), new ResourceLocation("createdieselgenerators:block/ethanol_flow"))
                    .lang("Ethanol")
                    .fluidAttributes(() -> new CreateAttributeHandler("block.createdieselgenerators.ethanol", 1500, 500))
                    .fluidProperties(p -> p.levelDecreasePerBlock(2)
                            .tickRate(25)
                            .flowSpeed(5)
                            .blastResistance(100f))
                    .register();

    public static void register() {}

    private record CreateAttributeHandler(Component name, int viscosity, boolean lighterThanAir) implements FluidVariantAttributeHandler {
        private CreateAttributeHandler(String key, int viscosity, int density) {
            this(Component.translatable(key), viscosity, density <= 0);
        }

        public CreateAttributeHandler(String key) {
            this(key, FluidConstants.WATER_VISCOSITY, 1000);
        }

        @Override
        public Component getName(FluidVariant fluidVariant) {
            return name.copy();
        }

        @Override
        public int getViscosity(FluidVariant variant, @Nullable Level world) {
            return viscosity;
        }

        @Override
        public boolean isLighterThanAir(FluidVariant variant) {
            return lighterThanAir;
        }
    }
}
