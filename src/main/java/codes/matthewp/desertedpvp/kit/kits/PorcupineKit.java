package codes.matthewp.desertedpvp.kit.kits;

import codes.matthewp.desertedpvp.kit.IKit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PorcupineKit extends IKit {

    @Override
    public String intelID() {
        return "porkupine";
    }

    @Override
    public void giveKit(Player p) {
        p.getInventory().clear();

        ItemStack helm = new ItemStack(Material.LEATHER_HELMET);
        helm.addEnchantment(Enchantment.THORNS, 2);
        ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
        chest.addEnchantment(Enchantment.THORNS, 2);
        ItemStack legs = new ItemStack(Material.IRON_LEGGINGS);
        legs.addEnchantment(Enchantment.THORNS, 2);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        boots.addEnchantment(Enchantment.THORNS, 2);

        p.getInventory().setHelmet(helm);
        p.getInventory().setChestplate(chest);
        p.getInventory().setLeggings(legs);
        p.getInventory().setBoots(boots);

        p.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
    }
}
