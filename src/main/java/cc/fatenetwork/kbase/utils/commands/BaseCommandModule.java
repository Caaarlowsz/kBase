package cc.fatenetwork.kbase.utils.commands;

import com.google.common.collect.Sets;

import java.util.Set;

public class BaseCommandModule {
    protected final Set<BaseCommand> commands = Sets.newHashSet();
    private boolean enabled = true;

    Set<BaseCommand> getCommands() {
        return this.commands;
    }

    void unregisterCommand(BaseCommand command) {
        this.commands.remove(command);
    }

    void unregisterCommands() {
        this.commands.clear();
    }

    boolean isEnabled() {
        return this.enabled;
    }

    void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
