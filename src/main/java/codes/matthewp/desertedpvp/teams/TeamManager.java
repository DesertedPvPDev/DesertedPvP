package codes.matthewp.desertedpvp.teams;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.data.TeamsDataAccess;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Mario on 9/15/2018.
 */
public class TeamManager {
    private static int maxId;

    private DesertedPvP pvp;
    private TeamsDataAccess teamsDataAccess;
    private List<TeamMember> teamMembers;
    private List<Team> teams;
    private HashMap<Player, Team> invites;


    public TeamManager(DesertedPvP pvp) {
        this.pvp = pvp;
        this.teamsDataAccess = pvp.getTeamsDataAcess();
        //Load the team with the current
        this.maxId = teamsDataAccess.loadMaxId();

        //Load all the teams and team members are store them in a list
        this.teamMembers = teamsDataAccess.loadTeamMembers();
        this.teams = teamsDataAccess.loadTeams();

        //Create a new instance of HashMap<Player, Team> that hold the team invites
        this.invites = new HashMap<>();
    }

    public Team createNewTeam(String name, UUID owner) {
        maxId++;

        Team team = teamsDataAccess.insertTeamIntoDataBase(maxId, name, owner);
        teams.add(team);
        return team;
    }
    public TeamMember createNewTeamMember(UUID uuid) {
        TeamMember member = teamsDataAccess.insertTeamMemberIntoDataBase(uuid);
        teamMembers.add(member);

        return member;
    }



    //Get Team Member based on UUID
    public TeamMember getMemberFromUUID(UUID uuid) {
        for(TeamMember member: teamMembers) {
            if(member.getUuid().equals(uuid)) {
                return member;
            }
        }
        return null;
    }

    //Get Team Members Based on Team
    public List<TeamMember> getMembersOfTeam(int id) {
        List<TeamMember> members = new ArrayList<>();
        for (TeamMember member : teamMembers) {
            if (member.getTeam_id() == id) {
                members.add(member);
            }
        }
        return members;
    }

    /**
     * Method that update all the team members information in the database
     * To be used mainly when the plugin is disabled
     */
    public void saveTeamMembers() {
        if(!teamMembers.isEmpty()) {
            for(TeamMember member: teamMembers) {
                teamsDataAccess.updateTeamMemberIntoDatabase(member);
            }
        }
    }

    /**
     * Method that update all teams information in the database
     * To be used mainly when the plugin is disabled
     */
    public void saveTeams() {
        if(!teams.isEmpty()) {
            for(Team team: teams) {
                teamsDataAccess.updateTeamInDatabase(team);
            }
        }
    }


    /**
     * Method that return a Team instance based of the name provided
     * @param name represent the name of the team to be found
     * @return
     */
    public Team returnTeamFromName(String name) {
        for(Team team: teams) {
            if(team.getName().equalsIgnoreCase(name)) {
                return team;
            }
        }
        return null;
    }
    public Team returnTeamFromId(int id) {
        for(Team team: teams) {
            if(team.getId() == id) {
                return team;
            }
        }
        return null;
    }
    private boolean containsSpecialCharacter(String s) {
        Pattern pat = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher mat = pat.matcher(s);

        return mat.find();
    }
    public boolean validateTeamName(String name) {
       //Check if the name if bigger than five or contains special Characters
       if(name.length() > 5 || containsSpecialCharacter(name)) {
           return false;
       }
       //Check if a already existing team has the same name
       else if(this.returnTeamFromName(name) != null) {
           return false;
       }
       //If both of the above conditions are not met than the name is valid
       else {
           return true;
       }
    }

    //Methods relating to the Invites System
    public boolean addPlayerInvite(Player p, Team team) {
        invites.put(p, team);
        return true;
    }
    public Team returnPlayerInviteTeam(Player p) {
        if(!invites.containsKey(p)) {
           return null;
        }
        return invites.get(p);
    }
    public boolean removeTeamInvite(Player p) {
        if(!invites.containsKey(p)) {
            return false;
        }
        invites.remove(p);
        return true;
    }
    public void runInviteDuration(Player p, Team t) {
        this.addPlayerInvite(p, t);
        new BukkitRunnable() {
            @Override
            public void run() {
                removeTeamInvite(p);
            }
        }.runTaskLater(pvp, 120*20L);
    }
}
