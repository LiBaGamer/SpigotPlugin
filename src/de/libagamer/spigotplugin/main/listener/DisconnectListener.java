package de.libagamer.spigotplugin.main.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

public class DisconnectListener implements Listener{

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event){

        if(event.getReason().equalsIgnoreCase("disconnect.spam")){

            event.setCancelled(true);

        }


    }
}
