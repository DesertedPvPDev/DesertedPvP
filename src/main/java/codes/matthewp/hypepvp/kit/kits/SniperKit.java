package codes.matthewp.hypepvp.kit.kits;

import codes.matthewp.hypepvp.data.Messages;
import codes.matthewp.hypepvp.item.LeatherArmorFactory;
import codes.matthewp.hypepvp.killstreak.KillStreaks;
import codes.matthewp.hypepvp.kit.IKit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SniperKit extends IKit {

    @Override
    public String intelID() {
        return "sniper";
    }

    @Override
    public void giveKit(Player p) {

        p.getInventory().clear();
        p.getInventory().setHelmet(new LeatherArmorFactory(Material.LEATHER_HELMET, Color.GREEN).build());
        p.getInventory().setChestplate(new LeatherArmorFactory(Material.LEATHER_CHESTPLATE, Color.GREEN).build());
        p.getInventory().setLeggings(new LeatherArmorFactory(Material.LEATHER_LEGGINGS, Color.GREEN).build());
        p.getInventory().setBoots(new LeatherArmorFactory(Material.LEATHER_BOOTS, Color.GREEN).build());

        p.getInventory().addItem(new ItemStack(Material.BOW));
        p.getInventory().addItem(new ItemStack(Material.ARROW));

        ItemStack sword = new ItemStack(Material.WOOD_SWORD);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
        p.getInventory().addItem(sword);


        p.sendMessage(Messages.getMessage("youHaveRecievedKit").replaceAll("%KIT%", stripColor(getName())));
        KillStreaks.giveKillStreaksItem(p);
    }

    public String stripColor(String str) {
        str = ChatColor.translateAlternateColorCodes('&', str);
        str = ChatColor.stripColor(str);
        return str;
    }
}

