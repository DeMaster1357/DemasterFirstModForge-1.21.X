package net.demaster.demasterfirstmod.component;

import com.mojang.serialization.Codec;
import net.demaster.demasterfirstmod.FirstMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, FirstMod.MOD_ID);

    public static final RegistryObject<DataComponentType<BlockPos>> LAST_USED_COORDINATES = register("last_used_coordinates",
            builder -> builder.persistent(BlockPos.CODEC));
    public static final RegistryObject<DataComponentType<Integer>> DIG_AREA_RADIUS = register("dig_area_size",
            builder -> builder.persistent(Codec.INT));


    private static <T> RegistryObject<DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return  DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
