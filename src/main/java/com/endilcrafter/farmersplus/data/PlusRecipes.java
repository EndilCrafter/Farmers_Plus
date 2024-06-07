package com.endilcrafter.farmersplus.data;

import com.endilcrafter.farmersplus.data.recipe.*;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.function.Consumer;

public class PlusRecipes extends RecipeProvider {
    public PlusRecipes(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        PlusCookingRecipes.register(consumer);
        PlusCraftingRecipes.register(consumer);
        PlusCuttingRecipes.register(consumer);
        PlusDryingRecipes.register(consumer);
        PlusMillingRecipes.register(consumer);
        PlusSmeltingRecipes.register(consumer);
    }
}
