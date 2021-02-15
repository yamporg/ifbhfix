package io.github.yamporg.ifbhfix.mixins;

import com.buuz135.industrial.tile.block.BlackHoleUnitBlock;
import io.github.yamporg.ifbhfix.ItemHandlerWrapper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.util.Constants;

@Mixin(BlackHoleUnitBlock.StorageItemHandler.class)
public abstract class MixinBlackHoleUnitBlock {
    @Shadow
    private IItemHandler itemHandler;

    @Inject(method = Constants.CTOR, at = @At("RETURN"))
    public void init(BlackHoleUnitBlock outer, ItemStack itemStack, CallbackInfo ci) {
        this.itemHandler = new ItemHandlerWrapper(itemStack, this.itemHandler);
    }
}
