package io.github.frqnny.cspirit.blockentity;

import io.github.frqnny.cspirit.client.screen.CookieTrayGUI;
import io.github.frqnny.cspirit.init.ModBlocks;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class CookieTrayBlockEntity extends CSBlockEntity implements ExtendedScreenHandlerFactory {
    public CookieTrayBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.COOKIE_TRAY_BLOCK_ENTITY, pos, state);
    }

    @Override
    public int getSizeInventory() {
        return 3;
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {
        packetByteBuf.writeBlockPos(pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new CookieTrayGUI(syncId, inv, ScreenHandlerContext.create(world, pos));
    }

    @Override
    public int getMaxCountPerStack() {
        return 3;
    }
}
