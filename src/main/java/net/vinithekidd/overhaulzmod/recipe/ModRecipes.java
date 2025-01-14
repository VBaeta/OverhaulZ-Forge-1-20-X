package net.vinithekidd.overhaulzmod.recipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.vinithekidd.overhaulzmod.OverhaulZ;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, OverhaulZ.MOD_ID);

    public static final RegistryObject<RecipeSerializer<RecyclerStationRecipe>> RECYCLER_STATION_SERIALIZER =
            SERIALIZERS.register("recycler_station", () -> RecyclerStationRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
