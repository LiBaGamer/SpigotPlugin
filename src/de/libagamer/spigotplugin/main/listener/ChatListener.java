package de.libagamer.spigotplugin.main.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChatListener implements Listener {

    @Deprecated
    @EventHandler
    public void onPlayerChatEvent(PlayerChatEvent event){

        String message = event.getMessage();

        if(message.contains("@")){

            //System.out.println("Message contains @");

            String[] strings = message.split(" ");

            List<String> outputList = new ArrayList<>();

            String playerName = null;

            for (int i = 0; i < strings.length; i++){

                if(strings[i].startsWith("@")){
                    playerName = strings[i].replace("@", "");

                    //System.out.println("Noticed Player Name: " + playerName);

                    outputList.add(ChatColor.AQUA + strings[i] + ChatColor.WHITE);

                    //mentionedPlayer = Bukkit.getPlayer(MojangAPI_UUID.getUuid(playerName));
                }
                else {

                    //System.out.println("Noticed NO Player Name: " + strings[i]);

                    //playerName = "";

                    outputList.add(strings[i]);

                }

            }




            event.setCancelled(true);

            String outputMessage = outputList.stream().collect(Collectors.joining(" "));

            for (Player p: Bukkit.getOnlinePlayers()) {

                //System.out.println(p.getName() + " ||| " + playerName);

                if(p.getName().equals(playerName)){
                    //System.out.println(p.getName() + " --- " + playerName);

                    p.sendMessage(event.getPlayer().getDisplayName() + ChatColor.DARK_GRAY + " » " + ChatColor.WHITE + outputMessage);
                    p.playSound(p.getLocation(), Sound.ANVIL_LAND, 5, 1);
                }
                else {
                    //System.out.println(p.getName());
                    p.sendMessage(event.getPlayer().getDisplayName() + ChatColor.DARK_GRAY + " » " + ChatColor.WHITE + message);
                }

            }

        }
        else {
            //System.out.println("Message doesnt contain @");
            event.setCancelled(false);
            event.setFormat(event.getPlayer().getDisplayName() + ChatColor.DARK_GRAY + " » " + ChatColor.WHITE + event.getMessage());
        }



    }
}
