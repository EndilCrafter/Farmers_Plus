package com.endilcrafter.farmersplus.common.registry;

import com.endilcrafter.farmersplus.common.advancement.MillstoneTrigger;
import net.minecraft.advancements.CriteriaTriggers;

public class PlusAdvancements {
    public static MillstoneTrigger MILLSTONE = new MillstoneTrigger();

    public static void register() {
        CriteriaTriggers.register(MILLSTONE);
    }
}
