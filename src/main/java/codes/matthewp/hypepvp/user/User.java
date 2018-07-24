package codes.matthewp.hypepvp.user;

import codes.matthewp.hypepvp.kit.IKit;
import codes.matthewp.hypepvp.kit.KitSelector;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * User class for custom data
 */
public class User {

    private IKit currentKit;
    private UUID playerUUID;
    private int currentKS;

    public User(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    public IKit getCurrentKit() {
        return currentKit;
    }

    public void setKit(IKit kit) {
        this.currentKit = currentKit;
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

    public void restPlayer() {
        resetKS();
        Player p = Bukkit.getPlayer(playerUUID);
        p.setExp(0F);
        p.setFireTicks(0);
        p.getActivePotionEffects().clear();
        KitSelector.giveKitSelector(p);
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
    }

}
