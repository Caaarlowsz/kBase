package cc.fatenetwork.kbase.gui;

import cc.fatenetwork.kbase.items.Item;
import lombok.Data;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

@Data
public class Page {
    private UUID uuid;
    private String name;
    private Inventory i;
    private int page;
    private Gui gui;

    /**
     *
     * @param name displayName of the inventory title
     * @param gui GUI object which it extends onto
     * @param page Integer of page number
     * @param itemStacks Items
     */
    public Page(String name, Gui gui, int page, ItemStack... itemStacks) {
        this.uuid = UUID.randomUUID();
        this.gui = gui;
        this.page = page;
        this.i = gui.getI();
        createPage();
    }

    /**
     *
     * @param name displayName of the inventory title
     * @param gui GUI object which it extends onto
     * @param page Integer of page number
     */
    public Page(String name, Gui gui, int page) {
        this.uuid = UUID.randomUUID();
        this.gui = gui;
        this.page = page;
        this.i = gui.getI();
        createPage();
    }

    void createPage() {
        this.gui.getPages().put(page, this);
    }

    public void addItem(Item item) {
        this.i.addItem(item.getItemStack());
    }

    public void addItem(ItemStack itemStack) {
        this.i.addItem(itemStack);
    }
}
