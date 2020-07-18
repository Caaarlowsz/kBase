package cc.fatenetwork.kbase.utils;

import net.mineaus.lunar.api.LunarClientAPI;
import net.mineaus.lunar.api.type.Notification;
import net.mineaus.lunar.api.type.ServerRule;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ClientAPI {

    private ClientAPI() {
        throw new UnsupportedOperationException("Cannot initialize utility class.");
    }

    public static boolean isClient(Player player) {
        return LunarClientAPI.INSTANCE().isAuthenticated(player);
    }

    public static void sendNotification(Player player, String message, int delay, TimeUnit timeUnit) throws IOException {
        LunarClientAPI.INSTANCE().sendNotification(player, message, delay);
    }

    public static void sendCooldown(Player player, String message, long delay, Material icon) throws IOException {
        LunarClientAPI.INSTANCE().sendCooldown(player, message, icon, delay);
    }

    public static void setWayPoint(Player player, String name, Location location, int color, boolean forced, boolean visible) {
        LunarClientAPI.INSTANCE().getWaypointManager().createWaypoint(name, player.getUniqueId(), location, 5, 4, 3, forced);
    }

    public static void sendTeamMate(Player player, Player... targets) throws IOException {
        LunarClientAPI.INSTANCE().sendTeamMate(player, targets);
    }

    public static void performEmote(Player player, int type, boolean everyone) throws IOException {
        LunarClientAPI.INSTANCE().performEmote(player, type, everyone);
    }

    public static void updateServerRule(Player player, ServerRule serverRule, boolean b, int i, float f, String s) throws IOException {
        LunarClientAPI.INSTANCE().updateServerRule(player, serverRule, b, i, f, s);
    }

    public static void sendTitle(Player player, boolean subtitle, String message, float size, int duration, int fadeIn, int fadeOut) throws IOException {
        LunarClientAPI.INSTANCE().sendTitle(player, subtitle, message, size, duration, fadeIn, fadeOut);
    }


}
