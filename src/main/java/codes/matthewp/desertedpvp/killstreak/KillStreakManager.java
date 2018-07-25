package codes.matthewp.desertedpvp.killstreak;

import codes.matthewp.desertedpvp.killstreak.impl.*;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KillStreakManager {

    private static List<IKillStreak> streaks;
    private static HashMap<Material, IKillStreak> matToKS;

    public static void lazyLoad() {
        checkNull();
        streaks.add(new AttackDogs());
        streaks.add(new SupplyCrate());
        streaks.add(new Nuke());
        streaks.add(new IronGolem());
        streaks.add(new DoppleGanger());
    }

    public static List<IKillStreak> getKillStreaks() {
        checkNull();
        return streaks;
    }

    public static void loadKS(String id, ConfigurationSection section) {
        checkNull();
        for (IKillStreak streak : streaks) {
            if (streak.getInterID().equals(id)) {
                streak.load(section);
                matToKS.put(streak.getIcon().getType(), streak);
            }
        }
    }

    public static IKillStreak getKSFromMat(Material mat) {
        return matToKS.get(mat);
    }

    private static void checkNull() {
        if (streaks == null) {
            streaks = new ArrayList<>();
        }
        if (matToKS == null) {
            matToKS = new HashMap<>();
        }
    }
}
