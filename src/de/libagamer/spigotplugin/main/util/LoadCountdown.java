package de.libagamer.spigotplugin.main.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class LoadCountdown {

    Player player;
    float time;
    float count;
    Plugin plugin;
    int scheduler;

    public LoadCountdown(Plugin plugin, Player player, float time){

        this.player = player;
        this.time = time;
        this.plugin = plugin;
        this.count = time;
        startCountdown();

    }

    private void startCountdown(){
        player.setExp(0.99f);
        player.setLevel((int) count);

        scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            @Override
            public void run() {

                count--;
                float exp = player.getExp();
                float remove = (float)1/time;
                float newExp = exp - remove;
                player.setExp(newExp);
                player.setLevel((int)count);

                if(count > 0) {
                    for(Player p:Bukkit.getOnlinePlayers()){
                        p.sendMessage(ChatColor.GRAY + "Der Countdown endet in " + ChatColor.AQUA + (int)count + ChatColor.GRAY + " Sekunden!");
                    }

                }
                if(count == 0){
                    for(Player p:Bukkit.getOnlinePlayers()) {
                        p.sendMessage(ChatColor.GRAY + "Der Countdown endet " + ChatColor.AQUA + "JETZT!");
                    }
                }

                if(count <= 3){
                    for(Player p:Bukkit.getOnlinePlayers()) {
                        p.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 1);
                    }
                }

                if(newExp <= 0){
                    Bukkit.getScheduler().cancelTask(scheduler);
                }

            }
        },0 ,20);
    }
}
