package com.endilcrafter.farmersplus.common;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlusConfiguration {
    //Common
    public static final String CATEGORY_SETTINGS = "Settings";
    public static final String CATEGORY_STORAGES = "Storages";
    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec.BooleanValue ENABLE_VANILLA_INGREDIENT_STORAGES;
    public static ForgeConfigSpec.BooleanValue ENABLE_APPLE_BAG;
    public static ForgeConfigSpec.BooleanValue ENABLE_BROWN_MUSHROOM_BAG;
    public static ForgeConfigSpec.BooleanValue ENABLE_COCOA_BEAN_BAG;
    public static ForgeConfigSpec.BooleanValue ENABLE_CRIMSON_FUNGUS_BAG;
    public static ForgeConfigSpec.BooleanValue ENABLE_EGG_BOX;
    public static ForgeConfigSpec.BooleanValue ENABLE_GLOW_BERRY_BAG;
    public static ForgeConfigSpec.BooleanValue ENABLE_GOLDEN_APPLE_BAG;
    public static ForgeConfigSpec.BooleanValue ENABLE_GOLDEN_CARROT_CRATE;
    public static ForgeConfigSpec.BooleanValue ENABLE_KELP_BLOCK;
    public static ForgeConfigSpec.BooleanValue ENABLE_RED_MUSHROOM_BAG;
    public static ForgeConfigSpec.BooleanValue ENABLE_SUGAR_CANE_BALE;
    public static ForgeConfigSpec.BooleanValue ENABLE_SWEET_BERRY_BAG;
    public static ForgeConfigSpec.BooleanValue ENABLE_WARPED_FUNGUS_BAG;


    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        //GENERAL
        COMMON_BUILDER.translation("config.farmersplus.general_settings").push(CATEGORY_SETTINGS);

        ENABLE_VANILLA_INGREDIENT_STORAGES = COMMON_BUILDER.translation("config.farmersplus.general_settings.enable_storages")
                .define("enableVanillaIngredientStorages", true);
        COMMON_BUILDER.pop();

        //STORAGES
        COMMON_BUILDER.translation("config.farmersplus.storages").push(CATEGORY_STORAGES);

        ENABLE_APPLE_BAG = COMMON_BUILDER.translation("config.farmersplus.storages.enable_apple_bag")
                .define("enableAppleBag", true);
        ENABLE_BROWN_MUSHROOM_BAG = COMMON_BUILDER.translation("config.farmersplus.storages.enable_brown_mushroom_bag")
                .define("enableBrownMushroomBag", true);
        ENABLE_COCOA_BEAN_BAG = COMMON_BUILDER.translation("config.farmersplus.storages.enable_cocoa_bean_bag")
                .define("enableCocoaBeanBag", true);
        ENABLE_CRIMSON_FUNGUS_BAG = COMMON_BUILDER.translation("config.farmersplus.storages.enable_crimson_fungus_bag")
                .define("enableCrimsonFungusBag", true);
        ENABLE_GLOW_BERRY_BAG = COMMON_BUILDER.translation("config.farmersplus.storages.enable_glow_berry_bag")
                .define("enableGlowBerryBag", true);
        ENABLE_GOLDEN_APPLE_BAG = COMMON_BUILDER.translation("config.farmersplus.storages.enable_golden_apple_bag")
                .define("enableGoldenAppleBag", true);
        ENABLE_GOLDEN_CARROT_CRATE = COMMON_BUILDER.translation("config.farmersplus.storages.enable_golden_carrot_crate")
                .define("enableGoldenCarrotCrate", true);
        ENABLE_EGG_BOX = COMMON_BUILDER.translation("config.farmersplus.storages.enable_egg_box")
                .define("enableEggBox", true);
        ENABLE_KELP_BLOCK = COMMON_BUILDER.translation("config.farmersplus.storages.enable_kelp_block")
                .define("enableKelpBlock", true);
        ENABLE_RED_MUSHROOM_BAG = COMMON_BUILDER.translation("config.farmersplus.storages.enable_red_mushroom_bag")
                .define("enableRedMushroomBag", true);
        ENABLE_SUGAR_CANE_BALE = COMMON_BUILDER.translation("config.farmersplus.storages.enable_sugar_cane_bale")
                .define("enableSugarCaneBale", true);
        ENABLE_SWEET_BERRY_BAG = COMMON_BUILDER.translation("config.farmersplus.storages.enable_sweet_berry_bag")
                .define("enableSweetBerryBag", true);
        ENABLE_WARPED_FUNGUS_BAG = COMMON_BUILDER.translation("config.farmersplus.storages.enable_warped_fungus_bag")
                .define("enableWarpedFungusBag", true);
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
