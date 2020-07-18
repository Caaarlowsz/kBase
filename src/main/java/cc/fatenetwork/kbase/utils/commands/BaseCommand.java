package cc.fatenetwork.kbase.utils.commands;

import cc.fatenetwork.kbase.utils.BukkitUtils;
import cc.fatenetwork.kbase.utils.StringUtil;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.regex.Pattern;

public class BaseCommand extends ArgumentExecutor {
    private static final Pattern USAGE_REPLACER_PATTERN = Pattern.compile("(command)", 16);
    private final String name;
    private final String description;
    private String[] aliases;
    private String usage;

    public BaseCommand(String name, String description) {
        super(name);
        this.name = name;
        this.description = description;
    }

    public static boolean checkNull(CommandSender sender, String player) {
        Player target = BukkitUtils.playerWithNameOrUUID(player);
        if (target == null || !BaseCommand.canSee(sender, target)) {
            sender.sendMessage(StringUtil.format("&cPlayer with name or uuid not found."));
            return true;
        }
        return false;
    }

    public static boolean canSee(CommandSender sender, Player target) {
        return target != null && (!(sender instanceof Player) || ((Player) sender).canSee(target));
    }

    public final String getPermission(){
        return "base.command." + this.name;
    }

    public boolean isPlayerOnlyCommand(){
        return false;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public String getUsage(){
        if(this.usage == null){
            this.usage = "";
        }
        return ChatColor.RED + "Usage: " + USAGE_REPLACER_PATTERN.matcher(this.usage).replaceAll(this.name);
    }

    public void setUsage(String usage){
        this.usage = usage;
    }

    public String getUsage(String label){
        return ChatColor.RED + "" + USAGE_REPLACER_PATTERN.matcher(this.usage).replaceAll(label);
    }

    public String[] getAliases(){
        if(this.aliases == null){
            this.aliases = ArrayUtils.EMPTY_STRING_ARRAY;
        }
        return Arrays.copyOf(this.aliases, this.aliases.length);
    }

    protected void setAliases(String[] aliases){
        this.aliases = aliases;
    }
}
