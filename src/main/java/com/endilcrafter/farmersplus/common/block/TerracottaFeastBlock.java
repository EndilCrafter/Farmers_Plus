package com.endilcrafter.farmersplus.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.function.Supplier;

public class TerracottaFeastBlock extends TerracottaPotBlock {
    public static final VoxelShape FEAST0 = Block.box(4.0D, 2.0D, 4.0D, 12.0D, 6.0D, 12.0D);
    public static final VoxelShape FEAST1 = Block.box(4.0D, 2.0D, 4.0D, 12.0D, 5.0D, 12.0D);
    public static final VoxelShape FEAST2 = Block.box(4.0D, 2.0D, 4.0D, 12.0D, 4.0D, 12.0D);
    public static final VoxelShape FEAST3 = Block.box(4.0D, 2.0D, 4.0D, 12.0D, 3.0D, 12.0D);
    public static final IntegerProperty SERVINGS = IntegerProperty.create("servings", 0, 8);
    protected static final VoxelShape[] SHAPES = new VoxelShape[]{SHAPE, Shapes.or(SHAPE, FEAST3), Shapes.or(SHAPE, FEAST3), Shapes.or(SHAPE, FEAST2), Shapes.or(SHAPE, FEAST2), Shapes.or(SHAPE, FEAST1), Shapes.or(SHAPE, FEAST1), Shapes.or(SHAPE, FEAST0), Shapes.or(SHAPE, FEAST0)};
    public final Supplier<Item> servingItem;

    public TerracottaFeastBlock(Properties properties, Supplier<Item> servingItem) {
        super(properties);
        this.servingItem = servingItem;
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(this.getServingsProperty(), this.getMaxServings()));
    }

    public IntegerProperty getServingsProperty() {
        return SERVINGS;
    }

    public int getMaxServings() {
        return 8;
    }

    public ItemStack getServingItem(BlockState state) {
        return new ItemStack(this.servingItem.get());
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES[state.getValue(SERVINGS)];
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        return level.isClientSide && this.takeServing(level, pos, state, player, hand).consumesAction() ? InteractionResult.SUCCESS : this.takeServing(level, pos, state, player, hand);
    }

    protected InteractionResult takeServing(LevelAccessor level, BlockPos pos, BlockState state, Player player, InteractionHand hand) {
        int servings = state.getValue(this.getServingsProperty());
        if (servings == 0) {
            level.playSound(null, pos, SoundEvents.STONE_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F);
            level.destroyBlock(pos, true);
            return InteractionResult.SUCCESS;
        } else {
            ItemStack serving = this.getServingItem(state);
            ItemStack heldStack = player.getItemInHand(hand);
            if (servings > 0) {
                if (!serving.hasCraftingRemainingItem() || ItemStack.isSameItem(heldStack, serving.getCraftingRemainingItem())) {
                    level.setBlock(pos, state.setValue(this.getServingsProperty(), servings - 1), 3);
                    if (!player.getAbilities().instabuild && serving.hasCraftingRemainingItem()) {
                        heldStack.shrink(1);
                    }

                    if (!player.getInventory().add(serving)) {
                        player.drop(serving, false);
                    }

                    level.playSound(null, pos, SoundEvents.ARMOR_EQUIP_GENERIC, SoundSource.BLOCKS, 1.0F, 1.0F);
                    return InteractionResult.SUCCESS;
                }

                player.displayClientMessage(TextUtils.getTranslation("block.feast.use_container", serving.getCraftingRemainingItem().getHoverName()), true);
            }

            return InteractionResult.PASS;
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, SERVINGS);
    }

    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos) {
        return blockState.getValue(this.getServingsProperty());
    }

    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
        return false;
    }
}
