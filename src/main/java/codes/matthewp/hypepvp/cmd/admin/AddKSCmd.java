package codes.matthewp.hypepvp.cmd.admin;

import codes.matthewp.hypepvp.HypePvP;
import codes.matthewp.hypepvp.data.Messages;
import codes.matthewp.hypepvp.user.User;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddKSCmd implements CommandExecutor {

    private HypePvP pvp;

    public AddKSCmd(HypePvP pvp) {
        this.pvp = pvp;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (s.equalsIgnoreCase("addks")) {
            if (commandSender.hasPermission("hypepvp.addks")) {
                if (args.length == 1) {
                    Player p = Bukkit.getPlayer(args[0]);

                    if (p != null) {
                        User user = pvp.getUserManager().getUser(p);
                        user.addKS();

                        commandSender.sendMessage(Messages.getMessage("addedKS"));
                        return true;
                    } else {
                        commandSender.sendMessage(Messages.getMessage("playerNotOnline"));
                        return false;
                    }
                } else {
                    commandSender.sendMessage(Messages.getMessage("addKSUsage"));
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
