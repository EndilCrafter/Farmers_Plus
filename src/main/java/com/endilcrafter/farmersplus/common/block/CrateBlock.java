package com.endilcrafter.farmersplus.common.block;

import com.endilcrafter.farmersplus.FarmersPlus;
import com.endilcrafter.farmersplus.common.block.entity.CrateBlockEntity;
import com.endilcrafter.farmersplus.common.registry.PlusBlockEntities;
import com.endilcrafter.farmersplus.common.registry.PlusBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CrateBlock extends BaseEntityBlock {

    public static final ResourceLocation CONTENTS = new ResourceLocation(FarmersPlus.MODID, "contents");

    public CrateBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof CrateBlockEntity crateBlockEntity) {
            if (!level.isClientSide && player.isCreative() && !crateBlockEntity.isEmpty()) {
                ItemStack itemstack = new ItemStack(PlusBlocks.CRATE.get());
                blockentity.saveToItem(itemstack);
                if (crateBlockEntity.hasCustomName()) {
                    itemstack.setHoverName(crateBlockEntity.getCustomName());
                }

                ItemEntity itementity = new ItemEntity(level, (double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, itemstack);
                itementity.setDefaultPickUpDelay();
                level.addFreshEntity(itementity);
            } else {
                crateBlockEntity.unpackLootTable(player);
            }
        }

        super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        BlockEntity blockentity = builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (blockentity instanceof CrateBlockEntity crateBlockEntity) {
            builder = builder.withDynamicDrop(CONTENTS, (consumer) -> {
                for (int i = 0; i < crateBlockEntity.getContainerSize(); ++i) {
                    consumer.accept(crateBlockEntity.getItem(i));
                }

            });
        }

        return super.getDrops(state, builder);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity player, ItemStack itemStack) {
        if (itemStack.hasCustomHoverName()) {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof CrateBlockEntity) {
                ((CrateBlockEntity) blockentity).setCustomName(itemStack.getHoverName());
            }
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof CrateBlockEntity) {
                level.updateNeighbourForOutputSignal(pos, state.getBlock());
            }

            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else if (player.isSpectator()) {
            return InteractionResult.CONSUME;
        } else {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof CrateBlockEntity crateBlockEntity) {
                player.openMenu(crateBlockEntity);
                return InteractionResult.CONSUME;
            } else {
                return InteractionResult.PASS;
            }
        }
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return AbstractContainerMenu.getRedstoneSignalFromContainer((Container) level.getBlockEntity(pos));
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        ItemStack itemstack = super.getCloneItemStack(level, pos, state);
        level.getBlockEntity(pos, PlusBlockEntities.CRATE.get()).ifPresent((crateBlockEntity) -> {
            crateBlockEntity.saveToItem(itemstack);
        });
        return itemstack;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable BlockGetter level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(itemStack, level, tooltip, flag);
        CompoundTag compoundtag = BlockItem.getBlockEntityData(itemStack);
        if (compoundtag != null) {
            if (compoundtag.contains("LootTable", 8)) {
                tooltip.add(Component.literal("???????"));
            }

            if (compoundtag.contains("Items", 9)) {
                NonNullList<ItemStack> nonnulllist = NonNullList.withSize(27, ItemStack.EMPTY);
                ContainerHelper.loadAllItems(compoundtag, nonnulllist);
                int i = 0;
                int j = 0;

                for (ItemStack itemstack : nonnulllist) {
                    if (!itemstack.isEmpty()) {
                        ++j;
                        if (i <= 4) {
                            ++i;
                            MutableComponent mutablecomponent = itemstack.getHoverName().copy();
                            mutablecomponent.append(" x").append(String.valueOf(itemstack.getCount()));
                            tooltip.add(mutablecomponent);
                        }
                    }
                }

                if (j - i > 0) {
                    tooltip.add(Component.translatable("container.shulkerBox.more", j - i).withStyle(ChatFormatting.WHITE));
                }
            }
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new CrateBlockEntity(blockPos, blockState);
    }
}
