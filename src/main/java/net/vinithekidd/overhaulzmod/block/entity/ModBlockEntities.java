package net.vinithekidd.overhaulzmod.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.vinithekidd.overhaulzmod.OverhaulZ;
import net.vinithekidd.overhaulzmod.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, OverhaulZ.MOD_ID);

    public static final RegistryObject<BlockEntityType<RecyclerStationBlockEntity>> RECYCLER_STATION_BE = BLOCK_ENTITIES.register("recycler_station_be", () ->
            BlockEntityType.Builder.of(RecyclerStationBlockEntity::new, ModBlocks.RECYCLER_STATION.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
