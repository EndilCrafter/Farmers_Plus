package com.endilcrafter.farmersplus.data.loot;

import com.endilcrafter.farmersplus.common.block.CrateBlock;
import com.endilcrafter.farmersplus.common.registry.PlusBlockEntities;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.DynamicLoot;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.functions.SetContainerContents;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.endilcrafter.farmersplus.common.registry.PlusBlocks.*;

public class PlusBlockLoot extends BlockLootSubProvider {
    private final Set<Block> generatedLootTables = new HashSet<>();

    public PlusBlockLoot() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        for (RegistryObject<Block> blockRegistryObject : Arrays.asList(
                APPLE_BAG,
                BROWN_MUSHROOM_BAG,
                COCOA_BEAN_BAG,
                CRIMSON_FUNGUS_BAG,
                EGG_BOX,
                GLOW_BERRY_BAG,
                GOLDEN_APPLE_BAG,
                GOLDEN_CARROT_CRATE,
                KELP_BLOCK,
                RED_MUSHROOM_BAG,
                SUGAR_CANE_BALE,
                SWEET_BERRY_BAG,
                WARPED_FUNGUS_BAG,
                CANVAS_BLOCK,
                FRAMED_CANVAS_BLOCK,
                CANVAS_CHEST,
                DRYING_RACK,
                MILLSTONE,
                TRAY
        )) {
            this.dropSelf(blockRegistryObject.get());
        }

        this.add(CRATE.get(), (block -> createCrateDrop(CRATE.get())));
    }

    protected LootTable.Builder createCrateDrop(Block pBlock) {
        return LootTable.lootTable().withPool(this.applyExplosionCondition(pBlock, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(pBlock).apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY)).apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY).copy("Lock", "BlockEntityTag.Lock").copy("LootTable", "BlockEntityTag.LootTable").copy("LootTableSeed", "BlockEntityTag.LootTableSeed")).apply(SetContainerContents.setContents(PlusBlockEntities.CRATE.get()).withEntry(DynamicLoot.dynamicEntry(CrateBlock.CONTENTS))))));
    }

    protected void add(Block block, LootTable.Builder builder) {
        this.generatedLootTables.add(block);
        this.map.put(block.getLootTable(), builder);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return generatedLootTables;
    }
}
