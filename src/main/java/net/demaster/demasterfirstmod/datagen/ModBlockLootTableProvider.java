package net.demaster.demasterfirstmod.datagen;

import net.demaster.demasterfirstmod.block.ModBlocks;
import net.demaster.demasterfirstmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.DEMASTERITE_BLOCK.get());
        dropSelf(ModBlocks.RAW_DEMASTERITE_BLOCK.get());

        this.add(ModBlocks.DEMASTERITE_ORE.get(),
                block -> createOreDrop(ModBlocks.DEMASTERITE_ORE.get(), ModItems.RAW_DEMASTERITE.get()));
        this.add(ModBlocks.DEEPSLATE_DEMASTERITE_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_DEMASTERITE_ORE.get(), ModItems.RAW_DEMASTERITE.get()));

        dropSelf(ModBlocks.MAGIC_BLOCK.get());

        dropSelf(ModBlocks.DEMASTERITE_STAIRS.get());
        this.add(ModBlocks.DEMASTERITE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.DEMASTERITE_SLAB.get()));

        dropSelf(ModBlocks.DEMASTERITE_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.DEMASTERITE_BUTTON.get());

        dropSelf(ModBlocks.DEMASTERITE_FENCE.get());
        dropSelf(ModBlocks.DEMASTERITE_FENCE_GATE.get());
        dropSelf(ModBlocks.DEMASTERITE_WALL.get());

        this.add(ModBlocks.DEMASTERITE_DOOR.get(),
            block -> createDoorTable(ModBlocks.DEMASTERITE_DOOR.get()));
        dropSelf(ModBlocks.DEMASTERITE_TRAPDOOR.get());
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                pBlock, this.applyExplosionDecay(
                        pBlock, LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
