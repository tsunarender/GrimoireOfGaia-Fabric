package gaia.fabric;

import gaia.GrimoireOfGaia;
import gaia.registry.GaiaRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public final class GaiaCreativeTab {
    public static final ResourceKey<CreativeModeTab> KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(),
            ResourceLocation.fromNamespaceAndPath(GrimoireOfGaia.MOD_ID, "tab")
    );

    private GaiaCreativeTab() {}

    public static void register() {
        Registry.register(
                BuiltInRegistries.CREATIVE_MODE_TAB,
                KEY,
                FabricCreativeModeTab.builder()
                        .title(Component.literal("Grimoire of Gaia"))
                        .icon(() -> new ItemStack(GaiaRegistry.DOLL_DRYAD.get()))
                        .displayItems((context, entries) -> GaiaRegistry.ITEMS.getEntries().forEach(holder ->
                                entries.accept(new ItemStack(holder.get()))))
                        .build()
        );
    }
}
