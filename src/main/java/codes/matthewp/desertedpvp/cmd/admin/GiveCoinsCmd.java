package codes.matthewp.desertedpvp.cmd.admin;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.data.Messages;
import codes.matthewp.desertedpvp.user.User;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveCoinsCmd implements CommandExecutor {

    private DesertedPvP pvp;

    public GiveCoinsCmd(DesertedPvP pvp) {
        this.pvp = pvp;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equalsIgnoreCase("addcoins")) {
            if (commandSender.hasPermission("desertedpvp.addcoins")) {
                if (strings.length == 2) {
                    Player p = Bukkit.getPlayer(strings[0]);
                    if (p != null) {
                        User user = pvp.getUserManager().getUser(p);
                        int amount = Integer.valueOf(strings[1]);
                        user.addCoins(amount);
                        String msg = Messages.getMessage("addCoins");
                        msg = msg.replaceAll("%AMOUNT%", strings[1]);
                        msg = msg.replaceAll("%PLAYER%", p.getName());
                        commandSender.sendMessage(msg);
                        return true;
                    } else {
                        commandSender.sendMessage(Messages.getMessage("playerNotOnline"));
                        return false;
                    }
                } else {
                    commandSender.sendMessage(Messages.getMessage("addCoinsUsage"));
                    return false;
                }
            } else {
                commandSender.sendMessage(Messages.getMessage("noPerm"));
                return false;
            }
        }
        return false;
    }
}
