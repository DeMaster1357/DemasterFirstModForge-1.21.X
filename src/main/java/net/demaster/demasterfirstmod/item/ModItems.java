package net.demaster.demasterfirstmod.item;

import net.demaster.demasterfirstmod.FirstMod;
import net.demaster.demasterfirstmod.item.custom.ChiselItem;
import net.demaster.demasterfirstmod.item.custom.FuelItem;
import net.demaster.demasterfirstmod.item.custom.HammerItem;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FirstMod.MOD_ID);

    public static final RegistryObject<Item> DEMASTERITE_INGOT = ITEMS.register("demasterite_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_DEMASTERITE = ITEMS.register("raw_demasterite",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(256)));

    public static final RegistryObject<Item> DEMASTERITE_POTATO = ITEMS.register("demasterite_potato",
            () -> new Item(new Item.Properties().food(ModFoodProperties.DEMASTERITE_POTATO)) {
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    pTooltipComponents.add(Component.translatable("tooltip.demasterfirstmod.demasterite_potato"));
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });

    public static final RegistryObject<Item> FUEL_RUNE = ITEMS.register("fuel_rune",
            () -> new FuelItem(new Item.Properties(), 2400));

    public static final RegistryObject<Item> DEMASTERITE_SWORD = registerSword("demasterite_sword", ModToolTiers.DEMASTERITE);
    public static final RegistryObject<Item> DEMASTERITE_PICKAXE = registerPickaxe("demasterite_pickaxe", ModToolTiers.DEMASTERITE);
    public static final RegistryObject<Item> DEMASTERITE_SHOVEL = registerShovel("demasterite_shovel", ModToolTiers.DEMASTERITE);
    public static final RegistryObject<Item> DEMASTERITE_AXE = registerAxe("demasterite_axe", ModToolTiers.DEMASTERITE);
    public static final RegistryObject<Item> DEMASTERITE_HOE = registerHoe("demasterite_hoe", ModToolTiers.DEMASTERITE);

    public static final RegistryObject<Item> DEMASTERITE_HAMMER = ITEMS.register("demasterite_hammer",
            () -> new HammerItem(ModToolTiers.DEMASTERITE, BlockTags.MINEABLE_WITH_PICKAXE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.DEMASTERITE, 7, -3.5f))));

    public static final RegistryObject<Item> DEMASTERITE_HELMET = ITEMS.register("demasterite_helmet",
            () -> new ArmorItem(ModArmorMaterials.DEMASTERITE_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(18))));
    public static final RegistryObject<Item> DEMASTERITE_CHESTPLATE = ITEMS.register("demasterite_chestplate",
            () -> new ArmorItem(ModArmorMaterials.DEMASTERITE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(18))));
    public static final RegistryObject<Item> DEMASTERITE_LEGGINGS = ITEMS.register("demasterite_leggings",
            () -> new ArmorItem(ModArmorMaterials.DEMASTERITE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(18))));
    public static final RegistryObject<Item> DEMASTERITE_BOOTS = ITEMS.register("demasterite_boots",
            () -> new ArmorItem(ModArmorMaterials.DEMASTERITE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(18))));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static RegistryObject<Item> registerSword(String name, Tier pTier) {
        return ITEMS.register(name,
            () -> new SwordItem(pTier, new Item.Properties()
                    .attributes(SwordItem.createAttributes(pTier, 3, -2.4f))));
    }

    public static RegistryObject<Item> registerPickaxe(String name, Tier pTier) {
        return ITEMS.register(name,
                () -> new PickaxeItem(pTier, new Item.Properties()
                        .attributes(PickaxeItem.createAttributes(pTier, 1, -2.0f))));
    }

    public static RegistryObject<Item> registerShovel(String name, Tier pTier) {
        return ITEMS.register(name,
                () -> new ShovelItem(pTier, new Item.Properties()
                        .attributes(ShovelItem.createAttributes(pTier, 1.5f, -3.0f))));
    }

    public static RegistryObject<Item> registerAxe(String name, Tier pTier) {
        return ITEMS.register(name,
                () -> new AxeItem(pTier, new Item.Properties()
                        .attributes(AxeItem.createAttributes(pTier, 6, -3.2f))));
    }

    public static RegistryObject<Item> registerHoe(String name, Tier pTier) {
        return ITEMS.register(name,
                () -> new HoeItem(pTier, new Item.Properties()
                        .attributes(HoeItem.createAttributes(pTier, 0, -3.0f))));
    }
}
