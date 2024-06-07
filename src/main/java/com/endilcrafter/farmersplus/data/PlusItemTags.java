package com.endilcrafter.farmersplus.data;

import com.endilcrafter.farmersplus.FarmersPlus;
import com.endilcrafter.farmersplus.common.tag.PlusCompatTags;
import com.endilcrafter.farmersplus.common.tag.PlusForgeTags;
import com.endilcrafter.farmersplus.common.tag.PlusTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ForgeTags;

import java.util.concurrent.CompletableFuture;

import static com.endilcrafter.farmersplus.common.registry.PlusItems.*;

public class PlusItemTags extends ItemTagsProvider {
    public PlusItemTags(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, FarmersPlus.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(PlusTags.CRATE_CAN_CONTAIN).addTag(Tags.Items.CROPS);
        this.tag(PlusTags.TRAY_CAN_SERVE).addTag(Tags.Items.TOOLS);

        this.tag(PlusCompatTags.DIET_FRUITS)
                .add(APPLE_SLICE.get(), GOLDEN_APPLE_SLICE.get(), DRIED_APPLE_SLICE.get(), DRIED_GOLDEN_APPLE_SLICE.get());
        this.tag(PlusCompatTags.DIET_GRAINS)
                .add(BREAD_CRUMB.get(), RICE_FLOUR.get(), WHEAT_FLOUR.get(), CHICKEN_NUGGET.get(), KOROKKE.get(), FRIED_HOLY_BASIL.get());
        this.tag(PlusCompatTags.DIET_PROTEINS)
                .add(
                        DRIED_BEEF.get(),
                        DRIED_CHICKEN.get(),
                        DRIED_COD_SLICE.get(),
                        DRIED_MUTTON.get(),
                        DRIED_PORKCHOP.get(),
                        DRIED_RABBIT.get(),
                        DRIED_SALMON_SLICE.get(),
                        BOILED_EGG.get(),
                        MINCED_CHICKEN.get(),
                        MINCED_PORK.get(),
                        COOKED_MINCED_CHICKEN.get(),
                        PORK_PATTY.get(),
                        CHICKEN_NUGGET.get(),
                        KOROKKE.get(),
                        FRIED_HOLY_BASIL.get(),
                        SALISBURY_STEAK.get()
                );
        this.tag(PlusCompatTags.DIET_VEGETABLES)
                .add(CHICKEN_NUGGET.get(), KOROKKE.get(), FRIED_HOLY_BASIL.get(), SALISBURY_STEAK.get(), TOMATO_JUICE.get());

        this.tag(Tags.Items.CHESTS).add(CANVAS_CHEST.get());
        this.tag(Tags.Items.DUSTS).addTag(PlusForgeTags.DUSTS_RICE).addTag(PlusForgeTags.DUSTS_WHEAT);
        this.tag(PlusForgeTags.DUSTS_RICE).add(RICE_FLOUR.get());
        this.tag(PlusForgeTags.DUSTS_WHEAT).add(WHEAT_FLOUR.get());
        this.tag(ForgeTags.COOKED_CHICKEN).add(COOKED_MINCED_CHICKEN.get());
        this.tag(ForgeTags.COOKED_PORK).add(PORK_PATTY.get());
        this.tag(ForgeTags.RAW_CHICKEN).add(MINCED_CHICKEN.get());
        this.tag(ForgeTags.RAW_PORK).add(MINCED_PORK.get());
        this.tag(PlusForgeTags.FLOUR).addTag(PlusForgeTags.FLOUR_RICE).addTag(PlusForgeTags.FLOUR_WHEAT);
        this.tag(PlusForgeTags.FLOUR_RICE).add(RICE_FLOUR.get());
        this.tag(PlusForgeTags.FLOUR_WHEAT).add(WHEAT_FLOUR.get());
        this.tag(PlusForgeTags.LARD).addTag(PlusForgeTags.LARD_BUCKET).addTag(PlusForgeTags.LARD_BOTTLE);
        this.tag(PlusForgeTags.LARD_BUCKET).add(LARD_BUCKET.get());
        this.tag(PlusForgeTags.LARD_BOTTLE).add(LARD_BOTTLE.get());
        this.tag(PlusTags.RAW_MINCED_MEATS).add(ModItems.MINCED_BEEF.get(), MINCED_CHICKEN.get(), MINCED_PORK.get());
    }
}
