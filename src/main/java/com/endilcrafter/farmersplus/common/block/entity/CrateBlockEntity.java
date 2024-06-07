package com.endilcrafter.farmersplus.common.block.entity;

import com.endilcrafter.farmersplus.common.registry.PlusBlockEntities;
import com.endilcrafter.farmersplus.common.world.inventory.CrateMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CrateBlockEntity extends RandomizableContainerBlockEntity {
    private static final int COLUMN = 6;
    private static final int ROWS = 9;
    private static final int CONTAINER_SIZE = 54;
    private NonNullList<ItemStack> items = NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);

    public CrateBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(PlusBlockEntities.CRATE.get(), pPos, pBlockState);
    }

    protected void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        if (!this.trySaveLootTable(compound)) {
            ContainerHelper.saveAllItems(compound, this.items, false);
        }
    }

    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.loadFromTag(pTag);
    }

    public void loadFromTag(CompoundTag pTag) {
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(pTag) && pTag.contains("Items", 9)) {
            ContainerHelper.loadAllItems(pTag, this.items);
        }
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("farmersplus.container.crate");
    }

    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return new CrateMenu(pContainerId, pInventory, this);
    }

    @Override
    public int getContainerSize() {
        return 54;
    }
}
