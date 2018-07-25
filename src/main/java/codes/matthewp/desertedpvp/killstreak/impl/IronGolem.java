package codes.matthewp.desertedpvp.killstreak.impl;

import codes.matthewp.desertedpvp.killstreak.IKillStreak;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class IronGolem extends IKillStreak {

    public IronGolem() {
        super("iron_boi");
    }

    @Override
    public void execute(Player p) {
        super.execute(p);

    }

    @Override
    public void load(ConfigurationSection config) {
        loadIcon(config.getConfigurationSection("icon"));
        setCost(config.getInt("cost"));
    }
}
