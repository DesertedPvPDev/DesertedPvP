package codes.matthewp.desertedpvp.kit.kits;

import codes.matthewp.desertedpvp.data.Messages;
import codes.matthewp.desertedpvp.killstreak.KillStreaks;
import codes.matthewp.desertedpvp.kit.IKit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;

/**
 * Created by Mario on 9/29/2018.
 */
public class InfectedKit extends IKit {
    @Override
    public String intelID() {
        return "infected";
    }
    @Override
    public void giveKit(Player p) {
        p.getInventory().clear();
        p.getInventory().setHelmet( new ItemStack(Material.SKULL_ITEM, 1, (short) 2));

        //Leather Green Chestplate
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
        chestplateMeta.setColor(Color.GREEN);
        chestplate.setItemMeta(chestplateMeta);
        p.getInventory().setChestplate(chestplate);

        //Leather Green Pants
        ItemStack pants = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta pantsMeta = (LeatherArmorMeta) pants.getItemMeta();
        pantsMeta.setColor(Color.GREEN);
        pants.setItemMeta(pantsMeta);
        p.getInventory().setLeggings(pants);

        p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));

        //Wooden Sword with Poison Two Lore
        ItemStack i = new ItemStack(Material.WOOD_SWORD, 1);
        ItemMeta imeta = i.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GREEN+"Poison II");
        imeta.setLore(lore);
        i.setItemMeta(imeta);
        p.getInventory().addItem(i);

        KillStreaks.giveKillStreaksItem(p);

        p.sendMessage(Messages.getMessage("youHaveRecievedKit").replaceAll("%KIT%", stripColor(getName())));

    }

    public String stripColor(String str) {
        str = ChatColor.translateAlternateColorCodes('&', str);
        str = ChatColor.stripColor(str);
        return str;
    }
}
