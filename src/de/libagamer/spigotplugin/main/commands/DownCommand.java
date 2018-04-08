package de.libagamer.spigotplugin.main.commands;

import de.libagamer.spigotplugin.main.permissions.PermissionManager;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DownCommand implements CommandExecutor{

    private PermissionManager permissionManager = new PermissionManager();

    @Override
    public boolean onCommand(CommandSender commandsender, Command command, String s, String[] args) {

        int heigth = 0;

        if(commandsender instanceof Player) {
            Player p = ((Player) commandsender).getPlayer();

            if(!permissionManager.hasPermission(p, "libaplugin.down")){
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

                    int teleportheight = p.getLocation().getBlockY() - heigth;

                    if(teleportheight < 0) {
                        p.sendMessage(ChatColor.RED + "Du kannst dich nicht auf Höhe " + teleportheight + " teleportieren!");
                    }
                    else {
                        p.sendMessage(ChatColor.GREEN + "Du wirst " + heigth + " Blöcke nach unten teleportiert! (Y: " + teleportheight + ")");

                        Location location = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() - heigth, p.getLocation().getZ());
                        location.setPitch(p.getLocation().getPitch());
                        location.setYaw(p.getLocation().getYaw());
                        p.teleport(location);
                        clearSurroundings(p);

                    }

                }

            }

        }
        return false;
    }


    private void clearSurroundings(Player player){

        World world = player.getWorld();
        Location location = player.getLocation();
        List<Location> locationList = new ArrayList<>();

        for(int x = -1; x < 2; x++){
            for (int y = 0; y < 3; y++){
                for(int z = -1; z < 2; z++){
                    locationList.add(new Location(world, location.getBlockX() + x, location.getBlockY() + y, location.getBlockZ() + z));
                }
            }
        }

        for (Location blockLocation : locationList) {
            world.getBlockAt(blockLocation).setType(Material.AIR);
        }

    }
}
