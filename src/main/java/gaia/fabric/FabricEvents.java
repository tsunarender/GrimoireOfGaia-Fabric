package gaia.fabric;

import gaia.item.edible.TaprootItem;
import gaia.entity.AbstractGaiaEntity;
import io.github.fabricators_of_create.porting_lib.entity.events.living.LivingDropsEvent;
import io.github.fabricators_of_create.porting_lib.entity.events.living.MobEffectEvent;

public final class FabricEvents {
    private FabricEvents() {}

    public static void register() {
        LivingDropsEvent.EVENT.register(event -> {
            if (event.getEntity() instanceof AbstractGaiaEntity gaia && gaia.isStaffSummoned()) {
                event.getDrops().clear();
            }
        });

        MobEffectEvent.Added.EVENT.register(event -> {
            if (event.getEffectInstance() != null && !event.getEffectInstance().getEffect().value().isBeneficial()) {
                event.getEffectInstance().getCures().add(TaprootItem.TAPROOT);
            }
        });
    }
}