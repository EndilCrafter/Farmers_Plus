package com.endilcrafter.farmersplus.client.renderer.blockentity;

import com.endilcrafter.farmersplus.FarmersPlus;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CanvasChestRenderer extends ChestRenderer<ChestBlockEntity> {
    public static final Material CANVAS_CHEST_LOCATION = new Material(Sheets.CHEST_SHEET, new ResourceLocation(FarmersPlus.MODID, "canvas_chest/" + "canvas"));
    public static final Material CANVAS_CHEST_LOCATION_LEFT = new Material(Sheets.CHEST_SHEET, new ResourceLocation(FarmersPlus.MODID, "canvas_chest/" + "canvas_left"));
    public static final Material CANVAS_CHEST_LOCATION_RIGHT = new Material(Sheets.CHEST_SHEET, new ResourceLocation(FarmersPlus.MODID, "canvas_chest/" + "canvas_right"));


    public CanvasChestRenderer(BlockEntityRendererProvider.Context pContext) {
        super(pContext);
    }

    private static Material chooseMaterial(ChestType pChestType, Material pDoubleMaterial, Material pLeftMaterial, Material pRightMaterial) {
        return switch (pChestType) {
            case LEFT -> pLeftMaterial;
            case RIGHT -> pRightMaterial;
            default -> pDoubleMaterial;
        };
    }

    @Override
    protected Material getMaterial(ChestBlockEntity blockEntity, ChestType chestType) {
        return chooseMaterial(chestType, CANVAS_CHEST_LOCATION, CANVAS_CHEST_LOCATION_LEFT, CANVAS_CHEST_LOCATION_RIGHT);
    }
}
