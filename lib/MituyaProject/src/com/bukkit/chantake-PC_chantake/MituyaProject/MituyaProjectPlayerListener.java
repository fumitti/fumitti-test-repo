package com.bukkit.chantake-PC_chantake.MituyaProject; 
 
import java.io.*; 
import org.bukkit.Location; 
import org.bukkit.entity.*; 
import org.bukkit.event.player.PlayerChatEvent; 
import org.bukkit.event.player.PlayerEvent; 
import org.bukkit.event.player.PlayerListener; 
import org.bukkit.event.player.PlayerMoveEvent; 
import org.bukkit.inventory.*; 
import org.bukkit.material.MaterialData; 
import org.bukkit.*; 
import org.bukkit.ChatColor; 
 
 
 
 
 
/** 
 * Handle events for all Player related events 
 * @author chantake-PC_chantake 
 */ 
public class MituyaProjectPlayerListener extends PlayerListener { 
 private final MituyaProject plugin; 
 
 public MituyaProjectPlayerListener(MituyaProject instance) { 
 plugin = instance; 
 } 
 
 public void onPlayerCommand(PlayerChatEvent event) { 
 Player players = event.getPlayer(); 
 String[] message = event.getMessage().split(" "); 
 if(message[0].equalsIgnoreCase("/clearinventory")) { 
 players.getInventory().clear(); 
 } 
 } 
 
 
 
} 
 