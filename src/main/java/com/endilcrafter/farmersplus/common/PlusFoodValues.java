package com.endilcrafter.farmersplus.common;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class PlusFoodValues {

    public static final FoodProperties APPLE_SLICE = (new FoodProperties.Builder().nutrition(1).saturationMod(0.3f)).fast().build();

    public static final FoodProperties GOLDEN_APPLE_SLICE = (new FoodProperties.Builder().nutrition(1).saturationMod(1.2f))
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 300, 0), 1.0F).alwaysEat().fast().build();

    public static final FoodProperties BOILED_EGG = (new FoodProperties.Builder().nutrition(2).saturationMod(0.4f)).alwaysEat().build();

    public static final FoodProperties DRIED_APPLE_SLICE = (new FoodProperties.Builder().nutrition(2).saturationMod(0.3f)).alwaysEat().fast().build();

    public static final FoodProperties DRIED_GOLDEN_APPLE_SLICE = (new FoodProperties.Builder().nutrition(1).saturationMod(2.4f))
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 600, 0), 1.0F).alwaysEat().fast().build();

    public static final FoodProperties DRIED_BEEF = (new FoodProperties.Builder().nutrition(4).saturationMod(0.8f)).alwaysEat().meat().build();

    public static final FoodProperties DRIED_CHICKEN = (new FoodProperties.Builder().nutrition(3).saturationMod(0.6f)).alwaysEat().meat().build();

    public static final FoodProperties DRIED_COD_SLICE = (new FoodProperties.Builder().nutrition(2).saturationMod(0.5f)).alwaysEat().build();

    public static final FoodProperties DRIED_MUTTON = (new FoodProperties.Builder().nutrition(3).saturationMod(0.8f)).alwaysEat().meat().build();

    public static final FoodProperties DRIED_PORKCHOP = (new FoodProperties.Builder().nutrition(4).saturationMod(0.8f)).alwaysEat().meat().build();

    public static final FoodProperties DRIED_RABBIT = (new FoodProperties.Builder().nutrition(3).saturationMod(0.6f)).alwaysEat().meat().build();

    public static final FoodProperties DRIED_SALMON_SLICE = (new FoodProperties.Builder().nutrition(2).saturationMod(0.8f)).alwaysEat().meat().build();

    public static final FoodProperties MINCED_CHICKEN = new FoodProperties.Builder().nutrition(1).saturationMod(0.3F)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F).meat().fast().build();

    public static final FoodProperties MINCED_PORK = new FoodProperties.Builder().nutrition(2).saturationMod(0.3F).meat().fast().build();

    public static final FoodProperties COOKED_MINCED_CHICKEN = new FoodProperties.Builder().nutrition(3).saturationMod(0.6F).meat().fast().build();

    public static final FoodProperties PORK_PATTY = new FoodProperties.Builder().nutrition(4).saturationMod(0.8F).meat().fast().build();

    public static final FoodProperties CHICKEN_NUGGET = new FoodProperties.Builder().nutrition(8).saturationMod(0.6F).meat().build();

    public static final FoodProperties KOROKKE = new FoodProperties.Builder().nutrition(7).saturationMod(0.5F).build();

    public static final FoodProperties FRIED_HOLY_BASIL = new FoodProperties.Builder().nutrition(12).saturationMod(0.8F)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 3600, 0), 1.0F).build();

    public static final FoodProperties SALISBURY_STEAK = new FoodProperties.Builder().nutrition(12).saturationMod(0.8F)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 3600, 0), 1.0F).build();

    public static final FoodProperties TOMATO_JUICE = new FoodProperties.Builder()
            .alwaysEat().effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 1200, 0), 1.0F).build();
}
