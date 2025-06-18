package net.demaster.demasterfirstmod.datagen;

import net.demaster.demasterfirstmod.FirstMod;
import net.demaster.demasterfirstmod.block.ModBlocks;
import net.demaster.demasterfirstmod.block.custom.DeMasteriteLampBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, FirstMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.DEMASTERITE_BLOCK);
        blockWithItem(ModBlocks.RAW_DEMASTERITE_BLOCK);

        blockWithItem(ModBlocks.DEMASTERITE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_DEMASTERITE_ORE);

        blockWithItem(ModBlocks.MAGIC_BLOCK);

        stairsBlockWithItem(ModBlocks.DEMASTERITE_STAIRS, ModBlocks.DEMASTERITE_BLOCK);
        slabBlockWithItem(ModBlocks.DEMASTERITE_SLAB, ModBlocks.DEMASTERITE_BLOCK);

        pressurePlateBlockWithItem(ModBlocks.DEMASTERITE_PRESSURE_PLATE, ModBlocks.DEMASTERITE_BLOCK);
        buttonBlockWithItem(ModBlocks.DEMASTERITE_BUTTON, ModBlocks.DEMASTERITE_BLOCK);

        fenceBlockWithItem(ModBlocks.DEMASTERITE_FENCE, ModBlocks.DEMASTERITE_BLOCK);
        fenceGateBlockWithItem(ModBlocks.DEMASTERITE_FENCE_GATE, ModBlocks.DEMASTERITE_BLOCK);
        wallBlockWithItem(ModBlocks.DEMASTERITE_WALL, ModBlocks.DEMASTERITE_BLOCK);

        doorBlockWithItem(ModBlocks.DEMASTERITE_DOOR);
        trapdoorBlockWithItem(ModBlocks.DEMASTERITE_TRAPDOOR);

        conditionalBlockWithItem(ModBlocks.DEMASTERITE_LAMP, DeMasteriteLampBlock.LIGHT_LEVEL, lightLevel -> lightLevel != 0, "_on", "_off");
    }

    private <T extends Comparable<T>> void conditionalBlockWithItem(RegistryObject<Block> blockRegistryObject, Property<T> property, Function<T, Boolean> condition,
                                                                    String trueSuffix, String falseSuffix) {
        String name = ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath();

        getVariantBuilder(blockRegistryObject.get()).forAllStates(state -> {
            String suffix = condition.apply(state.getValue(property)) ? trueSuffix : falseSuffix;
            return new ConfiguredModel[] {
                    new ConfiguredModel(models().cubeAll(name + suffix,
                            ResourceLocation.fromNamespaceAndPath(FirstMod.MOD_ID, "block/" + name + suffix)))
            };
        });

        simpleBlockItem(blockRegistryObject.get(), models().cubeAll(name + trueSuffix,
                ResourceLocation.fromNamespaceAndPath(FirstMod.MOD_ID, "block/" + name + trueSuffix)));
    }


    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void blockItem(RegistryObject<? extends Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(modLoc("block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath())));
    }

    private void blockItem(RegistryObject<? extends Block> blockRegistryObject, String appendix) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(modLoc("block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + appendix)));
    }

    private void stairsBlockWithItem(RegistryObject<StairBlock> stairBlockRegistryObject, RegistryObject<Block> parentBlock) {
        stairsBlock(stairBlockRegistryObject.get(), blockTexture(parentBlock.get()));
        blockItem(stairBlockRegistryObject);
    }

    private void slabBlockWithItem(RegistryObject<SlabBlock> slabBlockRegistryObject, RegistryObject<Block> parentBlock) {
        slabBlock(slabBlockRegistryObject.get(), blockTexture(parentBlock.get()), blockTexture(parentBlock.get()));
        blockItem(slabBlockRegistryObject);
    }

    private void pressurePlateBlockWithItem(RegistryObject<PressurePlateBlock> pressurePlateBlockRegistryObject, RegistryObject<Block> parentBlock) {
        pressurePlateBlock(pressurePlateBlockRegistryObject.get(), blockTexture(parentBlock.get()));
        blockItem(pressurePlateBlockRegistryObject);
    }

    private void buttonBlockWithItem(RegistryObject<ButtonBlock> buttonBlockRegistryObject, RegistryObject<Block> parentBlock) {
        buttonBlock(buttonBlockRegistryObject.get(), blockTexture(parentBlock.get()));
    }

    private void fenceBlockWithItem(RegistryObject<FenceBlock> fenceBlockRegistryObject, RegistryObject<Block> parentBlock) {
        fenceBlock(fenceBlockRegistryObject.get(), blockTexture(parentBlock.get()));
    }

    private void fenceGateBlockWithItem(RegistryObject<FenceGateBlock> fenceGateBlockRegistryObject, RegistryObject<Block> parentBlock) {
        fenceGateBlock(fenceGateBlockRegistryObject.get(), blockTexture(parentBlock.get()));
        blockItem(fenceGateBlockRegistryObject);
    }

    private void wallBlockWithItem(RegistryObject<WallBlock> wallBlockRegistryObject, RegistryObject<Block> parentBlock) {
        wallBlock(wallBlockRegistryObject.get(), blockTexture(parentBlock.get()));
    }

    private void doorBlockWithItem(RegistryObject<DoorBlock> doorBlockRegistryObject) {
        doorBlockWithRenderType(doorBlockRegistryObject.get(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(doorBlockRegistryObject.get()).getPath() + "_bottom"),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(doorBlockRegistryObject.get()).getPath() + "_top"),
                "cutout");
    }

    private void trapdoorBlockWithItem(RegistryObject<TrapDoorBlock> trapdoorBlockRegistryObject) {
        trapdoorBlockWithRenderType(trapdoorBlockRegistryObject.get(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(trapdoorBlockRegistryObject.get()).getPath()),
                true, "cutout");
        blockItem(trapdoorBlockRegistryObject, "_bottom");
    }
}
