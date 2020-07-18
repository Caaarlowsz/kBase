package cc.fatenetwork.kbase.commands;

import cc.fatenetwork.kbase.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            if (!player.hasPermission("kBase.commands.eat")) {
                player.sendMessage(StringUtil.format("&cYou do not have permissions to execute this command."));
                return true;
            }
            player.setFoodLevel(100);
            player.setSaturation(3f);
        } else {
            if (!player.hasPermission("kBase.commands.eat.other")) {
                player.sendMessage(StringUtil.format("&cYou do not have permissions to execute this command."));
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(StringUtil.format("&cTarget not found."));
                return true;
            }
            target.setFoodLevel(100);
            target.setSaturation(3f);
        }
        return false;
    }
}
