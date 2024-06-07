package com.endilcrafter.farmersplus.common.block;

import com.endilcrafter.farmersplus.common.block.entity.TrayBlockEntity;
import com.endilcrafter.farmersplus.common.registry.PlusBlockEntities;
import com.endilcrafter.farmersplus.common.tag.PlusTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.utility.ItemUtils;

public class TrayBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    private static final VoxelShape BOTTOM = Block.box(1.0F, 0.0F, 1.0F, 15.0F, 1.0F, 15.0F);
    private static final VoxelShape NORTH = Block.box(1.0F, 1.0F, 1.0F, 15.0F, 2.0F, 2.0F);
    private static final VoxelShape SOUTH = Block.box(1.0F, 1.0F, 14.0F, 15.0F, 2.0F, 15.0F);
    private static final VoxelShape EAST = Block.box(14.0F, 1.0F, 2.0F, 15.0F, 2.0F, 14.0F);
    private static final VoxelShape WEST = Block.box(1.0F, 1.0F, 2.0F, 2.0F, 2.0F, 14.0F);
    private static final VoxelShape SHAPE = Shapes.or(BOTTOM, NORTH, SOUTH, EAST, WEST);

    public TrayBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    @javax.annotation.Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    public BlockState rotate(BlockState pState, Rotation pRot) {
        return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)));
    }

    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(FACING);
    }

    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        ItemStack heldStack = player.getItemInHand(hand);
        BlockEntity tileEntity = level.getBlockEntity(pos);
        if (tileEntity instanceof TrayBlockEntity trayEntity) {
            int traySlot = trayEntity.getNextEmptySlot();
            if (traySlot < 0) {
                return InteractionResult.PASS;
            }
            if (heldStack.isEdible() || heldStack.is(PlusTags.TRAY_CAN_SERVE)) {
                if (!level.isClientSide && trayEntity.addItem(player.getAbilities().instabuild ? heldStack.copy() : heldStack, traySlot)) {
                    return InteractionResult.SUCCESS;
                }
                return InteractionResult.CONSUME;
            }
        }

        return InteractionResult.PASS;
    }

    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof TrayBlockEntity) {
                ItemUtils.dropItems(level, pos, ((TrayBlockEntity) tileEntity).getInventory());
            }

            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TrayBlockEntity(pPos, pState);
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return pFacing == Direction.DOWN && !pState.canSurvive(pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return pLevel.getBlockState(pPos.below()).isSolid();
    }

    @Override
    @javax.annotation.Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, PlusBlockEntities.TRAY.get(), level.isClientSide ? TrayBlockEntity::animationTick : TrayBlockEntity::displayTick);
    }
}
