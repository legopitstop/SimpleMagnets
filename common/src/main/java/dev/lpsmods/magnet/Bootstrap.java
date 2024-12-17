package dev.lpsmods.magnet;

import dev.lpsmods.magnet.core.ModBlocks;
import dev.lpsmods.magnet.core.ModCreativeModeTabs;
import dev.lpsmods.magnet.core.ModItems;

public class Bootstrap {
    public static void init() {
        ModBlocks.init();
        ModItems.init();
        ModCreativeModeTabs.init();
    }
}