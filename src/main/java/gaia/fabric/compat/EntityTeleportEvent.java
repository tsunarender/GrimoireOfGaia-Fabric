package gaia.fabric.compat;

import net.minecraft.world.entity.Entity;

public final class EntityTeleportEvent {
    private EntityTeleportEvent() {}

    public static final class EnderEntity {
        private final Entity entity;
        private final double targetX, targetY, targetZ;
        private boolean canceled;

        public EnderEntity(Entity entity, double targetX, double targetY, double targetZ) {
            this.entity = entity;
            this.targetX = targetX;
            this.targetY = targetY;
            this.targetZ = targetZ;
        }

        public boolean isCanceled() { return canceled; }
        public void setCanceled(boolean canceled) { this.canceled = canceled; }
        public double getTargetX() { return targetX; }
        public double getTargetY() { return targetY; }
        public double getTargetZ() { return targetZ; }
        public Entity getEntity() { return entity; }
    }
}
