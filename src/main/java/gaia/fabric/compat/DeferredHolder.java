package gaia.fabric.compat;

import java.util.Objects;
import java.util.function.Supplier;

public class DeferredHolder<R, T extends R> implements Supplier<T> {
    private String name;
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

    public String getName() { return name; }
    void setName(String name) { this.name = name; }

    void setRegistered(T value) {
        this.value = value;
    }
}
