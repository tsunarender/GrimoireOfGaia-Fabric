package gaia.fabric.compat;

import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class DeferredItem<T extends Item> extends DeferredHolder<Item, T> {
    public DeferredItem(Supplier<? extends T> supplier) {
        super(supplier);
    }
}
