package com.endilcrafter.farmersplus.common.block.entity;

import com.endilcrafter.farmersplus.common.registry.PlusBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TrayBlockEntity extends BlockEntity {
    private static final int INVENTORY_SLOT_COUNT = 4;
    private final ItemStackHandler inventory;

    public TrayBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(PlusBlockEntities.TRAY.get(), pPos, pBlockState);
        inventory = createHandler();
    }

    public static void displayTick(Level level, BlockPos pos, BlockState state, TrayBlockEntity rack) {
        rack.added();
    }

    public static void animationTick(Level level, BlockPos pos, BlockState state, TrayBlockEntity tray) {

    }

    private void added() {
        if (level == null) return;

        boolean didInventoryChange = false;
        for (int i = 0; i < inventory.getSlots(); ++i) {
            ItemStack trayStack = inventory.getStackInSlot(i);
            if (!trayStack.isEmpty()) {
                didInventoryChange = true;

            }
        }

        if (didInventoryChange) {
            inventoryChanged();
        }
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        if (compound.contains("Inventory")) {
            this.inventory.deserializeNBT(compound.getCompound("Inventory"));
        } else {
            this.inventory.deserializeNBT(compound);
        }
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        this.writeItems(compound);
    }

    private CompoundTag writeItems(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.put("Inventory", this.inventory.serializeNBT());
        return compound;
    }

    public int getNextEmptySlot() {
        for (int i = 0; i < this.inventory.getSlots(); ++i) {
            ItemStack slotStack = this.inventory.getStackInSlot(i);
            if (slotStack.isEmpty()) {
                return i;
            }
        }

        return -1;
    }

    public boolean addItem(ItemStack itemStackIn, int slot) {
        if (0 <= slot && slot < this.inventory.getSlots()) {
            ItemStack slotStack = this.inventory.getStackInSlot(slot);
            if (slotStack.isEmpty()) {
                this.inventory.setStackInSlot(slot, itemStackIn.split(1));
                this.inventoryChanged();
                return true;
            }
        }

        return false;
    }

    public ItemStackHandler getInventory() {
        return this.inventory;
    }

    public Vec2 getTrayItemOffset(int index) {
        float X_OFFSET = 0.2F; //0.3F->0.2F
        float Y_OFFSET = 0.2F;
        Vec2[] OFFSETS = new Vec2[]{new Vec2(0.2F, 0.2F), new Vec2(-0.2F, 0.2F), new Vec2(0.2F, -0.2F), new Vec2(-0.2F, -0.2F)};
        return OFFSETS[index];
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.writeItems(new CompoundTag());
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(4) {
            public int getSlotLimit(int slot) {
                return 1;
            }
        };
    }

    @Nullable
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.load(pkt.getTag());
    }

    protected void inventoryChanged() {
        super.setChanged();
        if (this.level != null) {
            this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
        }

    }
}
