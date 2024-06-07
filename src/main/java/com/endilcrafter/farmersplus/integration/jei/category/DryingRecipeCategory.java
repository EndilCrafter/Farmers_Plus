package com.endilcrafter.farmersplus.integration.jei.category;

import com.endilcrafter.farmersplus.FarmersPlus;
import com.endilcrafter.farmersplus.common.crafting.DryingRackRecipe;
import com.endilcrafter.farmersplus.common.registry.PlusItems;
import com.endilcrafter.farmersplus.integration.jei.FPRecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.common.utility.RecipeUtils;
import vectorwing.farmersdelight.common.utility.TextUtils;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class DryingRecipeCategory implements IRecipeCategory<DryingRackRecipe> {
    public static final int OUTPUT_GRID_X = 76;
    public static final int OUTPUT_GRID_Y = 10;
    private final IDrawable slot;
    private final Component title;
    private final IDrawable background;
    private final IDrawable icon;

    public DryingRecipeCategory(IGuiHelper helper) {
        title = TextUtils.getTranslation("jei.drying");
        ResourceLocation backgroundImage = new ResourceLocation(FarmersPlus.MODID, "textures/gui/jei/drying_rack.png");
        slot = helper.createDrawable(backgroundImage, 0, 58, 18, 18);
        background = helper.createDrawable(backgroundImage, 0, 0, 117, 57);
        icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(PlusItems.DRYING_RACK.get()));
    }

    @Override
    public RecipeType<DryingRackRecipe> getRecipeType() {
        return FPRecipeTypes.DRYING;
    }

    @Override
    public Component getTitle() {
        return this.title;
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, DryingRackRecipe recipe, IFocusGroup focusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 16, 27 - 19).addIngredients(recipe.getIngredients().get(0));

        ItemStack resultStack = RecipeUtils.getResultItem(recipe);

        int centerX = 10;
        int centerY = 10;

        builder.addSlot(RecipeIngredientRole.OUTPUT, OUTPUT_GRID_X + centerX, OUTPUT_GRID_Y + centerY).addItemStack(resultStack);
    }

    @Override
    public void draw(DryingRackRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        int centerX = 9;
        int centerY = 9;
        slot.draw(guiGraphics, OUTPUT_GRID_X + centerX, OUTPUT_GRID_Y + centerY);
        this.drawDryTime(recipe, guiGraphics, 45);
    }

    protected void drawDryTime(DryingRackRecipe recipe, GuiGraphics guiGraphics, int y) {
        int dryTime = recipe.getDryingTime();
        if (dryTime > 0) {
            int cookTimeSeconds = dryTime / 20;
            Component timeString = Component.translatable("gui.jei.category.smelting.time.seconds", cookTimeSeconds);
            Minecraft minecraft = Minecraft.getInstance();
            Font fontRenderer = minecraft.font;
            int stringWidth = fontRenderer.width(timeString);
            guiGraphics.drawString(fontRenderer, timeString, this.getWidth() - stringWidth, y, -8355712, false);
        }

    }
}