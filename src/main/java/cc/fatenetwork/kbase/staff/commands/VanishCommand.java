package cc.fatenetwork.kbase.staff.commands;

import cc.fatenetwork.kbase.Base;
import cc.fatenetwork.kbase.staff.events.StaffVanishEvent;
import cc.fatenetwork.kbase.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {
    private final Base plugin;

    public VanishCommand(Base plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("kbase.staff")) {
            player.sendMessage(StringUtil.format("&cYou do not have permissiosn to execute this command."));
            return true;
        }
        if (plugin.getStaffManager().isVanish(player)) {
            StaffVanishEvent event = new StaffVanishEvent(player);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                return true;
            }
            plugin.getStaffManager().setVanish(player, false);
            if (plugin.getStaffManager().isStaffMode(player)) {
                plugin.getStaffManager().setStaffMode(player, true);
            }
            player.sendMessage(StringUtil.format("&aYou are now visible."));
            return true;
        }
        StaffVanishEvent event = new StaffVanishEvent(player);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return true;
        }
        plugin.getStaffManager().setVanish(player, true);
        if (plugin.getStaffManager().isStaffMode(player)) {
            plugin.getStaffManager().setStaffMode(player, true);
        }
        player.sendMessage(StringUtil.format("&7You are now in &cvanish."));
        return false;
    }
}
