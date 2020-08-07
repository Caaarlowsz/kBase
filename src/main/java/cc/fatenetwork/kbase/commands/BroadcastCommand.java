package cc.fatenetwork.kbase.commands;

import cc.fatenetwork.kbase.utils.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

public class BroadcastCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        int position;
        boolean raw;
        if(args.length < 1){
            sender.sendMessage("&c/" + s + " <-raw|msg> <msg>");
            return true;
        }
        if(args.length > 1 && args[0].startsWith("-raw")){
            position = 1;
            raw = true;
        }else{
            position = 0;
            raw = false;
        }
        String message = StringUtils.join(args, ' ', position, args.length);
        if(raw){
            if(message.length() < 3){
                sender.sendMessage(ChatColor.RED + "Character limit not met, must have atleast 3 characters.");
                return true;
            }
        }else if(message.length() < 4){
            sender.sendMessage(ChatColor.RED + "Character limit not met, must have atleast 4 characters.");
            return true;
        }
        message = !raw ? ChatColor.translateAlternateColorCodes('&', String.format(Locale.ENGLISH, ChatColor.translateAlternateColorCodes('&',"&[&4&lALERT&8] "), message)) : ChatColor.translateAlternateColorCodes('&', message);
        Bukkit.broadcastMessage(message);
        return false;
    }
}
