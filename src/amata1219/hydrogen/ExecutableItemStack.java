package amata1219.hydrogen;

import com.google.common.collect.Iterables;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.function.Consumer;

public class ExecutableItemStack {

    public ItemStack baseItemStack;
    public Material type = Material.AIR;
    public int amount = 1;
    public int damage;
    public String displayName;
    public List<String> lore = new ArrayList<>();
    public Map<Enchantment, Integer> enchantments = new HashMap<>();
    public Set<ItemFlag> flags = new HashSet<>();
    public Consumer<ItemStack> raw = Constants.noOperation();
    public Consumer<PlayerInteractEvent> actionOnClick = Constants.noOperation();
    public Consumer<PlayerItemConsumeEvent> actionOnConsume = Constants.noOperation();

    public void lore(String... lines){
        lore.addAll(Arrays.asList(lines));
    }

    public void enchant(Enchantment enchantment, int level){
        enchantments.put(enchantment, level);
    }

    public void gleam(){
        enchant(GleamEnchantment.INSTANCE, 1);
    }

    public void flags(ItemFlag... flags){
        this.flags.addAll(Arrays.asList(flags));
    }

    public ItemStack buildItemStack(){
        ItemStack item = baseItemStack != null ? baseItemStack : new ItemStack(type);
        apply(item);
        return item;
    }

    private void apply(ItemStack item) {
        item.setAmount(amount);

        ItemMeta meta = item.getItemMeta();
        if(meta != null){
            if(meta instanceof Damageable) ((Damageable) meta).setDamage(damage);
            meta.setDisplayName(displayName);
            meta.setLore(lore);
            enchantments.forEach((enchantment, level) -> meta.addEnchant(enchantment, level, true));
            meta.addItemFlags(Iterables.toArray(flags, ItemFlag.class));
            item.setItemMeta(meta);
        }

        raw.accept(item);
    }

}
