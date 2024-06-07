package com.endilcrafter.farmersplus.client.gui.screens.inventory;

import com.endilcrafter.farmersplus.common.world.inventory.CrateMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CrateScreen extends AbstractContainerScreen<CrateMenu> implements MenuAccess<CrateMenu> {
    private static final ResourceLocation CONTAINER_BACKGROUND = new ResourceLocation("textures/gui/container/generic_54.png");

    public CrateScreen(CrateMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageHeight = 114 + 6 * 18;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int $$4 = (this.width - this.imageWidth) / 2;
        int $$5 = (this.height - this.imageHeight) / 2;
        pGuiGraphics.blit(CONTAINER_BACKGROUND, $$4, $$5, 0, 0, this.imageWidth, 6 * 18 + 17);
        pGuiGraphics.blit(CONTAINER_BACKGROUND, $$4, $$5 + 6 * 18 + 17, 0, 126, this.imageWidth, 96);
    }
}
