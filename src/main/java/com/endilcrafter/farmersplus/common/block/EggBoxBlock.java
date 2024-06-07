package com.endilcrafter.farmersplus.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class EggBoxBlock extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    protected static final VoxelShape SOUTH = Block.box(2.0D, 0.0D, 4.0D, 14.0D, 6.0D, 12.0D);
    protected static final VoxelShape NORTH = Block.box(2.0D, 0.0D, 4.0D, 14.0D, 6.0D, 12.0D);
    protected static final VoxelShape WEST = Block.box(4.0D, 0.0D, 2.0D, 12.0D, 6.0D, 14.0D);
    protected static final VoxelShape EAST = Block.box(4.0D, 0.0D, 2.0D, 12.0D, 6.0D, 14.0D);
    protected static final VoxelShape SOUTH_BOTTOM = Block.box(2.0D, 0.0D, 4.0D, 14.0D, 3.0D, 12.0D);
    protected static final VoxelShape NORTH_BOTTOM = Block.box(2.0D, 0.0D, 4.0D, 14.0D, 3.0D, 12.0D);
    protected static final VoxelShape WEST_BOTTOM = Block.box(4.0D, 0.0D, 2.0D, 12.0D, 3.0D, 14.0D);
    protected static final VoxelShape EAST_BOTTOM = Block.box(4.0D, 0.0D, 2.0D, 12.0D, 3.0D, 14.0D);
    protected static final VoxelShape SOUTH_TOP = Block.box(2.0D, 3.0D, 1.0D, 14.0D, 11.0D, 4.0D);
    protected static final VoxelShape NORTH_TOP = Block.box(2.0D, 3.0D, 12.0D, 14.0D, 11.0D, 15.0D);
    protected static final VoxelShape WEST_TOP = Block.box(12.0D, 3.0D, 2.0D, 15.0D, 11.0D, 14.0D);
    protected static final VoxelShape EAST_TOP = Block.box(1.0D, 3.0D, 2.0D, 4.0D, 11.0D, 14.0D);
    protected static final VoxelShape SOUTH_OPEN = Shapes.or(SOUTH_BOTTOM, SOUTH_TOP);
    protected static final VoxelShape NORTH_OPEN = Shapes.or(NORTH_BOTTOM, NORTH_TOP);
    protected static final VoxelShape WEST_OPEN = Shapes.or(WEST_BOTTOM, WEST_TOP);
    protected static final VoxelShape EAST_OPEN = Shapes.or(EAST_BOTTOM, EAST_TOP);

    public EggBoxBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OPEN, Boolean.valueOf(false)));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        boolean flag = !pState.getValue(OPEN);
        return switch (direction) {
            case SOUTH -> flag ? SOUTH : SOUTH_OPEN;
            default -> flag ? NORTH : NORTH_OPEN;
            case WEST -> flag ? WEST : WEST_OPEN;
            case EAST -> flag ? EAST : EAST_OPEN;
        };
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return pLevel.getBlockState(pPos.below()).isSolid();
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return pFacing == Direction.DOWN && !this.canSurvive(pState, pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }


    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, OPEN);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pPlayer.getItemInHand(pHand).isEmpty()) {
            pState = pState.cycle(OPEN);
            pLevel.setBlock(pPos, pState, 2);
            pLevel.playSound(pPlayer, pPos, SoundEvents.WOOL_HIT, SoundSource.BLOCKS, 1.0F, pLevel.getRandom().nextFloat() * 0.1F + 0.9F);
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        } else return InteractionResult.PASS;
    }
}
