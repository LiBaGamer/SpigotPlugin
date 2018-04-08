package de.libagamer.spigotplugin.main.commands;

import de.libagamer.spigotplugin.main.Main;
import de.libagamer.spigotplugin.main.permissions.PermissionManager;
import de.libagamer.spigotplugin.main.util.LoadCountdown;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CountdownCommand implements CommandExecutor {
    private final Main instance;
    public CountdownCommand(Main main){
        this.instance = main;
    }
    private PermissionManager permissionManager = new PermissionManager();


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender instanceof Player) {

            Player player = (Player) commandSender;

            if(!permissionManager.hasPermission(player, "libaplugin.countdown")){
                player.sendMessage(ChatColor.RED + "Du hast keine Rechte auf diesen Befehl!");
                return false;
            }

            if (args.length == 0) {
                player.sendMessage("Das Kommando muss eine Zahl enthalten!");
            }
            if(args.length == 1){
                String number = args[0];

                if(StringUtils.isNumeric(number)){
                    int starttimer = Integer.valueOf(number);

                    new LoadCountdown(instance, player, (float)starttimer);
                }
                else {
                    player.sendMessage(number + " sieht nicht nach einer Zahl aus!");
                }
            }
            else {
                player.sendMessage("Falsche Benutzung des Commands");
            }
        }



        return false;
    }



}
