package de.libagamer.spigotplugin.main.commands;


import de.libagamer.spigotplugin.main.Main;
import de.libagamer.spigotplugin.main.MojangAPI_UUID;
import de.libagamer.spigotplugin.main.permissions.PermissionManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class RankCommand implements CommandExecutor {

    private Main instance;

    public RankCommand(Main main){
        this.instance = main;
    }

    private PermissionManager permissionManager = new PermissionManager();


    @Deprecated
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if(commandSender instanceof Player){
            Player p = ((Player) commandSender).getPlayer();

            if(!permissionManager.hasPermission(p, "libaplugin.setrank")){
                p.sendMessage(ChatColor.RED + "Du hast keine Rechte auf diesen Befehl!");
                return false;
            }

            if(args.length < 2){
                p.sendMessage("Zu wenig Argumente!");
            }
            if(args.length == 2) {

                String targetPlayerName = args[0];
                String targetRank = args[1];
                String uuid = MojangAPI_UUID.getTrimmedUUID(targetPlayerName);

                if(!instance.getConfig().contains("ranks." + targetRank)){
                    p.sendMessage("Der Rang " + targetRank + " existiert nicht!");
                }
                else{

                    if (!uuid.equals("error")) {
                        if (!instance.getConfig().contains(uuid)) {
                            p.sendMessage(targetPlayerName + " war noch nie auf dem Server! Config-Eintrag wird erstellt!");
                            //List<String> list = new ArrayList<>();
                            //list.add(targetRank);
                            instance.getConfig().set("players." + uuid, targetRank);
                            instance.saveConfig();
                        }

                        //List<String> list = new ArrayList<>();
                        //list.add(targetRank);
                        instance.getConfig().set("players." + uuid, targetRank);
                        instance.saveConfig();

                        p.sendMessage("Der Spieler " + targetPlayerName + " hat den Rang " + targetRank + " erhalten!");
                    }

                }


            }
            if(args.length > 2){

                p.sendMessage("Zu viele Argumente");
            }

        }

        return false;
    }

}
