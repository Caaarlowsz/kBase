package cc.fatenetwork.kbase.staff;

import cc.fatenetwork.kbase.Base;
import cc.fatenetwork.kbase.staff.events.StaffJoinEvent;
import cc.fatenetwork.kbase.staff.events.StaffModeDisableEvent;
import cc.fatenetwork.kbase.staff.events.StaffModeEnableEvent;
import cc.fatenetwork.kbase.utils.ClientAPI;
import cc.fatenetwork.kbase.utils.StringUtil;
import lombok.SneakyThrows;
import net.mineaus.lunar.api.LunarClientAPI;
import net.mineaus.lunar.api.type.StaffModule;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class StaffListener implements Listener {
    private final Base plugin;

    public StaffListener(Base plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new BukkitRunnable() {
            @Override
            public void run() {
                if (player.hasPermission("kbase.staff")) {
                    plugin.getStaffManager().getStaff().add(player);
                    StaffJoinEvent e = new StaffJoinEvent(player);
                    Bukkit.getPluginManager().callEvent(e);
                }
            }
        }, 40L);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (plugin.getStaffManager().isStaffMode(player)) {
            if (event.getPlayer().getItemInHand() != null) {
                ItemStack itemStack = player.getItemInHand();
                if (itemStack.hasItemMeta()) {
                    if (itemStack.getItemMeta().getDisplayName().contains("Vanish")) {
                        player.performCommand("vanish");
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (plugin.getStaffManager().isStaffMode(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickUpItem(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        if (plugin.getStaffManager().isStaffMode(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onStaffJoin(StaffJoinEvent event) {
        Player player = event.getPlayer();
        StaffAPI.getStaffOnline().forEach(staff -> staff.sendMessage(StringUtil.format("&c" + player.getName() + " &7has joined the server.")));
    }

    @SneakyThrows
    @EventHandler
    public void onStaffEnterEvent(StaffModeEnableEvent event) {
        Player player = event.getPlayer();
        if (ClientAPI.isClient(player)) {
            ClientAPI.sendNotification(player, "You have entered staff mode.", 3, TimeUnit.SECONDS);
            LunarClientAPI.INSTANCE().toggleStaffModule(player, StaffModule.BUNNY_HOP, true);
            LunarClientAPI.INSTANCE().toggleStaffModule(player, StaffModule.NAME_TAGS, true);
            LunarClientAPI.INSTANCE().toggleStaffModule(player, StaffModule.XRAY, true);
            LunarClientAPI.INSTANCE().toggleStaffModule(player, StaffModule.NO_CLIP, true);
        }
    }

    @SneakyThrows
    @EventHandler
    public void onStaffLeaveEvent(StaffModeDisableEvent event) {
        Player player = event.getPlayer();
        if (ClientAPI.isClient(player)) {
            ClientAPI.sendNotification(player, "You have left staff mode.", 3, TimeUnit.SECONDS);
            LunarClientAPI.INSTANCE().toggleStaffModule(player, StaffModule.BUNNY_HOP, true);
            LunarClientAPI.INSTANCE().toggleStaffModule(player, StaffModule.NAME_TAGS, true);
            LunarClientAPI.INSTANCE().toggleStaffModule(player, StaffModule.XRAY, true);
            LunarClientAPI.INSTANCE().toggleStaffModule(player, StaffModule.NO_CLIP, true);
        }
    }

}
