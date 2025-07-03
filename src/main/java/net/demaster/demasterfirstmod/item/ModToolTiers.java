package net.demaster.demasterfirstmod.item;

import net.demaster.demasterfirstmod.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModToolTiers {
    public static final Tier DEMASTERITE = new ForgeTier(500, 12, 6, 80,
            ModTags.Blocks.NEEDS_DEMASTERITE_TOOL, () -> Ingredient.of(ModItems.DEMASTERITE_INGOT.get()),
            ModTags.Blocks.INCORRECT_FOR_DEMASTERITE_TOOL);
}
