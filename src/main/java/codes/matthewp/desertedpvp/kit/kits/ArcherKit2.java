package codes.matthewp.desertedpvp.kit.kits;

import codes.matthewp.desertedpvp.data.Messages;
import codes.matthewp.desertedpvp.killstreak.KillStreaks;
import codes.matthewp.desertedpvp.kit.IKit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ArcherKit2 extends IKit {

    @Override
    public String intelID() {
        return "archer2";
    }

    @Override
    public void giveKit(Player p) {
        p.getInventory().clear();
        p.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
        p.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        p.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        p.getInventory().setBoots(boots);

        ItemStack bow = new ItemStack(Material.BOW);
        bow.addEnchantment(Enchantment.ARROW_DAMAGE, 2);
        bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        p.getInventory().addItem(bow);

        ItemStack sword = new ItemStack(Material.STONE_SWORD);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);

        p.getInventory().addItem(sword);
        p.getInventory().addItem(new ItemStack(Material.ARROW));

        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));

        p.sendMessage(Messages.getMessage("youHaveRecievedKit").replaceAll("%KIT%", stripColor(getName())));
        KillStreaks.giveKillStreaksItem(p);
    }
}
