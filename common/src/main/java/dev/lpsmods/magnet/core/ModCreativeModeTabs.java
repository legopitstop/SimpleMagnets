package dev.lpsmods.magnet.core;

import dev.lpsmods.magnet.Constants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {
    public static final CreativeModeTab MAIN;

    private static CreativeModeTab registerTab(String name, CreativeModeTab tab) {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), tab);
    }

    public static void init () {}

    static {
        MAIN = registerTab("magnets", CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).title(Component.translatable("itemGroup."+Constants.MOD_ID)).icon(() -> {
            return new ItemStack(ModItems.DIAMOND_MAGNET);
        }).displayItems((CreativeModeTab.ItemDisplayParameters params, CreativeModeTab.Output out) -> {
            out.accept(ModItems.IRON_MAGNET);
            out.accept(ModItems.GOLD_MAGNET);
            out.accept(ModItems.COPPER_MAGNET);
            out.accept(ModItems.DIAMOND_MAGNET);
            out.accept(ModItems.NETHERITE_MAGNET);
            out.accept(ModItems.COPPER_MAGNET_BLOCK);
            out.accept(ModItems.DIAMOND_MAGNET_BLOCK);
            out.accept(ModItems.GOLD_MAGNET_BLOCK);
            out.accept(ModItems.IRON_MAGNET_BLOCK);
            out.accept(ModItems.NETHERITE_MAGNET_BLOCK);
        }).build());
    }
}
