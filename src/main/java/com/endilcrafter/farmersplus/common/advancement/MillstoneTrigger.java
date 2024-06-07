package com.endilcrafter.farmersplus.common.advancement;

import com.endilcrafter.farmersplus.FarmersPlus;
import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class MillstoneTrigger extends SimpleCriterionTrigger<MillstoneTrigger.TriggerInstance> {
    private static final ResourceLocation ID = new ResourceLocation(FarmersPlus.MODID, "use_millstone");

    @Override
    protected TriggerInstance createInstance(JsonObject jsonObject, ContextAwarePredicate contextAwarePredicate, DeserializationContext deserializationContext) {
        return new TriggerInstance(contextAwarePredicate);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, MillstoneTrigger.TriggerInstance::test);
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        public TriggerInstance(ContextAwarePredicate player) {
            super(MillstoneTrigger.ID, player);
        }

        public static MillstoneTrigger.TriggerInstance simple() {
            return new MillstoneTrigger.TriggerInstance(ContextAwarePredicate.ANY);
        }

        public boolean test() {
            return true;
        }
    }
}
