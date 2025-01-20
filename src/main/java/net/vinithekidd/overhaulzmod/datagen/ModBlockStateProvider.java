package net.vinithekidd.overhaulzmod.datagen;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.vinithekidd.overhaulzmod.OverhaulZ;
import net.vinithekidd.overhaulzmod.block.ModBlocks;
import net.vinithekidd.overhaulzmod.block.custom.CornCropBlock;
import net.vinithekidd.overhaulzmod.block.custom.RecyclerStationBlock;
import net.vinithekidd.overhaulzmod.block.custom.TomatoCropBlock;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, OverhaulZ.MOD_ID, exFileHelper);
    }

    public void directionalBlock(Block block, ModelFile model) {
        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(RecyclerStationBlock.FACING);
            int rotationY = getYRotationFromDirection(facing); // Calcula somente valores para Y

            return new ConfiguredModel[]{
                    new ConfiguredModel(model, 0, rotationY, false) // Aplica somente rotação no eixo Y
            };
        });
    }

    private int getYRotationFromDirection(Direction direction) {
        return switch (direction) {
            case NORTH -> 0;
            case EAST -> 90;
            case SOUTH -> 180;
            case WEST -> 270;
            default -> 0; // Qualquer direção inesperada
        };
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.ALUMINUM_BLOCK);

        blockWithItem(ModBlocks.BAUXITE_ORE);

        makeTomatoCrop((CropBlock) ModBlocks.TOMATO_CROP.get(), "tomato_crop_stage", "tomato_crop_stage");
        makeCornCrop(((CropBlock) ModBlocks.CORN_CROP.get()), "corn_stage_", "corn_stage_");

        directionalBlock(ModBlocks.RECYCLER_STATION.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/recycler_station")));
        directionalItemModel(ModBlocks.RECYCLER_STATION);

    }


    public void makeTomatoCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> tomatoStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] tomatoStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((TomatoCropBlock) block).getAgeProperty()),
                new ResourceLocation(OverhaulZ.MOD_ID, "block/" + textureName + state.getValue(((TomatoCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

    public void makeCornCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> cornStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] cornStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((CornCropBlock) block).getAgeProperty()),
                new ResourceLocation(OverhaulZ.MOD_ID, "block/" + textureName + state.getValue(((CornCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void directionalItemModel(RegistryObject<Block> block) {
        String name = block.getId().getPath(); // Pega o nome registrado do bloco
        itemModels().getBuilder(name)
                .parent(new ModelFile.UncheckedModelFile(modLoc("block/" + name))); // Aponta para o modelo do bloco
    }
}
