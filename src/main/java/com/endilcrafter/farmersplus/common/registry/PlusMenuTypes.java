package com.endilcrafter.farmersplus.common.registry;

import com.endilcrafter.farmersplus.FarmersPlus;
import com.endilcrafter.farmersplus.common.world.inventory.CrateMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PlusMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, FarmersPlus.MODID);

    public static final RegistryObject<MenuType<CrateMenu>> CRATE = MENUS.register("crate",
            () -> IForgeMenuType.create((containerId, inventory, friendlyByteBuf) -> new CrateMenu(containerId, inventory)));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
