package cc.fatenetwork.kbase.gui.impl;


import cc.fatenetwork.kbase.gui.Gui;
import cc.fatenetwork.kbase.gui.Page;
import cc.fatenetwork.kbase.items.Item;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestGUI implements CommandExecutor {

    public Gui getMainGUI() {
        Gui gui = new Gui("Test GUI", 9);
        Item item = new Item("Test Item", Material.PAPER, 1);
        gui.addItem(item);
        return gui;
    }

    public Page getPage1() {
        Page page = new Page("Test Page", getMainGUI(), 1);
        Item item = new Item("Test Item", Material.PAPER, 1);
        page.addItem(item);
        getMainGUI().getPages().put(1, page);
        return page;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        player.openInventory(getMainGUI().getI());
        return false;
    }
}
