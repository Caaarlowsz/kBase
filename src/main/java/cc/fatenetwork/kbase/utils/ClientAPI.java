package cc.fatenetwork.kbase.utils;

import lombok.SneakyThrows;
import net.mineaus.lunar.api.LunarClientAPI;
import net.mineaus.lunar.api.type.ServerRule;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import java.io.IOException;

public class ClientAPI {

    private ClientAPI() {
        throw new UnsupportedOperationException("Cannot initialize utility class.");
    }


    /**
     *
     * @param player Player
     * @return player has lunar client launched
     */
    public static boolean isClient(Player player) {
        return LunarClientAPI.INSTANCE().isAuthenticated(player);
    }

    /**
     *
     * @param player Player
     * @param message String message (limit characters)
     * @param delay amount in seconds until notification disappears
     */
    @SneakyThrows
    public static void sendNotification(Player player, String message, int delay) {
        LunarClientAPI.INSTANCE().sendNotification(player, message, delay);
    }

    /**
     * Does not work for 1.8
     * @param player Player
     * @param message String coodlown identifier
     * @param delay int delay in seconds until cooldown expires
     * @param icon Material of icon
     */
    @SneakyThrows
    public static void sendCooldown(Player player, String message, long delay, Material icon) {
        LunarClientAPI.INSTANCE().sendCooldown(player, message, icon, delay);
    }

    /**
     *
     * @param player Player
     * @param name String waypoint name
     * @param location Location waypoint location
     * @param forced boolean forced to appear
     */
    public static void setWayPoint(Player player, String name, Location location, boolean forced) {
        LunarClientAPI.INSTANCE().getWaypointManager().createWaypoint(name, player.getUniqueId(), location, 5, 4, 3, forced);
    }

    /**
     *
     * @param player Player
     * @param targets Player[]
     */
    @SneakyThrows
    public static void sendTeamMate(Player player, Player... targets) {
        LunarClientAPI.INSTANCE().sendTeamMate(player, targets);
    }

    /**
     *
     * @param player Player
     * @param type int emote type
     * @param everyone
     * @throws IOException @SneakyThrows
     */
    @SneakyThrows
    public static void performEmote(Player player, int type, boolean everyone) {
        LunarClientAPI.INSTANCE().performEmote(player, type, everyone);
    }

    /**
     *
     * @param player Player
     * @param serverRule ServerRule object
     * @param b enabled
     * @param i ?
     * @param f ?
     * @param s ?
     * @throws IOException @SneakyThrows
     */
    @SneakyThrows
    public static void updateServerRule(Player player, ServerRule serverRule, boolean b, int i, float f, String s) {
        LunarClientAPI.INSTANCE().updateServerRule(player, serverRule, b, i, f, s);
    }

    /**
     *
     * @param player Player
     * @param subtitle boolean if it is a subtitle
     * @param message String message
     * @param size int size
     * @param duration int duration in seconds
     * @param fadeIn int fadein in seconds
     * @param fadeOut int fadeout in seconds
     * @throws IOException @SneakyThrows
     */
    public static void sendTitle(Player player, boolean subtitle, String message, float size, int duration, int fadeIn, int fadeOut) throws IOException {
        LunarClientAPI.INSTANCE().sendTitle(player, subtitle, message, size, duration, fadeIn, fadeOut);
    }

    /**
     *
     * @param name String name
     * @param location Location location
     * @param lines List<String> lines
     */
    public static void createHologram(String name, Location location, String... lines) {
        LunarClientAPI.INSTANCE().getHologramManager().createHologram(name, location, lines);
    }

    /**
     *
     * @param player Player that will see the updated name tag
     * @param player1 Player whose name tag will be affected
     * @param string New tag can be multiple lines
     * @throws IOException @SneakyThrows
     */
    @SneakyThrows
    public static void updateNameTag(Player player, Player player1, String... string) {
        LunarClientAPI.INSTANCE().updateNameTag(player, player1, string);
    }

    @SneakyThrows
    public static void updateServerName(Player player, String server) {
        LunarClientAPI.INSTANCE().updateServerName(player, server);
    }

}
