package com.endilcrafter.farmersplus.common.crafting;

import com.endilcrafter.farmersplus.common.registry.PlusBlocks;
import com.endilcrafter.farmersplus.common.registry.PlusRecipeSerializers;
import com.endilcrafter.farmersplus.common.registry.PlusRecipeTypes;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class DryingRackRecipe implements Recipe<Container> {
    protected final ResourceLocation id;
    protected final Ingredient ingredient;
    protected final ItemStack result;
    protected final int dryingTime;

    public DryingRackRecipe(ResourceLocation id, Ingredient ingredient, ItemStack result, int dryingTime) {
        this.id = id;
        this.ingredient = ingredient;
        this.result = result;
        this.dryingTime = dryingTime;
    }

    @Override
    public boolean matches(Container pContainer, Level pLevel) {
        return this.ingredient.test(pContainer.getItem(0));
    }

    @Override
    public ItemStack assemble(Container pContainer, RegistryAccess pRegistryAccess) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return this.result;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> $$0 = NonNullList.create();
        $$0.add(this.ingredient);
        return $$0;

    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return PlusRecipeSerializers.DRYING.get();
    }

    @Override
    public RecipeType<?> getType() {
        return PlusRecipeTypes.DRYING.get();
    }

    public int getDryingTime() {
        return this.dryingTime;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(PlusBlocks.DRYING_RACK.get());
    }

    public static class Serializer implements RecipeSerializer<DryingRackRecipe> {
        public Serializer() {
        }

        @Override
        public DryingRackRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));

            JsonElement jsonelement = GsonHelper.isArrayNode(json, "ingredients") ? GsonHelper.getAsJsonArray(json, "ingredients") : GsonHelper.getAsJsonObject(json, "ingredients");
            Ingredient ingredient = Ingredient.fromJson(jsonelement, false);

            int dryingTime = GsonHelper.getAsInt(json, "dryingtime");
            return new DryingRackRecipe(id, ingredient, result, dryingTime);
        }

        @Override
        public @Nullable DryingRackRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf pBuffer) {
            Ingredient ingredient = Ingredient.fromNetwork(pBuffer);
            ItemStack result = pBuffer.readItem();
            int dryingTime = pBuffer.readVarInt();
            return new DryingRackRecipe(id, ingredient, result, dryingTime);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, DryingRackRecipe pRecipe) {
            pRecipe.ingredient.toNetwork(pBuffer);
            pBuffer.writeItem(pRecipe.result);
            pBuffer.writeVarInt(pRecipe.dryingTime);
        }
    }
}
