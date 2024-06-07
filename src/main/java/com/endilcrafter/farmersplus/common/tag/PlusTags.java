package com.endilcrafter.farmersplus.common.tag;

import com.endilcrafter.farmersplus.FarmersPlus;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class PlusTags {

    public static final TagKey<Item> CRATE_CAN_CONTAIN = item("crate_can_contain");
    public static final TagKey<Item> TRAY_CAN_SERVE = item("tray_can_serve");
    public static final TagKey<Item> RAW_MINCED_MEATS = item("raw_minced_meats");


    private static TagKey<Item> item(String path) {
        return ItemTags.create(new ResourceLocation(FarmersPlus.MODID, path));
    }

    private static TagKey<Block> block(String path) {
        return BlockTags.create(new ResourceLocation(FarmersPlus.MODID, path));
    }
}
