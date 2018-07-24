package codes.matthewp.hypepvp.kit.kits;

import codes.matthewp.hypepvp.HypePvP;
import codes.matthewp.hypepvp.data.Messages;
import codes.matthewp.hypepvp.data.TaskManager;
import codes.matthewp.hypepvp.killstreak.KillStreaks;
import codes.matthewp.hypepvp.kit.IKit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AssassinKit extends IKit {

    private ItemStack invisCloak;

    @Override
    public String intelID() {
        return "assassin";
    }

    @Override
    public void giveKit(Player p) {
        p.getInventory().clear();
        p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
        p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
        bootsMeta.setColor(Color.WHITE);
        boots.setItemMeta(bootsMeta);
        p.getInventory().setBoots(boots);

        ItemStack sword = new ItemStack(Material.IRON_SWORD);
        p.getInventory().addItem(sword);
        KillStreaks.giveKillStreaksItem(p);
        p.sendMessage(Messages.getMessage("youHaveRecievedKit").replaceAll("%KIT%", stripColor(getName())));
    }

    @Override
    public void load(ConfigurationSection section) {
        super.load(section);
        invisCloak = new ItemStack(Material.getMaterial(section.getString("cloak.mat")));
        ItemMeta meta = invisCloak.getItemMeta();
        meta.setDisplayName(color("name"));
        invisCloak.setItemMeta(meta);
        this.invisCloak = invisCloak;
    }

    public void activateCloak(Player p) {
        p.getInventory().setArmorContents(null);
        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 2));

        for (int i = 0; i < p.getInventory().getContents().length; i++) {
           ItemStack stack = p.getInventory().getContents()[i];
           if(stack.getType() == Material.IRON_SWORD) {
               stack.addEnchantment(Enchantment.DAMAGE_ALL, 1);
           }
        }

        TaskManager.registerTask(p.getUniqueId() + "-CloakCooldown",
                Bukkit.getScheduler().scheduleSyncRepeatingTask(HypePvP.getInstace(), new Runnable() {
            int count = 0;
            @Override
            public void run() {
                count++;
                if(count==5) {
                    p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
                    p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
                    p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                    ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                    LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
                    bootsMeta.setColor(Color.WHITE);
                    boots.setItemMeta(bootsMeta);
                    p.getInventory().setBoots(boots);
                    for (int i = 0; i < p.getInventory().getContents().length; i++) {
                        ItemStack stack = p.getInventory().getContents()[i];
                        if(stack.getType() == Material.IRON_SWORD) {
                            stack.getEnchantments().clear();
                        }
                    }
                    Bukkit.getScheduler().cancelTask(TaskManager.getTaskId(p.getUniqueId() + "-CloakCooldown"));
                }
            }
        }, 0L, 20L));
    }

    public ItemStack getInvisCloak() {
        return invisCloak;
    }

    public String stripColor(String str) {
        str = ChatColor.translateAlternateColorCodes('&', str);
        str = ChatColor.stripColor(str);
        return str;
    }
}
