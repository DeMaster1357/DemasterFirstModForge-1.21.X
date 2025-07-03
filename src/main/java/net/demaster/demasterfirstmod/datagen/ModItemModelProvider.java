package net.demaster.demasterfirstmod.datagen;

import net.demaster.demasterfirstmod.FirstMod;
import net.demaster.demasterfirstmod.block.ModBlocks;
import net.demaster.demasterfirstmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

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
        
        trimmedArmorItem(ModItems.DEMASTERITE_HELMET);
        trimmedArmorItem(ModItems.DEMASTERITE_CHESTPLATE);
        trimmedArmorItem(ModItems.DEMASTERITE_LEGGINGS);
        trimmedArmorItem(ModItems.DEMASTERITE_BOOTS);
    }

    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = FirstMod.MOD_ID; // Change this to your mod id

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {
                float trimValue = value;

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = armorItem.toString();
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.parse(armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.parse(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = ResourceLocation.parse(currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exists, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc.getNamespace() + ":item/" + armorItemResLoc.getPath())
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace()  + ":item/" + trimNameResLoc.getPath()))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
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
