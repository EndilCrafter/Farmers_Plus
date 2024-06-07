package com.endilcrafter.farmersplus.data;

import com.endilcrafter.farmersplus.FarmersPlus;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;

import static com.endilcrafter.farmersplus.common.registry.PlusBlocks.*;

public class PlusBlockStates extends BlockStateProvider {
    public PlusBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, FarmersPlus.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (RegistryObject<Block> blockRegistryObject : Arrays.asList(
                APPLE_BAG,
                BROWN_MUSHROOM_BAG,
                COCOA_BEAN_BAG,
                CRIMSON_FUNGUS_BAG,
                GLOW_BERRY_BAG,
                GOLDEN_APPLE_BAG,
                RED_MUSHROOM_BAG,
                SWEET_BERRY_BAG,
                WARPED_FUNGUS_BAG)) {
            bag(blockRegistryObject);
        }
        crate(GOLDEN_CARROT_CRATE);
        crate(CRATE);
        axisBlock((RotatedPillarBlock) SUGAR_CANE_BALE.get());
        horizontalBlock(FRAMED_CANVAS_BLOCK.get(), resourceBlock(blockName(FRAMED_CANVAS_BLOCK.get())), resourceBlock(blockName(FRAMED_CANVAS_BLOCK.get())), resourceBlock(blockName(FRAMED_CANVAS_BLOCK.get())));
    }

    private String blockName(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }

    public ResourceLocation resourceBlock(String path) {
        return new ResourceLocation(FarmersPlus.MODID, "block/" + path);
    }

    public ModelFile existingModel(Block block) {
        return new ModelFile.ExistingModelFile(resourceBlock(blockName(block)), models().existingFileHelper);
    }

    public ModelFile existingModel(String path) {
        return new ModelFile.ExistingModelFile(resourceBlock(path), models().existingFileHelper);
    }

    private void blockItem(RegistryObject<Block> block) {
        simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(FarmersPlus.MODID + ":block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void bag(RegistryObject<Block> bag) {
        String Bag = blockName(bag.get());
        simpleBlock(bag.get(), models().withExistingParent(Bag, "cube")
                .texture("particle", resourceBlock(Bag + "_top"))
                .texture("down", resourceBlock("bag_bottom"))
                .texture("up", resourceBlock(Bag + "_top"))
                .texture("north", resourceBlock("bag_side_tied"))
                .texture("south", resourceBlock("bag_side_tied"))
                .texture("east", resourceBlock("bag_side"))
                .texture("west", resourceBlock("bag_side")));
    }

    private void crate(RegistryObject<Block> crate) {
        simpleBlock(crate.get(), models().cubeBottomTop(blockName(crate.get()), resourceBlock(blockName(crate.get()) + "_side"), resourceBlock("crate_bottom"), resourceBlock(blockName(crate.get()) + "_top")));
    }
}
