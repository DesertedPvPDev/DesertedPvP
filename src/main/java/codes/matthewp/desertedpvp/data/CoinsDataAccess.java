package codes.matthewp.desertedpvp.data;

import codes.matthewp.desertedcore.database.Database;
import codes.matthewp.desertedcore.database.DatabaseAccess;
import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.user.User;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

import java.sql.*;
import java.util.Collection;

public class CoinsDataAccess extends DatabaseAccess {

    private CoinsDataAccess ins;
    private DesertedPvP pvp;

    public CoinsDataAccess(Database db, DesertedPvP pvp) {
        super(db);
        this.pvp = pvp;
        ins = this;
    }

    @Override
    public void loadTables() {
        setHasLoaded(true);
        try (Connection conn = db.getConnection(ins);
             Statement stmt = conn.createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS `user_coins`" +
                    " ( `uuid` VARCHAR(255) CHARACTER SET latin7 COLLATE latin7_bin, " +
                    "`amount` INT, PRIMARY KEY (`uuid`) ) ENGINE=InnoDB;";
            stmt.executeUpdate(query);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateUsersCoins(Collection<User> users) {
        String query = "UPDATE `user_coins` SET `amount` = ? WHERE `user_coins`.`uuid` = ?";

        try (Connection conn = db.getConnection(ins);
             PreparedStatement queryStmt = conn.prepareStatement(query)) {
            for (User user : users) {
                queryStmt.setInt(1, user.getCoins());
                queryStmt.setString(2, user.getPlayerUUID().toString());
                queryStmt.addBatch();
            }

            queryStmt.executeBatch();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertUserToDatabase(User user) {
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskAsynchronously(DesertedPvP.getInstace(), new Runnable() {
            @Override
            public void run() {
                String insertSql = "INSERT INTO `user_coins`(`uuid`, `amount`) VALUES (?,?)";
                try (Connection conn = db.getConnection(ins);
                     PreparedStatement stmt = conn.prepareStatement(insertSql)) {
                    stmt.setString(1, user.getPlayerUUID().toString());
                    stmt.setInt(2, user.getCoins());

                    stmt.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public User fetchUserCoins(User user) {
        user.setCoins(0);
        String sqlQuery = "SELECT * FROM `user_coins` WHERE uuid = ?";

        Bukkit.getScheduler().runTaskAsynchronously(pvp, new Runnable() {
            @Override
            public void run() {
                try (Connection con = db.getConnection(ins);
                     PreparedStatement statement = con.prepareStatement(sqlQuery)) {
                    statement.setString(1, user.getPlayerUUID().toString());
                    ResultSet set = statement.executeQuery();

                    if (!set.isBeforeFirst()) {
                        // User does not exist in database
                        insertUserToDatabase(user);
                    } else {
                        while (set.next()) {
                            user.setCoins(set.getInt("amount"));
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        return user;
    }

}
