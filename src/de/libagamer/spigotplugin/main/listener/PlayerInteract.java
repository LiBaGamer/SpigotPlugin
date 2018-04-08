package de.libagamer.spigotplugin.main.listener;

import de.libagamer.spigotplugin.main.util.InventoryManager;
import de.libagamer.spigotplugin.main.util.PlayerHeadLoader;
import org.bukkit.*;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerInteract implements Listener {

    private static PlayerHeadLoader headLoader = new PlayerHeadLoader();


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){

        Inventory clickedInventory = event.getClickedInventory();
        ItemStack clickedItem = clickedInventory.getItem(event.getRawSlot());
        HumanEntity player = event.getWhoClicked();
        Player p = (Player) player;
        Location spawn = player.getWorld().getSpawnLocation();
        Location simon = new Location(Bukkit.getWorld("world"), -168, 81 ,-104, 180, 0);
        Location lennart = new Location(Bukkit.getWorld("world"), -210, 82, -27, 90, 0);
        Location philipp = new Location(Bukkit.getWorld("world"), -210, 82, -6, 90, 0);

        //player.sendMessage("Open Inventory: " + clickedInventory.getName() + ChatColor.RESET + ". DragonEgg? " + (clickedItem.getType().equals(Material.DRAGON_EGG) ? "§aJA" : "§cNEIN"));//+ ". Klicked Item: " + clickedItem.getItemMeta().getDisplayName()+ " + Type: " + clickedItem.getType().toString());

        if(clickedInventory.getName().equalsIgnoreCase("§cTeleporter")){
            event.setCancelled(true);

            if(clickedItem == null){

            }
            else {
                if (clickedItem.getType().equals(Material.DRAGON_EGG)) {
                    if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("§6Spawn")) {
                        player.teleport(spawn);
                        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                        player.closeInventory();
                    }
                }
                if (clickedItem.getType().equals(Material.SKULL_ITEM)) {
                    if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("§5Der fette Simon")) {
                        player.teleport(simon);
                        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                        player.closeInventory();
                    }
                    if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("§6LiBaGamer")) {
                        player.teleport(lennart);
                        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                        player.closeInventory();
                    }
                    if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("§aAngebi_Philipp")) {
                        player.teleport(philipp);
                        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                        player.closeInventory();
                    }
                }
            }
        }


    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){

        Player player = event.getPlayer();

        //Inventory Teleporter = inventoryManager.getPlayerInventory(player);

        Inventory Teleporter = Bukkit.createInventory(player, 45, "§cTeleporter");
        ItemStack placeholder = new ItemStack(Material.STAINED_GLASS_PANE , 1, (byte) 15);
        ItemMeta meta = placeholder.getItemMeta();
        meta.setDisplayName("§0 ");
        meta.setLore(null);
        placeholder.setItemMeta(meta);

        //for(int i = 0; i < Teleporter.getSize(); i++){
        //    Teleporter.setItem(i, placeholder);
        //}

        ItemStack item_simon = headLoader.getHead(Bukkit.getOfflinePlayer(UUID.fromString("ffc9d1f5-9239-4be2-a620-f47045c2a8a4")));

        ItemStack item_lennart = headLoader.getHead(Bukkit.getOfflinePlayer(UUID.fromString("c8499aea-8e97-4bfc-be5f-675fc52c120e")));

        ItemStack item_philipp = headLoader.getHead(Bukkit.getOfflinePlayer(UUID.fromString("720814a9-23c5-45df-b488-66d9287f5a50")));

        ItemStack item_spawn = new ItemStack(Material.DRAGON_EGG);
        ItemMeta item_spawn_meta = item_spawn.getItemMeta();
        List<String> item_spawn_lore = new ArrayList<>();
        item_spawn_lore.add("§cTeleportiert dich zum Spawn!");
        item_spawn_meta.setDisplayName("§6Spawn");
        item_spawn_meta.setLore(item_spawn_lore);
        item_spawn.setItemMeta(item_spawn_meta);



        SkullMeta skullMeta = (SkullMeta) item_simon.getItemMeta();
        List<String> item_simon_lore = new ArrayList<>();
        item_simon_lore.add("§cTeleportiert dich zum ziemlich fetten Simon!");
        skullMeta.setDisplayName("§5Der fette Simon");
        skullMeta.setLore(item_simon_lore);
        item_simon.setItemMeta(skullMeta);

        SkullMeta lennartskullmeta = (SkullMeta) item_lennart.getItemMeta();
        List<String> item_lennart_lore = new ArrayList<>();
        item_lennart_lore.add("§cTeleportiert dich zum freshen Server Owner!");
        lennartskullmeta.setDisplayName("§6LiBaGamer");
        lennartskullmeta.setLore(item_lennart_lore);
        item_lennart.setItemMeta(lennartskullmeta);

        SkullMeta philippskullmeta = (SkullMeta) item_philipp.getItemMeta();
        List<String> item_philipp_lore = new ArrayList<>();
        item_philipp_lore.add("§cTeleportiert dich zum stolzen Rewinside.tv §aSupporter§c!");
        philippskullmeta.setDisplayName("§aAngebi_Philipp");
        philippskullmeta.setLore(item_philipp_lore);
        item_philipp.setItemMeta(philippskullmeta);


        /*
        ItemStack item_simon = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) item_simon.getItemMeta();
        List<String> item_simon_lore = new ArrayList<>();
        item_simon_lore.add("§cTeleportiert dich zum ziemlich fetten Simon!");
        skullMeta.setOwner("RushKeks");
        skullMeta.setDisplayName("§5Der fette Simon");
        skullMeta.setLore(item_simon_lore);

        item_simon.setItemMeta(skullMeta);

        */
        Teleporter.setItem(22, item_spawn);
        Teleporter.setItem(19, item_simon);
        Teleporter.setItem(25, item_lennart);
        Teleporter.setItem(4, item_philipp);

        if(event.hasItem()) {

            if (event.getItem().getType().equals(Material.COMPASS) && event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Teleporter")) {
                player.openInventory(Teleporter);
            }
        }




        /*
        switch (event.getAction()){
            case PHYSICAL: player.sendMessage("PHYSICAL interaction");break;
            case LEFT_CLICK_AIR: player.openInventory(Teleporter);player.sendMessage("Left Click Air with " + event.getItem().getItemMeta().getDisplayName() + ". Type of: " + event.getItem().getType().toString());break;
            case RIGHT_CLICK_AIR: player.openInventory(Teleporter);player.sendMessage("Right Click Air with " + event.getItem().getItemMeta().getDisplayName() + ". Type of: " + event.getItem().getType().toString());break;
            case LEFT_CLICK_BLOCK: player.sendMessage("Left Click Block with " + event.getItem().getItemMeta().getDisplayName() + ". Type of: " + event.getItem().getType().toString());break;
            case RIGHT_CLICK_BLOCK: player.sendMessage("Right Click Block with " + event.getItem().getItemMeta().getDisplayName() + ". Type of: " + event.getItem().getType().toString());break;
            default: player.sendMessage("Couldnt detect any interaction!"); break;
        }
        */

    }


}
