package cc.fatenetwork.kbase.gui;

import cc.fatenetwork.kbase.Base;
import cc.fatenetwork.kbase.items.Item;
import cc.fatenetwork.kbase.utils.StringUtil;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

@Data
public class Gui {
   private UUID uuid;
   private Inventory i;
   private String name;
   private List<ItemStack> items;
   private List<Item> itemList;
   private Map<Integer, Page> pages = new HashMap<>();
   private Base plugin;


   public Gui(String name, int size, ItemStack... itemStack) {
       this.uuid = UUID.randomUUID();
       this.i = Bukkit.createInventory(null, size, StringUtil.format(name));
       this.i.addItem(itemStack);
       plugin.getGuiInterface().createGUI(this);
   }

   public Gui(String name, int size) {
       this.uuid = UUID.randomUUID();
       this.i = Bukkit.createInventory(null, size, StringUtil.format(name));
       plugin.getGuiInterface().createGUI(this);
   }

    public Gui(String name, int size, Item... items) {
        this.uuid = UUID.randomUUID();
        this.i = Bukkit.createInventory(null, size, StringUtil.format(name));
        plugin.getGuiInterface().createGUI(this);
    }

    public void addItem(Item item) {
       getI().addItem(item.getItemStack());
    }

    public void addItem(ItemStack itemStack) {
        getI().addItem(itemStack);
    }

}
