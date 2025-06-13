package net.demaster.demasterfirstmod.item;

import net.demaster.demasterfirstmod.FirstMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FirstMod.MOD_ID);

    public static final RegistryObject<Item> DEMASTERITE_INGOT = ITEMS.register("demasterite_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_DEMASTERITE = ITEMS.register("raw_demasterite",
            () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
