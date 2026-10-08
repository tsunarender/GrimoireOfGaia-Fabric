package gaia.fabric;

import gaia.config.GaiaConfig;
import gaia.registry.GaiaRegistry;
import gaia.registry.GaiaSounds;
import io.github.fabricators_of_create.porting_lib.config.ConfigRegistry;
import io.github.fabricators_of_create.porting_lib.config.ModConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public final class GrimoireOfGaiaFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        GaiaRegistry.BLOCKS.register();
        GaiaRegistry.ITEMS.register();
        GaiaRegistry.ENTITIES.register();
        GaiaRegistry.CREATIVE_MODE_TABS.register();
        GaiaSounds.SOUND_EVENTS.register();

        ConfigRegistry.registerConfig("grimoireofgaia", ModConfig.Type.CLIENT, GaiaConfig.clientSpec);
        ConfigRegistry.registerConfig("grimoireofgaia", ModConfig.Type.COMMON, GaiaConfig.commonSpec);

        FabricSpawns.registerAttributesAndSpawns();
        FabricEvents.register();
        if (FabricLoader.getInstance().isModLoaded("trinkets")) {
            TrinketsCompat.register();
        }
    }
}