package com.bukkit.chantake.MituyaProject;

import com.bukkit.chantake.MituyaProject.tool.Tools;
import com.nijiko.Messaging;
import java.io.*;
import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import com.nijikokun.bukkit.Permissions.Permissions;
import com.nijiko.permissions.PermissionHandler;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

/** 
 * MituyaProject for Bukkit 
 * 
 * @author chantake-PC_chantake 
 */
public class MituyaProject extends JavaPlugin {

    private final MituyaProjectPlayerListener playerListener = new MituyaProjectPlayerListener(this);
    private final MituyaProjectBlockListener blockListener = new MituyaProjectBlockListener(this);
    private final HashMap debugees = new HashMap();
    public static PermissionHandler Permissions = null;//Permission
    public static final Logger log = Logger.getLogger("Minecraft");
    private ChatColor color;
    public Tools tool;
    //Plugin Name
    public static String name = "MituyaProject";
    //Plugin CodeName
    public static String codename = "Mituya";
    public static String version = "2.0";

    public MituyaProject(PluginLoader pluginLoader, Server instance,
            PluginDescriptionFile desc, File folder, File plugin,
            ClassLoader cLoader) throws IOException {
        super(pluginLoader, instance, desc, folder, plugin, cLoader);
        // TODO: Place any custom initialisation code here

        // NOTE: Event registration should be done in onEnable not here as all events are unregistered when a plugin is disabled

    }

    public static void main(String[] args) {
        //nothing
    }

    @Override
    public void onEnable() {
        // TODO: Place any custom enable code here including the registration of any events

        // Register our events
        PluginManager pm = getServer().getPluginManager();
        getServer().getPluginManager().registerEvent(Event.Type.BLOCK_PLACED, blockListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_COMMAND, playerListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_CHAT, playerListener, Priority.Normal, this);
        // EXAMPLE: Custom code, here we just output some info so we can check all is well
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
        setupPermissions();//Permission SetUp
    }

    @Override
    public void onDisable() {
        // TODO: Place any custom disable code here

        // NOTE: All registered events are automatically unregistered when a plugin is disabled

        // EXAMPLE: Custom code, here we just output some info so we can check all is well
        System.out.println("Goodbye world!");
    }

    public boolean isDebugging(final Player player) {
        if (debugees.containsKey(player)) {
            return true;//debugees.get(player);
        } else {
            return false;
        }
    }

    public void setDebugging(final Player player, final boolean value) {
        debugees.put(player, value);
    }

    public void setupPermissions() {
        Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");


        if (Permissions == null) {
            if (test != null) {
                Permissions = ((Permissions) test).getHandler();
            } else {
                log.log(Level.INFO, "{0} Permission system not enabled. Disabling plugin.", Messaging.bracketize(name));
                this.getServer().getPluginManager().disablePlugin(this);
            }
        }
    }

    public ChatColor getColor() {
        return color;
    }

    public Tools getTools(){
        return tool;
    }
}
