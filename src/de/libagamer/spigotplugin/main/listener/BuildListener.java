package de.libagamer.spigotplugin.main.listener;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

public class BuildListener implements Listener {

    @Deprecated
    @EventHandler
    public void onBuild(BlockBreakEvent event){

        Block brokenBlock = event.getBlock();
        Player player = event.getPlayer();
        World world = player.getWorld();
        Location BlockLocation = brokenBlock.getLocation();
        int X = BlockLocation.getBlockX();
        int Y = BlockLocation.getBlockY();
        int Z = BlockLocation.getBlockZ();

        if(player.getName().equalsIgnoreCase("RushKeks")) {

            boolean allowed = false;
            //allowed = brokenBlock.getType().equals(Material.SIGN) || brokenBlock.getType().equals(Material.SIGN_POST) || brokenBlock.getType().equals(Material.WALL_SIGN);

            for(int i = -1; i <= 1; i++){

                //for(int f = -1; f < 2; f++){

                    for(int d = -1; d <= 1; d++){

                        Location scanlocation = new Location(world, X + i, Y, Z + d);
                        Material material = world.getBlockAt(scanlocation).getType();
                        if(!allowed){
                            allowed = material.equals(Material.SIGN) || material.equals(Material.SIGN_POST) || material.equals(Material.WALL_SIGN);
                        }


                    }

                //}

            }

            if(allowed){

            if (X < -157 && X > -181) {

                if (Y > 64 && Y < 132) {

                    if (Z < -120 && Z > -136) {

                        event.setCancelled(true);
                        player.sendMessage(ChatColor.RED + "Der fette RushKeks darf leider keine wichtigen Blöcke (für Schilder) abbauen! :(");

                        if ((new Random().nextBoolean())) {
                            player.sendTitle(ChatColor.RED + "nö", "");
                        } else {
                            player.sendTitle("", ChatColor.RED + "nö");
                        }

                    }

                }

            }

            }


        }


    }
}
