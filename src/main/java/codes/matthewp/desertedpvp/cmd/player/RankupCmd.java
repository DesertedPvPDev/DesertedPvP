package codes.matthewp.desertedpvp.cmd.player;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.data.Messages;
import codes.matthewp.desertedpvp.rankup.Rank;
import codes.matthewp.desertedpvp.user.User;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankupCmd implements CommandExecutor {

    private DesertedPvP pvp;

    public RankupCmd(DesertedPvP pvp) {
        this.pvp = pvp;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equalsIgnoreCase("rankup")) {
            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if (p.hasPermission("desertedpvp.rankup")) {
                    Rank rank = pvp.getRankManager().getNextRank(p);
                    if (rank != null) {
                        User user = pvp.getUserManager().getUser(p);
                        if (user.getCoins() >= rank.getCost()) {
                            user.subtractCounts(rank.getCost());
                            pvp.getRankManager().setRank(p, rank);
                            commandSender.sendMessage(Messages.getMessage("youHaveRanked"));
                            return true;
                        } else {
                            commandSender.sendMessage(Messages.getMessage("noMoney").replaceAll("%COINS%", String.valueOf(rank.getCost() - user.getCoins())));
                            return false;
                        }
                    } else {
                        commandSender.sendMessage(Messages.getMessage("noRankUp"));
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
