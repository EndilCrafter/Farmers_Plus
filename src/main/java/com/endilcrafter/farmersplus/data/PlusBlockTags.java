package com.endilcrafter.farmersplus.data;

import com.endilcrafter.farmersplus.FarmersPlus;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import static com.endilcrafter.farmersplus.common.registry.PlusBlocks.*;

public class PlusBlockTags extends BlockTagsProvider {
    public PlusBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, FarmersPlus.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(GOLDEN_CARROT_CRATE.get(), FRAMED_CANVAS_BLOCK.get(), CANVAS_CHEST.get(), CRATE.get(), DRYING_RACK.get(), TRAY.get());
        this.tag(BlockTags.MINEABLE_WITH_HOE).add(KELP_BLOCK.get(), SUGAR_CANE_BALE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MILLSTONE.get());
        for (RegistryObject<Block> blockRegistryObject : Arrays.asList(
                APPLE_BAG,
                BROWN_MUSHROOM_BAG,
                COCOA_BEAN_BAG,
                CRIMSON_FUNGUS_BAG,
                GLOW_BERRY_BAG,
                GOLDEN_APPLE_BAG,
                RED_MUSHROOM_BAG,
                SWEET_BERRY_BAG,
                WARPED_FUNGUS_BAG,
                CANVAS_BLOCK,
                FRAMED_CANVAS_BLOCK,
                CANVAS_CHEST
        )) {
            this.tag(ModTags.MINEABLE_WITH_KNIFE).add(blockRegistryObject.get());
            this.tag(ModTags.STRAW_BLOCKS).add(blockRegistryObject.get());
        }
    }
}
