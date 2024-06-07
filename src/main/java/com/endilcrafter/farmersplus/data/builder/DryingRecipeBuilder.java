package com.endilcrafter.farmersplus.data.builder;

import com.endilcrafter.farmersplus.common.registry.PlusRecipeSerializers;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Consumer;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class DryingRecipeBuilder {
    private final Item result;
    private final Ingredient ingredient;
    private final int dryingTime;

    public DryingRecipeBuilder(Ingredient ingredient, Item result, int dryingTime) {
        this.result = result;
        this.ingredient = ingredient;
        this.dryingTime = dryingTime;
    }

    public static DryingRecipeBuilder dryingRecipe(Ingredient ingredient, Item result, int dryingTime) {
        return new DryingRecipeBuilder(ingredient, result, dryingTime);
    }

    public static DryingRecipeBuilder dryingRecipe(Ingredient ingredient, Item result) {
        return new DryingRecipeBuilder(ingredient, result, 600);
    }

    public void build(Consumer<FinishedRecipe> consumerIn) {
        ResourceLocation location = ForgeRegistries.ITEMS.getKey(this.ingredient.getItems()[0].getItem());
        this.build(consumerIn, "farmersplus:drying/" + location.getPath());
    }

    public void build(Consumer<FinishedRecipe> consumerIn, String save) {
        ResourceLocation resourcelocation = ForgeRegistries.ITEMS.getKey(this.ingredient.getItems()[0].getItem());
        if ((new ResourceLocation(save)).equals(resourcelocation)) {
            throw new IllegalStateException("Drying Recipe " + save + " should remove its 'save' argument");
        } else {
            this.build(consumerIn, new ResourceLocation(save));
        }
    }

    public void build(Consumer<FinishedRecipe> consumerIn, ResourceLocation id) {
        consumerIn.accept(new DryingRecipeBuilder.Result(id, this.ingredient, this.result, this.dryingTime));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Ingredient ingredient;
        private final Item result;
        private final int dryingTime;

        public Result(ResourceLocation idIn, Ingredient ingredientIn, Item result, int dryingTime) {
            this.id = idIn;
            this.ingredient = ingredientIn;
            this.result = result;
            this.dryingTime = dryingTime;
        }

        public void serializeRecipeData(JsonObject json) {
            JsonArray arrayIngredients = new JsonArray();
            arrayIngredients.add(this.ingredient.toJson());
            json.add("ingredients", arrayIngredients);
            JsonObject objectResults = new JsonObject();
            objectResults.addProperty("item", ForgeRegistries.ITEMS.getKey(this.result).toString());
            json.add("result", objectResults);
            json.addProperty("dryingtime", this.dryingTime);
        }

        public ResourceLocation getId() {
            return this.id;
        }

        public RecipeSerializer<?> getType() {
            return PlusRecipeSerializers.DRYING.get();
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
