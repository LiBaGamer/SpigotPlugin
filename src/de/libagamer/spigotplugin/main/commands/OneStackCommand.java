package de.libagamer.spigotplugin.main.commands;

import de.libagamer.spigotplugin.main.permissions.PermissionManager;
import org.apache.commons.lang.StringUtils;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


public class OneStackCommand implements CommandExecutor {

    private Plugin plugin;

    public OneStackCommand(Plugin plugin){
        this.plugin = plugin;
    }

    private PermissionManager permissionManager = new PermissionManager();

    @Override
    public boolean onCommand(CommandSender commandsender, Command command, String s, String[] args) {

        int heigth = 0;

        if(commandsender instanceof Player) {
            Player p = ((Player) commandsender).getPlayer();

            if(!permissionManager.hasPermission(p, "libaplugin.onestack")){
                p.sendMessage(ChatColor.RED + "Du hast keine Rechte auf diesen Befehl!");
                return false;
            }


            if (args.length == 0) {
                p.sendMessage(ChatColor.RED + "Deine Nachricht muss eine Höhe enthalten!");
            }
            if (args.length > 1) {
                p.sendMessage(ChatColor.RED + "Deine Nachricht darf nur eine Höhe enthalten!");
            }
            if (args.length == 1) {

                if (!StringUtils.isNumeric(args[0])) {
                    p.sendMessage(ChatColor.RED + args[0] + " sieht nicht nach einer Zahl aus!");
                } else {
                    try {
                        heigth = Integer.valueOf(args[0]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    int teleportheight = p.getLocation().getBlockY() + heigth;

                    if (teleportheight > p.getWorld().getMaxHeight()) {
                        p.sendMessage(ChatColor.RED + "Die Höhe " + teleportheight + " übersteigt das Limit!");
                    } else {

                        double playeryaw = p.getLocation().getYaw();
                        Location setBlock = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ());
                        World world = p.getWorld();

                        System.out.println(playeryaw);

                        if(playeryaw >= 0 && playeryaw < 45){

                            setBlock.setZ(setBlock.getBlockZ() + 1);
                            setBlock.setY(setBlock.getBlockY() - 1);

                            while (setBlock.getBlockY() < teleportheight){

                                world.getBlockAt(setBlock).setType(Material.SANDSTONE);

                                setBlock.setZ(setBlock.getBlockZ() + 1);

                                world.getBlockAt(setBlock).setType(Material.SANDSTONE);

                                setBlock.setY(setBlock.getBlockY() + 1);

                            }

                        }
                        if(playeryaw >= 0 && playeryaw >= 315){

                            setBlock.setZ(setBlock.getBlockZ() + 1);
                            setBlock.setY(setBlock.getBlockY() - 1);

                            while (setBlock.getBlockY() < teleportheight){

                                world.getBlockAt(setBlock).setType(Material.SANDSTONE);

                                setBlock.setZ(setBlock.getBlockZ() + 1);

                                world.getBlockAt(setBlock).setType(Material.SANDSTONE);

                                setBlock.setY(setBlock.getBlockY() + 1);
                            }

                        }
                        if(playeryaw >= 45 && playeryaw <= 135){

                            setBlock.setX(setBlock.getBlockX() - 1);
                            setBlock.setY(setBlock.getBlockY() - 1);

                            while (setBlock.getBlockY() < teleportheight){

                                world.getBlockAt(setBlock).setType(Material.SANDSTONE);

                                setBlock.setX(setBlock.getBlockX() - 1);

                                world.getBlockAt(setBlock).setType(Material.SANDSTONE);

                                setBlock.setY(setBlock.getBlockY() + 1);
                            }

                        }
                        if(playeryaw > 135 && playeryaw <= 225){

                            setBlock.setZ(setBlock.getBlockZ() - 1);
                            setBlock.setY(setBlock.getBlockY() - 1);

                            while (setBlock.getBlockY() < teleportheight){

                                world.getBlockAt(setBlock).setType(Material.SANDSTONE);

                                setBlock.setZ(setBlock.getBlockZ() - 1);

                                world.getBlockAt(setBlock).setType(Material.SANDSTONE);

                                setBlock.setY(setBlock.getBlockY() + 1);
                            }

                        }
                        if(playeryaw > 225 && playeryaw <= 315){

                            setBlock.setX(setBlock.getBlockX() + 1);
                            setBlock.setY(setBlock.getBlockY() - 1);

                            while (setBlock.getBlockY() < teleportheight){

                                world.getBlockAt(setBlock).setType(Material.SANDSTONE);

                                setBlock.setX(setBlock.getBlockX() + 1);

                                world.getBlockAt(setBlock).setType(Material.SANDSTONE);

                                setBlock.setY(setBlock.getBlockY() + 1);
                            }

                        }

                    }

                }

            }
        }


        return false;
    }
}
