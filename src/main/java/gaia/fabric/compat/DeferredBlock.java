package gaia.fabric.compat;

import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class DeferredBlock<T extends Block> extends DeferredHolder<Block, T> {
    public DeferredBlock(Supplier<? extends T> supplier) {
        super(supplier);
    }
}
