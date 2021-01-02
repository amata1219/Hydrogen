package amata1219.ragnarok;

import amata1219.ragnarok.dsl.ExecutableItem;
import org.bukkit.NamespacedKey;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class Ragnarok extends JavaPlugin implements RagnarokAPI {

    private final ExecutableItemRegistry executableItemRegistry = new ExecutableItemRegistry();

    @Override
    public void onEnable() {
        Constants.executable_item_id = new NamespacedKey(this, "executable_item_id");

        getServer().getPluginManager().registerEvents(new PlayerExecuteItemListener(executableItemRegistry), this);
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }

    @Override
    public void register(ExecutableItem item) {
        executableItemRegistry.register(item);
    }

    @Override
    public void unregister(ExecutableItem item) {
        executableItemRegistry.unregister(item);
    }

    @Override
    public boolean isRegistered(ExecutableItem item) {
        return executableItemRegistry.isRegistered(item);
    }

}
