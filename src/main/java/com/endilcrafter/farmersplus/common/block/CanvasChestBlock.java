package com.endilcrafter.farmersplus.common.block;

import com.endilcrafter.farmersplus.common.block.entity.CanvasChestBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class CanvasChestBlock extends ChestBlock {
    public CanvasChestBlock(Properties pProperties, Supplier<BlockEntityType<? extends ChestBlockEntity>> pBlockEntityType) {
        super(pProperties, pBlockEntityType);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new CanvasChestBlockEntity(pPos, pState);
    }


}
