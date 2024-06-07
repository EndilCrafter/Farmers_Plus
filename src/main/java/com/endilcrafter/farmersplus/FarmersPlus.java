package com.endilcrafter.farmersplus;

import com.endilcrafter.farmersplus.client.PlusClientSetup;
import com.endilcrafter.farmersplus.common.PlusCommonSetup;
import com.endilcrafter.farmersplus.common.PlusConfiguration;
import com.endilcrafter.farmersplus.common.registry.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(FarmersPlus.MODID)
public class FarmersPlus {
    public static final String MODID = "farmersplus";

    public FarmersPlus() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, PlusConfiguration.COMMON_CONFIG);
        modEventBus.addListener(PlusCommonSetup::init);
        modEventBus.addListener(PlusClientSetup::init);

        PlusBlockEntities.register(modEventBus);
        PlusBlocks.register(modEventBus);
        PlusItems.register(modEventBus);
        PlusMenuTypes.register(modEventBus);
        PlusRecipeSerializers.register(modEventBus);
        PlusRecipeTypes.register(modEventBus);
        PlusTabs.register(modEventBus);
    }
}
