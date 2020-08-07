package cc.fatenetwork.kbase.items;

import cc.fatenetwork.kbase.utils.StringUtil;
import lombok.Data;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@Data
public class Item {
    private String displayName;
    private ItemStack itemStack;
    private ItemMeta itemMeta;
    private List<String> lore;
    private byte b;

    public Item(String displayName, Material material, List<String> lore, int amount) {
        this.displayName = displayName;
        this.itemStack = new ItemStack(material, amount);
        this.lore = lore;
        createItem();
    }

    public Item(String displayName, Material material, int amount) {
        this.displayName = displayName;
        this.itemStack = new ItemStack(material, amount);
        createItem();
    }

    public Item(String displayName, Material material, List<String> lore, int amount, byte b) {
        this.displayName = displayName;
        this.itemStack = new ItemStack(material, amount, b);
        this.lore = lore;
        this.b = b;
    }

    void createItem() {
        this.itemMeta = itemStack.getItemMeta();
        this.itemMeta.setDisplayName(StringUtil.format(this.displayName));
        this.itemMeta.setLore(StringUtil.format(this.lore));
        this.itemStack.setItemMeta(itemMeta);
    }
}
