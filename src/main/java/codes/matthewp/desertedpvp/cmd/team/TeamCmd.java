package codes.matthewp.desertedpvp.cmd.team;

import codes.matthewp.desertedcore.string.ColorHelper;
import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.data.Messages;
import codes.matthewp.desertedpvp.teams.Team;
import codes.matthewp.desertedpvp.teams.TeamMember;
import codes.matthewp.desertedpvp.user.User;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeamCmd implements CommandExecutor {

    private DesertedPvP pvp;

    public TeamCmd(DesertedPvP pvp) {
        this.pvp = pvp;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equalsIgnoreCase("team")) {
            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if (p.hasPermission("pvp.team")) {
                    if (strings.length == 0) {
                        sendHelp(p);
                        return false;
                    } else {
                        User u = pvp.getUserManager().getUser(p.getUniqueId());
                        if (strings[0].equalsIgnoreCase("create")) {
                            //Check if the number of arguments is correct
                            if(strings.length == 1 || strings.length >= 3) {
                                sendHelp(p);
                                return true;
                            }
                            //Check if the User is already in a Team
                            TeamMember member = u.getTeamMember();
                            if(member.getTeam_id() != 0) {
                                p.sendMessage(Messages.getMessage("alreadyInTeam"));
                                return true;
                            }
                            //Check if the name for the new team if valid.
                            String name = strings[1];
                            if(!pvp.getTeamManager().validateTeamName(name)) {
                                p.sendMessage(Messages.getMessage("invalidTeamName"));
                                return true;
                            }

                            //Return an instance of the newly created team so we can change the team_id for
                            //team member and update the database
                            Team team = pvp.getTeamManager().createNewTeam(name, p.getUniqueId());
                            member.setTeam_id(team.getId());
                            pvp.getTeamsDataAcess().updateTeamMemberIntoDatabase(member);
                        } else if (strings[0].equalsIgnoreCase("kick")) {
                            //Check if the number of arguments is correct
                            if(strings.length == 1 || strings.length >= 3) {
                                sendHelp(p);
                                return true;
                            }

                            //Check if the user if part of a team
                            TeamMember teamMember = pvp.getTeamManager().getMemberFromUUID(p.getUniqueId());
                            if(teamMember.getTeam_id() == 0) {
                                p.sendMessage(Messages.getMessage("notCurrentlyInTeam"));
                                return true;
                            }

                            //Check if the user is the team owner
                            Team team = pvp.getTeamManager().returnTeamFromId(teamMember.getTeam_id());
                            if(!team.getOwner().equals(teamMember.getUuid())) {
                                p.sendMessage(Messages.getMessage("notOwner"));
                                return true;
                            }

                            //Check if the target player has never joined the server
                            //Or if the target is not a member of the team
                            OfflinePlayer off = Bukkit.getServer().getOfflinePlayer(strings[1]);
                            if(off == null
                                    || team.getMembers().contains(pvp.getTeamManager().getMemberFromUUID(off.getUniqueId()))) {
                                p.sendMessage("userNotInTeam");
                                return true;
                            }

                            //Change the id of the target Team Member to 0 and remove them from the team member list
                            //Update the change to the database.
                            TeamMember target = pvp.getTeamManager().getMemberFromUUID(off.getUniqueId());
                            target.setTeam_id(0);
                            team.getMembers().remove(target);

                            pvp.getTeamsDataAcess().updateTeamMemberIntoDatabase(target);
                        } else if (strings[0].equalsIgnoreCase("invite")) {
                            //Check if the number of arguments is correct
                            if(strings.length == 1 || strings.length >= 3) {
                                sendHelp(p);
                                return true;
                            }

                            //Check if the user if part of a team
                            TeamMember teamMember = pvp.getTeamManager().getMemberFromUUID(p.getUniqueId());
                            if(teamMember.getTeam_id() == 0) {
                                p.sendMessage(Messages.getMessage("notCurrentlyInTeam"));
                                return true;
                            }
                            //Check if the user is the team owner
                            Team team = pvp.getTeamManager().returnTeamFromId(teamMember.getTeam_id());
                            if(!team.getOwner().equals(teamMember.getUuid())) {
                                p.sendMessage(Messages.getMessage("notOwner"));
                                return true;
                            }

                            //Check if the mentioned player if online
                            Player target = Bukkit.getServer().getPlayer(strings[1]);
                            if(target == null) {
                                p.sendMessage(Messages.getMessage("playerNotOnline"));
                                return true;
                            }

                            pvp.getTeamManager().runInviteDuration(target, team);
                            p.sendMessage(Messages.getMessage("inviteSend"));
                            target.sendMessage(Messages.getMessage("inviteMessage").replace("%team%", team.getName()));
                            return true;
                        } else if (strings[0].equalsIgnoreCase("accept")) {
                            //Check if the number of arguments is correct
                            if(strings.length == 1 || strings.length >= 3) {
                                sendHelp(p);
                                return true;
                            }

                            //Check if the user if part of a team
                            TeamMember teamMember = pvp.getTeamManager().getMemberFromUUID(p.getUniqueId());
                            if(teamMember.getTeam_id() != 0) {
                                p.sendMessage(Messages.getMessage("alreadyInTeam"));
                                return true;
                            }

                            Team inviteTeam = pvp.getTeamManager().returnPlayerInviteTeam(p);
                            if(inviteTeam == null) {
                                p.sendMessage(Messages.getMessage("noPendingInvites"));
                                return true;
                            }

                            //Set the team member's team id to his new team
                            //Add the team member to the team members list
                            //Remove the invite from the list
                            //Update the databased for the TeamMember
                            pvp.getTeamManager().removeTeamInvite(p);
                            teamMember.setTeam_id(inviteTeam.getId());
                            inviteTeam.addMember(teamMember);

                            pvp.getTeamsDataAcess().updateTeamMemberIntoDatabase(teamMember);
                            p.sendMessage(Messages.getMessage("inviteAccepted"));
                            return true;

                        } else if (strings[0].equalsIgnoreCase("top")) {
                            sendHelp(p);
                            return false;
                        } else if (strings[0].equalsIgnoreCase("help")) {
                            sendHelp(p);
                            return false;
                        } else {
                            sendHelp(p);
                            return false;
                        }
                    }
                } else {
                    p.sendMessage(Messages.getMessage("noPerm"));
                    return false;
                }
            } else {
                commandSender.sendMessage(Messages.getMessage("mustBePlayer"));
                return false;
            }
        }
        return false;
    }


    private void sendHelp(Player p) {
        StringBuilder builder = new StringBuilder();
        for (String str : pvp.getFileUtil().getTeams().getConfig().getStringList("teamhelp")) {
            builder.append(str + " ");
        }
        p.sendMessage(ColorHelper.color(builder.toString()));
    }
}
