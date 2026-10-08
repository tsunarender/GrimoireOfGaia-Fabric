package gaia.fabric.compat;

import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public final class Tags {
    private Tags() {}

    public static final class Items {
        public static final TagKey<Item> COBBLESTONES = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "cobblestones"));
        public static final TagKey<Item> INGOTS_IRON = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "ingots/iron"));
        public static final TagKey<Item> INGOTS_GOLD = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "ingots/gold"));
        public static final TagKey<Item> BONES = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "bones"));
        public static final TagKey<Item> OBSIDIANS = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "obsidians"));
        public static final TagKey<Item> RECORDS = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "records"));
        public static final TagKey<Item> NUGGETS_DIAMOND = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "nuggets/diamond"));
        public static final TagKey<Item> NUGGETS_EMERALD = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "nuggets/emerald"));
    }
}
