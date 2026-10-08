package gaia.attachment;

import gaia.attachment.friended.Friended;
import gaia.entity.AbstractGaiaEntity;
import net.minecraft.world.entity.LivingEntity;

/** Fabric replacement for Gaia's NeoForge attachment lookup. */
public final class AttachmentHandler {
    private AttachmentHandler() {}

    public static Friended getFriended(LivingEntity livingEntity) {
        if (livingEntity instanceof AbstractGaiaEntity gaiaEntity) {
            return gaiaEntity.getFabricFriended();
        }
        return null;
    }
}
