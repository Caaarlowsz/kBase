package cc.fatenetwork.kbase.utils;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.libs.jline.internal.Preconditions;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BukkitUtils {

    public static List<String> getCompletions(final String[] args, final List<String> input){
        return getCompletions(args, input, 80);
    }

    public static List<String> getCompletions(final String[] args, final List<String> input, final int limit){
        Preconditions.checkNotNull(args);
        Preconditions.checkNotNull(args.length != 0);
        final String argument = args[args.length - 1];
        return input.stream().filter(string -> string.regionMatches(true, 0, argument, 0, argument.length())).limit(limit).collect(Collectors.toList());
    }

    public static Player playerWithNameOrUUID(final String string){
        if(string == null){
            return null;
        }
        return JavaUtils.isUUID(string) ? Bukkit.getPlayer(UUID.fromString(string)) : Bukkit.getPlayer(string);
    }

    @Deprecated
    public static OfflinePlayer offlinePlayerWithNameOrUUID(final String string){
        if(string == null){
            return null;
        }
        return JavaUtils.isUUID(string) ? Bukkit.getOfflinePlayer(UUID.fromString(string)) : Bukkit.getOfflinePlayer(string);
    }
}