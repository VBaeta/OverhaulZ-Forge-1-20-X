package net.vinithekidd.overhaulzmod.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.vinithekidd.overhaulzmod.OverhaulZ;
import net.vinithekidd.overhaulzmod.entity.client.LostSurvivorModel;
import net.vinithekidd.overhaulzmod.entity.client.ModModelLayers;

@Mod.EventBusSubscriber(modid = OverhaulZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.LOST_SURVIVOR_LAYER, LostSurvivorModel::createBodyLayer);
    }
}