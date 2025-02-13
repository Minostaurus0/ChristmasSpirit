package io.github.frqnny.cspirit.item;

import io.github.frqnny.cspirit.blockentity.WrappedPresentBlockEntity;
import io.github.frqnny.cspirit.present.PresentConstructor;
import io.github.frqnny.cspirit.util.ItemHelper;
import io.github.frqnny.cspirit.util.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WrappedPresentBlockItem extends BlockItem {
    public WrappedPresentBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        PresentConstructor constructor = PresentConstructor.fromStack(stack);

        if (!constructor.toPlayerName.isEmpty()) {
            tooltip.add(Text.literal("From: " + Formatting.GOLD + constructor.fromPlayerName));
            tooltip.add(Text.literal("To: " + Formatting.GOLD + constructor.toPlayerName));
            tooltip.add(Text.literal("Open on the " + Formatting.GOLD + TimeHelper.getFormattedDay(constructor.getActualDay())));
        }
    }

    @Override
    protected boolean place(ItemPlacementContext context, BlockState state) {
        boolean b = super.place(context, state);

        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        PresentConstructor constructor = PresentConstructor.fromStack(context.getStack());
        constructor.toBlock(world, pos);

        NbtCompound nbt = ItemHelper.getNBT(context.getStack());
        NbtCompound compoundnbt = nbt.getList("Items", 10).getCompound(0);
        BlockEntity newBe = world.getBlockEntity(pos);

        if (newBe instanceof WrappedPresentBlockEntity) {
            Inventory inventory = (WrappedPresentBlockEntity) newBe;
            inventory.setStack(0, ItemStack.fromNbt(compoundnbt));
        }

        return b;
    }
}
