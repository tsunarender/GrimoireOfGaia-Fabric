package gaia.fabric;

public final class FabricEvents {
    private FabricEvents() {}
    public static void register() {
        // Gaia's special drops and curing behavior are implemented directly in
        // the affected entities/items on Fabric, so no global NeoForge event bus is needed.
    }
}
