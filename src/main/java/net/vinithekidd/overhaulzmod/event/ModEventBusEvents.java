package net.vinithekidd.overhaulzmod.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.vinithekidd.overhaulzmod.OverhaulZ;
import net.vinithekidd.overhaulzmod.entity.ModEntities;
import net.vinithekidd.overhaulzmod.entity.custom.LostSurvivorEntity;

@Mod.EventBusSubscriber(modid = OverhaulZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.LOST_SURVIVOR.get(), LostSurvivorEntity.createAttributes().build());
    }
}