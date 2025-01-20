package net.vinithekidd.overhaulzmod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
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
import net.vinithekidd.overhaulzmod.keybinding.KeyBindings;
import net.vinithekidd.overhaulzmod.recipe.ModRecipes;
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

        ModEntities.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModSounds.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);
        MinecraftForge.EVENT_BUS.register(this);
    }

    // Configuração geral chamada na fase common setup
    public void commonSetup(final FMLCommonSetupEvent event) {
        OverhaulZ.LOGGER.info("Configuração comum concluída para " + OverhaulZ.MOD_ID);
    }

    // Adiciona itens às abas criativas personalizadas
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            // Adicione os itens ou blocos aqui
        }
    }

    // Evento chamado ao iniciar o servidor
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        OverhaulZ.LOGGER.info("Servidor inicializando!");
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Configuração de renderizadores de entidades e menus
            EntityRenderers.register(ModEntities.LOST_SURVIVOR.get(), LostSurvivorRenderer::new);
            MenuScreens.register(ModMenuTypes.RECYCLER_STATION_MENU.get(), RecyclerStationScreen::new);
        }
    }

    private void registerKeyBindings(RegisterKeyMappingsEvent event) {
        KeyBindings.register(event); // Registra as teclas personalizadas
    }
}