package codes.matthewp.desertedpvp.user;

import codes.matthewp.desertedpvp.data.Messages;
import codes.matthewp.desertedpvp.killstreak.KillStreaks;
import codes.matthewp.desertedpvp.kit.IKit;
import codes.matthewp.desertedpvp.kit.KitSelector;
import codes.matthewp.desertedpvp.teams.TeamMember;
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
    private int currentKS = 0;
    private int coins = 0;
    private Inventory lastInv;
    private boolean hasUsedOnceAbility = false;
    private TeamMember tMember;

    public User(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    public IKit getCurrentKit() {
        return currentKit;
    }

    public TeamMember getTeamMember()  { return tMember; }
    public void setTeamMember(TeamMember tMember) { this.tMember = tMember; }

    public void setKit(IKit kit) {
        this.currentKit = kit;
    }

    public int getCurrentKS() {
        return currentKS;
    }

    public void setCoins(int amount) {
        this.coins = amount;
    }

    public void addCoins(int amount) {
        this.coins = this.coins + amount;
        Bukkit.getPlayer(playerUUID).sendMessage(Messages.getMessage("gotCoins").replaceAll("%AMOUNT%", String.valueOf(amount)));
    }

    public void subtractCounts(int amount) {
        this.coins = coins - amount;
        Bukkit.getPlayer(playerUUID).sendMessage(Messages.getMessage("minusCoins").replaceAll("%AMOUNT%", String.valueOf(amount)));
    }

    public int getCoins() {
        return coins;
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

    public boolean hasUsedOnceAbility() {
        return hasUsedOnceAbility;
    }

    public void setHasUsedOnceAbility(boolean bool) {
        this.hasUsedOnceAbility = bool;
    }

    public Inventory getLastInv() {
        return lastInv;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public void setLastInv(Inventory inv) {
        this.lastInv = inv;
    }

    public void restPlayer() {
        resetKS();
        setHasUsedOnceAbility(false);
        Player p = Bukkit.getPlayer(playerUUID);
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
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
