package gaia.fabric;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketsApi;
import gaia.item.accessory.AbstractAccessoryItem;
import gaia.registry.GaiaRegistry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

/**
 * Optional bridge from Gaia's Curios accessories to Trinkets.
 */
public final class TrinketsCompat {
    private TrinketsCompat() {}

    public static void register() {
        List<? extends Item> accessoryItems = GaiaRegistry.ITEMS.getEntries().stream()
                .map(entry -> entry.get())
                .filter(item -> item instanceof AbstractAccessoryItem)
                .toList();

        for (Item item : accessoryItems) {
            AbstractAccessoryItem accessory = (AbstractAccessoryItem) item;
            TrinketsApi.registerTrinket(item, new Trinket() {
                @Override
                public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
                    accessory.onTick(entity, stack);
                }

                @Override
                public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
                    accessory.onEquip(entity, stack);
                }

                @Override
                public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
                    accessory.onUnequip(entity, stack);
                }
            });
        }
    }
}
