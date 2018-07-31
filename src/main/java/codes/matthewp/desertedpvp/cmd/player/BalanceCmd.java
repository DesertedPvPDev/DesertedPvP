package codes.matthewp.desertedpvp.cmd.player;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.data.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceCmd implements CommandExecutor {

    private DesertedPvP pvp;

    public BalanceCmd(DesertedPvP pvp) {
        this.pvp = pvp;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equalsIgnoreCase("balance")) {
            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if (p.hasPermission("desertedpvp.balance")) {
                    String msg = Messages.getMessage("bal");
                    msg = msg.replaceAll("%COINS%", String.valueOf(pvp.getUserManager().getUser(p).getCoins()));
                    p.sendMessage(msg);
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
