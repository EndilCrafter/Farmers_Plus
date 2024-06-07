package com.endilcrafter.farmersplus.common.crafting.condition.storage;

import com.endilcrafter.farmersplus.FarmersPlus;
import com.endilcrafter.farmersplus.common.PlusConfiguration;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class KelpBlockEnabledCondition implements ICondition {
    private final ResourceLocation location;

    public KelpBlockEnabledCondition(ResourceLocation location) {
        this.location = location;
    }

    @Override
    public ResourceLocation getID() {
        return this.location;
    }

    @Override
    public boolean test(IContext context) {
        return PlusConfiguration.ENABLE_KELP_BLOCK.get();
    }

    public static class Serializer implements IConditionSerializer<KelpBlockEnabledCondition> {
        private final ResourceLocation location;

        public Serializer() {
            this.location = new ResourceLocation(FarmersPlus.MODID, "kelp_block_enabled");
        }

        @Override
        public void write(JsonObject json, KelpBlockEnabledCondition value) {

        }

        @Override
        public KelpBlockEnabledCondition read(JsonObject json) {
            return new KelpBlockEnabledCondition(this.location);
        }

        @Override
        public ResourceLocation getID() {
            return this.location;
        }
    }
}
