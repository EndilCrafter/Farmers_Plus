package com.endilcrafter.farmersplus.common.registry;

import com.endilcrafter.farmersplus.FarmersPlus;
import com.endilcrafter.farmersplus.common.block.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PlusBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FarmersPlus.MODID);

    public static final RegistryObject<Block> APPLE_BAG = BLOCKS.register("apple_bag",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> BROWN_MUSHROOM_BAG = BLOCKS.register("brown_mushroom_bag",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> COCOA_BEAN_BAG = BLOCKS.register("cocoa_bean_bag",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> CRIMSON_FUNGUS_BAG = BLOCKS.register("crimson_fungus_bag",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> EGG_BOX = BLOCKS.register("egg_box",
            () -> new EggBoxBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).instabreak()));
    public static final RegistryObject<Block> GLOW_BERRY_BAG = BLOCKS.register("glow_berry_bag",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> GOLDEN_APPLE_BAG = BLOCKS.register("golden_apple_bag",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).mapColor(MapColor.GOLD)));
    public static final RegistryObject<Block> GOLDEN_CARROT_CRATE = BLOCKS.register("golden_carrot_crate",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD).mapColor(MapColor.GOLD)));
    public static final RegistryObject<Block> KELP_BLOCK = BLOCKS.register("kelp_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DRIED_KELP_BLOCK).mapColor(MapColor.COLOR_LIGHT_GREEN).sound(SoundType.WET_GRASS)));
    public static final RegistryObject<Block> RED_MUSHROOM_BAG = BLOCKS.register("red_mushroom_bag",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> SUGAR_CANE_BALE = BLOCKS.register("sugar_cane_bale",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final RegistryObject<Block> SWEET_BERRY_BAG = BLOCKS.register("sweet_berry_bag",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> WARPED_FUNGUS_BAG = BLOCKS.register("warped_fungus_bag",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));

    public static final RegistryObject<Block> CANVAS_BLOCK = BLOCKS.register("canvas_block",
            () -> new CanvasBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).sound(SoundType.GRASS).strength(0.2F)));
    public static final RegistryObject<Block> FRAMED_CANVAS_BLOCK = BLOCKS.register("framed_canvas_block",
            () -> new FramedCanvasBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).sound(SoundType.SCAFFOLDING).strength(0.3F)));

    public static final RegistryObject<Block> CANVAS_CHEST = BLOCKS.register("canvas_chest",
            () -> new CanvasChestBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).sound(SoundType.SCAFFOLDING).strength(0.5F), PlusBlockEntities.CANVAS_CHEST::get));
    public static final RegistryObject<Block> CRATE = BLOCKS.register("crate",
            () -> new CrateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> DRYING_RACK = BLOCKS.register("drying_rack",
            () -> new DryingRackBlock(BlockBehaviour.Properties.copy(Blocks.COMPOSTER).noOcclusion()));
    public static final RegistryObject<Block> MILLSTONE = BLOCKS.register("millstone",
            () -> new MillstoneBlock(BlockBehaviour.Properties.copy(Blocks.GRINDSTONE)));
    public static final RegistryObject<Block> TRAY = BLOCKS.register("tray",
            () -> new TrayBlock(BlockBehaviour.Properties.copy(Blocks.COMPOSTER).noOcclusion().instabreak()));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
