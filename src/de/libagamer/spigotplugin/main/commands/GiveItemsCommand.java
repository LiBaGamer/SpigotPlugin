package de.libagamer.spigotplugin.main.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveItemsCommand implements CommandExecutor {



    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player){

            Player player = (Player) commandSender;

            player.getInventory().clear();

            ItemStack Teleporter = new ItemStack(Material.COMPASS, 1);
            ItemMeta meta = Teleporter.getItemMeta();
            meta.setDisplayName("§6Teleporter");
            meta.setLore(null);
            Teleporter.setItemMeta(meta);
            player.getInventory().setItem(0, Teleporter);

            player.updateInventory();

        }


        return false;
    }
}
