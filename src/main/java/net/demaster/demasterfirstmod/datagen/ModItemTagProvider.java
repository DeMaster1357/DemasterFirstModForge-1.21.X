package net.demaster.demasterfirstmod.datagen;

import net.demaster.demasterfirstmod.FirstMod;
import net.demaster.demasterfirstmod.item.ModItems;
import net.demaster.demasterfirstmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider,
                              CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, FirstMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ItemTags.SWORDS)
            .add(ModItems.DEMASTERITE_SWORD.get())
        ;
        tag(ItemTags.PICKAXES)
                .add(ModItems.DEMASTERITE_PICKAXE.get())
        ;
        tag(ItemTags.SHOVELS)
                .add(ModItems.DEMASTERITE_SHOVEL.get())
        ;
        tag(ItemTags.AXES)
                .add(ModItems.DEMASTERITE_AXE.get())
        ;
        tag(ItemTags.HOES)
                .add(ModItems.DEMASTERITE_HOE.get())
        ;

        tag(ModTags.Items.SAMPLE_TAG)
                .add(ModItems.CHISEL.get())
        ;
    }
}
