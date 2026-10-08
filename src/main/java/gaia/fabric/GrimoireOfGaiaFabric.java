package gaia.fabric;

import gaia.registry.GaiaRegistry;
import gaia.registry.GaiaSounds;
import net.fabricmc.api.ModInitializer;

public final class GrimoireOfGaiaFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        GaiaRegistry.BLOCKS.register();
        GaiaRegistry.ITEMS.register();
        GaiaRegistry.ENTITIES.register();
        GaiaRegistry.CREATIVE_MODE_TABS.register();
        GaiaSounds.SOUND_EVENTS.register();
        FabricSpawns.registerAttributesAndSpawns();
        FabricEvents.register();
    }
}