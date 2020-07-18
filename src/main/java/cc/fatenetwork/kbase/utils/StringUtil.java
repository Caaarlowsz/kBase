package cc.fatenetwork.kbase.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

    public static String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> format(List<String> message) {
        List<String> toReturn = new ArrayList<>();

        for (String s : message) {
            s = ChatColor.translateAlternateColorCodes('&', s);
            toReturn.add(s);
        }

        return toReturn;
    }
}
