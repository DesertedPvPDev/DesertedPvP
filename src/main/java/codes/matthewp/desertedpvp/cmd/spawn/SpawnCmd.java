package codes.matthewp.desertedpvp.cmd.spawn;

import codes.matthewp.desertedpvp.data.CombatTag;
import codes.matthewp.desertedpvp.data.Messages;
import codes.matthewp.desertedpvp.data.SpawnData;
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
                if (p.hasPermission("desertedpvp.spawn")) {
                    if (!CombatTag.isTagged(p)) {
                        p.teleport(new Location(Bukkit.getWorld("world"),
                                SpawnData.getX(), SpawnData.getY(), SpawnData.getZ(), SpawnData.getYaw(), SpawnData.getPitch()));
                        p.sendMessage(Messages.getMessage("teleToSpawn"));
                    } else {
                        p.sendMessage(Messages.getMessage("cmdDisabledTag"));
                    }
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
