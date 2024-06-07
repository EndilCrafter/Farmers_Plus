package com.endilcrafter.farmersplus.integration.jei;

import com.endilcrafter.farmersplus.FarmersPlus;
import com.endilcrafter.farmersplus.common.crafting.DryingRackRecipe;
import com.endilcrafter.farmersplus.common.crafting.MillstoneRecipe;
import mezz.jei.api.recipe.RecipeType;

public class FPRecipeTypes {
    public static final RecipeType<DryingRackRecipe> DRYING = RecipeType.create(FarmersPlus.MODID, "drying", DryingRackRecipe.class);
    public static final RecipeType<MillstoneRecipe> MILLING = RecipeType.create(FarmersPlus.MODID, "milling", MillstoneRecipe.class);
}
