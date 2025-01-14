package net.vinithekidd.overhaulzmod.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.vinithekidd.overhaulzmod.OverhaulZ;
import net.vinithekidd.overhaulzmod.block.ModBlocks;
import net.vinithekidd.overhaulzmod.recipe.RecyclerStationRecipe;

public class RecyclerStationCategory implements IRecipeCategory<RecyclerStationRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(OverhaulZ.MOD_ID, "recycler_station");
    public static final ResourceLocation TEXTURE = new ResourceLocation(OverhaulZ.MOD_ID,
            "textures/gui/gem_polishing_station_gui.png");

    public static final RecipeType<RecyclerStationRecipe> RECYCLER_STATION_TYPE =
            new RecipeType<>(UID, RecyclerStationRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public RecyclerStationCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.RECYCLER_STATION.get()));
    }

    @Override
    public RecipeType<RecyclerStationRecipe> getRecipeType() {
        return RECYCLER_STATION_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.overhaulzmod.recycler_station");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecyclerStationRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 80, 11).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 59).addItemStack(recipe.getResultItem(null));
    }
}
