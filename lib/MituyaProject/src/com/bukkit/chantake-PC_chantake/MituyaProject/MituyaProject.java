package com.bukkit.chantake-PC_chantake.MituyaProject; 
 
import java.io.*; 
import java.util.HashMap; 
 
import org.bukkit.Server; 
import org.bukkit.entity.Player; 
import org.bukkit.event.Event; 
import org.bukkit.event.Event.Priority; 
import org.bukkit.plugin.PluginDescriptionFile; 
import org.bukkit.plugin.PluginLoader; 
import org.bukkit.plugin.PluginManager; 
import org.bukkit.plugin.java.JavaPlugin; 
 
/** 
 * MituyaProject for Bukkit 
 * 
 * @author chantake-PC_chantake 
 */ 
public class MituyaProject extends JavaPlugin { 
 private final MituyaProjectPlayerListener playerListener = new MituyaProjectPlayerListener(this); 
 private final MituyaProjectBlockListener blockListener = new MituyaProjectBlockListener(this); 
 private final HashMap debugees = new HashMap(); 
 
 public MituyaProject(PluginLoader pluginLoader, Server instance, 
 PluginDescriptionFile desc, File folder, File plugin, 
 ClassLoader cLoader) throws IOException { 
 super(pluginLoader, instance, desc, folder, plugin, cLoader); 
 // TODO: Place any custom initialisation code here 
 
 // NOTE: Event registration should be done in onEnable not here as all events are unregistered when a plugin is disabled 
 
 } 
 
 
 
 public void onEnable() { 
 // TODO: Place any custom enable code here including the registration of any events 
 
 // Register our events 
 PluginManager pm = getServer().getPluginManager(); 
 getServer().getPluginManager().registerEvent(Event.Type.BLOCK_PLACED, blockListener, Priority.Normal, this); 
 pm.registerEvent(Event.Type.PLAYER_COMMAND, playerListener, Priority.Normal, this); 
 // EXAMPLE: Custom code, here we just output some info so we can check all is well 
 PluginDescriptionFile pdfFile = this.getDescription(); 
 System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" ); 
 } 
 public void onDisable() { 
 // TODO: Place any custom disable code here 
 
 // NOTE: All registered events are automatically unregistered when a plugin is disabled 
 
 // EXAMPLE: Custom code, here we just output some info so we can check all is well 
 System.out.println("Goodbye world!"); 
 } 
 public boolean isDebugging(final Player player) { 
 if (debugees.containsKey(player)) { 
 return debugees.get(player); 
 } else { 
 return false; 
 } 
 } 
 
 public void setDebugging(final Player player, final boolean value) { 
 debugees.put(player, value); 
 } 
} 
 