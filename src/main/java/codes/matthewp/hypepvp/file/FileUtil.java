package codes.matthewp.hypepvp.file;

import codes.matthewp.hypepvp.HypePvP;
import codes.matthewp.hypepvp.data.Messages;
import codes.matthewp.hypepvp.data.SpawnData;
import codes.matthewp.hypepvp.killstreak.KillStreakManager;
import codes.matthewp.hypepvp.killstreak.KillStreaks;
import codes.matthewp.hypepvp.kit.KitManager;
import codes.matthewp.hypepvp.kit.KitSelector;
import codes.matthewp.hypepvp.loot.SupplyLoot;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * Util for files and their configurations
 */
public class FileUtil {

    private HypePvP hypePvP;

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

    public FileUtil(HypePvP pvp) {
        this.hypePvP = pvp;
        setup();
        loadData();
    }

    private void setup() {
        if (!hypePvP.getDataFolder().exists()) {
            hypePvP.getDataFolder().mkdir();
        }

        spawnFile = new File(hypePvP.getDataFolder() + File.separator + "spawn.yml");
        configFile = new File(hypePvP.getDataFolder() + File.separator + "config.yml");
        messagesFile = new File(hypePvP.getDataFolder() +  File.separator + "messages.yml");
        kitsFile = new File(hypePvP.getDataFolder() + File.separator  + "kits.yml");
        killStreaksFile = new File(hypePvP.getDataFolder() + File.separator + "killstreaks.yml");

        if (!spawnFile.exists()) {
            saveRes(spawnFile);
        }

        if (!configFile.exists()) {
            saveRes(configFile);
        }

        if(!messagesFile.exists()) {
            saveRes(messagesFile);
        }

        if(!kitsFile.exists()) {
            saveRes(kitsFile);
        }

        if(!killStreaksFile.exists()) {
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

        for(String key : spawn.getKeys(false)) {
            SpawnData.addSpawnDat(key, spawn.getInt(key));
        }

        for(String key : messages.getKeys(false)) {
            Messages.addMesssage(key, messages.getString(key));
        }

        SupplyLoot.load(config.getStringList("crateDropItems"));

        KitSelector.loadKitSelector(config.getConfigurationSection("kitSelectorItem"));
        KitSelector.loadGUI(config.getConfigurationSection("kitSelectorGUI"));

        KillStreaks.loadKillStreaksItem(killStreaks.getConfigurationSection("killStreakItem"));
        KillStreaks.loadGUI(killStreaks.getConfigurationSection("killStreakGUI"));

        KitManager.lazyLoad();
        KillStreakManager.lazyLoad();

        for(String key : kits.getKeys(false)) {
            KitManager.loadKit(key, kits.getConfigurationSection(key));
        }

        for(String key : killStreaks.getConfigurationSection("killStreaks").getKeys(false)) {
            KillStreakManager.loadKS(key, killStreaks.getConfigurationSection("killStreaks." + key));
        }
    }

    public void reloadSpawnDat() {
        for(String key : spawn.getKeys(false)) {
            SpawnData.addSpawnDat(key, spawn.getInt(key));
        }
    }

    private void saveRes(File f) {
        hypePvP.saveResource(f.getName(), false);
    }

}
