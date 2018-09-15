package codes.matthewp.desertedpvp.data;

import codes.matthewp.desertedcore.database.Database;
import codes.matthewp.desertedcore.database.DatabaseAccess;
import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.teams.Team;
import org.bukkit.Bukkit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
            String query = "CREATE TABLE IF NOT EXISTS `teams`" +
                    "( " +
                    "`team_id` INT ," +
                    "`team_name` VARCHAR(255), " +
                    "`team_leader` VARCHAR(255) CHARACTER SET latin7 COLLATE latin7_bin,"+
                    "`number_of_members` INT,"+
                    " PRIMARY KEY (`team_id`) " +
                    ") ENGINE=InnoDB;";
            stmt.executeUpdate(query);

            Statement stmt1 = conn.createStatement();
            String query1 = "CRETE TABLE IF NOT EXISTS `team_members`" +
                    "( "+
                    "`uuid` VARCHAR(255) CHARACTER SET latin7 COLLATE latin7_bin,"+
                    "`team_id` INT"+
                    ") ENGINE=InnoDB;";
            stmt1.executeQuery(query1);


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public Team insertTeamIntoDataBase(String name, UUID uuid) {
        String insertQuery = "INSERT INTO `teams`(`team_name`, `team_leader`, `number_of_members` " +
                "VALUES(?, ?, ?)";
        String fetchQuery = "SELECT team_id FROM `teams` WHERE `team_name` = ?";

        Bukkit.getServer().getScheduler().runTaskAsynchronously(pvp, new Runnable() {
            @Override
            public void run() {
                try(Connection con = db.getConnection(ins);
                    PreparedStatement stmt = con.prepareStatement(insertQuery)) {
                    stmt.setString(1, name);
                    stmt.setString(2, String.valueOf(uuid));
                    stmt.setInt(3,1);

                    stmt.executeUpdate();

                }
                catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });


        ArrayList<UUID> members = new ArrayList<>();
        members.add(uuid);
        return new Team(name, uuid, members);
    }

    public List<Team> loadTeams() {
        List<Team> teams = new ArrayList<Team>();
        String query = "SELECT * FROM `teams`";
        Bukkit.getServer().getScheduler().runTaskAsynchronously(pvp, new Runnable() {
            @Override
            public void run() {
                try (Connection con = db.getConnection(ins);
                     PreparedStatement statement = con.prepareStatement(query)) {

                     ResultSet set = statement.executeQuery();
                     while (set.next()) {
                        String name;
                        UUID uuid = null;
                        List<UUID> members =null;

                        name = set.getString("team_name");
                        uuid = UUID.fromString(set.getString("team_leader"));
                        members = loadTeamMembers(name);

                        teams.add(new Team(name, uuid, members));
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        return teams;
    }

    private List<UUID> loadTeamMembers(String name) {
        List<UUID> members = new ArrayList<UUID>();
        String query = "SELECT * FROM `team_members` where `team_name` = ?";
        try(Connection con = db.getConnection(ins);
            PreparedStatement stmt = con.prepareStatement(query))
        {
                stmt.setString(1, name);
                ResultSet set = stmt.executeQuery();
                while(set.next()) {
                    members.add(UUID.fromString(set.getString("uuid")));
                }
        }
        catch(SQLException ex) {
            ex.printStackTrace();;
        }


        return members;
    }

}
