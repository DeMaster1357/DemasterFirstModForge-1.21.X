package net.demaster.demasterfirstmod.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties DEMASTERITE_POTATO = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.5f)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 500), .2f)
            .alwaysEdible()
            .build();
}
