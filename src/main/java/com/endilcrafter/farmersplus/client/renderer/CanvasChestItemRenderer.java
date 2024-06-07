package com.endilcrafter.farmersplus.client.renderer;

import com.endilcrafter.farmersplus.common.block.entity.CanvasChestBlockEntity;
import com.endilcrafter.farmersplus.common.registry.PlusBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class CanvasChestItemRenderer extends BlockEntityWithoutLevelRenderer {
    private static CanvasChestItemRenderer INSTANCE = null;
    private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;
    private final CanvasChestBlockEntity tile;

    public CanvasChestItemRenderer(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet) {
        super(pBlockEntityRenderDispatcher, pEntityModelSet);
        this.blockEntityRenderDispatcher = pBlockEntityRenderDispatcher;
        this.tile = new CanvasChestBlockEntity(BlockPos.ZERO, PlusBlocks.CANVAS_CHEST.get().defaultBlockState());
    }

    public CanvasChestItemRenderer() {
        this(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    public static CanvasChestItemRenderer getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CanvasChestItemRenderer();
        }

        return INSTANCE;
    }

    @Override
    public void renderByItem(ItemStack p_108830_, ItemDisplayContext p_270899_, PoseStack p_108832_, MultiBufferSource p_108833_, int p_108834_, int p_108835_) {
        this.blockEntityRenderDispatcher.renderItem(tile, p_108832_, p_108833_, p_108834_, p_108835_);
    }
}
