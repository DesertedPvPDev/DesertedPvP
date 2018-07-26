package codes.matthewp.desertedpvp.user;

import codes.matthewp.desertedpvp.killstreak.KillStreaks;
import codes.matthewp.desertedpvp.kit.IKit;
import codes.matthewp.desertedpvp.kit.KitSelector;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;

import java.util.UUID;

/**
 * User class for custom data
 */
public class User {

    private IKit currentKit;
    private UUID playerUUID;
    private int currentKS;
    private Inventory lastInv;

    public User(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    public IKit getCurrentKit() {
        return currentKit;
    }

    public void setKit(IKit kit) {
        this.currentKit = kit;
    }

    public int getCurrentKS() {
        return currentKS;
    }

    public void resetKS() {
        this.currentKS = 0;
    }

    public void addKS() {
        this.currentKS++;
    }

    public void addKS(int amount) {
        this.currentKS = currentKS + amount;
    }

    public void subtractKS(int amount) {
        this.currentKS = currentKS - amount;
    }

    public Inventory getLastInv() {
        return lastInv;
    }

    public void setLastInv(Inventory inv) {
        this.lastInv = inv;
    }

    public void restPlayer() {
        resetKS();
        Player p = Bukkit.getPlayer(playerUUID);
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        p.getActivePotionEffects().clear();
        p.setFireTicks(0);
        p.setExp(0F);
        p.setGameMode(GameMode.SURVIVAL);
        p.setHealth(20);
        p.setFoodLevel(20);
        for (PotionEffect effect : p.getActivePotionEffects()) {
            p.removePotionEffect(effect.getType());
        }
        KitSelector.giveKitSelector(p);
        KillStreaks.giveKillStreaksItem(p);

    }

}
