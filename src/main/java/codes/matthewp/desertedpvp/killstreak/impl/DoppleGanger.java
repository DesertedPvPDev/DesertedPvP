package codes.matthewp.desertedpvp.killstreak.impl;

import codes.matthewp.desertedpvp.entity.EntityDoppleGanger;
import codes.matthewp.desertedpvp.entity.EntityTypes;
import codes.matthewp.desertedpvp.killstreak.IKillStreak;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class DoppleGanger extends IKillStreak {

    public DoppleGanger() {
        super("dopple_ganger");
    }

    @Override
    public void execute(Player p) {
        EntityTypes.spawnEntity(new EntityDoppleGanger(p.getWorld(), p.getUniqueId()), p.getLocation());
    }

    @Override
    public void load(ConfigurationSection config) {
        loadIcon(config.getConfigurationSection("icon"));
        setCost(config.getInt("cost"));
    }
}
