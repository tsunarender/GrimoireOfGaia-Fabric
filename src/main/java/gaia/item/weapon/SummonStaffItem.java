package gaia.item.weapon;

import gaia.registry.GaiaRegistry;
import gaia.fabric.compat.EntityHooks;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import java.util.List;
import java.util.function.Supplier;

public class SummonStaffItem extends Item {
    private final Supplier<EntityType<? extends Mob>> typeSupplier;
    private final Supplier<Ingredient> repairIngredient;

    public SummonStaffItem(Properties properties, Supplier<EntityType<? extends Mob>> typeSupplier, Supplier<Ingredient> repairIngredient) {
        super(properties);
        this.typeSupplier = typeSupplier;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairStack) {
        return repairIngredient.get().test(repairStack);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int timeLeft) {
        super.releaseUsing(stack, level, livingEntity, timeLeft);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof net.minecraft.world.entity.player.Player player) {
            stack.hurtAndBreak(1, player, Player.getSlotForHand(player.getUsedItemHand()));

            if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
                BlockPos spawnPos = BlockPos.containing(player.getEyePosition()).relative(player.getDirection());
                Mob summon = typeSupplier.get().create(level);
                if (summon != null) {
                    summon.moveTo(spawnPos, 0.0F, 0.0F);
                    EntityHooks.finalizeMobSpawn(summon, serverLevel, level.getCurrentDifficultyAt(spawnPos),
                            MobSpawnType.MOB_SUMMONED, null);
                    summon.setItemSlot(EquipmentSlot.HEAD, new ItemStack(GaiaRegistry.HEADGEAR_BOLT.get()));
                    summon.setDropChance(EquipmentSlot.MAINHAND, 0);
                    summon.setDropChance(EquipmentSlot.OFFHAND, 0);
                    summon.setDropChance(EquipmentSlot.FEET, 0);
                    summon.setDropChance(EquipmentSlot.LEGS, 0);
                    summon.setDropChance(EquipmentSlot.CHEST, 0);
                    summon.setDropChance(EquipmentSlot.HEAD, 0);
                    summon.setPersistenceRequired();
                    level.addFreshEntity(summon);
                }
            }

            player.playSound(SoundEvents.CHICKEN_EGG, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
        } else {
            stack.shrink(1);
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, net.minecraft.world.entity.player.Player player, InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        player.startUsingItem(interactionHand);
        return InteractionResultHolder.consume(itemstack);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(stack, context, list, flag);
        list.add(Component.translatable("text.grimoireofgaia.summoning_staff.desc",
                Component.translatable(typeSupplier.get().getDescriptionId()).getString()).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity livingEntity) {
        return 30;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }
}
