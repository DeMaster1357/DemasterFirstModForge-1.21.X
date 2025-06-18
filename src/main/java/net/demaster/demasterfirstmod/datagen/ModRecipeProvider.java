package net.demaster.demasterfirstmod.datagen;

import net.demaster.demasterfirstmod.FirstMod;
import net.demaster.demasterfirstmod.block.ModBlocks;
import net.demaster.demasterfirstmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        List<ItemLike> DEMASTERITE_SMELTABLES = List.of(
                ModItems.RAW_DEMASTERITE.get(),
                ModBlocks.DEMASTERITE_ORE.get(),
                ModBlocks.DEEPSLATE_DEMASTERITE_ORE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DEMASTERITE_BLOCK.get())
                .define('A', ModItems.DEMASTERITE_INGOT.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy(getHasName(ModItems.DEMASTERITE_INGOT.get()), has(ModItems.DEMASTERITE_INGOT.get()))
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DEMASTERITE_INGOT.get(), 9)
                .requires(ModBlocks.DEMASTERITE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.DEMASTERITE_BLOCK.get()), has(ModBlocks.DEMASTERITE_BLOCK.get()))
                .save(pRecipeOutput, FirstMod.MOD_ID + ":demasterite_ingot_from_demasterite_block");

        oreSmelting(pRecipeOutput, DEMASTERITE_SMELTABLES, RecipeCategory.MISC, ModItems.DEMASTERITE_INGOT.get(),
                5f, 1200, "demasterite");
        oreBlasting(pRecipeOutput, DEMASTERITE_SMELTABLES, RecipeCategory.MISC, ModItems.DEMASTERITE_INGOT.get(),
                5f, 600, "demasterite");

        stairBuilder(ModBlocks.DEMASTERITE_STAIRS.get(), Ingredient.of(ModItems.DEMASTERITE_INGOT.get()))
                .group("demasterite")
                .unlockedBy(getHasName(ModItems.DEMASTERITE_INGOT.get()), has(ModItems.DEMASTERITE_INGOT.get()))
                .save(pRecipeOutput);
        slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DEMASTERITE_SLAB.get(), ModItems.DEMASTERITE_INGOT.get());

        buttonBuilder(ModBlocks.DEMASTERITE_BUTTON.get(), Ingredient.of(ModItems.DEMASTERITE_INGOT.get()))
                .group("demasterite")
                .unlockedBy(getHasName(ModItems.DEMASTERITE_INGOT.get()), has(ModItems.DEMASTERITE_INGOT.get()))
                .save(pRecipeOutput);
        pressurePlate(pRecipeOutput, ModBlocks.DEMASTERITE_PRESSURE_PLATE.get(), ModItems.DEMASTERITE_INGOT.get());

        fenceBuilder(ModBlocks.DEMASTERITE_FENCE.get(), Ingredient.of(ModItems.DEMASTERITE_INGOT.get()))
                .group("demasterite")
                .unlockedBy(getHasName(ModItems.DEMASTERITE_INGOT.get()), has(ModItems.DEMASTERITE_INGOT.get()))
                .save(pRecipeOutput);
        fenceGateBuilder(ModBlocks.DEMASTERITE_FENCE_GATE.get(), Ingredient.of(ModItems.DEMASTERITE_INGOT.get()))
                .group("demasterite")
                .unlockedBy(getHasName(ModItems.DEMASTERITE_INGOT.get()), has(ModItems.DEMASTERITE_INGOT.get()))
                .save(pRecipeOutput);
        wall(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DEMASTERITE_WALL.get(), ModItems.DEMASTERITE_INGOT.get());

        doorBuilder(ModBlocks.DEMASTERITE_DOOR.get(), Ingredient.of(ModItems.DEMASTERITE_INGOT.get()))
                .group("demasterite")
                .unlockedBy(getHasName(ModItems.DEMASTERITE_INGOT.get()), has(ModItems.DEMASTERITE_INGOT.get()))
                .save(pRecipeOutput);
        trapdoorBuilder(ModBlocks.DEMASTERITE_TRAPDOOR.get(), Ingredient.of(ModItems.DEMASTERITE_INGOT.get()))
                .group("demasterite")
                .unlockedBy(getHasName(ModItems.DEMASTERITE_INGOT.get()), has(ModItems.DEMASTERITE_INGOT.get()))
                .save(pRecipeOutput);
    }

    protected static void oreSmelting(
            RecipeOutput pRecipeOutput,
            List<ItemLike> pIngredients,
            RecipeCategory pCategory,
            ItemLike pResult,
            float pExperience,
            int pCookingTime,
            String pGroup
    ) {
        oreCooking(pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(
            RecipeOutput pRecipeOutput,
            List<ItemLike> pIngredients,
            RecipeCategory pCategory,
            ItemLike pResult,
            float pExperience,
            int pCookingTime,
            String pGroup
    ) {
        oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory,
                pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    private static <T extends AbstractCookingRecipe> void oreCooking(
            RecipeOutput pRecipeOutput,
            RecipeSerializer<T> pSerializer,
            AbstractCookingRecipe.Factory<T> pRecipeFactory,
            List<ItemLike> pIngredients,
            RecipeCategory pCategory,
            ItemLike pResult,
            float pExperience,
            int pCookingTime,
            String pGroup,
            String pSuffix
    ) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pSerializer, pRecipeFactory)
                    .group(pGroup)
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pRecipeOutput, FirstMod.MOD_ID + ":" + getItemName(pResult) + pSuffix + "_" + getItemName(itemlike));
        }
    }
}
