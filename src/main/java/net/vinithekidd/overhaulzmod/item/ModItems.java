package net.vinithekidd.overhaulzmod.item;


import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.vinithekidd.overhaulzmod.OverhaulZ;
import net.vinithekidd.overhaulzmod.block.ModBlocks;
import net.vinithekidd.overhaulzmod.entity.ModEntities;
import net.vinithekidd.overhaulzmod.item.custom.ContainerItem;
import net.vinithekidd.overhaulzmod.util.ModTags;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, OverhaulZ.MOD_ID);

    public static final RegistryObject<Item> LOST_SURVIVOR_SPAWN_EGG = ITEMS.register("lost_survivor_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.LOST_SURVIVOR, 0x9a927f,0x354654, new Item.Properties()));

    public static final RegistryObject<Item> BAUXITE_SHARD = ITEMS.register("bauxite_shard",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ALUMINUM_INGOT = ITEMS.register("aluminum_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ALUMINUM_FOIL = ITEMS.register("aluminum_foil",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ALUMINUM_GEAR = ITEMS.register("aluminum_gear",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ALUMINUM_SCRAPS = ITEMS.register("aluminum_scraps",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ALUMINUM_TUBE = ITEMS.register("aluminum_tube",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ALUMINUM_WIRING = ITEMS.register("aluminum_wiring",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BIKE_PARTS = ITEMS.register("bike_parts",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLIERS = ITEMS.register("pliers",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TWEEZERS = ITEMS.register("tweezers",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RULER = ITEMS.register("ruler",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAPLER = ITEMS.register("stapler",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAN_OPENER = ITEMS.register("can_opener",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NAILS = ITEMS.register("nails",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCREWS = ITEMS.register("screws",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RUSTY_CAN = ITEMS.register("rusty_can",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TOMATO = ITEMS.register("tomato",
            () -> new Item(new Item.Properties().food(ModFoods.TOMATO)));

    public static final RegistryObject<Item> TOMATO_SEEDS = ITEMS.register("tomato_seeds",
            () -> new ItemNameBlockItem(ModBlocks.TOMATO_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> CORN = ITEMS.register("corn",
            () -> new Item(new Item.Properties().food(ModFoods.CORN)));

    public static final RegistryObject<Item> CORN_SEEDS = ITEMS.register("corn_seeds",
            () -> new ItemNameBlockItem(ModBlocks.CORN_CROP.get(), new Item.Properties()));


    public static final RegistryObject<Item> BOX_CONTAINER = ITEMS.register("box_container",
            () -> new ContainerItem(
                    new Item.Properties().stacksTo(1),
                    ModTags.Items.TINY_MATERIALS,128));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
