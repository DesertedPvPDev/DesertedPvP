package codes.matthewp.desertedpvp.data;

import codes.matthewp.desertedcore.database.Database;
import codes.matthewp.desertedcore.database.DatabaseAccess;
import codes.matthewp.desertedpvp.DesertedPvP;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TeamsDataAccess extends DatabaseAccess {


    private TeamsDataAccess ins;

    private DesertedPvP pvp;

    public TeamsDataAccess(Database db, DesertedPvP pvp) {
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

}
