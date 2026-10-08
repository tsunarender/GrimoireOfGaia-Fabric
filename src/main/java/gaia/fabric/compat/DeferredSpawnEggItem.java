package gaia.fabric.compat;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.SpawnEggItem;

import java.util.function.Supplier;

public class DeferredSpawnEggItem extends SpawnEggItem {
    public DeferredSpawnEggItem(Supplier<? extends EntityType<? extends Mob>> type, int backgroundColor, int highlightColor, Properties properties) {
        super(type.get(), backgroundColor, highlightColor, properties);
    }
}
