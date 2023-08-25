package com.jesz.createdieselgenerators.recipes;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.Create;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeSerializer;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public enum RecipeRegistry implements IRecipeTypeInfo {

    BASIN_FERMENTING(BasinFermentingRecipe::new);
    private final ResourceLocation id = new ResourceLocation("createdieselgenerators", "basin_fermenting");
    private final RecipeSerializer<?> serializerObject;
    @Nullable
    private final RecipeType<?> typeObject;
    private final Supplier<RecipeType<?>> type;

    RecipeRegistry(Supplier<RecipeSerializer<?>> serializerSupplier) {
        serializerObject = Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, id, serializerSupplier.get());
        typeObject = AllRecipeTypes.simpleType(id);
        Registry.register(BuiltInRegistries.RECIPE_TYPE, id, typeObject);
        type = () -> typeObject;
    }

    RecipeRegistry(ProcessingRecipeBuilder.ProcessingRecipeFactory<?> processingFactory) {
        this(() -> new ProcessingRecipeSerializer<>(processingFactory));
    }
    public static void register() {

    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public <T extends RecipeSerializer<?>> T getSerializer() {
        return (T) serializerObject;
    }

    @Override
    public <T extends RecipeType<?>> T getType() {
        return (T) type.get();
    }
}
