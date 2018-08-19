package codes.matthewp.desertedpvp.kit.kits;

import codes.matthewp.desertedpvp.data.Messages;
import codes.matthewp.desertedpvp.killstreak.KillStreaks;
import codes.matthewp.desertedpvp.kit.IKit;
import codes.matthewp.desertedpvp.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.inventory.ItemStack;

public class BeastMasterKit extends IKit {

    private String dogName;
    private String summonedBeasts;
    private String noMoreSummon;

    @Override
    public String intelID() {
        return "beast_master";
    }

    @Override
    public void giveKit(Player p) {
        p.getInventory().clear();

        p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
        p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));

        p.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
        p.getInventory().addItem(new ItemStack(Material.COOKED_BEEF));
        p.sendMessage(Messages.getMessage("youHaveRecievedKit").replaceAll("%KIT%", stripColor(getName())));
        KillStreaks.giveKillStreaksItem(p);
    }

    @Override
    public void load(ConfigurationSection sec) {
        super.load(sec);
        dogName = sec.getString("dogName");
        summonedBeasts = sec.getString("summonedBeasts");
        noMoreSummon = sec.getString("noMoreSummon");
    }

    @Override
    public void hasRightClicked(User user, ItemStack whatGotClicked) {
        if (whatGotClicked.getType() == Material.IRON_SWORD) {
            if (!user.hasUsedOnceAbility()) {
                Player p = Bukkit.getPlayer(user.getPlayerUUID());

                for (int i = 1; i <= 4; i++) {
                    Wolf wolf = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
                    wolf.setOwner(p);
                    wolf.setCustomNameVisible(true);
                    String cName = dogName;
                    cName = cName.replaceAll("%PLAYER%", p.getName());
                    cName = color(cName);
                    wolf.setCustomName(cName);
                }
                Bukkit.getPlayer(user.getPlayerUUID()).sendMessage(color(summonedBeasts));
                user.setHasUsedOnceAbility(true);
            } else {
                Bukkit.getPlayer(user.getPlayerUUID()).sendMessage(color(noMoreSummon));
            }
        }
    }


}
