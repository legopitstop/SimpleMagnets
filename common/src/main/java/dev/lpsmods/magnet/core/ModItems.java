package dev.lpsmods.magnet.core;

import dev.lpsmods.magnet.Constants;
import dev.lpsmods.magnet.item.MagnetItem;
import dev.lpsmods.magnet.item.ModTiers;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;

public class ModItems {
    public static final Item IRON_MAGNET;
    public static final Item GOLD_MAGNET;
    public static final Item COPPER_MAGNET;
    public static final Item DIAMOND_MAGNET;
    public static final Item NETHERITE_MAGNET;
    public static final Item COPPER_MAGNET_BLOCK;
    public static final Item DIAMOND_MAGNET_BLOCK;
    public static final Item GOLD_MAGNET_BLOCK;
    public static final Item IRON_MAGNET_BLOCK;
    public static final Item NETHERITE_MAGNET_BLOCK;

    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), item);
    }

    private static Item registerMagnet(String name, Tier tier, double delay, int radius) {
        return registerItem(name, new MagnetItem(tier,delay, radius, new Item.Properties().stacksTo(1)));
    }

    private static Item registerBlock(String name, Block block) {
        return registerItem(name, new BlockItem(block, new Item.Properties()));
    }

    public static void init() {}

    static {
        IRON_MAGNET = registerMagnet("iron_magnet", Tiers.IRON,3.425, 100);
        GOLD_MAGNET = registerMagnet("gold_magnet", Tiers.GOLD,5.425, 80);
        COPPER_MAGNET = registerMagnet("copper_magnet", ModTiers.COPPER,7.425, 60);
        DIAMOND_MAGNET = registerMagnet("diamond_magnet", Tiers.DIAMOND,9.425, 40);
        NETHERITE_MAGNET = registerMagnet("netherite_magnet", Tiers.NETHERITE,11.425, 20);
        COPPER_MAGNET_BLOCK = registerBlock("copper_magnet_block", ModBlocks.COPPER_MAGNET_BLOCK);
        DIAMOND_MAGNET_BLOCK = registerBlock("diamond_magnet_block", ModBlocks.DIAMOND_MAGNET_BLOCK);
        GOLD_MAGNET_BLOCK = registerBlock("gold_magnet_block", ModBlocks.GOLD_MAGNET_BLOCK);
        IRON_MAGNET_BLOCK = registerBlock("iron_magnet_block", ModBlocks.IRON_MAGNET_BLOCK);
        NETHERITE_MAGNET_BLOCK = registerBlock("netherite_magnet_block", ModBlocks.NETHERITE_MAGNET_BLOCK);
    }
}
