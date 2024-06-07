package com.endilcrafter.farmersplus.common.crafting.condition.storage;

import com.endilcrafter.farmersplus.FarmersPlus;
import com.endilcrafter.farmersplus.common.PlusConfiguration;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class SugarCaneBaleEnabledCondition implements ICondition {
    private final ResourceLocation location;

    public SugarCaneBaleEnabledCondition(ResourceLocation location) {
        this.location = location;
    }

    @Override
    public ResourceLocation getID() {
        return this.location;
    }

    @Override
    public boolean test(IContext context) {
        return PlusConfiguration.ENABLE_SUGAR_CANE_BALE.get();
    }

    public static class Serializer implements IConditionSerializer<SugarCaneBaleEnabledCondition> {
        private final ResourceLocation location;

        public Serializer() {
            this.location = new ResourceLocation(FarmersPlus.MODID, "sugar_cane_bale_enabled");
        }

        @Override
        public void write(JsonObject json, SugarCaneBaleEnabledCondition value) {

        }

        @Override
        public SugarCaneBaleEnabledCondition read(JsonObject json) {
            return new SugarCaneBaleEnabledCondition(this.location);
        }

        @Override
        public ResourceLocation getID() {
            return this.location;
        }
    }
}
