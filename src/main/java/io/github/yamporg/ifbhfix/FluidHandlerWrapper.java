package io.github.yamporg.ifbhfix;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;

public class FluidHandlerWrapper extends FluidHandlerItemStack {
    public FluidHandlerWrapper(@Nonnull ItemStack container, int capacity) {
        super(container, capacity);
    }

    @Nullable
    @Override
    public FluidStack getFluid() {
        return FluidStack.loadFluidStackFromNBT(container.getTagCompound());
    }

    @Override
    protected void setFluid(@Nonnull FluidStack fluid) {
        container.setTagCompound(fluid.writeToNBT(new NBTTagCompound()));
    }

    @Override
    protected void setContainerToEmpty() {
        container.setTagCompound(null);
    }
}
