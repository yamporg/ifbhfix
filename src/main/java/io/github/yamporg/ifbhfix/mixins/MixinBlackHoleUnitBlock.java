package io.github.yamporg.ifbhfix.mixins;

import com.buuz135.industrial.tile.misc.BlackHoleUnitTile;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "com.buuz135.industrial.tile.block.BlackHoleUnitBlock$StorageItemHandler$1", remap = false)
public abstract class MixinBlackHoleUnitBlock {
    @Shadow(aliases = "val$itemStack")
    private ItemStack itemStack;

    @Inject(method = "extractItem", at = @At("RETURN"))
    public void onExtractItem(int slot, int amount, boolean simulate, CallbackInfoReturnable<ItemStack> cir) {
        if (simulate) {
            return;
        }
        if (!itemStack.hasTagCompound()) {
            return;
        }
        NBTTagCompound nbt = itemStack.getTagCompound();
        int nbtAmount = nbt.getInteger(BlackHoleUnitTile.NBT_AMOUNT);
        if (nbtAmount > 0) {
            return;
        }
        nbt.removeTag(BlackHoleUnitTile.NBT_ITEMSTACK);
        nbt.removeTag(BlackHoleUnitTile.NBT_AMOUNT);
        nbt.removeTag(BlackHoleUnitTile.NBT_META);
        nbt.removeTag(BlackHoleUnitTile.NBT_ITEM_NBT);
    }
}
