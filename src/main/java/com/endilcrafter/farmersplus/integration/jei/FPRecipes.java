package com.endilcrafter.farmersplus.integration.jei;

import com.endilcrafter.farmersplus.common.crafting.DryingRackRecipe;
import com.endilcrafter.farmersplus.common.crafting.MillstoneRecipe;
import com.endilcrafter.farmersplus.common.registry.PlusRecipeTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

public class FPRecipes {
    private final RecipeManager recipeManager;

    public FPRecipes() {
        Minecraft minecraft = Minecraft.getInstance();
        ClientLevel level = minecraft.level;

        if (level != null) {
            this.recipeManager = level.getRecipeManager();
        } else {
            throw new NullPointerException("minecraft world must not be null.");
        }
    }

    public List<DryingRackRecipe> getDryingRackRecipes() {
        return recipeManager.getAllRecipesFor(PlusRecipeTypes.DRYING.get()).stream().toList();
    }

    public List<MillstoneRecipe> getMillstoneRecipes() {
        return recipeManager.getAllRecipesFor(PlusRecipeTypes.MILLING.get()).stream().toList();
    }
}
