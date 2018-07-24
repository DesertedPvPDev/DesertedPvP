package codes.matthewp.hypepvp.cmd.spawn;

import codes.matthewp.hypepvp.data.Messages;
import codes.matthewp.hypepvp.data.SpawnData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCmd implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (s.equalsIgnoreCase("spawn")) {
            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if(p.hasPermission("hypepvp.spawn")) {
                    p.teleport(new Location(Bukkit.getWorld("world"),
                            SpawnData.getX(), SpawnData.getY(), SpawnData.getZ(), SpawnData.getYaw(), SpawnData.getPitch()));
                    p.sendMessage(Messages.getMessage("teleToSpawn"));
                    return true;
                } else {
                    commandSender.sendMessage(Messages.getMessage("noPerm"));
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
