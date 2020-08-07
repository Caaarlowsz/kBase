package cc.fatenetwork.kbase.gui;

import org.spigotmc.CaseInsensitiveMap;

import java.util.*;

public class GuiManager implements GuiInterface{
    private final Map<String, Gui> guiNameMap = new CaseInsensitiveMap<>();
    private final Map<UUID, Gui> guiUUIDMap = new HashMap<>();
    private List<Gui> guis = new ArrayList<>();

    @Override
    public List<Gui> getGUIS() {
        return this.guis;
    }

    @Override
    public Map<Integer, Page> getPages(Gui gui) {
        return gui.getPages();
    }

    @Override
    public Gui getGUI(String name) {
        return this.guiNameMap.get(name);
    }

    @Override
    public Page getPage(Gui gui, int page) {
        return gui.getPages().get(page);
    }

    @Override
    public Gui getGUI(UUID uuid) {
        return this.guiUUIDMap.get(uuid);
    }


    @Override
    public void createGUI(Gui gui) {
        this.guiUUIDMap.put(gui.getUuid(), gui);
        this.guiNameMap.put(gui.getName(), gui);
        this.guis.add(gui);
    }
}
