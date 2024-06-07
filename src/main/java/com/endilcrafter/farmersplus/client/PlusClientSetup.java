package com.endilcrafter.farmersplus.client;

import com.endilcrafter.farmersplus.client.gui.screens.inventory.CrateScreen;
import com.endilcrafter.farmersplus.common.registry.PlusMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class PlusClientSetup {
    public static void init(final FMLClientSetupEvent event) {
        MenuScreens.register(PlusMenuTypes.CRATE.get(), CrateScreen::new);
    }
}
