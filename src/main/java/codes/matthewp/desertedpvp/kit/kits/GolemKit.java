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

public class GolemKit extends IKit {

    @Override
    public String intelID() {
        return "golem";
    }

    @Override
    public void giveKit(Player p) {
        p.getInventory().clear();

        ItemStack helm = new ItemStack(Material.IRON_HELMET);
        ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
        ItemStack legs = new ItemStack(Material.IRON_LEGGINGS);
        ItemStack boots = new ItemStack(Material.IRON_BOOTS);

        helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        chest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        legs.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);

        ItemStack sword = new ItemStack(Material.WOOD_SWORD);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);

        p.getInventory().setHelmet(helm);
        p.getInventory().setChestplate(chest);
        p.getInventory().setLeggings(legs);
        p.getInventory().setBoots(boots);
        p.getInventory().addItem(sword);

        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1));

        p.sendMessage(Messages.getMessage("youHaveRecievedKit").replaceAll("%KIT%", stripColor(getName())));
        KillStreaks.giveKillStreaksItem(p);
    }
}
