package com.endilcrafter.farmersplus.common.crafting.condition.storage;

import com.endilcrafter.farmersplus.FarmersPlus;
import com.endilcrafter.farmersplus.common.PlusConfiguration;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class CrimsonFungusBagEnabledCondition implements ICondition {
    private final ResourceLocation location;

    public CrimsonFungusBagEnabledCondition(ResourceLocation location) {
        this.location = location;
    }

    @Override
    public ResourceLocation getID() {
        return this.location;
    }

    @Override
    public boolean test(IContext context) {
        return PlusConfiguration.ENABLE_CRIMSON_FUNGUS_BAG.get();
    }

    public static class Serializer implements IConditionSerializer<CrimsonFungusBagEnabledCondition> {
        private final ResourceLocation location;

        public Serializer() {
            this.location = new ResourceLocation(FarmersPlus.MODID, "crimson_fungus_bag_enabled");
        }

        @Override
        public void write(JsonObject json, CrimsonFungusBagEnabledCondition value) {

        }

        @Override
        public CrimsonFungusBagEnabledCondition read(JsonObject json) {
            return new CrimsonFungusBagEnabledCondition(this.location);
        }

        @Override
        public ResourceLocation getID() {
            return this.location;
        }
    }
}
