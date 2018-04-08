package de.libagamer.spigotplugin.main.commands;

import de.libagamer.spigotplugin.main.permissions.PermissionManager;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UpCommand implements CommandExecutor {

    private PermissionManager permissionManager = new PermissionManager();

    @Override
    public boolean onCommand(CommandSender commandsender, Command command, String label, String[] args) {

        int heigth = 0;

        if(commandsender instanceof Player) {
            Player p = ((Player) commandsender).getPlayer();

            if(!permissionManager.hasPermission(p, "libaplugin.up")){
                p.sendMessage(ChatColor.RED + "Du hast keine Rechte auf diesen Befehl!");
                return false;
            }



            if(args.length == 0) {
                p.sendMessage(ChatColor.RED + "Deine Nachricht muss eine Höhe enthalten!");
            }
            if(args.length > 1) {
                p.sendMessage(ChatColor.RED + "Deine Nachricht darf nur eine Höhe enthalten!");
            }
            if(args.length == 1) {

                if(!StringUtils.isNumeric(args[0])) {
                    p.sendMessage(ChatColor.RED + args[0] + " sieht nicht nach einer Zahl aus!");
                }
                else {
                    try {
                        heigth = Integer.valueOf(args[0]);
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }

                    int teleportheight = p.getLocation().getBlockY() + heigth;

                    if(teleportheight > p.getWorld().getMaxHeight()) {
                        p.sendMessage(ChatColor.RED + "Du kannst dich nicht auf Höhe " + teleportheight + " teleportieren!");
                    }
                    else {
                        p.sendMessage(ChatColor.GREEN + "Du wirst " + heigth + " Blöcke nach oben teleportiert! (Y: " + teleportheight + ")");

                        Location location = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + heigth, p.getLocation().getZ());
                        location.setPitch(p.getLocation().getPitch());
                        location.setYaw(p.getLocation().getYaw());
                        p.teleport(location);

                        location.setY(location.getBlockY() - 1);
                        if(p.getWorld().getBlockAt(location).getType().equals(Material.AIR)){

                            p.getWorld().getBlockAt(location).setType(Material.GLASS);

                        }

                    }

                }

            }

        }
        return false;
    }
}
