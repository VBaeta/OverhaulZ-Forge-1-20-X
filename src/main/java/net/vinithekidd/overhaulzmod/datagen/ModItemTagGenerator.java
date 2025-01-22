package net.vinithekidd.overhaulzmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.vinithekidd.overhaulzmod.OverhaulZ;
import net.vinithekidd.overhaulzmod.item.ModItems;
import net.vinithekidd.overhaulzmod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, OverhaulZ.MOD_ID, existingFileHelper);
    }

    protected void addTags(HolderLookup.Provider pProvider) {
        // Adicionar itens à tag TINY_MATERIALS
        tag(ModTags.Items.TINY_MATERIALS)
                .add(ModItems.NAILS.get())
                .add(ModItems.SCREWS.get());

        tag(ModTags.Items.CASTS)
                .add(ModItems.GEAR_CAST.get());
    }

}
