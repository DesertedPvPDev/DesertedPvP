package codes.matthewp.desertedpvp.data;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.task.CombatCountdown;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Util used to keep track and manage combat tagging
 */
public class CombatTag {

    /**
     * List of currently tagged players
     */
    private static List<Player> combatTagged = new ArrayList<>();

    /**
     * Tag a player
     *
     * @param p Player player to tag
     */
    public static void tagPlayer(Player p) {
        if (combatTagged.contains(p)) {
            TaskManager.removeTask(p.getUniqueId() + "/CombatLog");
            combatTagged.remove(p);
        }
        combatTagged.add(p);
        TaskManager.registerTask(p.getUniqueId() + "/CombatLog",
                Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(DesertedPvP.getInstace(), new CombatCountdown(p)
                        , 0L, 20L));
    }

    /**
     * Untag a player.
     * NOTE: This DOES cancel the players combat task
     * @param p Player p
     */
    public static void removeTag(Player p) {
        combatTagged.remove(p);
        TaskManager.removeTask(p.getUniqueId() + "/CombatLog");
    }

    /**
     * Check if a player is tagged
     * @param p Player to check if tagged
     * @return true if player is tagged.
     */
    public static boolean isTagged(Player p) {
        return combatTagged.contains(p);
    }
}
