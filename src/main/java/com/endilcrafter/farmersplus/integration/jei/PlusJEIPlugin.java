package com.endilcrafter.farmersplus.integration.jei;

import com.endilcrafter.farmersplus.FarmersPlus;
import com.endilcrafter.farmersplus.common.registry.PlusItems;
import com.endilcrafter.farmersplus.integration.jei.category.DryingRecipeCategory;
import com.endilcrafter.farmersplus.integration.jei.category.MillingRecipeCategory;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

@JeiPlugin
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SuppressWarnings("unused")
public class PlusJEIPlugin implements IModPlugin {
    private static final ResourceLocation ID = new ResourceLocation(FarmersPlus.MODID, "jei_plugin");

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new DryingRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new MillingRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        FPRecipes modRecipes = new FPRecipes();
        registration.addRecipes(FPRecipeTypes.DRYING, modRecipes.getDryingRackRecipes());
        registration.addRecipes(FPRecipeTypes.MILLING, modRecipes.getMillstoneRecipes());
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(PlusItems.DRYING_RACK.get()), FPRecipeTypes.DRYING);
        registration.addRecipeCatalyst(new ItemStack(PlusItems.MILLSTONE.get()), FPRecipeTypes.MILLING);
    }

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }
}
