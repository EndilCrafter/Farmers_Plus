package com.endilcrafter.farmersplus.common;

import com.endilcrafter.farmersplus.common.crafting.condition.VanillaStorageEnabledCondition;
import com.endilcrafter.farmersplus.common.crafting.condition.storage.*;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class PlusCommonSetup {
    public static void init(final FMLCommonSetupEvent event) {
        CraftingHelper.register(new VanillaStorageEnabledCondition.Serializer());
        CraftingHelper.register(new AppleBagEnabledCondition.Serializer());
        CraftingHelper.register(new BrownMushroomBagEnabledCondition.Serializer());
        CraftingHelper.register(new CocoaBeanBagEnabledCondition.Serializer());
        CraftingHelper.register(new CrimsonFungusBagEnabledCondition.Serializer());
        CraftingHelper.register(new EggBoxEnabledCondition.Serializer());
        CraftingHelper.register(new GlowBerryBagEnabledCondition.Serializer());
        CraftingHelper.register(new GoldenAppleBagEnabledCondition.Serializer());
        CraftingHelper.register(new GoldenCarrotCrateEnabledCondition.Serializer());
        CraftingHelper.register(new KelpBlockEnabledCondition.Serializer());
        CraftingHelper.register(new RedMushroomBagEnabledCondition.Serializer());
        CraftingHelper.register(new SugarCaneBaleEnabledCondition.Serializer());
        CraftingHelper.register(new SweetBerryBagEnabledCondition.Serializer());
        CraftingHelper.register(new WarpedFungusBagEnabledCondition.Serializer());
    }
}
