package de.libagamer.spigotplugin.main.commands;

import de.libagamer.spigotplugin.main.permissions.PermissionManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PitchCommand implements CommandExecutor {

    private PermissionManager permissionManager = new PermissionManager();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player){

            Player p = (Player) commandSender;

            if(!permissionManager.hasPermission(p, "libaplugin.pitch")){
                p.sendMessage(ChatColor.RED + "Du hast keine Rechte auf diesen Befehl!");
                return false;
            }

            p.sendMessage("YAW: " + p.getLocation().getYaw() + " | Pitch: " + p.getLocation().getPitch());

        }
        return false;
    }
}
