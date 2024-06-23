package com.endilcrafter.farmersplus.data.recipe;

import com.endilcrafter.farmersplus.common.registry.PlusItems;
import com.endilcrafter.farmersplus.data.builder.DryingRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Consumer;

public class PlusDryingRecipes {
    public static void register(Consumer<FinishedRecipe> consumer) {
        DryingRecipeBuilder.dryingRecipe(Ingredient.of(PlusItems.APPLE_SLICE.get()), PlusItems.DRIED_APPLE_SLICE.get(), 300).build(consumer);
        DryingRecipeBuilder.dryingRecipe(Ingredient.of(PlusItems.GOLDEN_APPLE_SLICE.get()), PlusItems.DRIED_GOLDEN_APPLE_SLICE.get(), 300).build(consumer);
        DryingRecipeBuilder.dryingRecipe(Ingredient.of(Items.BEEF), PlusItems.DRIED_BEEF.get()).build(consumer);
        DryingRecipeBuilder.dryingRecipe(Ingredient.of(Items.CHICKEN), PlusItems.DRIED_CHICKEN.get()).build(consumer);
        DryingRecipeBuilder.dryingRecipe(Ingredient.of(ModItems.COD_SLICE.get()), PlusItems.DRIED_COD_SLICE.get()).build(consumer);
        DryingRecipeBuilder.dryingRecipe(Ingredient.of(Items.MUTTON), PlusItems.DRIED_MUTTON.get()).build(consumer);
        DryingRecipeBuilder.dryingRecipe(Ingredient.of(Items.PORKCHOP), PlusItems.DRIED_PORKCHOP.get()).build(consumer);
        DryingRecipeBuilder.dryingRecipe(Ingredient.of(Items.RABBIT), PlusItems.DRIED_RABBIT.get()).build(consumer);
        DryingRecipeBuilder.dryingRecipe(Ingredient.of(ModItems.SALMON_SLICE.get()), PlusItems.DRIED_SALMON_SLICE.get()).build(consumer);

        DryingRecipeBuilder.dryingRecipe(Ingredient.of(Items.KELP), Items.DRIED_KELP, 300).build(consumer);
        DryingRecipeBuilder.dryingRecipe(Ingredient.of(PlusItems.KELP_BLOCK.get()), Items.DRIED_KELP_BLOCK, 1200).build(consumer);
        DryingRecipeBuilder.dryingRecipe(Ingredient.of(Items.WET_SPONGE), Items.SPONGE).build(consumer);
    }
}
