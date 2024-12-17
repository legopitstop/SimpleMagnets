package dev.lpsmods.magnet;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class SimpleMagnets {

    public SimpleMagnets(IEventBus eventBus) {
        Bootstrap.init();
    }
}