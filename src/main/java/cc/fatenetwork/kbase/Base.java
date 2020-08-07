package cc.fatenetwork.kbase;

import cc.fatenetwork.kbase.commands.*;
import cc.fatenetwork.kbase.gui.GuiInterface;
import cc.fatenetwork.kbase.gui.GuiManager;
import cc.fatenetwork.kbase.gui.impl.TestGUI;
import cc.fatenetwork.kbase.staff.StaffListener;
import cc.fatenetwork.kbase.staff.StaffManager;
import cc.fatenetwork.kbase.staff.commands.HideStaffCommand;
import cc.fatenetwork.kbase.staff.commands.StaffModeCommand;
import cc.fatenetwork.kbase.staff.commands.VanishCommand;
import cc.fatenetwork.kbase.utils.ClientAPI;
import cc.fatenetwork.kbase.utils.commands.CommandManager;
import cc.fatenetwork.kbase.utils.commands.SimpleCommandManager;
import cc.fatenetwork.kbase.utils.itemdb.ItemDb;
import cc.fatenetwork.kbase.utils.itemdb.SimpleItemDb;
import lombok.Getter;
import net.mineaus.lunar.api.LunarClientAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

@Getter
public final class Base extends JavaPlugin {
    private final Logger log = Bukkit.getLogger();
    private StaffManager staffManager;
    private ItemDb itemDb;
    private CommandManager commandManager;
    private GuiInterface guiInterface;
    private TestGUI testGUI;
    @Getter private static Base plugin;

    @Override
    public void onEnable() {
        plugin = this;
        log.info("[kBase] initializing...");
        File file = new File(getDataFolder() + "config.yml");
        if (!file.exists()) {
            saveConfig();
        }
        log.info("[kBase] loading managers...");
        registerManagers();
        log.info("[kBase] loading events");
        registerEvents();
        log.info("[kBase] loading commands.");
        registerCommands();
        log.info("[kBase] done!");
    }

    @Override
    public void onDisable() {
        plugin = null;
    }

    private void registerManagers() {
        commandManager = new SimpleCommandManager(this);
        staffManager = new StaffManager();
        itemDb = new SimpleItemDb(this);
        guiInterface = new GuiManager();
        testGUI = new TestGUI();
    }


    private void registerEvents() {
        Arrays.asList(new StaffListener(this)).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    private void registerCommands() {
        getCommand("adventure").setExecutor(new AdventureCommand());
        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getCommand("creative").setExecutor(new CreativeCommand());
        getCommand("eat").setExecutor(new EatCommand());
        getCommand("enderchest").setExecutor(new EnderChestCommand());
        getCommand("hidestaff").setExecutor(new HideStaffCommand(this));
        getCommand("list").setExecutor(new ListCommand(this));
        getCommand("staffmode").setExecutor(new StaffModeCommand(this));
        getCommand("vanish").setExecutor(new VanishCommand(this));
        getCommand("gui").setExecutor(new TestGUI());
    }
}
