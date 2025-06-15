package net.demaster.demasterfirstmod.item;

import net.demaster.demasterfirstmod.FirstMod;
import net.demaster.demasterfirstmod.item.custom.ChiselItem;
import net.demaster.demasterfirstmod.item.custom.FuelItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
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

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
