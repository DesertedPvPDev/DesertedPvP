package codes.matthewp.desertedpvp.kit.kits;

import codes.matthewp.desertedpvp.data.Messages;
import codes.matthewp.desertedpvp.killstreak.KillStreaks;
import codes.matthewp.desertedpvp.kit.IKit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.ChatColor;

/**
 * Created by Mario on 9/25/2018.
 */
public class ReaperKit extends IKit{
    @Override
    public String intelID() {
        return "reaper";
    }


    @Override
    public void giveKit(Player p) {
        p.getInventory().clear();
        p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
        p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));

        ItemStack i = new ItemStack(Material.IRON_HOE);
        i.addEnchantment(Enchantment.DAMAGE_ALL, 5);
        p.getInventory().addItem(i);
        p.getInventory().addItem(new ItemStack(Material.APPLE, 1));

        KillStreaks.giveKillStreaksItem(p);

        p.sendMessage(Messages.getMessage("youHaveRecievedKit").replaceAll("%KIT%", stripColor(getName())));

    }

    public String stripColor(String str) {
        str = ChatColor.translateAlternateColorCodes('&', str);
        str = ChatColor.stripColor(str);
        return str;
    }


}
