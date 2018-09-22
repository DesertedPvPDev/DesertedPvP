package codes.matthewp.desertedpvp.teams;

import java.util.UUID;

/**
 * Created by Mario on 9/20/2018.
 */
public class TeamMember {
    private UUID uuid;
    private int arenaKills;
    private int team_id;

    public TeamMember(UUID uuid, int arenaKills, int team_id) {
        this.uuid = uuid;
        this.arenaKills = arenaKills;
        this.team_id = team_id;
    }

    public UUID getUuid() {
        return uuid;
    }
    public int getArenaKills() {
        return arenaKills;
    }
    public int getTeam_id() {
        return team_id;
    }
    public void setArenaKills(int amount) {
        this.arenaKills = amount;
    }
    public void setTeam_id(int id) { this.team_id = id; }
}
