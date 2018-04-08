package de.libagamer.spigotplugin.main.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener {

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event){
        event.setQuitMessage(null);
    }

}
