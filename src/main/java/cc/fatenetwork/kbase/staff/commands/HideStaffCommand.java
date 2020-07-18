package cc.fatenetwork.kbase.staff.commands;

import cc.fatenetwork.kbase.Base;
import cc.fatenetwork.kbase.utils.StringUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HideStaffCommand implements CommandExecutor {
    private final Base plugin;

    public HideStaffCommand(Base plugin) {
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
        if (plugin.getStaffManager().hasStaffHidden(player)) {
            plugin.getStaffManager().setHideStaff(player, false);
            player.sendMessage(StringUtil.format("&aYou can now see all staff online."));
            return true;
        }
        plugin.getStaffManager().setHideStaff(player, true);
        player.sendMessage(StringUtil.format("&cYou will no longer see staff who are in vanish."));
        return false;
    }
}
