package com.endilcrafter.farmersplus.common.registry;

import com.endilcrafter.farmersplus.FarmersPlus;
import com.endilcrafter.farmersplus.common.PlusFoodValues;
import com.endilcrafter.farmersplus.common.item.CanvasChestBlockItem;
import com.endilcrafter.farmersplus.common.item.DriedFishItem;
import com.endilcrafter.farmersplus.common.item.DriedMeatItem;
import com.google.common.collect.Sets;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.DrinkableItem;
import vectorwing.farmersdelight.common.item.FuelBlockItem;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class PlusItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FarmersPlus.MODID);

    public static LinkedHashSet<RegistryObject<Item>> CREATIVE_TAB_ITEMS = Sets.newLinkedHashSet();
    //Bag
    public static final RegistryObject<Item> APPLE_BAG = registerWithTab("apple_bag",
            () -> new BlockItem(PlusBlocks.APPLE_BAG.get(), basicItem()));
    public static final RegistryObject<Item> BROWN_MUSHROOM_BAG = registerWithTab("brown_mushroom_bag",
            () -> new BlockItem(PlusBlocks.BROWN_MUSHROOM_BAG.get(), basicItem()));
    public static final RegistryObject<Item> COCOA_BEAN_BAG = registerWithTab("cocoa_bean_bag",
            () -> new BlockItem(PlusBlocks.COCOA_BEAN_BAG.get(), basicItem()));
    public static final RegistryObject<Item> CRIMSON_FUNGUS_BAG = registerWithTab("crimson_fungus_bag",
            () -> new BlockItem(PlusBlocks.CRIMSON_FUNGUS_BAG.get(), basicItem()));
    public static final RegistryObject<Item> GLOW_BERRY_BAG = registerWithTab("glow_berry_bag",
            () -> new BlockItem(PlusBlocks.GLOW_BERRY_BAG.get(), basicItem()));
    public static final RegistryObject<Item> GOLDEN_APPLE_BAG = registerWithTab("golden_apple_bag",
            () -> new BlockItem(PlusBlocks.GOLDEN_APPLE_BAG.get(), basicItem()));
    public static final RegistryObject<Item> RED_MUSHROOM_BAG = registerWithTab("red_mushroom_bag",
            () -> new BlockItem(PlusBlocks.RED_MUSHROOM_BAG.get(), basicItem()));
    public static final RegistryObject<Item> SWEET_BERRY_BAG = registerWithTab("sweet_berry_bag",
            () -> new BlockItem(PlusBlocks.SWEET_BERRY_BAG.get(), basicItem()));
    public static final RegistryObject<Item> WARPED_FUNGUS_BAG = registerWithTab("warped_fungus_bag",
            () -> new BlockItem(PlusBlocks.WARPED_FUNGUS_BAG.get(), basicItem()));

    //Other Storage
    public static final RegistryObject<Item> EGG_BOX = registerWithTab("egg_box",
            () -> new BlockItem(PlusBlocks.EGG_BOX.get(), basicItem()));
    public static final RegistryObject<Item> GOLDEN_CARROT_CRATE = registerWithTab("golden_carrot_crate",
            () -> new BlockItem(PlusBlocks.GOLDEN_CARROT_CRATE.get(), basicItem()));
    public static final RegistryObject<Item> KELP_BLOCK = registerWithTab("kelp_block",
            () -> new BlockItem(PlusBlocks.KELP_BLOCK.get(), basicItem()));
    public static final RegistryObject<Item> SUGAR_CANE_BALE = registerWithTab("sugar_cane_bale",
            () -> new BlockItem(PlusBlocks.SUGAR_CANE_BALE.get(), basicItem()));

    //Building
    public static final RegistryObject<Item> CANVAS_BLOCK = registerWithTab("canvas_block",
            () -> new FuelBlockItem(PlusBlocks.CANVAS_BLOCK.get(), basicItem(), 800));
    public static final RegistryObject<Item> FRAMED_CANVAS_BLOCK = registerWithTab("framed_canvas_block",
            () -> new FuelBlockItem(PlusBlocks.FRAMED_CANVAS_BLOCK.get(), basicItem(), 800));

    //Util
    public static final RegistryObject<Item> CANVAS_CHEST = registerWithTab("canvas_chest",
            () -> new CanvasChestBlockItem(PlusBlocks.CANVAS_CHEST.get(), basicItem()));
    public static final RegistryObject<Item> CRATE = registerWithTab("crate",
            () -> new BlockItem(PlusBlocks.CRATE.get(), basicItem().stacksTo(1)));
    public static final RegistryObject<Item> DRYING_RACK = registerWithTab("drying_rack",
            () -> new BlockItem(PlusBlocks.DRYING_RACK.get(), basicItem()));
    public static final RegistryObject<Item> MILLSTONE = registerWithTab("millstone",
            () -> new BlockItem(PlusBlocks.MILLSTONE.get(), basicItem()));
    public static final RegistryObject<Item> TRAY = registerWithTab("tray",
            () -> new FuelBlockItem(PlusBlocks.TRAY.get(), basicItem(), 100));

    //Ingredient
    public static final RegistryObject<Item> BREAD_CRUMB = registerWithTab("bread_crumb",
            () -> new Item(basicItem()));
    public static final RegistryObject<Item> RICE_FLOUR = registerWithTab("rice_flour",
            () -> new Item(basicItem()));
    public static final RegistryObject<Item> WHEAT_FLOUR = registerWithTab("wheat_flour",
            () -> new Item(basicItem()));
    public static final RegistryObject<Item> LARD_BOTTLE = registerWithTab("lard_bottle",
            () -> new Item(basicItem().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));
    public static final RegistryObject<Item> LARD_BUCKET = registerWithTab("lard_bucket",
            () -> new Item(basicItem().craftRemainder(Items.BUCKET).stacksTo(1)));

    //Food
    public static final RegistryObject<Item> APPLE_SLICE = registerWithTab("apple_slice",
            () -> new Item(foodItem(PlusFoodValues.APPLE_SLICE)));
    public static final RegistryObject<Item> GOLDEN_APPLE_SLICE = registerWithTab("golden_apple_slice",
            () -> new Item(foodItem(PlusFoodValues.GOLDEN_APPLE_SLICE)));
    public static final RegistryObject<Item> DRIED_APPLE_SLICE = registerWithTab("dried_apple_slice",
            () -> new Item(foodItem(PlusFoodValues.DRIED_APPLE_SLICE)));
    public static final RegistryObject<Item> DRIED_GOLDEN_APPLE_SLICE = registerWithTab("dried_golden_apple_slice",
            () -> new Item(foodItem(PlusFoodValues.DRIED_GOLDEN_APPLE_SLICE)));
    public static final RegistryObject<Item> DRIED_BEEF = registerWithTab("dried_beef",
            () -> new DriedMeatItem(foodItem(PlusFoodValues.DRIED_BEEF)));
    public static final RegistryObject<Item> DRIED_CHICKEN = registerWithTab("dried_chicken",
            () -> new DriedMeatItem(foodItem(PlusFoodValues.DRIED_CHICKEN)));
    public static final RegistryObject<Item> DRIED_COD_SLICE = registerWithTab("dried_cod_slice",
            () -> new DriedFishItem(foodItem(PlusFoodValues.DRIED_COD_SLICE)));
    public static final RegistryObject<Item> DRIED_MUTTON = registerWithTab("dried_mutton",
            () -> new DriedMeatItem(foodItem(PlusFoodValues.DRIED_MUTTON)));
    public static final RegistryObject<Item> DRIED_PORKCHOP = registerWithTab("dried_porkchop",
            () -> new DriedMeatItem(foodItem(PlusFoodValues.DRIED_PORKCHOP)));
    public static final RegistryObject<Item> DRIED_RABBIT = registerWithTab("dried_rabbit",
            () -> new DriedMeatItem(foodItem(PlusFoodValues.DRIED_RABBIT)));
    public static final RegistryObject<Item> DRIED_SALMON_SLICE = registerWithTab("dried_salmon_slice",
            () -> new DriedFishItem(foodItem(PlusFoodValues.DRIED_SALMON_SLICE)));
    public static final RegistryObject<Item> BOILED_EGG = registerWithTab("boiled_egg",
            () -> new Item(foodItem(PlusFoodValues.BOILED_EGG)));

    public static final RegistryObject<Item> MINCED_CHICKEN = registerWithTab("minced_chicken",
            () -> new Item(foodItem(PlusFoodValues.MINCED_CHICKEN)));
    public static final RegistryObject<Item> MINCED_PORK = registerWithTab("minced_pork",
            () -> new Item(foodItem(PlusFoodValues.MINCED_PORK)));
    public static final RegistryObject<Item> COOKED_MINCED_CHICKEN = registerWithTab("cooked_minced_chicken",
            () -> new Item(foodItem(PlusFoodValues.COOKED_MINCED_CHICKEN)));
    public static final RegistryObject<Item> PORK_PATTY = registerWithTab("pork_patty",
            () -> new Item(foodItem(PlusFoodValues.PORK_PATTY)));

    public static final RegistryObject<Item> CHICKEN_NUGGET = registerWithTab("chicken_nugget",
            () -> new BowlFoodItem(foodItem(PlusFoodValues.CHICKEN_NUGGET).stacksTo(16)));
    public static final RegistryObject<Item> KOROKKE = registerWithTab("korokke",
            () -> new Item(foodItem(PlusFoodValues.KOROKKE)));

    public static final RegistryObject<Item> FRIED_HOLY_BASIL = registerWithTab("fried_holy_basil",
            () -> new ConsumableItem(foodItem(PlusFoodValues.FRIED_HOLY_BASIL).craftRemainder(Items.BOWL).stacksTo(16), true));
    public static final RegistryObject<Item> SALISBURY_STEAK = registerWithTab("salisbury_steak",
            () -> new ConsumableItem(foodItem(PlusFoodValues.SALISBURY_STEAK).craftRemainder(Items.BOWL).stacksTo(16), true));

    public static final RegistryObject<Item> TOMATO_JUICE = registerWithTab("tomato_juice",
            () -> new DrinkableItem(foodItem(PlusFoodValues.TOMATO_JUICE).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16), true, false));

    public static RegistryObject<Item> registerWithTab(final String name, final Supplier<Item> supplier) {
        RegistryObject<Item> block = ITEMS.register(name, supplier);
        CREATIVE_TAB_ITEMS.add(block);
        return block;
    }

    public static Item.Properties basicItem() {
        return new Item.Properties();
    }

    public static Item.Properties foodItem(FoodProperties food) {
        return new Item.Properties().food(food);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
