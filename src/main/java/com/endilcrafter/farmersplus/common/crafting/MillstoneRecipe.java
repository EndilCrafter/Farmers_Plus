package com.endilcrafter.farmersplus.common.crafting;

import com.endilcrafter.farmersplus.common.registry.PlusRecipeSerializers;
import com.endilcrafter.farmersplus.common.registry.PlusRecipeTypes;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MillstoneRecipe implements Recipe<RecipeWrapper> {
    private final ResourceLocation id;
    private final String group;
    private final Ingredient input;
    private final NonNullList<ChanceResult> results;
    private final String soundEvent;

    public MillstoneRecipe(ResourceLocation id, String group, Ingredient input, NonNullList<ChanceResult> results, String soundEvent) {
        this.id = id;
        this.group = group;
        this.input = input;
        this.results = results;
        this.soundEvent = soundEvent;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public String getGroup() {
        return this.group;
    }

    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        nonnulllist.add(this.input);
        return nonnulllist;
    }

    public String getSoundEventID() {
        return this.soundEvent;
    }

    @Override
    public boolean matches(RecipeWrapper inv, Level level) {
        return !inv.isEmpty() && this.input.test(inv.getItem(0));
    }

    protected int getMaxInputCount() {
        return 64;
    }

    @Override
    public ItemStack assemble(RecipeWrapper inv, RegistryAccess access) {
        return this.results.get(0).getStack().copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= this.getMaxInputCount();
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.results.get(0).getStack();
    }

    public List<ItemStack> getResults() {
        return this.getRollableResults().stream().map(ChanceResult::getStack).collect(Collectors.toList());
    }

    public NonNullList<ChanceResult> getRollableResults() {
        return this.results;
    }

    public List<ItemStack> rollResults(RandomSource rand) {
        List<ItemStack> results = new ArrayList();
        NonNullList<ChanceResult> rollableResults = this.getRollableResults();

        for (ChanceResult output : rollableResults) {
            ItemStack stack = output.rollOutput(rand, 0);
            if (!stack.isEmpty()) {
                results.add(stack);
            }
        }

        return results;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return PlusRecipeSerializers.MILLING.get();
    }

    @Override
    public RecipeType<?> getType() {
        return PlusRecipeTypes.MILLING.get();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            MillstoneRecipe that = (MillstoneRecipe) o;
            if (!this.getId().equals(that.getId())) {
                return false;
            } else if (!this.getGroup().equals(that.getGroup())) {
                return false;
            } else if (!this.input.equals(that.input)) {
                return false;
            } else {
                return this.getResults().equals(that.getResults()) && Objects.equals(this.soundEvent, that.soundEvent);
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = this.getId().hashCode();
        result = 31 * result + (this.getGroup() != null ? this.getGroup().hashCode() : 0);
        result = 31 * result + this.input.hashCode();
        result = 31 * result + this.getResults().hashCode();
        result = 31 * result + (this.soundEvent != null ? this.soundEvent.hashCode() : 0);
        return result;
    }

    public static class Serializer implements RecipeSerializer<MillstoneRecipe> {
        public Serializer() {
        }

        private static NonNullList<Ingredient> readIngredients(JsonArray ingredientArray) {
            NonNullList<Ingredient> nonnulllist = NonNullList.create();

            for (int i = 0; i < ingredientArray.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(ingredientArray.get(i));
                if (!ingredient.isEmpty()) {
                    nonnulllist.add(ingredient);
                }
            }

            return nonnulllist;
        }

        private static NonNullList<ChanceResult> readResults(JsonArray resultArray) {
            NonNullList<ChanceResult> results = NonNullList.create();

            for (JsonElement result : resultArray) {
                results.add(ChanceResult.deserialize(result));
            }

            return results;
        }

        public MillstoneRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            String groupIn = GsonHelper.getAsString(json, "group", "");
            NonNullList<Ingredient> inputItemsIn = readIngredients(GsonHelper.getAsJsonArray(json, "ingredients"));
            if (inputItemsIn.isEmpty()) {
                throw new JsonParseException("No ingredients for milling recipe");
            } else if (inputItemsIn.size() > 1) {
                throw new JsonParseException("Too many ingredients for milling recipe! Please define only one ingredient");
            } else {
                NonNullList<ChanceResult> results = readResults(GsonHelper.getAsJsonArray(json, "result"));
                if (results.size() > 4) {
                    throw new JsonParseException("Too many results for milling recipe! The maximum quantity of unique results is 4");
                } else {
                    String soundID = GsonHelper.getAsString(json, "sound", "");
                    return new MillstoneRecipe(recipeId, groupIn, inputItemsIn.get(0), results, soundID);
                }
            }
        }

        @Nullable
        public MillstoneRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            String groupIn = buffer.readUtf(32767);
            Ingredient inputItemIn = Ingredient.fromNetwork(buffer);
            int i = buffer.readVarInt();
            NonNullList<ChanceResult> resultsIn = NonNullList.withSize(i, ChanceResult.EMPTY);

            resultsIn.replaceAll(ignored -> ChanceResult.read(buffer));

            String soundEventIn = buffer.readUtf();
            return new MillstoneRecipe(recipeId, groupIn, inputItemIn, resultsIn, soundEventIn);
        }

        public void toNetwork(FriendlyByteBuf buffer, MillstoneRecipe recipe) {
            buffer.writeUtf(recipe.group);
            recipe.input.toNetwork(buffer);
            buffer.writeVarInt(recipe.results.size());

            for (ChanceResult result : recipe.results) {
                result.write(buffer);
            }

            buffer.writeUtf(recipe.soundEvent);
        }
    }
}
