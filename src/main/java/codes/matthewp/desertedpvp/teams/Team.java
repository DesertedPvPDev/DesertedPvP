package codes.matthewp.desertedpvp.teams;

import java.util.List;
import java.util.UUID;

/**
 * Created by Mario on 9/13/2018.
 */
public class Team {
    private String name;
    private UUID owner;
    private List<UUID> members;

    public Team(String name, UUID owner, List<UUID> members) {
        this.name = name;
        this.owner = owner;
        this.members = members;
    }
    public String getName() {
        return name;
    }
    public UUID getOwner() {
        return owner;
    }
    public List<UUID> getMembers() {
        return members;
    }
    public void setMembers(List<UUID> members) {
        this.members = members;
    }
    public boolean addMember(UUID uuid) {
        if(members.size() ==8 || members.contains(uuid)){
            return false;
        }
        members.add(uuid);
        return true;
    }
    public boolean removeMember(UUID uuid) {
        if(!members.contains(uuid)) {
            return false;
        }
        members.remove(uuid);
        return true;
    }
}
