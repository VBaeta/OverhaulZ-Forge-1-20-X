package net.vinithekidd.overhaulzmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.vinithekidd.overhaulzmod.OverhaulZ;
import net.vinithekidd.overhaulzmod.item.ModItems;

import java.util.Map;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, OverhaulZ.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.ALUMINUM_INGOT);
        simpleItem(ModItems.NAILS);
        simpleItem(ModItems.SCREWS);
        simpleItem(ModItems.RUSTY_CAN);
        simpleItem(ModItems.TOMATO);
        simpleItem(ModItems.TOMATO_SEEDS);
        simpleItem(ModItems.CORN);
        simpleItem(ModItems.CORN_SEEDS);

        containerVariationItem("box_of_nails");
        containerVariationItem("box_of_screws");

        containerItemWithOverrides(ModItems.BOX_CONTAINER, Map.of(
                1, "box_of_nails",
                2, "box_of_screws"
        ));

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(OverhaulZ.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder containerVariationItem(String name) {
        return withExistingParent(name, "item/generated")
                .texture("layer0", new ResourceLocation(OverhaulZ.MOD_ID, "item/" + name));
    }

    private ItemModelBuilder containerItemWithOverrides(RegistryObject<Item> item, Map<Integer, String> overrides) {
        ItemModelBuilder builder = withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(OverhaulZ.MOD_ID, "item/" + item.getId().getPath()));

        // Adiciona os overrides corretamente, sem namespace e com valores inteiros no JSON
        overrides.forEach((condition, modelPath) -> builder.override()
                .predicate(new ResourceLocation("stored_item"), condition.floatValue()) // Usa apenas "stored_item"
                .model(getExistingFile(new ResourceLocation(OverhaulZ.MOD_ID, "item/" + modelPath))));

        return builder;
    }

}
