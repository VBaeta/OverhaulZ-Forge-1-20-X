package net.vinithekidd.overhaulzmod.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.vinithekidd.overhaulzmod.OverhaulZ;
import net.vinithekidd.overhaulzmod.block.entity.ModBlockEntities;
import net.vinithekidd.overhaulzmod.block.entity.renderer.RecyclerStationBlockEntityRenderer;

@Mod.EventBusSubscriber(modid = OverhaulZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.RECYCLER_STATION_BE.get(), RecyclerStationBlockEntityRenderer::new);
    }
}