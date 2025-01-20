package net.vinithekidd.overhaulzmod.entity;

import net.minecraft.resources.ResourceLocation;
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

    // Registro seguro da entidade LostSurvivor
    public static final RegistryObject<EntityType<LostSurvivorEntity>> LOST_SURVIVOR = ENTITY_TYPES.register(
            "lost_survivor", // Nome interno da entidade
            () -> EntityType.Builder.of(LostSurvivorEntity::new, MobCategory.CREATURE)
                    .sized(0.6F, 1.8F) // Dimensões da entidade
                    .build(new ResourceLocation(OverhaulZ.MOD_ID, "lost_survivor").toString())
    );

    // Metodo para registrar o evento
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}