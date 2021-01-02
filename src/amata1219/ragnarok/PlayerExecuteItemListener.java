package amata1219.ragnarok;

import amata1219.ragnarok.dsl.ExecutableItem;
import amata1219.ragnarok.event.PlayerDamageEntityEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class PlayerExecuteItemListener implements Listener {

    private final ExecutableItemRegistry registry;

    public PlayerExecuteItemListener(ExecutableItemRegistry registry) {
        this.registry = registry;
    }

    @EventHandler(ignoreCancelled = true)
    public void on(PlayerInteractEvent event) {
        if (event.getAction() == Action.PHYSICAL || !event.hasItem()) return;

        ExecutableItem item = toExecutableItem(event.getItem());
        if (item != null) item.onClick(event);
    }

    @EventHandler(ignoreCancelled = true)
    public void on(PlayerItemConsumeEvent event) {
        ExecutableItem item = toExecutableItem(event.getItem());
        if (item != null) item.onConsume(event);
    }

    @EventHandler(ignoreCancelled = true)
    public void on(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;

        Player damager = (Player) event.getDamager();
        ExecutableItem item = toExecutableItem(damager.getInventory().getItemInMainHand());
        if (item != null) item.onDamageEntity(new PlayerDamageEntityEvent(event));

    }

    private ExecutableItem toExecutableItem(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return null;

        String executableItemId = item.getItemMeta().getPersistentDataContainer().get(Constants.executable_item_id(), PersistentDataType.STRING);
        if (executableItemId == null) return null;

        return registry.get(executableItemId);
    }

}
