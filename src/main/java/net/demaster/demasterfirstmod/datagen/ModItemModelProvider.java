package net.demaster.demasterfirstmod.datagen;

import net.demaster.demasterfirstmod.FirstMod;
import net.demaster.demasterfirstmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

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
    }
}
