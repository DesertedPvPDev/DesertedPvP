package codes.matthewp.desertedpvp.item;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

/**
 * Simple util to quickly create dyed leather armor
 * Matthew Parks
 */
public class LeatherArmorFactory {

    private ItemStack stack;
    private Color color;

    /**
     * Create a leather armor factory instance
     *
     * @param mat   The material, must be a LEATHER_ armor of some type.
     * @param color The bukkit color. The armor will be set to this
     */
    public LeatherArmorFactory(Material mat, Color color) {
        this.color = color;
        stack = new ItemStack(mat);
    }

    /**
     * Build the armor itemstack
     *
     * @return ItemStack with your colored leather armor
     */
    public ItemStack build() {
        LeatherArmorMeta meta = (LeatherArmorMeta) stack.getItemMeta();
        meta.setColor(color);
        stack.setItemMeta(meta);
        return stack;
    }
}
