package com.endilcrafter.farmersplus.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class TerracottaPotBlock extends Block {
    public static final VoxelShape BOTTOM = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D);
    public static final VoxelShape NORTH = Block.box(2.0D, 2.0D, 2.0D, 14.0D, 6.0D, 4.0D);
    public static final VoxelShape SOUTH = Block.box(2.0D, 2.0D, 12.0D, 14.0D, 6.0D, 14.0D);
    public static final VoxelShape WEST = Block.box(2.0D, 2.0D, 4.0D, 4.0D, 6.0D, 12.0D);
    public static final VoxelShape EAST = Block.box(12.0D, 2.0D, 4.0D, 14.0D, 6.0D, 12.0D);
    public static final VoxelShape SHAPE = Shapes.or(BOTTOM, NORTH, SOUTH, WEST, EAST);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public TerracottaPotBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return facing == Direction.DOWN && !stateIn.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).isSolid();
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(FACING);
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRot) {
        return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }
}
