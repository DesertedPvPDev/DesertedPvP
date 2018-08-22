package codes.matthewp.desertedpvp.data;

import codes.matthewp.desertedpvp.DesertedPvP;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CombatTag {

    private static List<Player> combatTagged = new ArrayList<>();

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

    public static void removeTag(Player p) {
        combatTagged.remove(p);
        TaskManager.removeTask(p.getUniqueId() + "/CombatLog");
    }

    public static boolean isTagged(Player p) {
        return combatTagged.contains(p);
    }
}
