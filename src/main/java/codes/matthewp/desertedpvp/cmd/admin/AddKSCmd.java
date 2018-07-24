package codes.matthewp.desertedpvp.cmd.admin;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.data.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddKSCmd implements CommandExecutor {

    private DesertedPvP pvp;

    public AddKSCmd(DesertedPvP pvp) {
        this.pvp = pvp;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (s.equalsIgnoreCase("addks")) {
            if (commandSender.hasPermission("desertedpvp.addks")) {
                if (args.length == 1) {
                    Player p = Bukkit.getPlayer(args[0]);

                    if (p != null) {
                        addKS(p, 1);

                        commandSender.sendMessage(Messages.getMessage("addedKS"));
                        return true;
                    } else {
                        commandSender.sendMessage(Messages.getMessage("playerNotOnline"));
                        return false;
                    }
                } else if (args.length == 2) {
                    Player p = Bukkit.getPlayer(args[0]);
                    if (p != null) {
                        addKS(p, Integer.valueOf(args[1]));

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

    private void addKS(Player p, int amount) {
        pvp.getUserManager().getUser(p).addKS(amount);
    }
}
