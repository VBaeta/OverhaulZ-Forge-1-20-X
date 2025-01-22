package net.vinithekidd.overhaulzmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.vinithekidd.overhaulzmod.OverhaulZ;
import net.vinithekidd.overhaulzmod.block.ModBlocks;
import net.vinithekidd.overhaulzmod.item.ModItems;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> BAUXITE_SMELTABLES = List.of(ModItems.BAUXITE_SHARD.get(),
            ModBlocks.BAUXITE_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        //BAUXITE SMELTABLES
        oreSmelting(pWriter, BAUXITE_SMELTABLES, RecipeCategory.MISC, ModItems.ALUMINUM_INGOT.get(),
                0.55f, 400, "aluminum");

        oreBlasting(pWriter, BAUXITE_SMELTABLES, RecipeCategory.MISC, ModItems.ALUMINUM_INGOT.get(),
                0.65f, 200, "aluminum");


        //CAST IRON
        oreSmelting(
                pWriter, List.of(Items.IRON_INGOT), RecipeCategory.MISC, ModItems.CAST_IRON_INGOT.get(),
                1f, 800, "cast_iron_ingot");

        oreBlasting(pWriter, List.of(Items.IRON_INGOT), RecipeCategory.MISC, ModItems.CAST_IRON_INGOT.get(),
                1.5f, 600, "cast_iron_ingot");


        //CRAFTING TABLE RECIPES
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GEAR_CAST.get())
                .pattern("IDI")
                .pattern("DID")
                .pattern("IDI")
                .define('D', Blocks.DIRT)
                .define('I', ModItems.CAST_IRON_INGOT.get())
                .unlockedBy(getHasName(ModItems.CAST_IRON_INGOT.get()), has(ModItems.CAST_IRON_INGOT.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALUMINUM_BLOCK.get())
                .pattern("AA")
                .pattern("AA")
                .define('A', ModItems.ALUMINUM_INGOT.get())
                .unlockedBy(getHasName(ModItems.ALUMINUM_INGOT.get()), has(ModItems.ALUMINUM_INGOT.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ALUMINUM_INGOT.get(), 4)
                .requires(ModBlocks.ALUMINUM_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.ALUMINUM_BLOCK.get()), has(ModBlocks.ALUMINUM_BLOCK.get()))
                .save(pWriter);
    }



    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer,  OverhaulZ.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

}
