package com.endilcrafter.farmersplus.common.crafting.condition.storage;

import com.endilcrafter.farmersplus.FarmersPlus;
import com.endilcrafter.farmersplus.common.PlusConfiguration;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class CocoaBeanBagEnabledCondition implements ICondition {
    private final ResourceLocation location;

    public CocoaBeanBagEnabledCondition(ResourceLocation location) {
        this.location = location;
    }

    @Override
    public ResourceLocation getID() {
        return this.location;
    }

    @Override
    public boolean test(IContext context) {
        return PlusConfiguration.ENABLE_COCOA_BEAN_BAG.get();
    }

    public static class Serializer implements IConditionSerializer<CocoaBeanBagEnabledCondition> {
        private final ResourceLocation location;

        public Serializer() {
            this.location = new ResourceLocation(FarmersPlus.MODID, "cocoa_bean_bag_enabled");
        }

        @Override
        public void write(JsonObject json, CocoaBeanBagEnabledCondition value) {

        }

        @Override
        public CocoaBeanBagEnabledCondition read(JsonObject json) {
            return new CocoaBeanBagEnabledCondition(this.location);
        }

        @Override
        public ResourceLocation getID() {
            return this.location;
        }
    }
}
