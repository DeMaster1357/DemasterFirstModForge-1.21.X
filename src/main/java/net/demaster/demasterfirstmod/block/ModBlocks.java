package net.demaster.demasterfirstmod.block;

import net.demaster.demasterfirstmod.FirstMod;
import net.demaster.demasterfirstmod.block.custom.MagicBlock;
import net.demaster.demasterfirstmod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, FirstMod.MOD_ID);

    //<editor-fold desc="DEMASTERITE_BLOCK">
    public static final RegistryObject<Block> DEMASTERITE_BLOCK = registerBlock("demasterite_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)
            ));
    //</editor-fold>
    //<editor-fold desc="RAW_DEMASTERITE_BLOCK">
    public static final RegistryObject<Block> RAW_DEMASTERITE_BLOCK = registerBlock("raw_demasterite_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)
            ));
    //</editor-fold>

    //<editor-fold desc="DEMASTERITE_ORE">
    public static final RegistryObject<Block> DEMASTERITE_ORE = registerBlock("demasterite_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 6), BlockBehaviour.Properties.of()
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));
    //</editor-fold>
    //<editor-fold desc="DEEPSLATE_DEMASTERITE_ORE">
    public static final RegistryObject<Block> DEEPSLATE_DEMASTERITE_ORE = registerBlock("deepslate_demasterite_ore",
            () -> new DropExperienceBlock(UniformInt.of(6, 10), BlockBehaviour.Properties.of()
                    .strength(5f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)
            ));
    //</editor-fold>

    //<editor-fold desc="MAGIC_BLOCK">
    public static final RegistryObject<Block> MAGIC_BLOCK = registerBlock("magic_block",
            () -> new MagicBlock(BlockBehaviour.Properties.of()
                    .strength(10f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)
            ));
    //</editor-fold>

    //<editor-fold desc="DEMASTERITE_STAIRS">
    public static final RegistryObject<StairBlock> DEMASTERITE_STAIRS = registerBlock("demasterite_stairs",
            () -> new StairBlock(ModBlocks.DEMASTERITE_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.of()
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)
            ));
    //</editor-fold>
    //<editor-fold desc="DEMASTERITE_SLAB">
    public static final RegistryObject<SlabBlock> DEMASTERITE_SLAB = registerBlock("demasterite_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)
            ));
    //</editor-fold>

    //<editor-fold desc="DEMASTERITE_PRESSURE_PLATE">
    public static final RegistryObject<PressurePlateBlock> DEMASTERITE_PRESSURE_PLATE = registerBlock("demasterite_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.IRON, BlockBehaviour.Properties.of()
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)
            ));
    //</editor-fold>
    //<editor-fold desc="DEMASTERITE_BUTTON">
    public static final RegistryObject<ButtonBlock> DEMASTERITE_BUTTON = registerBlock("demasterite_button",
            () -> new ButtonBlock(BlockSetType.IRON, 1, BlockBehaviour.Properties.of()
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)
                    .noCollission()
            ));
    //</editor-fold>

    //<editor-fold desc="DEMASTERITE_FENCE">
    public static final RegistryObject<FenceBlock> DEMASTERITE_FENCE = registerBlock("demasterite_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of()
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)
            ));
    //</editor-fold>
    //<editor-fold desc="DEMASTERITE_FENCE_GATE">
    public static final RegistryObject<FenceGateBlock> DEMASTERITE_FENCE_GATE = registerBlock("demasterite_fence_gate",
            () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.of()
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)
            ));
    //</editor-fold>
    //<editor-fold desc="DEMASTERITE_WALL">
    public static final RegistryObject<WallBlock> DEMASTERITE_WALL = registerBlock("demasterite_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)
            ));
    //</editor-fold>

    //<editor-fold desc="DEMASTERITE_DOOR">
    public static final RegistryObject<DoorBlock> DEMASTERITE_DOOR = registerBlock("demasterite_door",
            () -> new DoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.of()
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)
                    .noOcclusion()
            ));
    //</editor-fold>
    //<editor-fold desc="DEMASTERITE_TRAPDOOR">
    public static final RegistryObject<TrapDoorBlock> DEMASTERITE_TRAPDOOR = registerBlock("demasterite_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.of()
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)
                    .noOcclusion()
            ));
    //</editor-fold>


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
