package io.github.yamporg.ifbhfix;

import com.buuz135.industrial.tile.misc.BlackHoleUnitTile;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

public final class ItemHandlerWrapper implements IItemHandler {
    public ItemStack itemStack;
    public IItemHandler itemHandler;

    public ItemHandlerWrapper(ItemStack itemStack, IItemHandler itemHandler) {
        this.itemStack = itemStack;
        this.itemHandler = itemHandler;
    }

    @Nonnull
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        ItemStack r = itemHandler.extractItem(slot, amount, simulate);
        if (simulate) {
            return r;
        }
        if (!itemStack.hasTagCompound()) {
            return r;
        }
        NBTTagCompound nbt = itemStack.getTagCompound();
        int nbtAmount = nbt.getInteger(BlackHoleUnitTile.NBT_AMOUNT);
        if (nbtAmount > 0) {
            return r;
        }
        nbt.removeTag(BlackHoleUnitTile.NBT_ITEMSTACK);
        nbt.removeTag(BlackHoleUnitTile.NBT_AMOUNT);
        nbt.removeTag(BlackHoleUnitTile.NBT_META);
        nbt.removeTag(BlackHoleUnitTile.NBT_ITEM_NBT);
        return r;
    }

    public int getSlots() {
        return itemHandler.getSlots();
    }

    @Nonnull
    public ItemStack getStackInSlot(int slot) {
        return itemHandler.getStackInSlot(slot);
    }

    @Nonnull
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return itemHandler.insertItem(slot, stack, simulate);
    }

    public int getSlotLimit(int slot) {
        return itemHandler.getSlotLimit(slot);
    }
}