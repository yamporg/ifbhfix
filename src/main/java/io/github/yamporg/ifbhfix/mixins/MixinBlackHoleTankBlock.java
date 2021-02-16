package io.github.yamporg.ifbhfix.mixins;

import com.buuz135.industrial.tile.block.BlackHoleTankBlock;
import io.github.yamporg.ifbhfix.FluidHandlerWrapper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.util.Constants;

@Mixin(value = BlackHoleTankBlock.class)
public abstract class MixinBlackHoleTankBlock {
    @Mixin(value = BlackHoleTankBlock.TankCapabilityProvider.class)
    public static abstract class MixinTankCapabilityProvider {
        @Mutable
        @Final
        @Shadow(remap = false)
        public FluidHandlerItemStack fluidHandlerItemStack;

        @Inject(method = Constants.CTOR, at = @At("RETURN"))
        public void init(BlackHoleTankBlock outer, ItemStack itemStack, CallbackInfo ci) {
            this.fluidHandlerItemStack = new FluidHandlerWrapper(itemStack, Integer.MAX_VALUE);
        }
    }
}
