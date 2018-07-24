package codes.matthewp.desertedpvp;

import codes.matthewp.desertedpvp.cmd.admin.AddKSCmd;
import codes.matthewp.desertedpvp.cmd.admin.ResetKSCmd;
import codes.matthewp.desertedpvp.cmd.spawn.SetSpawnCmd;
import codes.matthewp.desertedpvp.cmd.spawn.SpawnCmd;
import codes.matthewp.desertedpvp.event.interact.InteractEvent;
import codes.matthewp.desertedpvp.event.interact.InventoryClickEvent;
import codes.matthewp.desertedpvp.event.player.*;
import codes.matthewp.desertedpvp.event.world.DropEvent;
import codes.matthewp.desertedpvp.event.world.TransEntityEvent;
import codes.matthewp.desertedpvp.file.FileUtil;
import codes.matthewp.desertedpvp.user.UserManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main entry point
 * Hello World!
 */
public class DesertedPvP extends JavaPlugin {

    private FileUtil fileUtil;
    private UserManager user;
    private static DesertedPvP instace;

    @Override
    public void onEnable() {
        fileUtil = new FileUtil(this);
        user = new UserManager();
        regCommands();
        regListeners();
        instace = this;
    }

    @Override
    public void onDisable() {

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

    private void regListeners() {
        getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
        getServer().getPluginManager().registerEvents(new LeaveEvent(this), this);
        getServer().getPluginManager().registerEvents(new InteractEvent(), this);
        getServer().getPluginManager().registerEvents(new HungerEvent(), this);
        getServer().getPluginManager().registerEvents(new RespawnEvent(this), this);
        getServer().getPluginManager().registerEvents(new DeathEvent(this), this);
        getServer().getPluginManager().registerEvents(new HurtEvent(this), this);
        getServer().getPluginManager().registerEvents(new InventoryClickEvent(this), this);
        getServer().getPluginManager().registerEvents(new DropEvent(this), this);
        getServer().getPluginManager().registerEvents(new TransEntityEvent(), this);
    }

    public static DesertedPvP getInstace() {
        return instace;
    }

    public UserManager getUserManager() {
        return user;
    }
}
