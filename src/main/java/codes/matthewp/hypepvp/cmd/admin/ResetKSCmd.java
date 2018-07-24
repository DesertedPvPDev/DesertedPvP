package codes.matthewp.hypepvp.cmd.admin;

import codes.matthewp.hypepvp.HypePvP;
import codes.matthewp.hypepvp.data.Messages;
import codes.matthewp.hypepvp.user.User;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetKSCmd implements CommandExecutor {

    private HypePvP pvp;

    public ResetKSCmd(HypePvP pvp) {
        this.pvp = pvp;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (s.equalsIgnoreCase("resetks")) {
            if (commandSender.hasPermission("hypepvp.resetks")) {
                if (args.length == 1) {
                    Player p = Bukkit.getPlayer(args[0]);
                    if (p != null) {

                        User user = pvp.getUserManager().getUser(p);
                        user.resetKS();

                        commandSender.sendMessage(Messages.getMessage("resetUserKS"));
                        return true;
                    } else {
                        commandSender.sendMessage(Messages.getMessage("playerNotOnline"));
                        return false;
                    }
                } else {
                    commandSender.sendMessage(Messages.getMessage("resetKSUsage"));
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
