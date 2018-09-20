package codes.matthewp.desertedpvp.data;

import codes.matthewp.desertedcore.database.Database;
import codes.matthewp.desertedcore.database.DatabaseAccess;
import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.teams.Team;
import codes.matthewp.desertedpvp.teams.TeamMember;
import org.bukkit.Bukkit;

import java.sql.*;
import java.util.*;

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
                    " PRIMARY KEY (`team_id`) " +
                    ") ENGINE=InnoDB;";
            stmt.executeUpdate(query);

            Statement stmt1 = conn.createStatement();
            String query1 = "CRETE TABLE IF NOT EXISTS `team_members`" +
                    "( "+
                    "`uuid` VARCHAR(255) CHARACTER SET latin7 COLLATE latin7_bin,"+
                    "`team_id` INT"+
                    "`arena_kills` INT,"+
                    "PRIMARY KEY(`uuid`)) ENGINE=InnoDB;";
            stmt1.executeQuery(query1);


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public int loadMaxId() {
        String query = "SELECT COUNT(*) FROM `teams` AS `maxId`";
        try(Connection con  = db.getConnection(ins); PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet set = stmt.executeQuery();
            while(set.next()) {
                int maxId = set.getInt("maxId");
                return maxId;
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
     }


    //Methods related to Team Class
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
                        int id = 0;
                        String name;
                        UUID uuid = null;
                        List<UUID> members =null;

                        id = set.getInt("team_id");
                        name = set.getString("team_name");
                        uuid = UUID.fromString(set.getString("team_leader"));
                        members = pvp.getTeamManager().getMembersOfTeam(id);

                        teams.add(new Team(id, name, uuid, members));
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        return teams;
    }
    public Team insertTeamIntoDataBase(int id, String name, UUID uuid) {
        String insertQuery = "INSERT INTO `teams`(`team_id` ,`team_name`, `team_leader`" +
                "VALUES(?, ?, ?, ?)";

        Bukkit.getServer().getScheduler().runTaskAsynchronously(pvp, new Runnable() {
            @Override
            public void run() {
                try(Connection con = db.getConnection(ins);
                    PreparedStatement stmt = con.prepareStatement(insertQuery)) {
                    stmt.setInt(1, id);
                    stmt.setString(2, name);
                    stmt.setString(3, String.valueOf(uuid));
                    stmt.executeUpdate();



                }
                catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });


        ArrayList<UUID> members = new ArrayList<>();
        members.add(uuid);
        return new Team(id, name, uuid, members);
    }
    public void updateTeamInDatabase(Team team) {
        String updateQuery = "UPDATE `team` SET " +
                "`team_name` = ?,"+
                "`team_leader` = ?"+
                "WHERE `team_id` = ?;";
        Bukkit.getServer().getScheduler().runTaskAsynchronously(pvp, new Runnable() {
            @Override
            public void run() {
                try(Connection con = db.getConnection(ins); PreparedStatement stmt = con.prepareStatement(updateQuery)) {
                    stmt.setString(1, team.getName());
                    stmt.setString(2, String.valueOf(team.getOwner()));
                    stmt.setInt(3, team.getId());
                }
                catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    //Methods related to TeamMember Class
    public List<TeamMember> loadTeamMembers() {
        List<TeamMember> members = new ArrayList<TeamMember>();
        String query = "SELECT * FROM `team_members`";
        try(Connection con = db.getConnection(ins);
            PreparedStatement stmt = con.prepareStatement(query))
        {
                ResultSet set = stmt.executeQuery();
                while(set.next()) {
                    UUID uuid = UUID.fromString(set.getString("uuid"));
                    int arenaKills = Integer.parseInt("arena_kills");
                    int team_id = Integer.parseInt("team_id");

                    members.add(new TeamMember(uuid, arenaKills, team_id));
                }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        return members;
    }
    public TeamMember insertTeamMemberIntoDataBase(UUID uuid) {
       String query = "INSERT INTO `team_members`(`uuid`, `team_id`, `arena_kills`) VALUES(?, ?, ?)";
        Bukkit.getServer().getScheduler().runTaskAsynchronously(pvp, new Runnable() {
            @Override
            public void run() {
                try(Connection con = db.getConnection(ins); PreparedStatement stmt = con.prepareStatement(query)) {
                    stmt.setString(1, uuid.toString());
                    stmt.setInt(2, 0);
                    stmt.setInt(3, 0);


                    stmt.executeUpdate();
                }
                catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        TeamMember member = new TeamMember(uuid, 0, 0);
        return member;
    }
    public void updateTeamMemberIntoDatabase(TeamMember member) {
        String query = "UPDATE `team_members` SET " +
                "`arena_kills` = ?, `team_id` = ? WHERE `uuid` = ?";
        Bukkit.getServer().getScheduler().runTaskAsynchronously(pvp, new Runnable() {
            @Override
            public void run() {
                try(Connection con = db.getConnection(ins); PreparedStatement stmt = con.prepareStatement(query)) {
                    stmt.setInt(1, member.getArenaKills());
                    stmt.setInt(2, member.getTeam_id());
                    stmt.setString(3, member.getUuid().toString());

                    stmt.executeUpdate();
                }
                catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


}
