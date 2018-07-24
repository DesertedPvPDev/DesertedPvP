package codes.matthewp.hypepvp;

import codes.matthewp.hypepvp.cmd.admin.AddKSCmd;
import codes.matthewp.hypepvp.cmd.admin.ResetKSCmd;
import codes.matthewp.hypepvp.cmd.spawn.SetSpawnCmd;
import codes.matthewp.hypepvp.cmd.spawn.SpawnCmd;
import codes.matthewp.hypepvp.event.interact.InteractEvent;
import codes.matthewp.hypepvp.event.interact.InventoryClickEvent;
import codes.matthewp.hypepvp.event.player.*;
import codes.matthewp.hypepvp.event.world.DropEvent;
import codes.matthewp.hypepvp.event.world.TransEntityEvent;
import codes.matthewp.hypepvp.file.FileUtil;
import codes.matthewp.hypepvp.user.UserManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main entry point
 * Hello World!
 */
public class HypePvP extends JavaPlugin {

    private FileUtil fileUtil;
    private UserManager user;
    private static HypePvP instace;

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
        getServer().getPluginManager().registerEvents(new InventoryClickEvent(this), this);
        getServer().getPluginManager().registerEvents(new DropEvent(this), this);
        getServer().getPluginManager().registerEvents(new TransEntityEvent(), this);
    }

    public static HypePvP getInstace() {
        return instace;
    }

    public UserManager getUserManager() {
        return user;
    }
}
