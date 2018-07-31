package codes.matthewp.desertedpvp.data;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.user.User;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

    private static Database instance;

    private String url;
    private String userName;
    private String password;
    private int port;

    private HikariDataSource dataSource;

    public Database(String url, int port, String username, String password) {
        this.url = url;
        this.port = port;
        this.userName = username;
        this.password = password;
        instance = this;
        loadDataSource();
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void disconnect() {
        dataSource.close();
    }

    private void loadDataSource() {
        dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://" + url + ":" + String.valueOf(port) + "/desertedpvp");
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
    }

    public void insertUserToDatabase(User user) {
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.scheduleSyncDelayedTask(DesertedPvP.getInstace(), new Runnable() {
            @Override
            public void run() {
                try {
                    // TODO NO PARAENTHESIS
                    String insertSql = "INSERT INTO `user_coins`(`uuid`, `amount`) VALUES (?,?)";
                    Connection conn = getConnection();
                    PreparedStatement stmt = conn.prepareStatement(insertSql);
                    stmt.setString(1, user.getPlayerUUID().toString());
                    stmt.setInt(2, user.getCoins());

                    stmt.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }, 20L);
    }

    public int getUsersCoins(User user) {
        int amount = 0;
        String sqlQuery = "SELECT * FROM `user_coins` WHERE uuid = ?";
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(sqlQuery);
            statement.setString(1, user.getPlayerUUID().toString());
            ResultSet set = statement.executeQuery();

            if (!set.isBeforeFirst()) {
                // User does not exist in database
                insertUserToDatabase(user);
            } else {
                while (set.next()) {
                    amount = set.getInt("amount");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return amount;
    }

    public static Database getInstance() {
        return instance;
    }
}
