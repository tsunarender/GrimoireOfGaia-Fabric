package gaia.fabric;

import net.fabricmc.api.ClientModInitializer;

public final class GrimoireOfGaiaFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FabricClient.register();
    }
}