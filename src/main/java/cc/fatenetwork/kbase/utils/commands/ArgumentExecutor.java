package cc.fatenetwork.kbase.utils.commands;

import cc.fatenetwork.kbase.utils.BukkitUtils;
import cc.fatenetwork.kbase.utils.StringUtil;
import cc.fatenetwork.kbase.utils.chat.ClickAction;
import cc.fatenetwork.kbase.utils.chat.Text;
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArgumentExecutor implements CommandExecutor, TabCompleter {
    protected final List<CommandArgument> arguments = new ArrayList<>();
    private final String label;

    public ArgumentExecutor(String label) {
        this.label = label;
    }

    public boolean containsArgument(CommandArgument argument) {
        return this.arguments.contains(argument);
    }

    public void addArgument(CommandArgument argument) {
        this.arguments.add(argument);
    }

    public void removeArgument(CommandArgument argument) {
        this.arguments.remove(argument);
    }

    public CommandArgument getArgument(String id) {
        for (CommandArgument argument : this.arguments) {
            String name = argument.getName();
            if (!name.equalsIgnoreCase(id) && !Arrays.asList(argument.getAliases()).contains(id.toLowerCase())) continue;
            return argument;
        }
        return null;
    }

    public String getLabel() {
        return this.label;
    }

    public List<CommandArgument> getArguments() {
        return ImmutableList.copyOf(this.arguments);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String permission2;
        List<String> message = new ArrayList<>();
        if (args.length < 1) {
            message.add("&7&m--------------------------------------------------");
            message.add("&9&l" + WordUtils.capitalizeFully(label) + " &8Help &7(Page &b1 &7out of &71)");
            message.add("");
            for (CommandArgument argument : arguments) {
                String permission = argument.getPermission();
                if (permission != null && !sender.hasPermission(permission)) continue;
                new Text(StringUtil.format("&9" + argument.getUsage(label) + " &7- &b" + argument.getDescription())).setClick(ClickAction.SUGGEST_COMMAND, "/" + argument.getUsage(label)).setColor(ChatColor.GRAY).send(sender);
            }
            message.add("&7&m--------------------------------------------------");
            return true;
        }
        CommandArgument argument = this.getArgument(args[0]);
        String string = permission2 = argument == null ? null : argument.getPermission();
        if (argument == null || permission2 != null && !sender.hasPermission(permission2)) {
            sender.sendMessage(StringUtil.format("&c" + WordUtils.capitalizeFully(this.label) + " sub-command " + args[0] + " not found."));
        }
        argument.onCommand(sender, command, label, args);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> results = new ArrayList<String>();
        if (args.length < 2) {
            for (CommandArgument argument : arguments) {
                String permission = argument.getPermission();
                if (permission != null && !sender.hasPermission(permission)) continue;
                results.add(argument.getName());
            }
        } else {
            CommandArgument argument = getArgument(args[0]);
            if (argument == null) {
                return results;
            }
            String permission = argument.getPermission();
            if ((permission == null || sender.hasPermission(permission)) && (results = argument.onTabComplete(sender, command, label, args)) == null) {
                return null;
            }
        }
        return BukkitUtils.getCompletions(args, results);
    }
}
