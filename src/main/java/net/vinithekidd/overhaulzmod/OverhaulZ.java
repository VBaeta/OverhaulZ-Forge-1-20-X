package net.vinithekidd.overhaulzmod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.vinithekidd.overhaulzmod.block.ModBlocks;
import net.vinithekidd.overhaulzmod.block.entity.ModBlockEntities;
import net.vinithekidd.overhaulzmod.datagen.loot.ModLootModifiers;
import net.vinithekidd.overhaulzmod.entity.ModEntities;
import net.vinithekidd.overhaulzmod.entity.client.LostSurvivorRenderer;
import net.vinithekidd.overhaulzmod.item.ModCreativeModeTabs;
import net.vinithekidd.overhaulzmod.item.ModItems;
import net.vinithekidd.overhaulzmod.screen.ModMenuTypes;
import net.vinithekidd.overhaulzmod.screen.RecyclerStationScreen;
import net.vinithekidd.overhaulzmod.sound.ModSounds;
import org.slf4j.Logger;

@Mod(OverhaulZ.MOD_ID)
public class OverhaulZ {
    public static final String MOD_ID = "overhaulzmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public OverhaulZ() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        ModEntities.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModSounds.register(modEventBus);
        ModMenuTypes.register(modEventBus);




        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
        }

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.LOST_SURVIVOR.get(), LostSurvivorRenderer::new);
            MenuScreens.register(ModMenuTypes.RECYCLER_STATION_MENU.get(), RecyclerStationScreen::new);

        }
    }
}
