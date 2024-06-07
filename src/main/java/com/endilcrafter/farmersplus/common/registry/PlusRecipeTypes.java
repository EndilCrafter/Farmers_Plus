package com.endilcrafter.farmersplus.common.registry;

import com.endilcrafter.farmersplus.FarmersPlus;
import com.endilcrafter.farmersplus.common.crafting.DryingRackRecipe;
import com.endilcrafter.farmersplus.common.crafting.MillstoneRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PlusRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, FarmersPlus.MODID);

    public static final RegistryObject<RecipeType<DryingRackRecipe>> DRYING = RECIPE_TYPES.register("drying",
            () -> registerRecipeType("drying"));
    public static final RegistryObject<RecipeType<MillstoneRecipe>> MILLING = RECIPE_TYPES.register("milling",
            () -> registerRecipeType("milling"));

    public static void register(IEventBus eventBus) {
        RECIPE_TYPES.register(eventBus);
    }

    public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String identifier) {
        return new RecipeType<T>() {
            public String toString() {
                return FarmersPlus.MODID + ":" + identifier;
            }
        };
    }
}
