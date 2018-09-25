package codes.matthewp.desertedpvp.teams;

import java.util.List;
import java.util.UUID;

/**
 * Created by Mario on 9/13/2018.
 */
public class Team {
    private int id;
    private String name;
    private UUID owner;
    private List<TeamMember> members;

    public Team(int id, String name, UUID owner, List<TeamMember> members) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.members = members;
    }
    public int getId() { return id; }
    public String getName() {
        return name;
    }
    public UUID getOwner() {
        return owner;
    }
    public List<TeamMember> getMembers() {
        return members;
    }
    public void setMembers(List<TeamMember> members) {
        this.members = members;
    }
    public boolean addMember(TeamMember member) {
        if(members.size() ==8 || members.contains(member)){
            return false;
        }
        members.add(member);
        return true;
    }
    public boolean removeMember(TeamMember member) {
        if(!members.contains(member)) {
            return false;
        }
        members.remove(member);
        return true;
    }
}
