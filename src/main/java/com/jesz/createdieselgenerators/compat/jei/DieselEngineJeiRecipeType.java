package com.jesz.createdieselgenerators.compat.jei;

import io.github.fabricators_of_create.porting_lib.util.FluidStack;
import mezz.jei.api.recipe.RecipeType;

import java.util.List;

public class DieselEngineJeiRecipeType {
    public static final RecipeType<DieselEngineJeiRecipeType> DIESEL_BURNING =
            RecipeType.create("createdieselgenerators", "diesel_burning", DieselEngineJeiRecipeType.class);

    int type;
    float speed;
    List<FluidStack> fluids;
    float stress;
    public DieselEngineJeiRecipeType(int type, float speed, float stress, List<FluidStack> fluids) {
        this.stress = stress;
        this.speed = speed;
        this.type = type;
        this.fluids = fluids;
    }
}