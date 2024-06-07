package com.endilcrafter.farmersplus.common.crafting.condition.storage;

import com.endilcrafter.farmersplus.FarmersPlus;
import com.endilcrafter.farmersplus.common.PlusConfiguration;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class BrownMushroomBagEnabledCondition implements ICondition {
    private final ResourceLocation location;

    public BrownMushroomBagEnabledCondition(ResourceLocation location) {
        this.location = location;
    }

    @Override
    public ResourceLocation getID() {
        return this.location;
    }

    @Override
    public boolean test(IContext context) {
        return PlusConfiguration.ENABLE_BROWN_MUSHROOM_BAG.get();
    }

    public static class Serializer implements IConditionSerializer<BrownMushroomBagEnabledCondition> {
        private final ResourceLocation location;

        public Serializer() {
            this.location = new ResourceLocation(FarmersPlus.MODID, "brown_mushroom_bag_enabled");
        }

        @Override
        public void write(JsonObject json, BrownMushroomBagEnabledCondition value) {

        }

        @Override
        public BrownMushroomBagEnabledCondition read(JsonObject json) {
            return new BrownMushroomBagEnabledCondition(this.location);
        }

        @Override
        public ResourceLocation getID() {
            return this.location;
        }
    }
}
