package codes.matthewp.hypepvp.loot;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SupplyLoot {

    private static List<ItemStack> possibleItems = new ArrayList<>();

    public static void generateLoot(Inventory inv) {
        Random ran = new Random();
        for (int i = 0; i < 4; i++) {
            int slot = ran.nextInt(inv.getSize());
            int item = ran.nextInt(possibleItems.size());
            inv.setItem(slot, possibleItems.get(item));
        }
    }

    public static void load(List<String> items) {

        for (String s : items) {
            String[] parts = s.split(":");
            if (!(parts.length < 3)) {
                if (parts.length == 3) {
                    ItemStack stack = new ItemStack(Material.getMaterial(parts[0]), Integer.valueOf(parts[1]), Byte.valueOf(parts[2]));
                    possibleItems.add(stack);
                } else {
                    ItemStack stack = new ItemStack(Material.getMaterial(parts[0]), Integer.valueOf(parts[1]), Byte.valueOf(parts[2]));
                    for (int i = 4; i <= parts.length; i++) {
                        String[] bits = parts[i - 1].split("~");
                        stack.addEnchantment(Enchantment.getByName(bits[0]), Integer.valueOf(bits[1]));
                    }
                    possibleItems.add(stack);
                }
            } else {
                System.err.println("Error loading item for create drop. " + s + " is not a valid configuration");
            }
        }
    }
}
