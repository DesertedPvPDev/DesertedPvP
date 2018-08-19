package codes.matthewp.desertedpvp.kit.kits;

import codes.matthewp.desertedpvp.data.Messages;
import codes.matthewp.desertedpvp.killstreak.KillStreaks;
import codes.matthewp.desertedpvp.kit.IKit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ManiacKit extends IKit {

    @Override
    public String intelID() {
        return "maniac";
    }

    @Override
    public void giveKit(Player p) {
        p.getInventory().clear();

        ItemStack helm = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta helmMeta = (LeatherArmorMeta) helm.getItemMeta();
        helmMeta.setColor(Color.RED);
        helm.setItemMeta(helmMeta);
        p.getInventory().setHelmet(helm);
        p.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        p.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
        bootsMeta.setColor(Color.RED);
        boots.setItemMeta(bootsMeta);
        p.getInventory().setBoots(boots);

        ItemStack sword = new ItemStack(Material.IRON_AXE);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 2);
        p.getInventory().addItem(sword);
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));

        KillStreaks.giveKillStreaksItem(p);
        p.sendMessage(Messages.getMessage("youHaveRecievedKit").replaceAll("%KIT%", stripColor(getName())));
    }

    public String stripColor(String str) {
        str = ChatColor.translateAlternateColorCodes('&', str);
        str = ChatColor.stripColor(str);
        return str;
    }
}
