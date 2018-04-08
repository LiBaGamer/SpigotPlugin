package de.libagamer.spigotplugin.main.commands;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if(commandSender instanceof Player){

            Player p = ((Player) commandSender).getPlayer();

            World currentWorld = p.getWorld();

            Location WorldSpawn = currentWorld.getSpawnLocation();

            p.teleport(WorldSpawn);
            p.sendMessage("Du wurdest zum Spawn teleportiert!");

        }



        return false;
    }
}
