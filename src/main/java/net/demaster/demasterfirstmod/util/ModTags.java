package net.demaster.demasterfirstmod.util;

import net.demaster.demasterfirstmod.FirstMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_DEMASTERITE_TOOL = createTag("needs_demasterite_tool");
        public static final TagKey<Block> INCORRECT_FOR_DEMASTERITE_TOOL = createTag("incorrect_for_demasterite_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(FirstMod.MOD_ID, name));
        }
    }

    public static  class Items {
        public static final TagKey<Item> SAMPLE_TAG = createTag("sample_tag");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(FirstMod.MOD_ID, name));
        }
    }
}
