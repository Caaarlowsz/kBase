package cc.fatenetwork.kbase.staff.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class StaffModeDisableEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;

    public StaffModeDisableEvent(Player player) {
        super(player);
    }

    public static HandlerList getHandlerList(){
        return handlers;
    }

    public HandlerList getHandlers(){
        return handlers;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }
}
