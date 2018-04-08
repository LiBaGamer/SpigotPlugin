package de.libagamer.spigotplugin.main.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InventoryManager {

    private HashMap<Player, Inventory> playerInventoryHashMap = new HashMap<>();

    public Inventory getPlayerInventory(Player player) {

        if(playerInventoryHashMap.containsKey(player)) {
            player.sendMessage("Teleporter Inventar wird geladen...");
            return playerInventoryHashMap.get(player);
        }
        else {
            player.sendMessage("Inventar konnte nicht geladen werden.");
            return null;
        }
    }

    public void setPlayerInventoryHashMap(Player player, Inventory inventory){

        ItemStack placeholder = new ItemStack(Material.STAINED_GLASS_PANE , 1, (byte) 15);
        ItemMeta meta = placeholder.getItemMeta();
        meta.setDisplayName("§0 ");
        meta.setLore(null);
        placeholder.setItemMeta(meta);
        ItemStack item_spawn = new ItemStack(Material.DRAGON_EGG);
        ItemMeta item_spawn_meta = item_spawn.getItemMeta();
        List<String> item_spawn_lore = new ArrayList<>();
        item_spawn_lore.add("§cTeleportiert dich zum Spawn!");
        item_spawn_meta.setDisplayName("§6Spawn");
        item_spawn_meta.setLore(item_spawn_lore);
        item_spawn.setItemMeta(item_spawn_meta);
        inventory.setItem(13, item_spawn);

        ItemStack item_simon = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) item_simon.getItemMeta();
        List<String> item_simon_lore = new ArrayList<>();
        item_simon_lore.add("§cTeleportiert dich zum ziemlich fetten Simon!");
        skullMeta.setOwner("RushKeks");
        skullMeta.setDisplayName("§5Der fette Simon");
        skullMeta.setLore(item_simon_lore);
        item_simon.setItemMeta(skullMeta);
        inventory.setItem(10, item_simon);

        playerInventoryHashMap.put(player, inventory);

        player.sendMessage("Teleporter Inventar wurde erstellt und gesichert!");
    }

}
