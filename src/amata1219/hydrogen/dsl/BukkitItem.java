package amata1219.hydrogen.dsl;

import amata1219.hydrogen.Constants;
import amata1219.hydrogen.enchantment.GleamEnchantment;
import com.google.common.collect.Iterables;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;
import java.util.function.Consumer;

public class BukkitItem {

    public ItemStack baseItemStack;
    public Material type = Material.AIR;
    public int amount = 1;
    public int damage;
    public String displayName;
    public List<String> lore = new ArrayList<>();
    public Map<Enchantment, Integer> enchantments = new HashMap<>();
    public Set<ItemFlag> flags = new HashSet<>();
    public Consumer<ItemStack> raw = Constants.noOperation();

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

    public ItemStack build(String id){
        ItemStack item = baseItemStack != null ? baseItemStack : new ItemStack(type);

        item.setAmount(amount);

        ItemMeta meta = item.getItemMeta();

        if(meta instanceof Damageable) ((Damageable) meta).setDamage(damage);
        meta.setDisplayName(displayName);
        meta.setLore(lore);
        enchantments.forEach((enchantment, level) -> meta.addEnchant(enchantment, level, true));
        meta.addItemFlags(Iterables.toArray(flags, ItemFlag.class));
        meta.getPersistentDataContainer().set(Constants.executable_item_id(), PersistentDataType.STRING, id);
        item.setItemMeta(meta);

        raw.accept(item);
        return item;
    }

}
