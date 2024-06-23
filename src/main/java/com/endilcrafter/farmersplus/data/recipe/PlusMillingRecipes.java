package com.endilcrafter.farmersplus.data.recipe;

import com.endilcrafter.farmersplus.common.registry.PlusItems;
import com.endilcrafter.farmersplus.data.builder.MillstoneRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Consumer;

public class PlusMillingRecipes {
    public static void register(Consumer<FinishedRecipe> consumer) {
        MillstoneRecipeBuilder.millingRecipe(Ingredient.of(Items.BREAD), PlusItems.BREAD_CRUMB.get(), 3).addResultWithChance(PlusItems.BREAD_CRUMB.get(), 0.25F).build(consumer);
        MillstoneRecipeBuilder.millingRecipe(Ingredient.of(ModItems.RICE.get()), PlusItems.RICE_FLOUR.get(), 3).addResultWithChance(PlusItems.RICE_FLOUR.get(), 0.25F).build(consumer);
        MillstoneRecipeBuilder.millingRecipe(Ingredient.of(Items.WHEAT), PlusItems.WHEAT_FLOUR.get(), 3).addResultWithChance(PlusItems.WHEAT_FLOUR.get(), 0.25F).build(consumer);

        MillstoneRecipeBuilder.millingRecipe(Ingredient.of(Items.BEEF), ModItems.MINCED_BEEF.get(), 3).addResultWithChance(ModItems.MINCED_BEEF.get(), 0.25F).build(consumer);
        MillstoneRecipeBuilder.millingRecipe(Ingredient.of(Items.CHICKEN), PlusItems.MINCED_CHICKEN.get(), 3).addResultWithChance(PlusItems.MINCED_CHICKEN.get(), 0.25F).build(consumer);
        MillstoneRecipeBuilder.millingRecipe(Ingredient.of(Items.PORKCHOP), PlusItems.MINCED_PORK.get(), 3).addResultWithChance(PlusItems.MINCED_PORK.get(), 0.25F).build(consumer);

        MillstoneRecipeBuilder.millingRecipe(Ingredient.of(Items.COBBLESTONE), Items.GRAVEL).build(consumer);
        MillstoneRecipeBuilder.millingRecipe(Ingredient.of(Items.COBBLED_DEEPSLATE), Items.GRAVEL).build(consumer);
        MillstoneRecipeBuilder.millingRecipe(Ingredient.of(Items.BONE), Items.BONE_MEAL, 3).addResultWithChance(Items.BONE_MEAL, 0.25F, 3).addResultWithChance(Items.WHITE_DYE, 0.25F).build(consumer);
        MillstoneRecipeBuilder.millingRecipe(Ingredient.of(Items.GRAVEL), Items.SAND).addResultWithChance(Items.FLINT, 0.25F).build(consumer);
        MillstoneRecipeBuilder.millingRecipe(Ingredient.of(Items.SUGAR_CANE), Items.SUGAR, 2).addResultWithChance(Items.SUGAR, 0.1F).build(consumer);
    }
}
