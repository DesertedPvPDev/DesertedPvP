package codes.matthewp.desertedpvp.rankup;

import codes.matthewp.desertedpvp.DesertedPvP;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RankManager {

    public List<Rank> ranks;
    private FileConfiguration config;
    private DesertedPvP pvp;

    public RankManager(FileConfiguration ranks, DesertedPvP pvp) {
        this.config = ranks;
        this.pvp = pvp;
        this.ranks = new ArrayList<>();
        load();
    }

    private void load() {
        ConfigurationSection sec = config.getConfigurationSection("path");
        for (int i = 0; i < sec.getKeys(false).size(); i++) {
            Rank rank = new Rank(i,
                    config.getString("path." + String.valueOf(i) + ".group"),
                    config.getInt("path." + String.valueOf(i) + ".cost"));
            ranks.add(rank);
        }
    }

    public void setRank(Player p, Rank rank) {
        pvp.getPerms().playerAddGroup(p, rank.getGroup());
    }

    public Rank getNextRank(Player p) {

        List<Rank> playerRanks = new ArrayList<>();
        String[] playerGroups = pvp.getPerms().getPlayerGroups(p);

        for (Rank rank : ranks) {
            for (int i = 0; i < playerGroups.length; i++) {
                if (rank.getGroup().equalsIgnoreCase(playerGroups[i])) {
                    playerRanks.add(rank);
                }
            }
        }

        Rank topRank = playerRanks.get(0);

        for (Rank rank : playerRanks) {
            if (topRank.getOrder() < rank.getOrder()) {
                topRank = rank;
            }
        }

        for (Rank rank : ranks) {
            if (rank.getOrder() == topRank.getOrder() + 1) {
                return rank;
            }
        }

        return null;
    }
}
