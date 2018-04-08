package de.libagamer.spigotplugin.main.commands;

import de.libagamer.spigotplugin.main.permissions.PermissionManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RainCommand implements CommandExecutor {

    private PermissionManager permissionManager = new PermissionManager();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player){

            Player player = (Player) commandSender;

            if(!permissionManager.hasPermission(player, "libaplugin.weather")){
                player.sendMessage(ChatColor.RED + "Du hast keine Rechte auf diesen Befehl!");
                return false;
            }

            World world = player.getWorld();
            world.setStorm(true);
            world.setThundering(true);

            player.sendMessage(ChatColor.GREEN + "Das Wetter wurde zu regnerisch geändert!");

        }

        return false;
    }
}
