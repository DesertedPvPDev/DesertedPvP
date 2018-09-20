package codes.matthewp.desertedpvp.teams;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.data.TeamsDataAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Mario on 9/15/2018.
 */
public class TeamManager {
    private static int maxId;

    private TeamsDataAccess teamsDataAccess;
    private List<TeamMember> teamMembers;
    private List<Team> teams;


    public TeamManager(DesertedPvP pvp) {
        this.teamsDataAccess = pvp.getTeamsDataAcess();
        this.maxId = teamsDataAccess.loadMaxId();

        //Load all the teams and team members are store them in a list
        this.teamMembers = teamsDataAccess.loadTeamMembers();
        this.teams = teamsDataAccess.loadTeams();
    }

    public void createNewTeam(String name, UUID owner) {
        maxId++;

        Team team = teamsDataAccess.insertTeamIntoDataBase(maxId, name, owner);
        teams.add(team);
    }
    public void createNewTeamMember(UUID uuid) {
        TeamMember member = teamsDataAccess.insertTeamMemberIntoDataBase(uuid);
        teamMembers.add(member);
    }

    //Get Team Members Based on Team
    public List<UUID> getMembersOfTeam(int id) {
        List<UUID> members = new ArrayList<>();
        for(TeamMember member: teamMembers) {
            if(member.getTeam_id() == id) {
                members.add(member.getUuid());
            }
        }
        return members;
    }


    //Method that handle teams's name
    public Team returnNameFromName(String name) {
        for(Team team: teams) {
            if(team.getName().equalsIgnoreCase(name)) {
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
       else if(this.returnNameFromName(name) != null) {
           return false;
       }
       //If both of the above conditions are not met than the name is valid
       else {
           return true;
       }
    }
}
