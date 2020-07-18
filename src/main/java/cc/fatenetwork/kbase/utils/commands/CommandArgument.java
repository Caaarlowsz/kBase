package cc.fatenetwork.kbase.utils.commands;

import lombok.Getter;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
public abstract class CommandArgument {
    private final String name;
    protected boolean isPlayerOnly = false;
    protected String description;
    protected String permission;
    protected String[] aliases;

    public CommandArgument(String name, String description) {
        this(name, description, (String) null);
    }

    public CommandArgument(String name, String description, String permission) {
        this(name, description, permission, ArrayUtils.EMPTY_STRING_ARRAY);
    }

    public CommandArgument(String name, String description, String[] aliases) {
        this(name, description, null, aliases);
    }

    public CommandArgument(String name, String description, String permission, String[] aliases) {
        this.name = name;
        this.description = description;
        this.permission = permission;
        this.aliases = aliases;
    }

    public abstract String getUsage(String var1);

    public abstract boolean onCommand(CommandSender sender, Command command, String label, String[] args);

    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return Collections.emptyList();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommandArgument)) {
            return false;
        }
        CommandArgument that = (CommandArgument) o;
        if (!(this.name != null ? this.name.equalsIgnoreCase(that.name) : that.name == null)) {
            return false;
        }
        if (!(this.description != null ? this.description.equalsIgnoreCase(that.description) : that.description == null)) {
            return false;
        }
        if (!(this.permission != null ? this.permission.equalsIgnoreCase(that.permission) : that.permission == null)) {
            return ArrayUtils.isEquals(this.aliases, that.aliases);
        }
        return false;
    }

    public int hashCode() {
        int result = this.name != null ? this.name.hashCode() : 0;
        result = 31 * result + (this.description != null ? this.description.hashCode() : 0);
        result = 31 * result + (this.permission != null ? this.permission.hashCode() : 0);
        result = 31 * result + (this.aliases != null ? Arrays.hashCode(this.aliases) : 0);
        return result;
    }

}
