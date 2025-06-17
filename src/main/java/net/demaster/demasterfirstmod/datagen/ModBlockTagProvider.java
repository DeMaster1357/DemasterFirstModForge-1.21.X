package net.demaster.demasterfirstmod.datagen;

import net.demaster.demasterfirstmod.FirstMod;
import net.demaster.demasterfirstmod.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, FirstMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.DEMASTERITE_BLOCK.get())
                .add(ModBlocks.RAW_DEMASTERITE_BLOCK.get())
                .add(ModBlocks.DEMASTERITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_DEMASTERITE_ORE.get())
                .add(ModBlocks.MAGIC_BLOCK.get())
        ;

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.MAGIC_BLOCK.get())
        ;

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.DEMASTERITE_BLOCK.get())
                .add(ModBlocks.RAW_DEMASTERITE_BLOCK.get())
                .add(ModBlocks.DEMASTERITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_DEMASTERITE_ORE.get())
        ;
    }
}
