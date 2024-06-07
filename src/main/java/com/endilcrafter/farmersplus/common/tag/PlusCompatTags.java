package com.endilcrafter.farmersplus.common.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class PlusCompatTags {
    public static final String DIET = "diet";
    public static final TagKey<Item> DIET_FRUITS = externalItemTag(DIET, "fruits");
    public static final TagKey<Item> DIET_GRAINS = externalItemTag(DIET, "grains");
    public static final TagKey<Item> DIET_INGREDIENTS = externalItemTag(DIET, "ingredients");
    public static final TagKey<Item> DIET_PROTEINS = externalItemTag(DIET, "proteins");
    public static final TagKey<Item> DIET_SPECIAL_FOOD = externalItemTag(DIET, "special_food");
    public static final TagKey<Item> DIET_SUGARS = externalItemTag(DIET, "sugars");
    public static final TagKey<Item> DIET_VEGETABLES = externalItemTag(DIET, "vegetables");


    private static TagKey<Item> externalItemTag(String modId, String path) {
        return ItemTags.create(new ResourceLocation(modId, path));
    }
}
