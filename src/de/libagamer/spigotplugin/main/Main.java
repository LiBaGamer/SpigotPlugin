package de.libagamer.spigotplugin.main;

import de.libagamer.spigotplugin.main.commands.*;
import de.libagamer.spigotplugin.main.listener.*;
import de.libagamer.spigotplugin.main.npc.NPCManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    private static Main instance;
    private NPCManager npcManager;

    @Override
    public void onEnable(){
        System.out.println("Plugin by LiBaGamer enabled!");

        setInstance(this);
        this.npcManager = new NPCManager();

        config();

        registerCommands();
        registerListeners();
    }

    private void config() {

        if(!new File(getDataFolder() + "config.yml").exists()) {
            getConfig().options().copyDefaults(true);
            System.out.println("Couldn't find any config. Creating it now!");
        }
        saveConfig();

    }

    @Override
    public void onDisable(){

        saveConfig();

    }

    private void registerCommands(){

        getCommand("up").setExecutor(new UpCommand());
        getCommand("rank").setExecutor(new RankCommand(instance));
        getCommand("gm").setExecutor(new GamemodeCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("sun").setExecutor(new SunCommand());
        getCommand("rain").setExecutor(new RainCommand());
        getCommand("down").setExecutor(new DownCommand());
        getCommand("onestack").setExecutor(new OneStackCommand(this));
        getCommand("pitch").setExecutor(new PitchCommand());
        getCommand("yaw").setExecutor(new PitchCommand());
        getCommand("giveitems").setExecutor(new GiveItemsCommand());
        getCommand("countdown").setExecutor(new CountdownCommand(this));

    }

    private void registerListeners(){

        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new JoinListener(instance), this);
        pm.registerEvents(new ChatListener(), this);
        pm.registerEvents(new LeaveListener(), this);
        pm.registerEvents(new PlayerInteract(), this);
        pm.registerEvents(new DamageListener(), this);
        pm.registerEvents(new ToggleFlightListener(), this);
        pm.registerEvents(new HungerListener(), this);
        pm.registerEvents(new BuildListener(), this);
        pm.registerEvents(new DisconnectListener(), this);

    }

    public static Main getInstance(){
        return instance;
    }

    private void setInstance(Main maininstance){
        instance = maininstance;
    }


}
