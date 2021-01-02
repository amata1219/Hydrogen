package amata1219.hydrogen;

import org.bukkit.NamespacedKey;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class Hydrogen extends JavaPlugin {

    @Override
    public void onEnable() {
        Constants.executable_item_id = new NamespacedKey(this, "executable_item_id");
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }

}
