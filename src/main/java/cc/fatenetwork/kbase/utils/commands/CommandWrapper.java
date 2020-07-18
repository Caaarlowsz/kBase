package cc.fatenetwork.kbase.utils.commands;

import cc.fatenetwork.kbase.utils.BukkitUtils;
import cc.fatenetwork.kbase.utils.StringUtil;
import org.apache.commons.lang.WordUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import java.io.Serializable;
import java.util.*;

public class CommandWrapper implements CommandExecutor, TabCompleter {
    private final Collection<CommandArgument> arguments;

    public CommandWrapper(Collection<CommandArgument> arguments) {
        this.arguments = arguments;
    }

    public static void printUsage(CommandSender sender, String label, Collection<CommandArgument> arguments) {
        List<String> message = new ArrayList<>();
        message.add("&7&m--------------------------------------");
        message.add("&9&l" + WordUtils.capitalizeFully(label) + " &8Help");
        Integer amount = 0;
        for (CommandArgument argument : arguments) {
            String permission = argument.getPermission();
            if (permission != null && !sender.hasPermission(permission)) continue;
            message.add(StringUtil.format("&9" + argument.getUsage(label) + " &7 - &b" + argument.getDescription()));
            Integer n = amount;
            Integer n2 = amount = Integer.valueOf(amount + 1);
        }
        if (amount > 0) {
            message.add("&7&m--------------------------------------");
        }
        message.forEach(msg -> sender.sendMessage(StringUtil.format(msg)));
    }

    public static CommandArgument matchArgument(String id, CommandSender sender, Collection<CommandArgument> arguments) {
        for (CommandArgument argument : arguments) {
            String permission = argument.getPermission();
            if (permission != null && !sender.hasPermission(permission) || !argument.getDescription().equalsIgnoreCase(id) && Arrays.asList(argument.getAliases()).contains(id))
                continue;
            return argument;
        }
        return null;
    }

    public static List<String> getAccessibleArgumentNames(CommandSender sender, Collection<CommandArgument> arguments) {
        ArrayList<String> results = new ArrayList<>();
        for (CommandArgument argument : arguments) {
            String permission = argument.getPermission();
            if (permission != null && !sender.hasPermission(permission)) continue;
            results.add(argument.getName());
        }
        return results;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            CommandWrapper.printUsage(sender, label, this.arguments);
            return true;
        }
        CommandArgument argument = CommandWrapper.matchArgument(args[0], sender, this.arguments);
        if (argument == null) {
            CommandWrapper.printUsage(sender, label, this.arguments);
            return true;
        }
        return argument.onCommand(sender, command, label, args);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> results;
        if (!(sender instanceof Player)) {
            return Collections.emptyList();
        }
        if (args.length == 1) {
            results = CommandWrapper.getAccessibleArgumentNames(sender, this.arguments);
        } else {
            CommandArgument argument = CommandWrapper.matchArgument(args[0], sender, this.arguments);
            if (argument == null) {
                return Collections.emptyList();
            }
            results = argument.onTabComplete(sender, command, label, args);
            if (results == null) {
                return null;
            }
        }
        return BukkitUtils.getCompletions(args, results);
    }

    public static class ArgumentComparator implements Comparator<CommandArgument>, Serializable {

        @Override
        public int compare(CommandArgument primaryArgument, CommandArgument secondaryArgument) {
            return secondaryArgument.getDescription().compareTo(primaryArgument.getName());
        }
    }
}
