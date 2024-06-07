package com.endilcrafter.farmersplus.client.renderer.blockentity;

import com.endilcrafter.farmersplus.common.block.DryingRackBlock;
import com.endilcrafter.farmersplus.common.block.entity.DryingRackBlockEntity;
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

public class DryingRackRenderer implements BlockEntityRenderer<DryingRackBlockEntity> {
    public DryingRackRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(DryingRackBlockEntity rackEntity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int combinedLightIn, int combinedOverlayIn) {
        Direction direction = rackEntity.getBlockState().getValue(DryingRackBlock.FACING).getOpposite();

        ItemStackHandler inventory = rackEntity.getInventory();
        int posLong = (int) rackEntity.getBlockPos().asLong();

        for (int i = 0; i < inventory.getSlots(); ++i) {
            ItemStack stoveStack = inventory.getStackInSlot(i);
            if (!stoveStack.isEmpty()) {
                poseStack.pushPose();

                // Center item above the stove
                poseStack.translate(0.5D, 0.4575D, 0.5D); //0.50D->0.4375

                // Rotate item to face the stove's front side
                float f = -direction.toYRot();
                poseStack.mulPose(Axis.YP.rotationDegrees(f));

                // Rotate item flat on the stove. Use X and Y from now on
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));

                // Neatly align items according to their index
                Vec2 itemOffset = rackEntity.getRackItemOffset(i);
                poseStack.translate(itemOffset.x, itemOffset.y, 0.0D);

                // Resize the items
                poseStack.scale(0.375F, 0.375F, 0.375F);

                if (rackEntity.getLevel() != null) {
                    Minecraft.getInstance().getItemRenderer().renderStatic(stoveStack, ItemDisplayContext.FIXED, LevelRenderer.getLightColor(rackEntity.getLevel(), rackEntity.getBlockPos().above()), combinedOverlayIn, poseStack, buffer, rackEntity.getLevel(), posLong + i);
                }

                poseStack.popPose();
            }
        }
    }
}
