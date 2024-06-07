package com.endilcrafter.farmersplus.client.renderer.blockentity;

import com.endilcrafter.farmersplus.common.block.TrayBlock;
import com.endilcrafter.farmersplus.common.block.entity.TrayBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.items.ItemStackHandler;

import java.util.Objects;

public class TrayRenderer implements BlockEntityRenderer<TrayBlockEntity> {
    public TrayRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(TrayBlockEntity trayEntity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int combinedLightIn, int combinedOverlayIn) {
        Direction direction = trayEntity.getBlockState().getValue(TrayBlock.FACING).getOpposite();

        ItemStackHandler inventory = trayEntity.getInventory();
        int posLong = (int) trayEntity.getBlockPos().asLong();

        for (int i = 0; i < inventory.getSlots(); ++i) {
            ItemStack trayStack = inventory.getStackInSlot(i);
            if (!trayStack.isEmpty()) {
                poseStack.pushPose();

                poseStack.translate(0.5D, 0.0825D, 0.5D); //0.50D->0.0625

                float f = -direction.toYRot();
                poseStack.mulPose(Axis.YP.rotationDegrees(f));

                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));

                Vec2 itemOffset = trayEntity.getTrayItemOffset(i);
                poseStack.translate(itemOffset.x, itemOffset.y, 0.0D);

                poseStack.scale(0.375F, 0.375F, 0.375F);

                Minecraft.getInstance().getItemRenderer().renderStatic(trayStack, ItemDisplayContext.FIXED, LevelRenderer.getLightColor(Objects.requireNonNull(trayEntity.getLevel()), trayEntity.getBlockPos().above()), combinedOverlayIn, poseStack, buffer, trayEntity.getLevel(), posLong + i);
                poseStack.popPose();
            }
        }
    }
}
