package codes.matthewp.desertedpvp.killstreak;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.stream.Collectors;

public class KillStreaks {

    private static int slot;

    private static ItemStack item;

    private static String name;
    private static Material mat;

    private static String guiName;
    private static int guiSlots;

    public static void giveKillStreaksItem(Player p) {
        p.getInventory().setItem(slot, item);
    }

    public static void loadKillStreaksItem(ConfigurationSection sec) {
        mat = Material.getMaterial(sec.getString("mat"));
        ItemStack stack = new ItemStack(mat);
        ItemMeta meta = stack.getItemMeta();
        name = color(sec.getString("name"));
        meta.setDisplayName(name);
        meta.setLore(colorList(sec.getStringList("lore")));
        stack.setItemMeta(meta);
        item = stack;
        slot = sec.getInt("slot") - 1;
    }

    public static void loadGUI(ConfigurationSection sec) {
        guiName = sec.getString("name");
        guiSlots = sec.getInt("slots");
    }

    public static void openGUI(Player p) {
        Inventory inv = Bukkit.createInventory(null, guiSlots, color(guiName));

        for (IKillStreak ks : KillStreakManager.getKillStreaks()) {
            inv.addItem(ks.getIcon());
        }

        p.openInventory(inv);
    }

    public static boolean isKillStreaks(ItemStack stack) {
        if (stack != null) {
            if (stack.getType() == mat) {
                String name2 = ChatColor.stripColor(name);
                String stackName2 = ChatColor.stripColor(stack.getItemMeta().getDisplayName());
                return name2.equals(stackName2);
            } else {
                return false;
            }
        } else {
            System.out.println("Somehow, a null item was passed to kit selector. Ignoring...");
            return false;
        }
    }

    public static String getGuiName() {
        return strip(color(guiName));
    }

    private static String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    private static List<String> colorList(List<String> list) {
        return list.stream().map(s -> ChatColor.translateAlternateColorCodes('&', s)).collect(Collectors.toList());
    }

    private static String strip(String s) {
        return ChatColor.stripColor(s);
    }
}
