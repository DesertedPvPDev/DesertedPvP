package codes.matthewp.hypepvp.kit;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Interface rep of a kit object
 */
public class IKit {

    public String name;
    public int price;
    public List<String> lore;
    public Material iconMat;
    public String perm;

    public String intelID() {
        return null;
    }

    public String getName() {
        return name;
    }

    public String getPerm() {
        return perm;
    }

    public int getPrice() {
        return price;
    }

    public List<String> getLore() {
        return lore;
    }

    public void giveKit(Player p) {

    }

    public void gotKill(Player killer, Player killed) {

    }

    public boolean canKill(Player p) {
        return true;
    }

    public void load(ConfigurationSection sec) {
        name = sec.getString("name");
        price = sec.getInt("price");
        iconMat = Material.getMaterial(sec.getString("icon"));
        lore = sec.getStringList("lore");
        perm = sec.getString("perm");
    }

    public ItemStack icon() {
        ItemStack stack = new ItemStack(iconMat);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(color(getName()));
        meta.setLore(colorList(getLore()));
        stack.setItemMeta(meta);
        return stack;
    }

    public String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public List<String> colorList(List<String> list) {
        return list.stream().map(s -> ChatColor.translateAlternateColorCodes('&', s)).collect(Collectors.toList());
    }
}
