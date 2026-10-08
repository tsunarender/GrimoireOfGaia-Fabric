package gaia.fabric.compat;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public final class Tags {
    private Tags() {}

    public static final class Items {
        public static final TagKey<Item> COBBLESTONES = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "cobblestones"));
        public static final TagKey<Item> INGOTS_IRON = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/iron"));
        public static final TagKey<Item> INGOTS_GOLD = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/gold"));
        public static final TagKey<Item> BONES = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "bones"));
        public static final TagKey<Item> OBSIDIANS = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "obsidians"));
        public static final TagKey<Item> RECORDS = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "records"));
        public static final TagKey<Item> ENDER_PEARLS = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ender_pearls"));
        public static final TagKey<Item> GEMS_LAPIS = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gems/lapis"));
        public static final TagKey<Item> NUGGETS_DIAMOND = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/diamond"));
        public static final TagKey<Item> NUGGETS_EMERALD = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/emerald"));
    }
}
