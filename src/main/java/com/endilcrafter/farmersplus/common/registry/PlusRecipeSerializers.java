package com.endilcrafter.farmersplus.common.registry;

import com.endilcrafter.farmersplus.FarmersPlus;
import com.endilcrafter.farmersplus.common.crafting.DryingRackRecipe;
import com.endilcrafter.farmersplus.common.crafting.MillstoneRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PlusRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, FarmersPlus.MODID);

    public static final RegistryObject<RecipeSerializer<?>> DRYING = RECIPE_SERIALIZERS.register("drying", DryingRackRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<?>> MILLING = RECIPE_SERIALIZERS.register("milling", MillstoneRecipe.Serializer::new);

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
    }
}
