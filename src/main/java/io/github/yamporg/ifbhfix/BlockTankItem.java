package io.github.yamporg.ifbhfix;

import com.buuz135.industrial.tile.block.BlackHoleTankBlock;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class BlockTankItem extends BlackHoleTankBlock.BlockTankItem {
    public BlockTankItem(Block block) {
        ((BlackHoleTankBlock) block).super(block);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new FluidHandlerWrapper(stack);
    }
}
