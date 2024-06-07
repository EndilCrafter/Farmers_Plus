package com.endilcrafter.farmersplus.common.block.entity;

import com.endilcrafter.farmersplus.common.crafting.MillstoneRecipe;
import com.endilcrafter.farmersplus.common.registry.PlusAdvancements;
import com.endilcrafter.farmersplus.common.registry.PlusBlockEntities;
import com.endilcrafter.farmersplus.common.registry.PlusRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.block.entity.SyncedBlockEntity;
import vectorwing.farmersdelight.common.mixin.accessor.RecipeManagerAccessor;
import vectorwing.farmersdelight.common.utility.ItemUtils;
import vectorwing.farmersdelight.common.utility.TextUtils;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class MillstoneBlockEntity extends SyncedBlockEntity {
    private final ItemStackHandler inventory = this.createHandler();
    private final LazyOptional<IItemHandler> inputHandler = LazyOptional.of(() -> this.inventory);
    private ResourceLocation lastRecipeID;

    public MillstoneBlockEntity(BlockPos pos, BlockState state) {
        super(PlusBlockEntities.MILLSTONE.get(), pos, state);
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.inventory.deserializeNBT(compound.getCompound("Inventory"));
    }

    @Override
    protected void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.put("Inventory", this.inventory.serializeNBT());
    }

    public boolean processStoredItem(@Nullable Player player) {
        if (this.level == null) {
            return false;
        } else {
            Optional<MillstoneRecipe> matchingRecipe = this.getMatchingRecipe(new RecipeWrapper(this.inventory), player);
            matchingRecipe.ifPresent((recipe) -> {
                List<ItemStack> results = recipe.rollResults(this.level.random);

                for (ItemStack resultStack : results) {
                    ItemUtils.spawnItemEntity(this.level, resultStack.copy(), (double) this.worldPosition.getX() + 0.5, (double) this.worldPosition.getY() + 0.8, (double) this.worldPosition.getZ() + 0.5, 0.0, 0.0, 0.0);
                }

                this.playProcessingSound(recipe.getSoundEventID(), this.getStoredItem());
                this.consumeItem();
                if (player instanceof ServerPlayer) {
                    PlusAdvancements.MILLSTONE.trigger((ServerPlayer) player);
                }

            });
            return matchingRecipe.isPresent();
        }
    }

    private Optional<MillstoneRecipe> getMatchingRecipe(RecipeWrapper recipeWrapper, @Nullable Player player) {
        if (this.level == null) {
            return Optional.empty();
        } else {
            if (this.lastRecipeID != null) {
                Recipe<RecipeWrapper> recipe = (Recipe) ((RecipeManagerAccessor) this.level.getRecipeManager()).getRecipeMap((RecipeType) PlusRecipeTypes.MILLING.get()).get(this.lastRecipeID);
                if (recipe instanceof MillstoneRecipe && recipe.matches(recipeWrapper, this.level)) {
                    return Optional.of((MillstoneRecipe) recipe);
                }
            }

            List<MillstoneRecipe> recipeList = this.level.getRecipeManager().getRecipesFor((RecipeType) PlusRecipeTypes.MILLING.get(), recipeWrapper, this.level);
            if (recipeList.isEmpty()) {
                if (player != null) {
                    player.displayClientMessage(TextUtils.getTranslation("block.millstone.invalid_item"), true);
                }

                return Optional.empty();
            } else {
                Optional<MillstoneRecipe> recipe = recipeList.stream().findFirst();
                this.lastRecipeID = recipe.get().getId();
                return recipe;
            }
        }
    }

    public void playProcessingSound(String soundEventID, ItemStack boardItem) {
        SoundEvent sound = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(soundEventID));
        if (sound != null) {
            this.playSound(sound, 1.0F, 1.0F);
        } else {
            Item var6 = boardItem.getItem();
            if (var6 instanceof BlockItem blockItem) {
                Block block = blockItem.getBlock();
                SoundType soundType = block.defaultBlockState().getSoundType();
                this.playSound(soundType.getBreakSound(), 1.0F, 0.8F);
            } else {
                this.playSound(SoundEvents.STONE_HIT, 1.0F, 0.8F);
            }
        }

    }

    public void playSound(SoundEvent sound, float volume, float pitch) {
        if (this.level != null) {
            this.level.playSound(null, (float) this.worldPosition.getX() + 0.5F, (float) this.worldPosition.getY() + 0.5F, (float) this.worldPosition.getZ() + 0.5F, sound, SoundSource.BLOCKS, volume, pitch);
        }

    }

    public boolean addItem(ItemStack itemStack) {
        if (this.isEmpty() && !itemStack.isEmpty()) {
            this.inventory.setStackInSlot(0, itemStack.split(itemStack.getCount()));
            this.inventoryChanged();
            return true;
        } else {
            return false;
        }
    }

    public ItemStack consumeItem() {
        if (!this.isEmpty()) {
            ItemStack item = this.getStoredItem().split(1);
            this.inventoryChanged();
            return item;
        } else {
            return ItemStack.EMPTY;
        }
    }

    public ItemStack removeItem() {
        if (!this.isEmpty()) {
            ItemStack item = this.getStoredItem().split(this.getStoredItem().getCount());
            this.inventoryChanged();
            return item;
        } else {
            return ItemStack.EMPTY;
        }
    }

    public ItemStackHandler getInventory() {
        return this.inventory;
    }

    public ItemStack getStoredItem() {
        return this.inventory.getStackInSlot(0);
    }

    public boolean isEmpty() {
        return this.inventory.getStackInSlot(0).isEmpty();
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap.equals(ForgeCapabilities.ITEM_HANDLER) ? this.inputHandler.cast() : super.getCapability(cap, side);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        this.inputHandler.invalidate();
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler() {
            public int getSlotLimit(int slot) {
                return 1;
            }

            protected void onContentsChanged(int slot) {
                MillstoneBlockEntity.this.inventoryChanged();
            }
        };
    }
}
