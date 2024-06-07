package com.endilcrafter.farmersplus.common.registry;

import com.endilcrafter.farmersplus.FarmersPlus;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class PlusTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FarmersPlus.MODID);

    public static final RegistryObject<CreativeModeTab> FU_TAB = TABS.register(FarmersPlus.MODID,
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.farmersplus"))
                    .icon(() -> new ItemStack(PlusItems.CRATE.get()))
                    .displayItems((parameters, output) -> PlusItems.CREATIVE_TAB_ITEMS.forEach((item) -> output.accept(item.get())))
                    .build());

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }
}
