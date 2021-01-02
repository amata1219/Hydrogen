package amata1219.hydrogen.dsl;

import amata1219.hydrogen.event.PlayerDamageEntityEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public abstract class ExecutableBukkitItem {

    public final String id;

    protected ExecutableBukkitItem(String identifier) {
        this.id = identifier;
    }

    public abstract ItemStack buildItemStack(Player user);

    protected ItemStack define(Consumer<BukkitItem> settings) {
        BukkitItem bukkitItem = new BukkitItem();
        settings.accept(bukkitItem);
        return bukkitItem.build(id);
    }

    public void onClick(Consumer<PlayerInteractEvent> action) {

    }

    public void onConsume(Consumer<PlayerItemConsumeEvent> action) {

    }

    public void onAttackEntity(Consumer<PlayerDamageEntityEvent> action) {

    }

}
