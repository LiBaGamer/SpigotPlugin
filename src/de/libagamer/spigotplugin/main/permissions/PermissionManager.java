package de.libagamer.spigotplugin.main.permissions;


import de.libagamer.spigotplugin.main.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PermissionManager {

    public PermissionManager(){

    }

    private List<String> permissions = new ArrayList<>();

    private Main instance = Main.getInstance();
    private FileConfiguration config = instance.getConfig();

    public boolean hasPermission(Player player, String permission){

        //System.out.println(player.getName() + " - " + permission);

        String uniqueID = "players." + player.getUniqueId().toString().replace("-", "");
        String group;
        if(config.contains(uniqueID)) {
            group = config.getString(uniqueID);
            //System.out.println("Gruppe: " + group);
        }
        else {
            //System.out.println("Keine Gruppe");
            return false;
        }

        String grouppath = "permissions." + group;

        if(config.contains(grouppath)){

            permissions = config.getStringList(grouppath);

            //System.out.println(permissions.stream().collect(Collectors.joining(" | ")));

            if(permissions.contains(permission)){
                //System.out.println("Permission ist 1zu1 in der Liste");
                return true;
            }
            else if(permission.startsWith("*")){
                //System.out.println("User hat alle Permissions");
                return true;
            }
            else{

                for (String containedpermission: permissions) {

                    //System.out.println(containedpermission + " <-- | --> " + permission);

                    if(containedpermission.startsWith(permission)){
                        //System.out.println("Permission aus der Liste beginnt mit " + permission);
                        return true;
                    }

                    if(containedpermission.contains("*")){

                        //System.out.println("Permission hat ein '*'");

                        String cuttedPermission = containedpermission.replace("*", "");

                        if(permission.startsWith(cuttedPermission)){
                            //System.out.println(permission + " startet mit " +  cuttedPermission);
                            return true;
                        }
                    }
                }
            }
        }
        else
            return false;

        return false;
    }
}
