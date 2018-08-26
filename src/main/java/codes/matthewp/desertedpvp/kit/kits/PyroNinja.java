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

public class PyroNinja extends IKit {

    @Override
    public String intelID() {
        return "pyro_ninja";
    }

    @Override
    public void giveKit(Player p) {
        p.getInventory().clear();

        p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
        p.getInventory().setHelmet(new ItemStack(Material.IRON_CHESTPLATE));
        ItemStack legs = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        legs.addEnchantment(Enchantment.PROTECTION_FIRE, 1);
        p.getInventory().setLeggings(legs);
        p.getInventory().setHelmet(new ItemStack(Material.IRON_BOOTS));

        ItemStack stack = new ItemStack(Material.IRON_SWORD);
        stack.addEnchantment(Enchantment.FIRE_ASPECT, 1);
        p.getInventory().addItem(stack);

        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));

        p.sendMessage(Messages.getMessage("youHaveRecievedKit").replaceAll("%KIT%", stripColor(getName())));
        KillStreaks.giveKillStreaksItem(p);
    }
}
