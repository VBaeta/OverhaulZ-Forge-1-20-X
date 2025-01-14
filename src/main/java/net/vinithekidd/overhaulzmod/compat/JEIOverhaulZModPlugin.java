package net.vinithekidd.overhaulzmod.compat;


import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.vinithekidd.overhaulzmod.OverhaulZ;
import net.vinithekidd.overhaulzmod.recipe.RecyclerStationRecipe;
import net.vinithekidd.overhaulzmod.screen.RecyclerStationScreen;

import java.util.List;

@JeiPlugin
public class JEIOverhaulZModPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(OverhaulZ.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new RecyclerStationCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<RecyclerStationRecipe> recyclerStationRecipes = recipeManager.getAllRecipesFor(RecyclerStationRecipe.Type.INSTANCE);
        registration.addRecipes(RecyclerStationCategory.RECYCLER_STATION_TYPE, recyclerStationRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(RecyclerStationScreen.class, 60, 30, 20, 30,
                RecyclerStationCategory.RECYCLER_STATION_TYPE);
    }
}