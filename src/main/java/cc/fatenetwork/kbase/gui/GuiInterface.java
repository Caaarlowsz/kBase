package cc.fatenetwork.kbase.gui;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface GuiInterface {

    List<Gui> getGUIS();

    Map<Integer, Page> getPages(Gui gui);

    Gui getGUI(String name);

    Page getPage(Gui gui, int page);

    Gui getGUI(UUID uuid);

    void createGUI(Gui gui);
}
