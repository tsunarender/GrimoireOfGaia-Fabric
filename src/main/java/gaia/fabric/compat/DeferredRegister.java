package gaia.fabric.compat;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

@SuppressWarnings({"unchecked", "rawtypes"})
public class DeferredRegister<T> {
    protected final Registry<T> registry;
    protected final String modId;
    protected final Map<String, DeferredHolder<T, ?>> entries = new LinkedHashMap<>();

    protected DeferredRegister(Registry<T> registry, String modId) {
        this.registry = registry;
        this.modId = modId;
    }

    public static <T> DeferredRegister<T> create(Registry<T> registry, String modId) {
        return new DeferredRegister<>(registry, modId);
    }

    public static <T> DeferredRegister<T> create(ResourceKey<? extends Registry<T>> key, String modId) {
        Registry<T> registry = (Registry<T>) BuiltInRegistries.REGISTRY
                .get(key.location())
                .orElseThrow(() -> new IllegalArgumentException("Unknown registry " + key.location()));
        return new DeferredRegister<>(registry, modId);
    }

    public static Blocks createBlocks(String modId) {
        return new Blocks(BuiltInRegistries.BLOCK, modId);
    }

    public static Items createItems(String modId) {
        return new Items(BuiltInRegistries.ITEM, modId);
    }

    public <I extends T> DeferredHolder<T, I> register(String name, Supplier<? extends I> supplier) {
        DeferredHolder<T, I> holder = new DeferredHolder<>(supplier);
        holder.setName(name);
        entries.put(name, holder);
        return holder;
    }

    public Set<DeferredHolder<T, ?>> getEntries() {
        return Set.copyOf(entries.values());
    }

    public void register() {
        for (Map.Entry<String, DeferredHolder<T, ?>> entry : entries.entrySet()) {
            DeferredHolder holder = entry.getValue();
            ResourceLocation id = ResourceLocation.fromNamespaceAndPath(modId, entry.getKey());
            Object value = holder.get();
            Registry.register(registry, id, value);
            holder.setRegistered(value);
        }
    }

    public static class Items extends DeferredRegister<Item> {
        protected Items(Registry<Item> registry, String modId) {
            super(registry, modId);
        }

        @Override
        public <I extends Item> DeferredItem<I> register(String name, Supplier<? extends I> supplier) {
            DeferredItem<I> holder = new DeferredItem<>(supplier);
            holder.setName(name);
            entries.put(name, holder);
            return holder;
        }

        public <B extends Block> DeferredItem<BlockItem> registerSimpleBlockItem(DeferredBlock<B> block) {
            return registerSimpleBlockItem(block, new Item.Properties());
        }

        public <B extends Block> DeferredItem<BlockItem> registerSimpleBlockItem(DeferredBlock<B> block, Item.Properties properties) {
            return register(block.getName(), () -> new BlockItem(block.get(), properties));
        }
    }

    public static class Blocks extends DeferredRegister<Block> {
        protected Blocks(Registry<Block> registry, String modId) {
            super(registry, modId);
        }

        @Override
        public <B extends Block> DeferredBlock<B> register(String name, Supplier<? extends B> supplier) {
            DeferredBlock<B> holder = new DeferredBlock<>(supplier);
            holder.setName(name);
            entries.put(name, holder);
            return holder;
        }
    }
}
