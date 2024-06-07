package com.endilcrafter.farmersplus.common.block.entity;

import com.endilcrafter.farmersplus.common.registry.PlusBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class CanvasChestBlockEntity extends ChestBlockEntity {
    public CanvasChestBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(PlusBlockEntities.CANVAS_CHEST.get(), pPos, pBlockState);
    }

    @Override
    public AABB getRenderBoundingBox() {
        return new AABB(worldPosition.offset(-1, 0, -1), worldPosition.offset(2, 2, 2));
    }
}
