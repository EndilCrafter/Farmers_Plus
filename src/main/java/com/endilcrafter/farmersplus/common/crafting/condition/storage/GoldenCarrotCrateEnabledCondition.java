package com.endilcrafter.farmersplus.common.crafting.condition.storage;

import com.endilcrafter.farmersplus.FarmersPlus;
import com.endilcrafter.farmersplus.common.PlusConfiguration;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class GoldenCarrotCrateEnabledCondition implements ICondition {
    private final ResourceLocation location;

    public GoldenCarrotCrateEnabledCondition(ResourceLocation location) {
        this.location = location;
    }

    @Override
    public ResourceLocation getID() {
        return this.location;
    }

    @Override
    public boolean test(IContext context) {
        return PlusConfiguration.ENABLE_GOLDEN_CARROT_CRATE.get();
    }

    public static class Serializer implements IConditionSerializer<GoldenCarrotCrateEnabledCondition> {
        private final ResourceLocation location;

        public Serializer() {
            this.location = new ResourceLocation(FarmersPlus.MODID, "golden_carrot_crate_enabled");
        }

        @Override
        public void write(JsonObject json, GoldenCarrotCrateEnabledCondition value) {

        }

        @Override
        public GoldenCarrotCrateEnabledCondition read(JsonObject json) {
            return new GoldenCarrotCrateEnabledCondition(this.location);
        }

        @Override
        public ResourceLocation getID() {
            return this.location;
        }
    }
}
