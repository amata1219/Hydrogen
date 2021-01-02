### 使用法
pluginsフォルダに本プラグインを導入して下さい。<br>
また、本プラグインをライブラリとして使用するプラグインは、plugin.ymlのdepend項目にRagnarokを加えて下さい。

### 使用例
```Java
import amata1219.ragnarok.RagnarokAPI;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        //APIを取得する
        //pluginsフォルダに本プラグインが導入されており、plugin.ymlも正しく設定されていれば、無検査キャストでも問題ありません
        RagnarokAPI ragnarokAPI = (RagnarokAPI) getServer().getPluginManager().getPlugin("Ragnarok");

        //アイテムの一意な識別子を設定した上で登録する
        ragnarokAPI.register(new SampleExecutableItem("sample_executable_item"));
    }

    @Override
    public void onDisable() {

    }

}
```
```Java
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
```