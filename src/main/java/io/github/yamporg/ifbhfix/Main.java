package io.github.yamporg.ifbhfix;

import com.buuz135.industrial.proxy.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(
        modid = Main.MOD_ID,
        name = Main.NAME,
        version = Main.VERSION,
        dependencies = "required-after:industrialforegoing")
@Mod.EventBusSubscriber
public class Main {
    static final String MOD_ID = "ifbhfix";
    static final String NAME = "IFBHFix";
    static final String VERSION = "0.0.5";

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void overrideTankItem(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> reg = event.getRegistry();

        Block block = BlockRegistry.blackHoleTankBlock;
        ResourceLocation key = block.getRegistryName();
        if (key == null || !reg.containsKey(key)) {
            return;
        }
        reg.register(new BlockTankItem(block).setRegistryName(key));
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void overrideUnitItem(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> reg = event.getRegistry();

        Block block = BlockRegistry.blackHoleUnitBlock;
        ResourceLocation key = block.getRegistryName();
        if (key == null || !reg.containsKey(key)) {
            return;
        }
        reg.register(new BlockStorageItem(block).setRegistryName(key));
    }
}
