package codes.matthewp.hypepvp.kit.kits;

import codes.matthewp.hypepvp.data.Messages;
import codes.matthewp.hypepvp.killstreak.KillStreaks;
import codes.matthewp.hypepvp.kit.IKit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KnightKit extends IKit {

    @Override
    public String intelID() {
        return "knight";
    }

    @Override
    public void giveKit(Player p) {
        p.getInventory().clear();
        p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
        p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));

        p.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
        KillStreaks.giveKillStreaksItem(p);
        p.sendMessage(Messages.getMessage("youHaveRecievedKit").replaceAll("%KIT%", stripColor(getName())));
    }

    public String stripColor(String str) {
        str = ChatColor.translateAlternateColorCodes('&', str);
        str = ChatColor.stripColor(str);
        return str;
    }
}
