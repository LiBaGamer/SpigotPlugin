package de.libagamer.spigotplugin.main.npc;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import de.libagamer.spigotplugin.main.MojangAPI_UUID;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class NPCManager {

    public void createNPC(Player player, String npcname){

        Location location = player.getLocation();
        location.setPitch(player.getLocation().getPitch());
        location.setYaw(player.getLocation().getYaw());

        MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer nmsWorld = ((CraftWorld) player.getWorld()).getHandle();
        String uuid = MojangAPI_UUID.getUuid(npcname);
        //String texture = MojangAPI_UUID.getTexture(MojangAPI_UUID.getTrimmedUUID(npcname));
        if(uuid.equalsIgnoreCase("error")){
            player.sendMessage("Dieser Spieler scheint nicht zu existieren!");
            return;
        }
        //if(texture.equalsIgnoreCase("error")){
        //    player.sendMessage("Skin konnte nicht gefunden werden!");
        //    return;
        //}


        GameProfile gameProfile = new GameProfile(UUID.fromString(uuid), "§a§l" + npcname);
        //gameProfile.getProperties().put("textures", new Property("textures", texture));

        EntityPlayer npc = new EntityPlayer(nmsServer, nmsWorld, gameProfile, new PlayerInteractManager(nmsWorld));
        npc.setPositionRotation(BlockPosition.ZERO, player.getLocation().getYaw(), player.getLocation().getPitch());
        Player npcPlayer = npc.getBukkitEntity().getPlayer();
        npcPlayer.setGameMode(GameMode.ADVENTURE);
        npcPlayer.setPlayerListName("");

        npc.setLocation(location.getX(), location.getY(), location.getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());


        for(Player p : Bukkit.getOnlinePlayers()){
            PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;

            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npc));
        }




    }
}
