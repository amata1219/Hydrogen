package amata1219.ragnarok;

import amata1219.ragnarok.dsl.ExecutableItem;
import amata1219.ragnarok.event.PlayerDamageEntityEvent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class SampleExecutableItem extends ExecutableItem {

    //このアイテムの一意な識別子を設定する
    public SampleExecutableItem(String id) {
        super(id);
    }

    //生成するアイテムの設定をする
    @Override
    public ItemStack buildItemStack(Player executor) {
        return define(i -> {
            //種類を指定する
            i.type = Material.SWEET_BERRIES;

            //表示名を設定する
            i.displayName = ChatColor.WHITE + "spinulose sweet berries";

            //個数を設定する
            i.amount = 7;

            //説明文を設定する
            i.lore(
                    "this is a little strange sweet berries.",
                    "no one but a which should touch this."
            );

            //エンチャントオーラを付与する
            i.gleam();

            //エンチャントをする
            i.enchant(Enchantment.ARROW_FIRE, 1);
        });
    }

    //クリック時のアクションを設定する
    @Override
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Location clicked = event.getClickedBlock().getLocation();
        clicked.getWorld().strikeLightning(clicked);
    }

    //アイテムを食べた時のアクションを設定する
    @Override
    public void onConsume(PlayerItemConsumeEvent event) {
        if (event.getPlayer().getLevel() < 50) event.setCancelled(true);
    }

    //エンティティに攻撃した時のアクションを設定する
    @Override
    public void onDamageEntity(PlayerDamageEntityEvent event) {
        event.setDamage(120);
    }

}
