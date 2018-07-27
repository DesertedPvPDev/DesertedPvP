package codes.matthewp.desertedpvp;

import codes.matthewp.desertedpvp.cmd.admin.AddKSCmd;
import codes.matthewp.desertedpvp.cmd.admin.ResetKSCmd;
import codes.matthewp.desertedpvp.cmd.spawn.SetSpawnCmd;
import codes.matthewp.desertedpvp.cmd.spawn.SpawnCmd;
import codes.matthewp.desertedpvp.event.entity.EntityDeath;
import codes.matthewp.desertedpvp.event.interact.InteractEvent;
import codes.matthewp.desertedpvp.event.interact.InventoryClickEvent;
import codes.matthewp.desertedpvp.event.player.*;
import codes.matthewp.desertedpvp.event.world.DropEvent;
import codes.matthewp.desertedpvp.event.world.TransEntityEvent;
import codes.matthewp.desertedpvp.file.FileUtil;
import codes.matthewp.desertedpvp.user.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main entry point
 * Hello World!
 */
public class DesertedPvP extends JavaPlugin {

    private FileUtil fileUtil;
    private UserManager user;
    private static DesertedPvP instance;

    @Override
    public void onEnable() {
        fileUtil = new FileUtil(this);
        user = new UserManager();
        user.scanForUsers();
        regCommands();
        regListeners(
                new JoinEvent(this),
                new LeaveEvent(this),
                new InteractEvent(this),
                new HungerEvent(),
                new RespawnEvent(this),
                new DeathEvent(this),
                new HurtEvent(this),
                new InventoryClickEvent(this),
                new DropEvent(this),
                new TransEntityEvent(),
                new EntityDeath());
        instance = this;
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public FileUtil getFileUtil() {
        return fileUtil;
    }

    private void regCommands() {
        getCommand("spawn").setExecutor(new SpawnCmd());
        getCommand("setspawn").setExecutor(new SetSpawnCmd(this));
        getCommand("addks").setExecutor(new AddKSCmd(this));
        getCommand("resetks").setExecutor(new ResetKSCmd(this));
    }

    private void regListeners(Listener... listeners) {
        PluginManager plMan = Bukkit.getPluginManager();

        for (Listener listener : listeners) {
            plMan.registerEvents(listener, this);
        }
    }

    public static DesertedPvP getInstace() {
        return instance;
    }

    public UserManager getUserManager() {
        return user;
    }
}