package com.endilcrafter.farmersplus.data.recipe;

import com.endilcrafter.farmersplus.FarmersPlus;
import com.endilcrafter.farmersplus.common.registry.PlusItems;
import com.endilcrafter.farmersplus.common.tag.PlusForgeTags;
import com.endilcrafter.farmersplus.common.tag.PlusTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;

import java.util.function.Consumer;

public class PlusCookingRecipes {
    public static void register(Consumer<FinishedRecipe> consumer) {
        CookingPotRecipeBuilder.cookingPotRecipe(PlusItems.LARD_BUCKET.get(), 1, 200, 0.35F, Items.BUCKET)
                .addIngredient(Items.PORKCHOP)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC).unlockedByItems("has_porkchop", Items.PORKCHOP)
                .build(consumer, new ResourceLocation(FarmersPlus.MODID, "cooking/lard_bucket"));

        CookingPotRecipeBuilder.cookingPotRecipe(PlusItems.BOILED_EGG.get(), 1, 200, 0.35F)
                .addIngredient(Items.EGG)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS).unlockedByItems("has_egg", Items.EGG)
                .build(consumer, new ResourceLocation(FarmersPlus.MODID, "cooking/boiled_egg"));

        CookingPotRecipeBuilder.cookingPotRecipe(PlusItems.CHICKEN_NUGGET.get(), 1, 200, 0.35F, Items.BOWL)
                .addIngredient(PlusItems.MINCED_CHICKEN.get())
                .addIngredient(PlusForgeTags.FLOUR)
                .addIngredient(ForgeTags.EGGS)
                .addIngredient(PlusForgeTags.LARD)
                .addIngredient(ModItems.TOMATO_SAUCE.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS).unlockedByItems("has_minced_chicken", PlusItems.MINCED_CHICKEN.get())
                .build(consumer, new ResourceLocation(FarmersPlus.MODID, "cooking/chicken_nugget"));

        CookingPotRecipeBuilder.cookingPotRecipe(PlusItems.KOROKKE.get(), 1, 200, 0.35F)
                .addIngredient(Tags.Items.CROPS_POTATO)
                .addIngredient(PlusTags.RAW_MINCED_MEATS)
                .addIngredient(PlusForgeTags.FLOUR)
                .addIngredient(ForgeTags.EGGS)
                .addIngredient(PlusItems.BREAD_CRUMB.get())
                .addIngredient(PlusForgeTags.LARD)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS).unlockedByItems("has_potato", Items.POTATO)
                .build(consumer, new ResourceLocation(FarmersPlus.MODID, "cooking/korokke"));

        CookingPotRecipeBuilder.cookingPotRecipe(PlusItems.FRIED_HOLY_BASIL.get(), 1, 200, 0.35F, Items.BOWL)
                .addIngredient(PlusItems.MINCED_PORK.get())
                .addIngredient(ForgeTags.EGGS)
                .addIngredient(ForgeTags.CROPS_RICE)
                .addIngredient(ForgeTags.CROPS_TOMATO)
                .addIngredient(Tags.Items.CROPS_CARROT)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS).unlockedByItems("has_minced_pork", PlusItems.MINCED_PORK.get())
                .build(consumer, new ResourceLocation(FarmersPlus.MODID, "cooking/fried_holy_basil"));

        CookingPotRecipeBuilder.cookingPotRecipe(PlusItems.SALISBURY_STEAK.get(), 1, 200, 0.35F, Items.BOWL)
                .addIngredient(PlusTags.RAW_MINCED_MEATS)
                .addIngredient(ForgeTags.CROPS_ONION)
                .addIngredient(ForgeTags.CROPS_CABBAGE)
                .addIngredient(Tags.Items.CROPS_CARROT)
                .addIngredient(ModItems.TOMATO_SAUCE.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS).unlockedByAnyIngredient(ModItems.MINCED_BEEF.get(), PlusItems.MINCED_CHICKEN.get(), PlusItems.MINCED_PORK.get())
                .build(consumer, new ResourceLocation(FarmersPlus.MODID, "cooking/salisbury_steak"));
    }
}
