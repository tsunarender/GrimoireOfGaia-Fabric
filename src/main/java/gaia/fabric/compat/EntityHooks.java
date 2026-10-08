package gaia.fabric.compat;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;

public final class EntityHooks {
    private EntityHooks() {}

    public static EntityTeleportEvent.EnderEntity onEnderTeleport(Entity entity, double x, double y, double z) {
        return new EntityTeleportEvent.EnderEntity(entity, x, y, z);
    }
    
    public static <T extends Mob> T finalizeMobSpawn(
            T mob, ServerLevel level, DifficultyInstance difficulty,
            MobSpawnType spawnType, SpawnGroupData data) {
        mob.finalizeSpawn(level, difficulty, spawnType, data);
        return mob;
    }
}

