package com.endilcrafter.farmersplus.data.recipe;

import com.endilcrafter.farmersplus.FarmersPlus;
import com.endilcrafter.farmersplus.common.registry.PlusItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.function.Consumer;

public class PlusCuttingRecipes {
    public static void register(Consumer<FinishedRecipe> consumer) {
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(PlusItems.BEEF_TONGUE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), PlusItems.SKINNED_BEEF_TONGUE.get())
                .addResult(PlusItems.BEEF_TONGUE_SKIN.get()).build(consumer, new ResourceLocation(FarmersPlus.MODID, "cutting/skinned_beef_tongue"));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(PlusItems.SKINNED_BEEF_TONGUE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), PlusItems.BEEF_TONGUE_SLICE.get(), 16)
                .build(consumer, new ResourceLocation(FarmersPlus.MODID, "cutting/beef_tongue_slice"));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(PlusItems.COOKED_BEEF_TONGUE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), PlusItems.COOKED_BEEF_TONGUE_SLICE.get(), 16)
                .build(consumer, new ResourceLocation(FarmersPlus.MODID, "cutting/cooked_beef_tongue_slice"));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(PlusItems.BEEF_TONGUE_SLICE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), ModItems.MINCED_BEEF.get())
                .addResultWithChance(ModItems.MINCED_BEEF.get(), 0.2F).build(consumer, new ResourceLocation(FarmersPlus.MODID, "cutting/minced_beef"));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.APPLE), Ingredient.of(ForgeTags.TOOLS_KNIVES), PlusItems.APPLE_SLICE.get(), 8)
                .build(consumer, new ResourceLocation(FarmersPlus.MODID, "cutting/apple_slice"));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.GOLDEN_APPLE), Ingredient.of(ForgeTags.TOOLS_KNIVES), PlusItems.GOLDEN_APPLE_SLICE.get(), 8)
                .build(consumer, new ResourceLocation(FarmersPlus.MODID, "cutting/golden_apple_slice"));
    }
}
