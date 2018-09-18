package codes.matthewp.desertedpvp.kit.kits;

import codes.matthewp.desertedpvp.data.Messages;
import codes.matthewp.desertedpvp.killstreak.KillStreaks;
import codes.matthewp.desertedpvp.kit.IKit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RichKit extends IKit {

    @Override
    public String intelID() {
        return "rich";
    }

    @Override
    public void giveKit(Player p) {
        p.getInventory().clear();

        ItemStack helm = new ItemStack(Material.DIAMOND_HELMET);
        helm.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
        p.getInventory().setHelmet(helm);

        ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
        chest.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
        p.getInventory().setChestplate(chest);

        ItemStack legs = new ItemStack(Material.DIAMOND_LEGGINGS);
        legs.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
        p.getInventory().setLeggings(legs);

        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        boots.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
        p.getInventory().setBoots(boots);

        p.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
        p.getInventory().addItem(new ItemStack(Material.APPLE));

        p.sendMessage(Messages.getMessage("youHaveRecievedKit").replaceAll("%KIT%", stripColor(getName())));
        KillStreaks.giveKillStreaksItem(p);
    }
}
