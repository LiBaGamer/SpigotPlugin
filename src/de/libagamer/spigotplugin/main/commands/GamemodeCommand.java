package de.libagamer.spigotplugin.main.commands;

import de.libagamer.spigotplugin.main.permissions.PermissionManager;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {

    private PermissionManager permissionManager = new PermissionManager();


    @Deprecated
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

       if(commandSender instanceof Player){
           Player p = ((Player) commandSender).getPlayer();

           if(!permissionManager.hasPermission(p, "libaplugin.gamemode")){
               p.sendMessage(ChatColor.RED + "Du hast keine Rechte auf diesen Befehl!");
               return false;
           }

           if(args.length < 1){
               p.sendMessage("Zu wenig Argumente!");

           }
           if(args.length == 1){

               int gm;
               if(StringUtils.isNumeric(args[0])){
                   gm = Integer.valueOf(args[0]);
               }
               else {

                   gm = p.getGameMode().getValue();
               }



               switch (gm){
                   case 0: p.setGameMode(GameMode.SURVIVAL); p.setAllowFlight(allowFlight(p)); break;
                   case 1: p.setGameMode(GameMode.CREATIVE); p.setAllowFlight(true); break;
                   case 2: p.setGameMode(GameMode.ADVENTURE); p.setAllowFlight(allowFlight(p)); break;
                   case 3: p.setGameMode(GameMode.SPECTATOR); p.setAllowFlight(true); break;

                   default: p.sendMessage("Gamemode " + gm + " existiert nicht!");

               }


           }
           if(args.length > 1){
               p.sendMessage("Zu viele Argumente!");
           }


       }





        return false;
    }


    private boolean allowFlight(Player player){

        return permissionManager.hasPermission(player, "libaplugin.doublejump");

    }
}
