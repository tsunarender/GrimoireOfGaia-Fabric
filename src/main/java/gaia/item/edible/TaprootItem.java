package gaia.item.edible;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TaprootItem extends Item {
    public TaprootItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (!level.isClientSide) {
            for (MobEffectInstance effect : livingEntity.getActiveEffects().toArray(MobEffectInstance[]::new)) {
                if (!effect.getEffect().value().isBeneficial()) {
                    livingEntity.removeEffect(effect.getEffect());
                }
            }
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
