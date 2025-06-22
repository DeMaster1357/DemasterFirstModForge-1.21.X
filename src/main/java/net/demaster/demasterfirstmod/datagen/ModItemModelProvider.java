package net.demaster.demasterfirstmod.datagen;

import net.demaster.demasterfirstmod.FirstMod;
import net.demaster.demasterfirstmod.block.ModBlocks;
import net.demaster.demasterfirstmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, FirstMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.DEMASTERITE_INGOT.get());
        basicItem(ModItems.RAW_DEMASTERITE.get());

        basicItem(ModItems.CHISEL.get());

        basicItem(ModItems.DEMASTERITE_POTATO.get());

        basicItem(ModItems.FUEL_RUNE.get());

        buttonItem(ModBlocks.DEMASTERITE_BUTTON, ModBlocks.DEMASTERITE_BLOCK);
        fenceItem(ModBlocks.DEMASTERITE_FENCE, ModBlocks.DEMASTERITE_BLOCK);
        wallItem(ModBlocks.DEMASTERITE_WALL, ModBlocks.DEMASTERITE_BLOCK);

        simpleBlockItem(ModBlocks.DEMASTERITE_DOOR);

        handHeldItem(ModItems.DEMASTERITE_SWORD);
        handHeldItem(ModItems.DEMASTERITE_PICKAXE);
        handHeldItem(ModItems.DEMASTERITE_SHOVEL);
        handHeldItem(ModItems.DEMASTERITE_AXE);
        handHeldItem(ModItems.DEMASTERITE_HOE);

        handHeldItem(ModItems.DEMASTERITE_HAMMER);
    }

    public ItemModelBuilder handHeldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(FirstMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    public void buttonItem(RegistryObject<? extends Block> block, RegistryObject<Block> parentBlock) {
        irregularItem(block, parentBlock, "button", "texture");
    }

    public void fenceItem(RegistryObject<? extends Block> block, RegistryObject<Block> parentBlock) {
        irregularItem(block, parentBlock, "fence", "texture");
    }

    public void wallItem(RegistryObject<? extends Block> block, RegistryObject<Block> parentBlock) {
        irregularItem(block, parentBlock, "wall", "wall");
    }

    public void irregularItem(RegistryObject<? extends Block> block, RegistryObject<Block> parentBlock, String itemType, String textureKey) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/" + itemType + "_inventory"))
                .texture(textureKey, ResourceLocation.fromNamespaceAndPath(FirstMod.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(parentBlock.get()).getPath()));
    }

    public ItemModelBuilder simpleBlockItem(RegistryObject<? extends  Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(FirstMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}
