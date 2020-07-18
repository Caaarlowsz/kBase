package cc.fatenetwork.kbase.staff.commands;

import cc.fatenetwork.kbase.Base;
import cc.fatenetwork.kbase.staff.events.StaffModeDisableEvent;
import cc.fatenetwork.kbase.staff.events.StaffModeEnableEvent;
import cc.fatenetwork.kbase.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffModeCommand implements CommandExecutor {
    private final Base plugin;

    public StaffModeCommand(Base plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("kbase.staff")) {
            player.sendMessage(StringUtil.format("&cYou do not have permissions to execute this command."));
            return true;
        }
        if (plugin.getStaffManager().isStaffMode(player)) {
            StaffModeDisableEvent event = new StaffModeDisableEvent(player);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                return true;
            }
            plugin.getStaffManager().setStaffMode(player, false);
            player.sendMessage(StringUtil.format("&cYou are no longer in staff mode."));
            if (plugin.getStaffManager().isVanish(player)) {
                plugin.getStaffManager().setVanish(player, false);
            }
            return true;
        }
        StaffModeEnableEvent event = new StaffModeEnableEvent(player);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return true;
        }
        plugin.getStaffManager().setStaffMode(player, true);
        player.sendMessage(StringUtil.format("&aYou are now in staff mode."));
        if (!plugin.getStaffManager().isVanish(player)) {
            plugin.getStaffManager().setVanish(player, true);
        }
        return false;
    }
}
