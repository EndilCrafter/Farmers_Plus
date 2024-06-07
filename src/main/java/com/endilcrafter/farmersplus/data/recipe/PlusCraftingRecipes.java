package com.endilcrafter.farmersplus.data.recipe;

import com.endilcrafter.farmersplus.FarmersPlus;
import com.endilcrafter.farmersplus.common.registry.PlusItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ForgeTags;

import java.util.function.Consumer;

public class PlusCraftingRecipes {
    public static void register(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, PlusItems.LARD_BOTTLE.get(), 4)
                .requires(PlusItems.LARD_BUCKET.get())
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .unlockedBy("has_lard_bucket", InventoryChangeTrigger.TriggerInstance.hasItems(PlusItems.LARD_BUCKET.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PlusItems.LARD_BUCKET.get())
                .requires(Items.BUCKET)
                .requires(PlusItems.LARD_BOTTLE.get())
                .requires(PlusItems.LARD_BOTTLE.get())
                .requires(PlusItems.LARD_BOTTLE.get())
                .requires(PlusItems.LARD_BOTTLE.get())
                .unlockedBy("has_lard_bottle", InventoryChangeTrigger.TriggerInstance.hasItems(PlusItems.LARD_BOTTLE.get()))
                .save(consumer, new ResourceLocation(FarmersPlus.MODID, "lard_bucket_from_bottles"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, PlusItems.TOMATO_JUICE.get())
                .requires(Items.GLASS_BOTTLE)
                .requires(ModItems.TOMATO.get())
                .requires(ModItems.TOMATO.get())
                .requires(ModItems.TOMATO.get())
                .requires(ModItems.TOMATO.get())
                .unlockedBy("has_tomato", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TOMATO.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.WHEAT_DOUGH.get(), 2)
                .requires(Items.WATER_BUCKET)
                .requires(Ingredient.of(PlusItems.RICE_FLOUR.get(), PlusItems.WHEAT_FLOUR.get()))
                .requires(Ingredient.of(PlusItems.RICE_FLOUR.get(), PlusItems.WHEAT_FLOUR.get()))
                .requires(Ingredient.of(PlusItems.RICE_FLOUR.get(), PlusItems.WHEAT_FLOUR.get()))
                .requires(Ingredient.of(PlusItems.RICE_FLOUR.get(), PlusItems.WHEAT_FLOUR.get()))
                .unlockedBy("has_flour", InventoryChangeTrigger.TriggerInstance.hasItems(PlusItems.RICE_FLOUR.get(), PlusItems.WHEAT_FLOUR.get()))
                .save(consumer, new ResourceLocation(FarmersPlus.MODID, "wheat_dough_from_flour_and_water"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.WHEAT_DOUGH.get(), 2)
                .requires(ForgeTags.EGGS)
                .requires(Ingredient.of(PlusItems.RICE_FLOUR.get(), PlusItems.WHEAT_FLOUR.get()))
                .requires(Ingredient.of(PlusItems.RICE_FLOUR.get(), PlusItems.WHEAT_FLOUR.get()))
                .requires(Ingredient.of(PlusItems.RICE_FLOUR.get(), PlusItems.WHEAT_FLOUR.get()))
                .requires(Ingredient.of(PlusItems.RICE_FLOUR.get(), PlusItems.WHEAT_FLOUR.get()))
                .unlockedBy("has_flour", InventoryChangeTrigger.TriggerInstance.hasItems(PlusItems.RICE_FLOUR.get(), PlusItems.WHEAT_FLOUR.get()))
                .save(consumer, new ResourceLocation(FarmersPlus.MODID, "wheat_dough_from_flour_and_egg"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.HAMBURGER.get())
                .requires(ForgeTags.BREAD)
                .requires(PlusItems.PORK_PATTY.get())
                .requires(ForgeTags.SALAD_INGREDIENTS)
                .requires(ForgeTags.CROPS_TOMATO)
                .requires(ForgeTags.CROPS_ONION)
                .unlockedBy("has_pork_patty", InventoryChangeTrigger.TriggerInstance.hasItems(PlusItems.PORK_PATTY.get()))
                .save(consumer, new ResourceLocation(FarmersPlus.MODID, "hamburger_from_pork"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, PlusItems.CANVAS_BLOCK.get(), 2)
                .pattern("CC")
                .pattern("CC")
                .define('C', Ingredient.of(ModItems.CANVAS.get()))
                .unlockedBy("has_canvas", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CANVAS.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, PlusItems.FRAMED_CANVAS_BLOCK.get())
                .pattern("S S")
                .pattern(" C ")
                .pattern("S S")
                .define('S', Ingredient.of(Tags.Items.RODS_WOODEN))
                .define('C', Ingredient.of(PlusItems.CANVAS_BLOCK.get()))
                .unlockedBy("has_canvas_block", InventoryChangeTrigger.TriggerInstance.hasItems(PlusItems.CANVAS_BLOCK.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, PlusItems.CANVAS_CHEST.get())
                .pattern("FFF")
                .pattern("F F")
                .pattern("FFF")
                .define('F', Ingredient.of(PlusItems.FRAMED_CANVAS_BLOCK.get()))
                .unlockedBy("has_framed_canvas_block", InventoryChangeTrigger.TriggerInstance.hasItems(PlusItems.FRAMED_CANVAS_BLOCK.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, PlusItems.CRATE.get())
                .pattern("S S")
                .pattern("S S")
                .pattern("SWS")
                .define('S', Ingredient.of(Tags.Items.RODS_WOODEN))
                .define('W', Ingredient.of(ItemTags.WOODEN_SLABS))
                .unlockedBy("has_stick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, PlusItems.DRYING_RACK.get())
                .pattern("SSS")
                .pattern("I I")
                .define('S', Ingredient.of(Items.STRING))
                .define('I', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_string", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STRING))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, PlusItems.MILLSTONE.get())
                .pattern("G").pattern("S").define('G', Ingredient.of(Items.GRINDSTONE)).define('S', Ingredient.of(Items.STONE_SLAB))
                .unlockedBy("has_grindstone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GRINDSTONE))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, PlusItems.TRAY.get())
                .requires(Ingredient.of(Items.BOWL))
                .unlockedBy("has_bowl", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BOWL))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Items.BOWL)
                .requires(Ingredient.of(PlusItems.TRAY.get()))
                .unlockedBy("has_tray", InventoryChangeTrigger.TriggerInstance.hasItems(PlusItems.TRAY.get()))
                .save(consumer, new ResourceLocation(FarmersPlus.MODID, "bowl_from_tray"));


    }
}
