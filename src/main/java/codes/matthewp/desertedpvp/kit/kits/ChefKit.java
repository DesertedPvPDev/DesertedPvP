package codes.matthewp.desertedpvp.kit.kits;

import codes.matthewp.desertedpvp.data.Messages;
import codes.matthewp.desertedpvp.killstreak.KillStreaks;
import codes.matthewp.desertedpvp.kit.IKit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

/**
 * Created by Mario on 9/25/2018.
 */
public class ChefKit extends IKit {
    @Override
    public String intelID() {
        return "chef";
    }

    @Override
    public void giveKit(Player p) {
        p.getInventory().clear();

        //Helmet
        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
        helmetMeta.setColor(Color.WHITE);
        helmet.setItemMeta(helmetMeta);
        p.getInventory().setHelmet(helmet);

        //Chestplate
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
        chestplateMeta.setColor(Color.WHITE);
        chestplate.setItemMeta(chestplateMeta);
        p.getInventory().setChestplate(chestplate);

        //Leggings
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
        leggingsMeta.setColor(Color.WHITE);
        leggings.setItemMeta(leggingsMeta);
        p.getInventory().setLeggings(leggings);

        //Boots
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
        bootsMeta.setColor(Color.WHITE);
        boots.setItemMeta(bootsMeta);
        p.getInventory().setBoots(boots);

        p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
        p.getInventory().addItem(new ItemStack(Material.APPLE, 10));


        KillStreaks.giveKillStreaksItem(p);

        p.sendMessage(Messages.getMessage("youHaveRecievedKit").replaceAll("%KIT%", stripColor(getName())));
    }

    public String stripColor(String str) {
        str = ChatColor.translateAlternateColorCodes('&', str);
        str = ChatColor.stripColor(str);
        return str;
    }
}
