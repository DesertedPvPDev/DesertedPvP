package codes.matthewp.desertedpvp.killstreak.impl;

import codes.matthewp.desertedpvp.killstreak.IKillStreak;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

public class AttackDogs extends IKillStreak {

    private int dogCount;
    private String dogName;

    public AttackDogs() {
        super("attack_dog");
    }

    @Override
    public void load(ConfigurationSection config) {
        loadIcon(config.getConfigurationSection("icon"));
        dogCount = config.getInt("dogCount");
        dogName = config.getString("dogName");
        setCost(config.getInt("cost"));
    }

    @Override
    public void execute(Player p) {
        for (int i = 1; i <= dogCount; i++) {
            Wolf wolf = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
            wolf.setOwner(p);
            wolf.setCustomNameVisible(true);
            String cName = dogName;
            cName = cName.replaceAll("%PLAYER%", p.getName());
            cName = color(cName);
            wolf.setCustomName(cName);
        }
    }
}
