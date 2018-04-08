package de.libagamer.spigotplugin.main.listener;

import com.google.common.base.Splitter;
import de.libagamer.spigotplugin.main.ColorCodeParser;
import de.libagamer.spigotplugin.main.Main;
import de.libagamer.spigotplugin.main.permissions.PermissionManager;
import de.libagamer.spigotplugin.main.util.InventoryManager;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.*;
import org.bukkit.scoreboard.Scoreboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class JoinListener implements Listener {

    private ScoreboardManager sbmanager = Bukkit.getScoreboardManager();
    private Scoreboard scoreboard = sbmanager.getNewScoreboard();

    private final Main instance;

    private InventoryManager inventoryManager = new InventoryManager();

    public JoinListener(Main main) {
        this.instance = main;
    }

    private PermissionManager permissionManager = new PermissionManager();

    @Deprecated
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){

        Player p = event.getPlayer();

        //ProxiedPlayer player = BungeeCord.getInstance().getPlayer(p.getName());
        //player.getUniqueId();

        if (!instance.getConfig().contains("players." + p.getUniqueId().toString().replace("-", ""))) {
            System.out.println("Spieler " + p.getName() + " war noch nie auf diesem Server! Config wird angepasst!");

            instance.getConfig().set("players." + p.getUniqueId().toString().replace("-", ""), "User");
            instance.saveConfig();
        }

        System.out.println("Spieler " + p.getName() + " war bereits auf dem Server! Rollen werden geladen!");

        String rank = instance.getConfig().getString("players." + p.getUniqueId().toString().replace("-", ""));



        if(!instance.getConfig().contains("ranks." + rank)){
            p.sendMessage("Der Rang " + rank + " konnte nicht gefunden werden!");
        }
        else {

            String prefix = ColorCodeParser.parseColors(instance.getConfig().getStringList("ranks." + rank).get(0));

            //System.out.println(p.getName());

            //if(p.getName().equals("Angeber_Philipp")){
            //    p.setDisplayName(prefix + "Angebi_Philipp");
            //}
            //else {
                p.setDisplayName(prefix + p.getName());
            //}

            Iterator<String> iterator = Splitter.fixedLength(16).split(prefix).iterator();
            String splittedPrefix = iterator.next();

            String splittedSuffix;

            if(prefix.length() > 16){

                splittedSuffix = iterator.next();

            }
            else
                splittedSuffix = "";

            if(splittedSuffix.length() > 16){
                splittedSuffix = splittedSuffix.substring(0 , 13);
            }


            Team team;


            if(!scoreboard.getTeams().contains(scoreboard.getTeam(rank))){
                team = scoreboard.registerNewTeam(rank);
            }
            else {
                team = scoreboard.getTeam(rank);
            }

            team.addPlayer(p);
            team.setPrefix(splittedPrefix);
            team.setSuffix(splittedSuffix);
            team.setNameTagVisibility(NameTagVisibility.ALWAYS);
            team.setCanSeeFriendlyInvisibles(true);


            for (Player OP: Bukkit.getOnlinePlayers()) {

                OP.setScoreboard(scoreboard);
                OP.setDisplayName(OP.getDisplayName());
            }

        }

        p.setPlayerListName(p.getDisplayName());
        //event.setJoinMessage(ChatColor.GRAY + p.getDisplayName() + ChatColor.AQUA + " hat den Server betreten!");
        event.setJoinMessage(null);
        p.sendMessage(ChatColor.GRAY + "---" + ChatColor.AQUA + " Willkommen " + p.getDisplayName() + " auf dem LiBaGamer-DevServer! " + ChatColor.GRAY + "---");
        p.getInventory().clear();
        ItemStack Teleporter = new ItemStack(Material.COMPASS, 1);
        ItemMeta meta = Teleporter.getItemMeta();
        meta.setDisplayName("§6Teleporter");
        meta.setLore(null);
        Teleporter.setItemMeta(meta);
        p.getInventory().setItem(0, Teleporter);
        p.updateInventory();
        p.setHealth(20);
        p.setFoodLevel(20);
        p.setAllowFlight(permissionManager.hasPermission(p, "libaplugin.doublejump"));
        if(p.getGameMode().equals(GameMode.CREATIVE) || p.getGameMode().equals(GameMode.SPECTATOR)){
            p.setAllowFlight(true);
        }

    }

}
