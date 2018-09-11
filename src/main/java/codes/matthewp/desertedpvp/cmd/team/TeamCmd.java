package codes.matthewp.desertedpvp.cmd.team;

import codes.matthewp.desertedcore.string.ColorHelper;
import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.data.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeamCmd implements CommandExecutor {

    private DesertedPvP pvp;

    public TeamCmd(DesertedPvP pvp) {
        this.pvp = pvp;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equalsIgnoreCase("team")) {
            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if (p.hasPermission("pvp.team")) {
                    if (strings.length == 0) {
                        sendHelp(p);
                        return false;
                    } else {
                        if (strings[0].equalsIgnoreCase("create")) {

                        } else if (strings[0].equalsIgnoreCase("kick")) {

                        } else if (strings[0].equalsIgnoreCase("invite")) {

                        } else if (strings[0].equalsIgnoreCase("accept")) {

                        } else if (strings[0].equalsIgnoreCase("top")) {

                        } else if (strings[0].equalsIgnoreCase("help")) {
                            sendHelp(p);
                            return false;
                        } else {
                            sendHelp(p);
                            return false;
                        }
                    }
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


    private void sendHelp(Player p) {
        StringBuilder builder = new StringBuilder();
        for (String str : pvp.getFileUtil().getTeams().getConfig().getStringList("teamhelp")) {
            builder.append(str + " ");
        }
        p.sendMessage(ColorHelper.color(builder.toString()));
    }
}
