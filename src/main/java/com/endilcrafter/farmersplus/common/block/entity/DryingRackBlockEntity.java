package com.endilcrafter.farmersplus.common.block.entity;

import com.endilcrafter.farmersplus.common.crafting.DryingRackRecipe;
import com.endilcrafter.farmersplus.common.registry.PlusBlockEntities;
import com.endilcrafter.farmersplus.common.registry.PlusRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.items.ItemStackHandler;
import vectorwing.farmersdelight.common.mixin.accessor.RecipeManagerAccessor;
import vectorwing.farmersdelight.common.utility.ItemUtils;

import javax.annotation.Nullable;
import java.util.Optional;

public class DryingRackBlockEntity extends BlockEntity {
    private static final int INVENTORY_SLOT_COUNT = 4;
    private final ItemStackHandler inventory;
    private final int[] dryingTimes;
    private final int[] dryingTimesTotal;
    private final ResourceLocation[] lastRecipeIDs;

    public DryingRackBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(PlusBlockEntities.DRYING_RACK.get(), pPos, pBlockState);
        inventory = createHandler();
        dryingTimes = new int[INVENTORY_SLOT_COUNT];
        dryingTimesTotal = new int[INVENTORY_SLOT_COUNT];
        lastRecipeIDs = new ResourceLocation[INVENTORY_SLOT_COUNT];
    }

    public static void dryingTick(Level level, BlockPos pos, BlockState state, DryingRackBlockEntity rack) {
        rack.dryAndOutputItems();
    }

    public static void animationTick(Level level, BlockPos pos, BlockState state, DryingRackBlockEntity rack) {

    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        if (compound.contains("Inventory")) {
            this.inventory.deserializeNBT(compound.getCompound("Inventory"));
        } else {
            this.inventory.deserializeNBT(compound);
        }

        int[] arrayDryingTimesTotal;
        if (compound.contains("DryingTimes", 11)) {
            arrayDryingTimesTotal = compound.getIntArray("DryingTimes");
            System.arraycopy(arrayDryingTimesTotal, 0, this.dryingTimes, 0, Math.min(this.dryingTimesTotal.length, arrayDryingTimesTotal.length));
        }

        if (compound.contains("DryingTotalTimes", 11)) {
            arrayDryingTimesTotal = compound.getIntArray("DryingTotalTimes");
            System.arraycopy(arrayDryingTimesTotal, 0, this.dryingTimesTotal, 0, Math.min(this.dryingTimesTotal.length, arrayDryingTimesTotal.length));
        }

    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        this.writeItems(compound);
        compound.putIntArray("DryingTimes", this.dryingTimes);
        compound.putIntArray("DryingTotalTimes", this.dryingTimesTotal);
    }

    private CompoundTag writeItems(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.put("Inventory", this.inventory.serializeNBT());
        return compound;
    }

    private void dryAndOutputItems() {
        if (level == null) return;

        boolean didInventoryChange = false;
        for (int i = 0; i < inventory.getSlots(); ++i) {
            ItemStack rackStack = inventory.getStackInSlot(i);
            if (!rackStack.isEmpty()) {
                ++dryingTimes[i];
                if (dryingTimes[i] >= dryingTimesTotal[i]) {
                    Container inventoryWrapper = new SimpleContainer(rackStack);
                    Optional<DryingRackRecipe> recipe = getMatchingRecipe(inventoryWrapper, i);
                    if (recipe.isPresent()) {
                        ItemStack resultStack = recipe.get().getResultItem(level.registryAccess());
                        if (!resultStack.isEmpty()) {
                            ItemUtils.spawnItemEntity(level, resultStack.copy(),
                                    worldPosition.getX() + 0.5, worldPosition.getY() + 1.0, worldPosition.getZ() + 0.5,
                                    level.random.nextGaussian() * (double) 0.01F, 0.1F, level.random.nextGaussian() * (double) 0.01F);
                        }
                    }
                    inventory.setStackInSlot(i, ItemStack.EMPTY);
                    didInventoryChange = true;
                }
            }
        }

        if (didInventoryChange) {
            inventoryChanged();
        }
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

    public boolean addItem(ItemStack itemStackIn, DryingRackRecipe recipe, int slot) {
        if (0 <= slot && slot < this.inventory.getSlots()) {
            ItemStack slotStack = this.inventory.getStackInSlot(slot);
            if (slotStack.isEmpty()) {
                this.dryingTimesTotal[slot] = recipe.getDryingTime();
                this.dryingTimes[slot] = 0;
                this.inventory.setStackInSlot(slot, itemStackIn.split(1));
                this.lastRecipeIDs[slot] = recipe.getId();
                this.inventoryChanged();
                return true;
            }
        }

        return false;
    }

    public Optional<DryingRackRecipe> getMatchingRecipe(Container recipeWrapper, int slot) {
        if (this.level == null) {
            return Optional.empty();
        } else {
            if (this.lastRecipeIDs[slot] != null) {
                Recipe<Container> recipe = ((RecipeManagerAccessor) this.level.getRecipeManager()).getRecipeMap(PlusRecipeTypes.DRYING.get()).get(this.lastRecipeIDs[slot]);
                if (recipe instanceof DryingRackRecipe && recipe.matches(recipeWrapper, this.level)) {
                    return Optional.of((DryingRackRecipe) recipe);
                }
            }

            return this.level.getRecipeManager().getRecipeFor(PlusRecipeTypes.DRYING.get(), recipeWrapper, this.level);
        }
    }

    public ItemStackHandler getInventory() {
        return this.inventory;
    }

    public Vec2 getRackItemOffset(int index) {
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