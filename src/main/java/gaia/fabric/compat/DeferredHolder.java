package gaia.fabric.compat;

import java.util.Objects;
import java.util.function.Supplier;

public class DeferredHolder<R, T extends R> implements Supplier<T> {
    private final Supplier<? extends T> supplier;
    private T value;

    public DeferredHolder(Supplier<? extends T> supplier) {
        this.supplier = Objects.requireNonNull(supplier);
    }

    @Override
    public T get() {
        if (value == null) value = supplier.get();
        return value;
    }

    void setRegistered(T value) {
        this.value = value;
    }
}
