package codes.matthewp.desertedpvp.teams;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.data.TeamsDataAccess;

import java.util.List;
import java.util.UUID;

/**
 * Created by Mario on 9/15/2018.
 */
public class TeamManager {
    private List<Team> teams;
    private TeamsDataAccess teamsDataAccess;

    public TeamManager(DesertedPvP pvp) {
        this.teamsDataAccess = pvp.getTeamsDataAcess();
        this.teams = teamsDataAccess.loadTeams();
    }

    public void createNewTeam(String name, UUID owner) {
        Team team = teamsDataAccess.insertTeamIntoDataBase(name, owner);
        teams.add(team);
    }


}
