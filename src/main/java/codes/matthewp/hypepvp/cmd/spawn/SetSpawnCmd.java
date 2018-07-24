package codes.matthewp.hypepvp.cmd.spawn;

import codes.matthewp.hypepvp.HypePvP;
import codes.matthewp.hypepvp.data.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class SetSpawnCmd implements CommandExecutor {

    private HypePvP pvp;

    public SetSpawnCmd(HypePvP pvp) {
        this.pvp = pvp;
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (s.equalsIgnoreCase("setspawn")) {
            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if (p.hasPermission("hypepvp.setspawn")) {

                    pvp.getFileUtil().getSpawn().set("x", p.getLocation().getX());
                    pvp.getFileUtil().getSpawn().set("y", p.getLocation().getY());
                    pvp.getFileUtil().getSpawn().set("z", p.getLocation().getZ());
                    pvp.getFileUtil().getSpawn().set("pitch", p.getLocation().getPitch());
                    pvp.getFileUtil().getSpawn().set("yaw", p.getLocation().getYaw());

                    try {
                        pvp.getFileUtil().getSpawn().save(pvp.getFileUtil().getSpawnFile());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        p.sendMessage(Messages.getMessage("failedToSaveSpawn"));
                    }

                    pvp.getFileUtil().reloadSpawnDat();

                    p.sendMessage(Messages.getMessage("setSpawn"));
                    return true;
                } else {
                    p.sendMessage(Messages.getMessage("noPerm"));
                    return false;
                }
            } else {
                commandSender.sendMessage(Messages.getMessage("mustBePlayer"));
                return false;
            }
        }
        return false;
    }
}
