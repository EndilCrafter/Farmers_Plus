package com.endilcrafter.farmersplus.data;

import com.endilcrafter.farmersplus.FarmersPlus;
import com.endilcrafter.farmersplus.common.registry.PlusBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static com.endilcrafter.farmersplus.common.registry.PlusItems.*;
import static vectorwing.farmersdelight.data.ItemModels.takeAll;

public class PlusItemModels extends ItemModelProvider {
    public PlusItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, FarmersPlus.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        Set<Item> items = ForgeRegistries.ITEMS.getValues().stream().filter(i -> FarmersPlus.MODID.equals(ForgeRegistries.ITEMS.getKey(i).getNamespace()))
                .collect(Collectors.toSet());

        items.remove(EGG_BOX.get());
        items.remove(CANVAS_CHEST.get());
        spriteModel(PlusBlocks.EGG_BOX);

        takeAll(items, i -> i instanceof BlockItem).forEach(item -> blockBasedModel(item, ""));
        for (RegistryObject<Item> itemRegistryObject : Arrays.asList(
                BREAD_CRUMB,
                RICE_FLOUR,
                WHEAT_FLOUR,
                LARD_BOTTLE,
                LARD_BUCKET,
                APPLE_SLICE,
                GOLDEN_APPLE_SLICE,
                DRIED_APPLE_SLICE,
                DRIED_GOLDEN_APPLE_SLICE,
                DRIED_BEEF,
                DRIED_CHICKEN,
                DRIED_COD_SLICE,
                DRIED_MUTTON,
                DRIED_PORKCHOP,
                DRIED_RABBIT,
                DRIED_SALMON_SLICE,
                BOILED_EGG,
                MINCED_CHICKEN,
                MINCED_PORK,
                COOKED_MINCED_CHICKEN,
                PORK_PATTY,
                CHICKEN_NUGGET,
                KOROKKE,
                FRIED_HOLY_BASIL,
                SALISBURY_STEAK
        )) {
            simpleModel(itemRegistryObject);
        }
        mugModel(TOMATO_JUICE);
    }

    private ItemModelBuilder simpleModel(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(FarmersPlus.MODID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldModel(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(FarmersPlus.MODID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder spriteModel(RegistryObject<Block> block) {
        return withExistingParent(block.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(FarmersPlus.MODID, "item/" + block.getId().getPath()));
    }

    private ItemModelBuilder plantModel(RegistryObject<Block> plant) {
        return withExistingParent(plant.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(FarmersPlus.MODID, "block/" + plant.getId().getPath()));
    }

    private ItemModelBuilder mugModel(RegistryObject<Item> mug) {
        return withExistingParent(itemName(mug.get()),
                new ResourceLocation(FarmersPlus.MODID, "item/mug")).texture("layer0",
                new ResourceLocation(FarmersPlus.MODID, "item/" + mug.getId().getPath()));
    }

    public void blockBasedModel(Item item, String suffix) {
        withExistingParent(itemName(item), resourceBlock(itemName(item) + suffix));
    }

    private String itemName(Item item) {
        return ForgeRegistries.ITEMS.getKey(item).getPath();
    }

    public ResourceLocation resourceBlock(String path) {
        return new ResourceLocation(FarmersPlus.MODID, "block/" + path);
    }

    public ResourceLocation resourceItem(String path) {
        return new ResourceLocation(FarmersPlus.MODID, "item/" + path);
    }
}
