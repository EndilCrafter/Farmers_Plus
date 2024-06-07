package com.endilcrafter.farmersplus.common.world.inventory;

import com.endilcrafter.farmersplus.common.registry.PlusMenuTypes;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class CrateMenu extends AbstractContainerMenu {
    private static final int CONTAINER_SIZE = 54;
    private final Container container;

    public CrateMenu(int pContainerId, Inventory pPlayerInventory) {
        this(pContainerId, pPlayerInventory, new SimpleContainer(54));
    }

    public CrateMenu(int pContainerId, Inventory inventory, Container container) {
        super(PlusMenuTypes.CRATE.get(), pContainerId);
        checkContainerSize(container, 54);
        this.container = container;
        container.startOpen(inventory.player);

        int i;
        int j;

        for (i = 0; i < 6; ++i) {
            for (j = 0; j < 9; ++j) {
                this.addSlot(new CrateSlot(container, j + i * 9, 8 + j * 18, 18 + i * 18));
            }
        }

        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 103 + i * 18 + 36));
            }
        }

        for (j = 0; j < 9; ++j) {
            this.addSlot(new Slot(inventory, j, 8 + j * 18, 161 + 36));
        }
    }

    public boolean stillValid(Player pPlayer) {
        return this.container.stillValid(pPlayer);
    }

    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(pIndex);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (pIndex < this.container.getContainerSize()) {
                if (!this.moveItemStackTo(itemstack1, this.container.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, this.container.getContainerSize(), false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    public void removed(Player pPlayer) {
        super.removed(pPlayer);
        this.container.stopOpen(pPlayer);
    }

    public Container getContainer() {
        return this.container;
    }
}
