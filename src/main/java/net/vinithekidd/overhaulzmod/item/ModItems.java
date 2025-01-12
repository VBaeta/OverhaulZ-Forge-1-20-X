package net.vinithekidd.overhaulzmod.item;


import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.vinithekidd.overhaulzmod.OverhaulZ;
import net.vinithekidd.overhaulzmod.item.custom.BoxContainerItem;
import net.vinithekidd.overhaulzmod.util.ModTags;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, OverhaulZ.MOD_ID);

    public static final RegistryObject<Item> ALUMINUM_INGOT = ITEMS.register("aluminum_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NAILS = ITEMS.register("nails",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TOMATO = ITEMS.register("tomato",
            () -> new Item(new Item.Properties().food(ModFoods.TOMATO)));


    public static final RegistryObject<Item> BOX_CONTAINER = ITEMS.register("box_container",
            () -> new BoxContainerItem(
                    new Item.Properties().stacksTo(1),
                    ModTags.Items.TINY_MATERIALS,128));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
