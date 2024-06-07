package com.endilcrafter.farmersplus.data.builder;

import com.endilcrafter.farmersplus.common.registry.PlusRecipeSerializers;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class MillstoneRecipeBuilder {
    private final List<ChanceResult> results = new ArrayList(4);
    private final Ingredient ingredient;
    private String soundEventID;

    private MillstoneRecipeBuilder(Ingredient ingredient, ItemLike mainResult, int count, float chance) {
        this.results.add(new ChanceResult(new ItemStack(mainResult.asItem(), count), chance));
        this.ingredient = ingredient;
    }

    public static MillstoneRecipeBuilder millingRecipe(Ingredient ingredient, ItemLike mainResult, int count) {
        return new MillstoneRecipeBuilder(ingredient, mainResult, count, 1.0F);
    }

    public static MillstoneRecipeBuilder millingRecipe(Ingredient ingredient, ItemLike mainResult, int count, int chance) {
        return new MillstoneRecipeBuilder(ingredient, mainResult, count, (float) chance);
    }

    public static MillstoneRecipeBuilder millingRecipe(Ingredient ingredient, ItemLike mainResult) {
        return new MillstoneRecipeBuilder(ingredient, mainResult, 1, 1.0F);
    }

    public MillstoneRecipeBuilder addResult(ItemLike result) {
        return this.addResult(result, 1);
    }

    public MillstoneRecipeBuilder addResult(ItemLike result, int count) {
        this.results.add(new ChanceResult(new ItemStack(result.asItem(), count), 1.0F));
        return this;
    }

    public MillstoneRecipeBuilder addResultWithChance(ItemLike result, float chance) {
        return this.addResultWithChance(result, chance, 1);
    }

    public MillstoneRecipeBuilder addResultWithChance(ItemLike result, float chance, int count) {
        this.results.add(new ChanceResult(new ItemStack(result.asItem(), count), chance));
        return this;
    }

    public MillstoneRecipeBuilder addSound(String soundEventID) {
        this.soundEventID = soundEventID;
        return this;
    }

    public void build(Consumer<FinishedRecipe> consumerIn) {
        ResourceLocation location = ForgeRegistries.ITEMS.getKey(this.ingredient.getItems()[0].getItem());
        this.build(consumerIn, "farmersplus:milling/" + location.getPath());
    }

    public void build(Consumer<FinishedRecipe> consumerIn, String save) {
        ResourceLocation resourcelocation = ForgeRegistries.ITEMS.getKey(this.ingredient.getItems()[0].getItem());
        if ((new ResourceLocation(save)).equals(resourcelocation)) {
            throw new IllegalStateException("Milling Recipe " + save + " should remove its 'save' argument");
        } else {
            this.build(consumerIn, new ResourceLocation(save));
        }
    }

    public void build(Consumer<FinishedRecipe> consumerIn, ResourceLocation id) {
        consumerIn.accept(new MillstoneRecipeBuilder.Result(id, this.ingredient, this.results, this.soundEventID == null ? "" : this.soundEventID));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Ingredient ingredient;
        private final List<ChanceResult> results;
        private final String soundEventID;

        public Result(ResourceLocation idIn, Ingredient ingredientIn, List<ChanceResult> resultsIn, String soundEventIDIn) {
            this.id = idIn;
            this.ingredient = ingredientIn;
            this.results = resultsIn;
            this.soundEventID = soundEventIDIn;
        }

        public void serializeRecipeData(JsonObject json) {
            JsonArray arrayIngredients = new JsonArray();
            arrayIngredients.add(this.ingredient.toJson());
            json.add("ingredients", arrayIngredients);
            JsonArray arrayResults = new JsonArray();

            JsonObject jsonobject;
            for (Iterator var4 = this.results.iterator(); var4.hasNext(); arrayResults.add(jsonobject)) {
                ChanceResult result = (ChanceResult) var4.next();
                jsonobject = new JsonObject();
                jsonobject.addProperty("item", ForgeRegistries.ITEMS.getKey(result.getStack().getItem()).toString());
                if (result.getStack().getCount() > 1) {
                    jsonobject.addProperty("count", result.getStack().getCount());
                }

                if (result.getChance() < 1.0F) {
                    jsonobject.addProperty("chance", result.getChance());
                }
            }

            json.add("result", arrayResults);
            if (!this.soundEventID.isEmpty()) {
                json.addProperty("sound", this.soundEventID);
            }

        }

        public ResourceLocation getId() {
            return this.id;
        }

        public RecipeSerializer<?> getType() {
            return PlusRecipeSerializers.MILLING.get();
        }

        @Nullable
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Nullable
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }
}
