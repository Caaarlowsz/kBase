package cc.fatenetwork.kbase.commands;

import cc.fatenetwork.kbase.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdventureCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("kbase.commands.adventure")) {
            player.sendMessage(StringUtil.format("&cYou do not have permissions to execute this command."));
            return true;
        }
        if (args.length == 0) {
            player.setGameMode(GameMode.ADVENTURE);
        } else {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(StringUtil.format("&cTarget not found."));
                return true;
            }
            target.setGameMode(GameMode.ADVENTURE);
        }
        return false;
    }
}
