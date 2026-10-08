package gaia.fabric.compat;

import net.minecraft.world.entity.Entity;

public final class EntityHooks {
    private EntityHooks() {}

    public static EntityTeleportEvent.EnderEntity onEnderTeleport(Entity entity, double x, double y, double z) {
        return new EntityTeleportEvent.EnderEntity(entity, x, y, z);
    }
}
