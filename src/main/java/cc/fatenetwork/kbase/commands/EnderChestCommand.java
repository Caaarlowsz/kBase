package cc.fatenetwork.kbase.commands;

import cc.fatenetwork.kbase.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderChestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("kbase.commands.enderchest")) {
            player.sendMessage(StringUtil.format("&cYou do not have permissions to execute this command."));
            return true;
        }
        if (args.length == 0) {
            player.openInventory(player.getEnderChest());
        } else {
            if (!player.hasPermission("kbase.commands.enderchest.other")) {
                player.sendMessage(StringUtil.format("&cYou do not have permissions to execute this command."));
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(StringUtil.format("&cTarget not found."));
                return true;
            }
            player.openInventory(target.getEnderChest());
        }
        return false;
    }
}
