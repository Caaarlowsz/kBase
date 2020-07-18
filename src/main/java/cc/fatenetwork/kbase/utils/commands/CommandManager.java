package cc.fatenetwork.kbase.utils.commands;

public interface CommandManager {

    boolean containsCommand(BaseCommand var1);

    void registerAll(BaseCommandModule var1);

    void registerCommand(BaseCommand var1);

    void registerCommands(BaseCommand[] commands);

    void unregisterCommand(BaseCommand var1);

    BaseCommand getCommand(String var1);
}
