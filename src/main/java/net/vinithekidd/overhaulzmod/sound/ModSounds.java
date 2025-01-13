package net.vinithekidd.overhaulzmod.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.vinithekidd.overhaulzmod.OverhaulZ;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, OverhaulZ.MOD_ID);

    public static final RegistryObject<SoundEvent> LOST_SURVIVOR_AMBIENT = registerSoundEvents("lost_survivor_ambient");
    public static final RegistryObject<SoundEvent> LOST_SURVIVOR_HURT = registerSoundEvents("lost_survivor_hurt");
    public static final RegistryObject<SoundEvent> LOST_SURVIVOR_DEATH = registerSoundEvents("lost_survivor_death");


    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(OverhaulZ.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}