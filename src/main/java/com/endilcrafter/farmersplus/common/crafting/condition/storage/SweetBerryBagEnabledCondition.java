package com.endilcrafter.farmersplus.common.crafting.condition.storage;

import com.endilcrafter.farmersplus.FarmersPlus;
import com.endilcrafter.farmersplus.common.PlusConfiguration;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class SweetBerryBagEnabledCondition implements ICondition {
    private final ResourceLocation location;

    public SweetBerryBagEnabledCondition(ResourceLocation location) {
        this.location = location;
    }

    @Override
    public ResourceLocation getID() {
        return this.location;
    }

    @Override
    public boolean test(IContext context) {
        return PlusConfiguration.ENABLE_SWEET_BERRY_BAG.get();
    }

    public static class Serializer implements IConditionSerializer<SweetBerryBagEnabledCondition> {
        private final ResourceLocation location;

        public Serializer() {
            this.location = new ResourceLocation(FarmersPlus.MODID, "sweet_berry_bag_enabled");
        }

        @Override
        public void write(JsonObject json, SweetBerryBagEnabledCondition value) {

        }

        @Override
        public SweetBerryBagEnabledCondition read(JsonObject json) {
            return new SweetBerryBagEnabledCondition(this.location);
        }

        @Override
        public ResourceLocation getID() {
            return this.location;
        }
    }
}
