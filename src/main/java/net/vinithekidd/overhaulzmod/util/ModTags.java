package net.vinithekidd.overhaulzmod.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.vinithekidd.overhaulzmod.OverhaulZ;

public class ModTags {
    public static class Blocks {
    }

    public static class Items {

        public static final TagKey<Item> TINY_MATERIALS = tag("tiny_materials");
        public static final TagKey<Item> CASTS = tag("casts");


        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(OverhaulZ.MOD_ID, name));
        }
    }
}
