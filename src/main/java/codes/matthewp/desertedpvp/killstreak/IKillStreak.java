package codes.matthewp.desertedpvp.killstreak;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class IKillStreak {

    private String interID;
    private ItemStack icon;
    private int cost;
    private String perm;

    public IKillStreak(String interID) {
        this.interID = interID;
    }

    public void execute(Player p) {
        p.sendMessage(ChatColor.RED + "Error: KillStreak Impl execute not overridden");
    }

    public void load(ConfigurationSection config) {

    }

    public void loadIcon(ConfigurationSection config) {
        ItemStack stack = new ItemStack(Material.getMaterial(config.getString("mat")));
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(color(config.getString("name")));
        List<String> lore = new ArrayList<>();
        lore.add(color(config.getString("lore")));
        meta.setLore(lore);
        stack.setItemMeta(meta);
        icon = stack;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public String getInterID() {
        return interID;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public String getPerm() {
        return perm;
    }

    public void setPerm(String perm) {
        this.perm = perm;
    }
}
