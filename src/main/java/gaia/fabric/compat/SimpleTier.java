package gaia.fabric.compat;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class SimpleTier implements Tier {
    private final TagKey<Block> incorrect;
    private final int uses, enchantmentValue;
    private final float speed, attackDamage;
    private final Supplier<Ingredient> repair;

    public SimpleTier(TagKey<Block> incorrect, int uses, float speed, float attackDamage, int enchantmentValue, Supplier<Ingredient> repair) {
        this.incorrect = incorrect;
        this.uses = uses;
        this.speed = speed;
        this.attackDamage = attackDamage;
        this.enchantmentValue = enchantmentValue;
        this.repair = repair;
    }

    @Override public int getUses() { return uses; }
    @Override public float getSpeed() { return speed; }
    @Override public float getAttackDamageBonus() { return attackDamage; }
    @Override public int getEnchantmentValue() { return enchantmentValue; }
    @Override public Ingredient getRepairIngredient() { return repair.get(); }
    @Override public TagKey<Block> getIncorrectBlocksForDrops() { return incorrect; }
}
