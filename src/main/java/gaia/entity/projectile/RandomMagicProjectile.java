package gaia.entity.projectile;

import gaia.registry.GaiaRegistry;
import gaia.util.SharedEntityData;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class RandomMagicProjectile extends SmallFireball {
    private Holder<MobEffect> effectHolder = MobEffects.MOVEMENT_SLOWDOWN;

    public RandomMagicProjectile(EntityType<? extends SmallFireball> entityType, Level level) {
        super(entityType, level);
    }

    public RandomMagicProjectile(Level level, LivingEntity owner, Vec3 velocity) {
        super(level, owner, velocity);
    }

    public RandomMagicProjectile(Level level, double x, double y, double z, Vec3 velocity) {
        super(level, x, y, z, velocity);
    }

    @Override
    public ItemStack getItem() {
        ItemStack stack = super.getItem();
        return stack.isEmpty() ? new ItemStack(GaiaRegistry.PROJECTILE_RANDOM_MAGIC.get()) : stack;
    }

    @Override
    public EntityType<?> getType() {
        return GaiaRegistry.RANDOM_MAGIC.get();
    }

    public Holder<MobEffect> getEffectHolder() {
        return effectHolder;
    }

    public void setEffectHolder(Holder<MobEffect> effectHolder) {
        this.effectHolder = effectHolder;
    }

    public void setEffect(Holder<MobEffect> effectHolder) {
        setEffectHolder(effectHolder);
    }

    @Override
    protected ParticleOptions getTrailParticle() {
        return ParticleTypes.END_ROD;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.tickCount > 60) {
            discard();
        }
    }

    @Override
    protected float getInertia() {
        return isInvulnerable() ? 0.73F : super.getInertia();
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString("EffectLocation", getEffectHolder().unwrapKey()
                .orElse(MobEffects.DARKNESS.unwrapKey().orElseThrow()).location().toString());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        Optional.ofNullable(ResourceLocation.tryParse(tag.getString("EffectLocation")))
                .map(location -> ResourceKey.create(Registries.MOB_EFFECT, location))
                .flatMap(key -> this.registryAccess().registryOrThrow(Registries.MOB_EFFECT)
                        .getHolder(key))
                .ifPresent(this::setEffect);
    }

    @Override
    protected void onHitBlock(BlockHitResult hitResult) {
        // No fire.
    }

    @Override
    protected void onHitEntity(EntityHitResult entityResult) {
        if (!this.level().isClientSide) {
            Entity owner = this.getOwner();
            if (owner instanceof LivingEntity ownerEntity) {
                Entity entity = entityResult.getEntity();
                entity.hurt(damageSources().indirectMagic(this, ownerEntity),
                        SharedEntityData.getAttackDamage2() / 2.0F);

                if (entity instanceof LivingEntity livingEntity) {
                    int effectTime = switch (this.level().getDifficulty()) {
                        case NORMAL -> 10;
                        case HARD -> 20;
                        default -> 0;
                    };
                    if (effectTime > 0) {
                        livingEntity.addEffect(new MobEffectInstance(
                                getEffectHolder(), effectTime * 20, 1));
                    }
                }
            }
        }
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        return false;
    }
}
