package com.endilcrafter.farmersplus.client.event;

import com.endilcrafter.farmersplus.FarmersPlus;
import com.endilcrafter.farmersplus.client.renderer.blockentity.CanvasChestRenderer;
import com.endilcrafter.farmersplus.client.renderer.blockentity.DryingRackRenderer;
import com.endilcrafter.farmersplus.client.renderer.blockentity.TrayRenderer;
import com.endilcrafter.farmersplus.common.registry.PlusBlockEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FarmersPlus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class PlusClientSetupEvents {
    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(PlusBlockEntities.CANVAS_CHEST.get(), CanvasChestRenderer::new);
        event.registerBlockEntityRenderer(PlusBlockEntities.DRYING_RACK.get(), DryingRackRenderer::new);
        event.registerBlockEntityRenderer(PlusBlockEntities.TRAY.get(), TrayRenderer::new);
    }


}
