package net.vinithekidd.overhaulzmod;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.vinithekidd.overhaulzmod.entity.ModEntities;
import net.vinithekidd.overhaulzmod.item.ModItems;

@Mod.EventBusSubscriber(modid = "overhaulzmod", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class OverhaulZClient {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ItemProperties.register(ModItems.BOX_CONTAINER.get(), new ResourceLocation("stored_item"), (stack, level, entity, id) -> {
            if (stack.hasTag() && stack.getTag().contains("StoredItem")) {
                String storedItem = stack.getTag().getString("StoredItem");
                return switch (storedItem) {
                    case "overhaulzmod:nails" -> 1;
                    case "overhaulzmod:screws" -> 2;
                    default -> 0;
                };
            }
            return 0;
        });
    }
}