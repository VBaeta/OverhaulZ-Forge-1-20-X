package net.vinithekidd.overhaulzmod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.vinithekidd.overhaulzmod.OverhaulZ;
import net.vinithekidd.overhaulzmod.entity.custom.LostSurvivorEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, OverhaulZ.MOD_ID);

    public static final RegistryObject<EntityType<LostSurvivorEntity>> LOST_SURVIVOR =
            ENTITY_TYPES.register("lost_survivor", () -> EntityType.Builder.of(LostSurvivorEntity::new, MobCategory.CREATURE)
                    .sized(1f, 2f).build("lost_survivor"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}