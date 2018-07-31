package codes.matthewp.desertedpvp.cmd.player;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.data.Messages;
import codes.matthewp.desertedpvp.event.interact.InteractEvent;
import codes.matthewp.desertedpvp.user.User;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PayCmd implements CommandExecutor {

    private DesertedPvP pvp;

    public PayCmd(DesertedPvP pvp) {
        this.pvp = pvp;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equalsIgnoreCase("pay")) {
            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if (p.hasPermission("desertedpvp.pay")) {
                    if (strings.length == 2) {
                        Player target = Bukkit.getPlayer(strings[0]);
                        if (target != null) {

                            int amount = Integer.valueOf(strings[1]);

                            User sender = pvp.getUserManager().getUser(p);
                            User targetUser = pvp.getUserManager().getUser(target);

                            if(sender.getCoins() >= amount) {
                                sender.subtractCounts(amount);
                                targetUser.addCoins(amount);
                                return true;
                            } else {
                                commandSender.sendMessage(Messages.getMessage("cantPayThatMuch"));
                                return false;
                            }
                        } else {
                            commandSender.sendMessage(Messages.getMessage("playerNotOnline"));
                            return false;
                        }
                    } else {
                        commandSender.sendMessage(Messages.getMessage("payInvalidUsage"));
                        return false;
                    }
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
