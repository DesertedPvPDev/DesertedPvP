package codes.matthewp.desertedpvp.kit.kits;

import codes.matthewp.desertedpvp.data.Messages;
import codes.matthewp.desertedpvp.killstreak.KillStreaks;
import codes.matthewp.desertedpvp.kit.IKit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BattleBaccaKit extends IKit {

    @Override
    public String intelID() {
        return "battle_bacca";
    }

    @Override
    public void giveKit(Player p) {
        p.getInventory().clear();

        p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
        p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));

        ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
        axe.addEnchantment(Enchantment.DAMAGE_ALL, 1);
        p.getInventory().addItem(axe);
        p.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 3));

        p.sendMessage(Messages.getMessage("youHaveRecievedKit").replaceAll("%KIT%", stripColor(getName())));
        KillStreaks.giveKillStreaksItem(p);
    }

    @Override
    public void gotKill(Player killer, Player killed) {
        for (ItemStack stack : killer.getInventory().getContents()) {
            if (stack != null) {
                if (stack.getType() == Material.DIAMOND_AXE) {
                    for (Enchantment enchant : stack.getEnchantments().keySet()) {
                        System.out.println(enchant.getName());
                        if (enchant.getName().equalsIgnoreCase("DAMAGE_ALL")) {
                            int nextLevel = stack.getEnchantments().get(enchant) + 1;
                            if (nextLevel <= 4) {
                                stack.removeEnchantment(enchant);
                                stack.addEnchantment(Enchantment.DAMAGE_ALL, nextLevel);
                            }
                        }
                    }
                }
            }
        }
    }
}
