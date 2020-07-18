package cc.fatenetwork.kbase.staff;

import cc.fatenetwork.kbase.utils.StringUtil;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
public class StaffManager {
    private final Set<Player> staff = new HashSet<>();
    private final Set<Player> vanish = new HashSet<>();
    private final Set<Player> staffMode = new HashSet<>();
    private final Set<Player> hideStaff = new HashSet<>();
    private final Map<Player, ItemStack[]> contents = new HashMap<>();
    private final Map<Player, ItemStack[]> armorContents = new HashMap<>();

    public boolean isStaff(Player player) {
        return staff.contains(player);
    }

    public boolean isStaffMode(Player player) {
        return staffMode.contains(player);
    }

    public boolean isVanish(Player player) {
        return vanish.contains(player);
    }

    public boolean hasStaffHidden(Player player) {
        return hideStaff.contains(player);
    }


    public void setVanish(Player player, boolean v) {
        if (v) {
            vanish.add(player);
            Bukkit.getOnlinePlayers().forEach(online -> {
                if (hasStaffHidden(online)) {
                    online.hidePlayer(player);
                }
            });
            return;
        }
        Bukkit.getOnlinePlayers().forEach(online -> {
            if (hasStaffHidden(online)) {
                online.showPlayer(online);
            }
        });
        vanish.remove(player);
    }

    public void setStaffMode(Player player, boolean s) {
        if (s) {
            staffMode.add(player);
            contents.put(player, player.getInventory().getContents());
            armorContents.put(player, player.getInventory().getArmorContents());
            player.getInventory().clear();
            player.getInventory().setContents(getStaffInventory(player).getContents());
        } else {
            staffMode.remove(player);
            player.getInventory().clear();
            player.getInventory().setContents(contents.get(player));
            player.getInventory().setArmorContents(armorContents.get(player));
        }
    }

    public void setHideStaff(Player player, boolean h) {
        if (h) {
            hideStaff.add(player);
            Bukkit.getOnlinePlayers().forEach(online -> {
                if (isVanish(online)) {
                    player.hidePlayer(online);
                }
            });
            return;
        }
        hideStaff.remove(player);
        Bukkit.getOnlinePlayers().forEach(online -> {
            if (isVanish(online)) {
                player.showPlayer(online);
            }
        });
    }

    public Inventory getStaffInventory(Player player) {
        Inventory i = Bukkit.createInventory(null, 36, "");

        ItemStack itemStack;
        if (isVanish(player)) {
            itemStack = new ItemStack(Material.INK_SACK, 1, (byte) 8);
        } else {
            itemStack = new ItemStack(Material.INK_SACK, 1, (byte) 10);
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&c* &6&lVanish"));
        itemStack.setItemMeta(itemMeta);

        i.setItem(0, itemStack);

        return i;
    }
}
