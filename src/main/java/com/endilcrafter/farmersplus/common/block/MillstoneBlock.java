package com.endilcrafter.farmersplus.common.block;

import com.endilcrafter.farmersplus.common.block.entity.MillstoneBlockEntity;
import com.endilcrafter.farmersplus.common.registry.PlusBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.tag.ModTags;

public class MillstoneBlock extends BaseEntityBlock {
    public static final IntegerProperty SPIN = IntegerProperty.create("spin", 0, 7);
    protected static final VoxelShape BASE = Block.box(0.0F, 0.0F, 0.0F, 16.0F, 4.0F, 16.0F);
    protected static final VoxelShape MILLSTONE = Block.box(3.0F, 4.0F, 3.0F, 13.0F, 10.0F, 13.0F);
    protected static final VoxelShape SHAPE = Shapes.or(BASE, MILLSTONE);

    public MillstoneBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(SPIN, 0));
    }

    public static void spawnMillingParticles(Level level, BlockPos pos, ItemStack stack, int count) {
        for (int i = 0; i < count; ++i) {
            Vec3 vec3d = new Vec3(((double) level.random.nextFloat() - 0.5) * 0.1, Math.random() * 0.1 + 0.1, ((double) level.random.nextFloat() - 0.5) * 0.1);
            if (level instanceof ServerLevel) {
                ((ServerLevel) level).sendParticles(new ItemParticleOption(ParticleTypes.ITEM, stack), (float) pos.getX() + 0.5F, (float) pos.getY() + 0.1F, (float) pos.getZ() + 0.5F, 1, vec3d.x, vec3d.y + 0.05, vec3d.z, 0.0);
            } else {
                level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, stack), (float) pos.getX() + 0.5F, (float) pos.getY() + 0.3F, (float) pos.getZ() + 0.5F, vec3d.x, vec3d.y + 0.05, vec3d.z);
            }
        }

    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        BlockEntity tileEntity = level.getBlockEntity(pos);
        if (tileEntity instanceof MillstoneBlockEntity millstoneBlockEntity) {
            ItemStack heldStack = player.getItemInHand(hand);
            ItemStack offhandStack = player.getOffhandItem();
            if (millstoneBlockEntity.isEmpty()) {
                if (!offhandStack.isEmpty()) {
                    if (hand.equals(InteractionHand.MAIN_HAND) && !offhandStack.is(ModTags.OFFHAND_EQUIPMENT) && !(heldStack.getItem() instanceof BlockItem)) {
                        return InteractionResult.PASS;
                    }

                    if (hand.equals(InteractionHand.OFF_HAND) && offhandStack.is(ModTags.OFFHAND_EQUIPMENT)) {
                        return InteractionResult.PASS;
                    }
                }

                if (heldStack.isEmpty()) {
                    return InteractionResult.PASS;
                }

                if (millstoneBlockEntity.addItem(player.getAbilities().instabuild ? heldStack.copy() : heldStack)) {
                    level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 0.8F);
                    return InteractionResult.SUCCESS;
                }
            } else {
                if (hand.equals(InteractionHand.MAIN_HAND) && !player.isShiftKeyDown()) {
                    level.setBlockAndUpdate(pos, state.cycle(SPIN));
                    int process = state.getValue(SPIN);
                    level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.STONE_HIT, SoundSource.BLOCKS, 1.0F, 0.8F);
                    if (process == 7) {
                        ItemStack boardStack = millstoneBlockEntity.getStoredItem().copy();
                        if (millstoneBlockEntity.processStoredItem(player)) {
                            spawnMillingParticles(level, pos, boardStack, 5);
                            return InteractionResult.SUCCESS;
                        }
                    }
                    return InteractionResult.SUCCESS;
                }

                if (hand.equals(InteractionHand.MAIN_HAND) && player.isShiftKeyDown()) {
                    if (!player.isCreative()) {
                        if (!player.getInventory().add(millstoneBlockEntity.removeItem())) {
                            Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), millstoneBlockEntity.removeItem());
                        }
                    } else {
                        millstoneBlockEntity.removeItem();
                    }

                    level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.25F, 0.5F);
                    return InteractionResult.SUCCESS;
                }
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof MillstoneBlockEntity millstone) {
                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), millstone.getStoredItem());
                level.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(SPIN, 0);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(SPIN);
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState pState) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof MillstoneBlockEntity) {
            return !((MillstoneBlockEntity) blockEntity).isEmpty() ? 15 : 0;
        } else {
            return 0;
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return PlusBlockEntities.MILLSTONE.get().create(pos, state);
    }
}
