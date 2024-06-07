package com.endilcrafter.farmersplus.common.registry;

import com.endilcrafter.farmersplus.FarmersPlus;
import com.endilcrafter.farmersplus.common.block.entity.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PlusBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, FarmersPlus.MODID);

    public static void register(IEventBus eventBus) {
        TILES.register(eventBus);
    }

    public static final RegistryObject<BlockEntityType<CrateBlockEntity>> CRATE = TILES.register("crate",
            () -> BlockEntityType.Builder.of(CrateBlockEntity::new, PlusBlocks.CRATE.get()).build(null));
    public static final RegistryObject<BlockEntityType<CanvasChestBlockEntity>> CANVAS_CHEST = TILES.register("canvas_chest",
            () -> BlockEntityType.Builder.of(CanvasChestBlockEntity::new, PlusBlocks.CANVAS_CHEST.get()).build(null));
    public static final RegistryObject<BlockEntityType<DryingRackBlockEntity>> DRYING_RACK = TILES.register("drying_rack",
            () -> BlockEntityType.Builder.of(DryingRackBlockEntity::new, PlusBlocks.DRYING_RACK.get()).build(null));
    public static final RegistryObject<BlockEntityType<MillstoneBlockEntity>> MILLSTONE = TILES.register("millstone",
            () -> BlockEntityType.Builder.of(MillstoneBlockEntity::new, PlusBlocks.MILLSTONE.get()).build(null));
    public static final RegistryObject<BlockEntityType<TrayBlockEntity>> TRAY = TILES.register("tray",
            () -> BlockEntityType.Builder.of(TrayBlockEntity::new, PlusBlocks.TRAY.get()).build(null));

}
