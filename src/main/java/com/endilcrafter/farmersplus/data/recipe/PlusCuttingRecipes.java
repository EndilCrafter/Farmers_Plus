package com.endilcrafter.farmersplus.data.recipe;

import com.endilcrafter.farmersplus.FarmersPlus;
import com.endilcrafter.farmersplus.common.registry.PlusItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.function.Consumer;

public class PlusCuttingRecipes {
    public static void register(Consumer<FinishedRecipe> consumer) {
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.APPLE), Ingredient.of(ForgeTags.TOOLS_KNIVES), PlusItems.APPLE_SLICE.get(), 8)
                .build(consumer, new ResourceLocation(FarmersPlus.MODID, "cutting/apple_slice"));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.GOLDEN_APPLE), Ingredient.of(ForgeTags.TOOLS_KNIVES), PlusItems.GOLDEN_APPLE_SLICE.get(), 8)
                .build(consumer, new ResourceLocation(FarmersPlus.MODID, "cutting/golden_apple_slice"));
    }
}
