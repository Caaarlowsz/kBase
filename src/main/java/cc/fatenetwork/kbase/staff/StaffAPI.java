package cc.fatenetwork.kbase.staff;

import cc.fatenetwork.kbase.Base;
import org.bukkit.entity.Player;
import java.util.List;

public class StaffAPI {

    public static boolean isStaff(Player player) {
        return Base.getPlugin().getStaffManager().isStaff(player);
    }

    public static boolean isStaffMode(Player player) {
        return Base.getPlugin().getStaffManager().isStaffMode(player);
    }

    public static boolean isVanish(Player player) {
        return Base.getPlugin().getStaffManager().isVanish(player);
    }

    public static boolean hasStaffHidden(Player player) {
        return Base.getPlugin().getStaffManager().hasStaffHidden(player);
    }

    public static List<Player> getStaffOnline() {
        return Base.getPlugin().getStaffManager().getStaff();
    }
}
