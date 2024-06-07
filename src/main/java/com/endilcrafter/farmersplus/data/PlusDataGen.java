package com.endilcrafter.farmersplus.data;

import com.endilcrafter.farmersplus.FarmersPlus;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = FarmersPlus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PlusDataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();
        ExistingFileHelper helper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();

        gen.addProvider(event.includeServer(), PlusLootTables.create(output));
        gen.addProvider(event.includeClient(), new PlusBlockStates(output, helper));
        PlusBlockTags blockTags = gen.addProvider(event.includeServer(), new PlusBlockTags(output, provider, helper));
        gen.addProvider(event.includeClient(), new PlusItemModels(output, helper));
        gen.addProvider(event.includeServer(), new PlusItemTags(output, provider, blockTags.contentsGetter(), helper));
        gen.addProvider(event.includeServer(), new PlusRecipes(output));
    }
}
