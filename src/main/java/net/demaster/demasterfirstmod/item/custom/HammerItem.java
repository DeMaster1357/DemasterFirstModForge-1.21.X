package net.demaster.demasterfirstmod.item.custom;

import net.demaster.demasterfirstmod.component.ModDataComponentTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class HammerItem extends DiggerItem {
    public int defaultRadius;
    public int minRadius;
    public int maxRadius;

    public HammerItem(Tier pTier, TagKey<Block> pBlocks, Properties pProperties) {
        super(pTier, pBlocks, pProperties);
        this.defaultRadius = 1;
        this.minRadius = 0;
        this.maxRadius = 3;
    }

    public HammerItem(Tier pTier, TagKey<Block> pBlocks, Properties pProperties, int defaultRadius, int minRadius, int maxRadius) {
        super(pTier, pBlocks, pProperties);
        this.defaultRadius = defaultRadius;
        this.minRadius = minRadius;
        this.maxRadius = maxRadius;
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);

        if (!pLevel.isClientSide && pStack.get(ModDataComponentTypes.DIG_AREA_RADIUS.get()) == null) {
            pStack.set(ModDataComponentTypes.DIG_AREA_RADIUS.get(), defaultRadius);
        }
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, @NotNull InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);

        if (!pLevel.isClientSide) {
            if (pPlayer.isShiftKeyDown()) {
                int radius = Optional.ofNullable(pPlayer.getItemInHand(pUsedHand).get(ModDataComponentTypes.DIG_AREA_RADIUS.get())).orElse(3);
                radius++;
                if (radius > maxRadius) {
                    radius = minRadius;
                }
                pPlayer.getItemInHand(pUsedHand).set(ModDataComponentTypes.DIG_AREA_RADIUS.get(), radius);
                return InteractionResultHolder.success(stack);
            }
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("tooltip.demasterfirstmod.hammer"));

        if (pStack.get(ModDataComponentTypes.DIG_AREA_RADIUS.get()) != null) {
            pTooltipComponents.add(Component.literal("Shift right-click to change area: " + (1 + 2 * pStack.get(ModDataComponentTypes.DIG_AREA_RADIUS.get()))));
        }

        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }

    private final HashSet<BlockPos> currentlyBreaking = new HashSet<>();

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pMiningEntity) {
        if (!pLevel.isClientSide && pMiningEntity instanceof ServerPlayer player) {
            if (currentlyBreaking.contains(pPos)) {
                return super.mineBlock(pStack, pLevel, pState, pPos, pMiningEntity);
            }

            List<BlockPos> blocksToBeDestroyed = getBlocksToBeDestroyed(pStack, pPos, player);
            for (BlockPos blockPos : blocksToBeDestroyed) {
                if (blockPos.equals(pPos)) continue;
                if (!this.isCorrectToolForDrops(player.getMainHandItem(), pLevel.getBlockState(blockPos))) continue;

                currentlyBreaking.add(blockPos);
                player.gameMode.destroyBlock(blockPos);
                currentlyBreaking.remove(blockPos);
            }
        }

        return super.mineBlock(pStack, pLevel, pState, pPos, pMiningEntity);
    }

    public List<BlockPos> getBlocksToBeDestroyed(ItemStack stack, BlockPos initialBlockPos, ServerPlayer player) {
        List<BlockPos> positions = new ArrayList<>();
        int radius = Optional.ofNullable(stack.get(ModDataComponentTypes.DIG_AREA_RADIUS.get()))
                .orElse(defaultRadius);

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    positions.add(new BlockPos(initialBlockPos.getX() + x, initialBlockPos.getY() + y, initialBlockPos.getZ() + z));
                }
            }
        }

        return positions;
    }
}
