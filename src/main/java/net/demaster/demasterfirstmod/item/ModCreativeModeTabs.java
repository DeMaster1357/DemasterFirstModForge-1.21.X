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
            .title(Component.translatable("creativemodetab.demasterfirstmod.demasterite_items_tab"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModItems.DEMASTERITE_INGOT.get());
                output.accept(ModItems.RAW_DEMASTERITE.get());

                output.accept(ModItems.CHISEL.get());

                output.accept(ModItems.DEMASTERITE_POTATO.get());

                output.accept(ModItems.FUEL_RUNE.get());

            }).build());

    public static final RegistryObject<CreativeModeTab> DEMASTERITE_BLOCKS_TAB = CREATIVE_MODE_TABS.register("demasterite_blocks_tab",
    () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.DEMASTERITE_BLOCK.get()))
            .withTabsBefore(DEMASTERITE_ITEMS_TAB.getId())
            .title(Component.translatable("creativemodetab.demasterfirstmod.demasterite_blocks_tab"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModBlocks.DEMASTERITE_BLOCK.get());
                output.accept(ModBlocks.RAW_DEMASTERITE_BLOCK.get());

                output.accept(ModBlocks.DEMASTERITE_ORE.get());
                output.accept(ModBlocks.DEEPSLATE_DEMASTERITE_ORE.get());

                output.accept(ModBlocks.MAGIC_BLOCK.get());

                output.accept(ModBlocks.DEMASTERITE_STAIRS.get());
                output.accept(ModBlocks.DEMASTERITE_SLAB.get());

                output.accept(ModBlocks.DEMASTERITE_PRESSURE_PLATE.get());
                output.accept(ModBlocks.DEMASTERITE_BUTTON.get());

                output.accept(ModBlocks.DEMASTERITE_FENCE.get());
                output.accept(ModBlocks.DEMASTERITE_FENCE_GATE.get());
                output.accept(ModBlocks.DEMASTERITE_WALL.get());

                output.accept(ModBlocks.DEMASTERITE_DOOR.get());
                output.accept(ModBlocks.DEMASTERITE_TRAPDOOR.get());

            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
