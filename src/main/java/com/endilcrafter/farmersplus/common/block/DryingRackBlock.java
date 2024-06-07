package com.endilcrafter.farmersplus.common.block;

import com.endilcrafter.farmersplus.common.block.entity.DryingRackBlockEntity;
import com.endilcrafter.farmersplus.common.crafting.DryingRackRecipe;
import com.endilcrafter.farmersplus.common.registry.PlusBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
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

import java.util.Optional;

public class DryingRackBlock extends BaseEntityBlock {
    public static final VoxelShape POST1 = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 6.0D, 2.0D);
    public static final VoxelShape POST2 = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 6.0D, 2.0D);
    public static final VoxelShape POST3 = Block.box(0.0D, 0.0D, 14.0D, 2.0D, 6.0D, 16.0D);
    public static final VoxelShape POST4 = Block.box(14.0D, 0.0D, 14.0D, 16.0D, 6.0D, 16.0D);
    public static final VoxelShape FRAME1 = Block.box(0.0D, 6.0D, 0.0D, 16.0D, 8.0D, 2.0D);
    public static final VoxelShape FRAME2 = Block.box(0.0D, 6.0D, 14.0D, 16.0D, 8.0D, 16.0D);
    public static final VoxelShape FRAME3 = Block.box(0.0D, 6.0D, 2.0D, 2.0D, 8.0D, 14.0D);
    public static final VoxelShape FRAME4 = Block.box(14.0D, 6.0D, 2.0D, 16.0D, 8.0D, 14.0D);
    public static final VoxelShape MESH = Block.box(2.0D, 6.9D, 2.0D, 14.0D, 7.0D, 14.0D);
    public static final VoxelShape SHAPE = Shapes.or(POST1, POST2, POST3, POST4, FRAME1, FRAME2, FRAME3, FRAME4, MESH);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public DryingRackBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new DryingRackBlockEntity(pPos, pState);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        ItemStack heldStack = player.getItemInHand(hand);
        BlockEntity tileEntity = level.getBlockEntity(pos);
        if (tileEntity instanceof DryingRackBlockEntity rackEntity) {
            int rackSlot = rackEntity.getNextEmptySlot();
            if (rackSlot < 0) {
                return InteractionResult.PASS;
            }

            Optional<DryingRackRecipe> recipe = rackEntity.getMatchingRecipe(new SimpleContainer(heldStack), rackSlot);
            if (recipe.isPresent()) {
                if (!level.isClientSide && rackEntity.addItem(player.getAbilities().instabuild ? heldStack.copy() : heldStack, recipe.get(), rackSlot)) {
                    return InteractionResult.SUCCESS;
                }

                return InteractionResult.CONSUME;
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
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
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof DryingRackBlockEntity) {
                ItemUtils.dropItems(level, pos, ((DryingRackBlockEntity) tileEntity).getInventory());
            }

            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(FACING);
    }

    @Override
    @javax.annotation.Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, PlusBlockEntities.DRYING_RACK.get(), level.isClientSide
                ? DryingRackBlockEntity::animationTick
                : DryingRackBlockEntity::dryingTick);
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
