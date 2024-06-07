package com.endilcrafter.farmersplus.common.crafting.condition.storage;

import com.endilcrafter.farmersplus.FarmersPlus;
import com.endilcrafter.farmersplus.common.PlusConfiguration;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class GoldenAppleBagEnabledCondition implements ICondition {
    private final ResourceLocation location;

    public GoldenAppleBagEnabledCondition(ResourceLocation location) {
        this.location = location;
    }

    @Override
    public ResourceLocation getID() {
        return this.location;
    }

    @Override
    public boolean test(IContext context) {
        return PlusConfiguration.ENABLE_GOLDEN_APPLE_BAG.get();
    }

    public static class Serializer implements IConditionSerializer<GoldenAppleBagEnabledCondition> {
        private final ResourceLocation location;

        public Serializer() {
            this.location = new ResourceLocation(FarmersPlus.MODID, "golden_apple_bag_enabled");
        }

        @Override
        public void write(JsonObject json, GoldenAppleBagEnabledCondition value) {

        }

        @Override
        public GoldenAppleBagEnabledCondition read(JsonObject json) {
            return new GoldenAppleBagEnabledCondition(this.location);
        }

        @Override
        public ResourceLocation getID() {
            return this.location;
        }
    }
}
