package com.endilcrafter.farmersplus.common.world.inventory;

import com.endilcrafter.farmersplus.common.tag.PlusTags;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class CrateSlot extends Slot {

    public CrateSlot(Container pContainer, int pSlot, int pX, int pY) {
        super(pContainer, pSlot, pX, pY);
    }

    @Override
    public boolean mayPlace(ItemStack pStack) {
        return pStack.is(PlusTags.CRATE_CAN_CONTAIN);
    }
}
