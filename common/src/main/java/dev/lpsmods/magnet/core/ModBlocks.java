package dev.lpsmods.magnet.core;

import dev.lpsmods.magnet.Constants;
import dev.lpsmods.magnet.block.MagnetBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class ModBlocks {
    public static final Block IRON_MAGNET_BLOCK;
    public static final Block GOLD_MAGNET_BLOCK;
    public static final Block COPPER_MAGNET_BLOCK;
    public static final Block DIAMOND_MAGNET_BLOCK;
    public static final Block NETHERITE_MAGNET_BLOCK;

    private static Block registerMagnetBlock(String name, float radius, int delay) {
        return registerBlock(name, new MagnetBlock(radius, delay, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).destroyTime(1.5f).forceSolidOn().pushReaction(PushReaction.NORMAL).randomTicks()));
    }

    private static Block registerBlock(String name, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), block);
    }

    public static void init () {}

    static {
        IRON_MAGNET_BLOCK = registerMagnetBlock("iron_magnet_block", 3.425F, 10);
        GOLD_MAGNET_BLOCK = registerMagnetBlock("gold_magnet_block", 5.425F, 8);
        COPPER_MAGNET_BLOCK = registerMagnetBlock("copper_magnet_block", 7.425F, 6);
        DIAMOND_MAGNET_BLOCK = registerMagnetBlock("diamond_magnet_block", 9.425F, 4);
        NETHERITE_MAGNET_BLOCK = registerMagnetBlock("netherite_magnet_block", 11.425F, 2);
    }
}
