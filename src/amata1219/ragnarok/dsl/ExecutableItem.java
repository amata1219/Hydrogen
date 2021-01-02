package amata1219.ragnarok.dsl;

import amata1219.ragnarok.event.PlayerDamageEntityEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public abstract class ExecutableItem {

    public final String id;

    protected ExecutableItem(String identifier) {
        this.id = identifier;
    }

    public abstract ItemStack buildItemStack(Player user);

    protected ItemStack define(Consumer<BukkitItem> settings) {
        BukkitItem bukkitItem = new BukkitItem();
        settings.accept(bukkitItem);
        return bukkitItem.build(id);
    }

    public void onClick(PlayerInteractEvent action) {

    }

    public void onConsume(PlayerItemConsumeEvent action) {

    }

    public void onDamageEntity(PlayerDamageEntityEvent action) {

    }

}
