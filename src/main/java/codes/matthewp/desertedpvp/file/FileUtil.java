package codes.matthewp.desertedpvp.file;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.data.Messages;
import codes.matthewp.desertedpvp.data.SpawnData;
import codes.matthewp.desertedpvp.killstreak.KillStreakManager;
import codes.matthewp.desertedpvp.killstreak.KillStreaks;
import codes.matthewp.desertedpvp.kit.KitManager;
import codes.matthewp.desertedpvp.kit.KitSelector;
import codes.matthewp.desertedpvp.loot.SupplyLoot;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * Util for files and their configurations
 */
public class FileUtil {

    private DesertedPvP desertedPvP;

    private File spawnFile;
    private File configFile;
    private File messagesFile;
    private File kitsFile;
    private File killStreaksFile;

    private FileConfiguration spawn;
    private FileConfiguration config;
    private FileConfiguration messages;
    private FileConfiguration kits;
    private FileConfiguration killStreaks;

    public FileUtil(DesertedPvP pvp) {
        this.desertedPvP = pvp;
        setup();
        loadData();
    }

    private void setup() {
        if (!desertedPvP.getDataFolder().exists()) {
            desertedPvP.getDataFolder().mkdir();
        }

        spawnFile = new File(desertedPvP.getDataFolder() + File.separator + "spawn.yml");
        configFile = new File(desertedPvP.getDataFolder() + File.separator + "config.yml");
        messagesFile = new File(desertedPvP.getDataFolder() + File.separator + "messages.yml");
        kitsFile = new File(desertedPvP.getDataFolder() + File.separator + "kits.yml");
        killStreaksFile = new File(desertedPvP.getDataFolder() + File.separator + "killstreaks.yml");

        if (!spawnFile.exists()) {
            saveRes(spawnFile);
        }

        if (!configFile.exists()) {
            saveRes(configFile);
        }

        if (!messagesFile.exists()) {
            saveRes(messagesFile);
        }

        if (!kitsFile.exists()) {
            saveRes(kitsFile);
        }

        if (!killStreaksFile.exists()) {
            saveRes(killStreaksFile);
        }

    }

    public File getSpawnFile() {
        return spawnFile;
    }

    public FileConfiguration getSpawn() {
        return spawn;
    }

    public FileConfiguration getKillStreaks() {
        return killStreaks;
    }


    private void loadData() {
        config = YamlConfiguration.loadConfiguration(configFile);
        spawn = YamlConfiguration.loadConfiguration(spawnFile);
        messages = YamlConfiguration.loadConfiguration(messagesFile);
        kits = YamlConfiguration.loadConfiguration(kitsFile);
        killStreaks = YamlConfiguration.loadConfiguration(killStreaksFile);

        for (String key : spawn.getKeys(false)) {
            SpawnData.addSpawnDat(key, spawn.getInt(key));
        }

        for (String key : messages.getKeys(false)) {
            Messages.addMesssage(key, messages.getString(key));
        }

        SupplyLoot.load(config.getStringList("crateDropItems"));

        KitSelector.loadKitSelector(config.getConfigurationSection("kitSelectorItem"));
        KitSelector.loadGUI(config.getConfigurationSection("kitSelectorGUI"));

        KillStreaks.loadKillStreaksItem(killStreaks.getConfigurationSection("killStreakItem"));
        KillStreaks.loadGUI(killStreaks.getConfigurationSection("killStreakGUI"));

        KitManager.lazyLoad();
        KillStreakManager.lazyLoad();

        for (String key : kits.getKeys(false)) {
            KitManager.loadKit(key, kits.getConfigurationSection(key));
        }

        for (String key : killStreaks.getConfigurationSection("killStreaks").getKeys(false)) {
            KillStreakManager.loadKS(key, killStreaks.getConfigurationSection("killStreaks." + key));
        }
    }

    public void reloadSpawnDat() {
        for (String key : spawn.getKeys(false)) {
            SpawnData.addSpawnDat(key, spawn.getInt(key));
        }
    }

    private void saveRes(File f) {
        desertedPvP.saveResource(f.getName(), false);
    }

}
