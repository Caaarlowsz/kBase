package cc.fatenetwork.kbase.commands;

import cc.fatenetwork.kbase.Base;
import cc.fatenetwork.kbase.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class ListCommand implements CommandExecutor {
    private final Base plugin;

    public ListCommand(Base plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        List<String> message = new ArrayList<>();
        message.add("&7&m---------------------------------");
        message.add("&7There are &4" + visiblePlayers() + " &7online.");
        message.add("");
        if (staffVisible().size() == 0) {
            message.add("&cThere are no staff online.");
        } else {
            message.add("&7Staff online: ");
            staffVisible().forEach(staff -> message.add("&6* &c" + staff.getName()));
        }
        message.add("&7&m---------------------------------");
        message.forEach(msg -> player.sendMessage(StringUtil.format(msg)));
        return false;
    }

    private List<Player> staffVisible() {
        List<Player> toReturn;
        List<Player> staff = plugin.getStaffManager().getStaff();
        List<Player> vanish = plugin.getStaffManager().getVanish();
        toReturn = staff;
        toReturn.removeAll(vanish);
        return toReturn;
    }

    private int visiblePlayers() {
        int online = Bukkit.getOnlinePlayers().size();
        int vanish = plugin.getStaffManager().getVanish().size();
        return online - vanish;
    }
}
