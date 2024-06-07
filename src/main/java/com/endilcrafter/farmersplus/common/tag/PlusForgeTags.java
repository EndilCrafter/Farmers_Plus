package com.endilcrafter.farmersplus.common.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class PlusForgeTags {

    public static final TagKey<Item> LARD = forgeItemTag("lard");
    public static final TagKey<Item> LARD_BUCKET = forgeItemTag("lard/lard");
    public static final TagKey<Item> LARD_BOTTLE = forgeItemTag("lard/lard_bottle");
    public static final TagKey<Item> FLOUR = forgeItemTag("flour");
    public static final TagKey<Item> FLOUR_RICE = forgeItemTag("flour/rice");
    public static final TagKey<Item> FLOUR_WHEAT = forgeItemTag("flour/wheat");
    public static final TagKey<Item> DUSTS_RICE = forgeItemTag("dusts/rice");
    public static final TagKey<Item> DUSTS_WHEAT = forgeItemTag("dusts/wheat");

    private static TagKey<Block> forgeBlockTag(String path) {
        return BlockTags.create(new ResourceLocation("forge", path));
    }

    private static TagKey<Item> forgeItemTag(String path) {
        return ItemTags.create(new ResourceLocation("forge", path));
    }
}
