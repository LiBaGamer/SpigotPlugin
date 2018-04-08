package de.libagamer.spigotplugin.main.listener;

import de.libagamer.spigotplugin.main.permissions.PermissionManager;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

public class ToggleFlightListener implements Listener {

    private PermissionManager permissionManager = new PermissionManager();

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event){

        Player p = event.getPlayer();

        if(permissionManager.hasPermission(p, "libaplugin.doublejump")){

            if(p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR){
                p.playSound(p.getLocation(), Sound.FIREWORK_LAUNCH, 5, 1);
                event.setCancelled(true);
                Vector v = p.getLocation().getDirection().multiply(1).setY(1);
                p.setVelocity(v);
            }

        }
    }
}
