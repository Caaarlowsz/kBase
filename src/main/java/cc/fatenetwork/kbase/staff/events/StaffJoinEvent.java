package cc.fatenetwork.kbase.staff.events;


import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class StaffJoinEvent extends PlayerEvent {
    private final static HandlerList handlers = new HandlerList();

    public StaffJoinEvent(Player player) {
        super(player);
        this.player = player;
    }

    public static HandlerList getHandlerList(){
        return handlers;
    }

    public HandlerList getHandlers(){
        return handlers;
    }

}
