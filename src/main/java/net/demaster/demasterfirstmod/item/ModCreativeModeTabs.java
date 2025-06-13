package net.demaster.demasterfirstmod.item;

import net.demaster.demasterfirstmod.FirstMod;
import net.demaster.demasterfirstmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FirstMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> DEMASTERITE_ITEMS_TAB = CREATIVE_MODE_TABS.register("demasterite_items_tab",
    () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DEMASTERITE_INGOT.get()))
            .title(Component.translatable("creativetab_demasterfirstmod_demasterite_items"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModItems.DEMASTERITE_INGOT.get());
                output.accept(ModItems.RAW_DEMASTERITE.get());

            }).build());

    public static final RegistryObject<CreativeModeTab> DEMASTERITE_BLOCKS_TAB = CREATIVE_MODE_TABS.register("demasterite_blocks_tab",
    () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.DEMASTERITE_BLOCK.get()))
            .withTabsBefore(DEMASTERITE_ITEMS_TAB.getId())
            .title(Component.translatable("creativetab_demasterfirstmod_demasterite_blocks"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModBlocks.DEMASTERITE_BLOCK.get());
                output.accept(ModBlocks.RAW_DEMASTERITE_BLOCK.get());

            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
