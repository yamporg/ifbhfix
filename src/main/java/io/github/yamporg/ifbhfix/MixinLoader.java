package io.github.yamporg.ifbhfix;

import org.spongepowered.asm.mixin.Mixins;

@zone.rong.mixinbooter.MixinLoader
public class MixinLoader {
    { Mixins.addConfiguration("ifbhfix.mixins.json"); }
}
