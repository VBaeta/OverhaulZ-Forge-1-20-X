package net.vinithekidd.overhaulzmod.item;

import net.minecraft.world.item.CreativeModeTabs;
import net.vinithekidd.overhaulzmod.OverhaulZ;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.vinithekidd.overhaulzmod.block.ModBlocks;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OverhaulZ.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MATERIALS_TAB = CREATIVE_MODE_TABS.register("materials_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ALUMINUM_INGOT.get()))
                    .title(Component.translatable("creativetab.materials_tab"))
                    .displayItems((pParameters, pOutput) -> {

                        //Aluminum Items
                        pOutput.accept(ModBlocks.ALUMINUM_BLOCK.get());
                        pOutput.accept(ModItems.ALUMINUM_INGOT.get());
                        pOutput.accept(ModItems.ALUMINUM_FOIL.get());
                        pOutput.accept(ModItems.ALUMINUM_GEAR.get());
                        pOutput.accept(ModItems.ALUMINUM_SCRAPS.get());
                        pOutput.accept(ModItems.ALUMINUM_TUBE.get());
                        pOutput.accept(ModItems.ALUMINUM_WIRING.get());
                        pOutput.accept(ModItems.BIKE_PARTS.get());
                        pOutput.accept(ModItems.PLIERS.get());
                        pOutput.accept(ModItems.TWEEZERS.get());
                        pOutput.accept(ModItems.RULER.get());
                        pOutput.accept(ModItems.STAPLER.get());
                        pOutput.accept(ModItems.CAN_OPENER.get());
                        pOutput.accept(ModItems.RUSTY_CAN.get());

                        //Materials and Containers
                        pOutput.accept(ModItems.NAILS.get());
                        pOutput.accept(ModItems.SCREWS.get());
                        pOutput.accept(ModItems.BOX_CONTAINER.get());

                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> FOODS_TAB = CREATIVE_MODE_TABS.register("foods_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TOMATO.get()))
                    .title(Component.translatable("creativetab.foods_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.TOMATO.get());
                        pOutput.accept(ModItems.TOMATO_SEEDS.get());
                        pOutput.accept(ModItems.CORN.get());
                        pOutput.accept(ModItems.CORN_SEEDS.get());

                    })
                    .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                    .build());

    public static final RegistryObject<CreativeModeTab> ENTITIES_TAB = CREATIVE_MODE_TABS.register("entities_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.LOST_SURVIVOR_SPAWN_EGG.get()))
                    .title(Component.translatable("creativetab.entities_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.LOST_SURVIVOR_SPAWN_EGG.get());
                    })

                    .build());
    public static final RegistryObject<CreativeModeTab> STATIONS_TAB = CREATIVE_MODE_TABS.register("stations_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.RECYCLER_STATION.get()))
                    .title(Component.translatable("creativetab.stations_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.RECYCLER_STATION.get());
                    })

                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}