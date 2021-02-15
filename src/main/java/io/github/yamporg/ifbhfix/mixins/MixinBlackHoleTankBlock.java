package io.github.yamporg.ifbhfix.mixins;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "com.buuz135.industrial.tile.block.BlackHoleTankBlock$TankCapabilityProvider$1", remap = false)
public abstract class MixinBlackHoleTankBlock {
    @Shadow(aliases = "val$itemStack")
    private ItemStack itemStack;

    @Inject(method = "setContainerToEmpty", at = @At("RETURN"))
    public void onSetContainerToEmpty(CallbackInfo ci) {
        if (!itemStack.hasTagCompound()) {
            return;
        }
        NBTTagCompound nbt = itemStack.getTagCompound();
        nbt.removeTag("FluidName"); // BlackHoleTankBlock.FLUID_NBT
        nbt.removeTag("Amount");
        nbt.removeTag("Tag");
    }
}
