package gaia.attachment.friended;

import net.minecraft.nbt.CompoundTag;
import java.util.UUID;

public class Friended implements IFriended {
    private boolean friended = false;
    private UUID friendedBy = null;
    private boolean changed = false;

    public boolean isFriendly() { return friended; }
    public UUID getFriendedBy() { return friendedBy; }
    public void setFriendedBy(UUID value) { this.friendedBy = value; }
    public boolean isChanged() { return changed; }
    public void setFriendly(boolean value) { this.friended = value; this.changed = true; }
    public void setChanged(boolean value) { this.changed = value; }

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("friended", friended);
        if (friendedBy != null) tag.putUUID("friendedBy", friendedBy);
        return tag;
    }

    public void deserializeNBT(CompoundTag tag) {
        friended = tag.getBoolean("friended");
        friendedBy = tag.contains("friendedBy") ? tag.getUUID("friendedBy") : null;
    }
}