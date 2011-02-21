package com.bukkit.chantake.MituyaProject;

import com.bukkit.chantake.MituyaProject.Command.AdminCommand;
import com.bukkit.chantake.MituyaProject.Command.GMCommand;
import com.bukkit.chantake.MituyaProject.Command.PlayerCommand;
import com.bukkit.chantake.MituyaProject.Command.SuperGMCommand;
import com.bukkit.chantake.MituyaProject.Perfomance.PerfomanceCheck;
import com.bukkit.chantake.MituyaProject.Player.PlayerInstance;
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

    //private final MituyaProject plugin;
    public static MituyaProject plugin;
    private ChatColor color;
    public PlayerInstance playerins = null;
    // public Misc Misc = new Misc();

    public MituyaProjectPlayerListener(MituyaProject instance) {
        plugin = instance;
    }

    public PlayerInstance getPlayerIns(Player players) {
        if (playerins == null) {
            playerins = new PlayerInstance(players);
        }
        return playerins;
    }

    @Override
    public void onPlayerChat(PlayerChatEvent event) {
        Player players = event.getPlayer();
        PlayerInstance player = this.getPlayerIns(players);
        String mes = event.getMessage();
        if (player.getGobaku()) {
            if (mes.startsWith("lwc") || mes.startsWith("shop") || mes.startsWith("tp")) {
                players.sendMessage(color.RED + "You Are ''GOBAKU'' :" + mes);
                event.setMessage(color.RED + "**GOBAKU**");
            }
        }
    }

    @Override
    public void onPlayerCommand(PlayerChatEvent event) {
        Player players = event.getPlayer();
        PlayerInstance player = this.getPlayerIns(players);
        boolean sdebug = false;
        String[] message = event.getMessage().split(" ");
        /* rank
         * 0 = default
         * 1 = *
         * 2 = VIP
         * 3 = GM
         * 4 = *
         * 5 = SuperGM
         * 6 = Admin
         */
        if (!PlayerCommand.Command(plugin, message, players, player) && player.getRank() >= 2) {
                if (!GMCommand.Command(plugin, message, players, player) && player.getRank() >= 5) {
                        if (!SuperGMCommand.Command(plugin, message, players, player) && player.getRank() == 6) {
                            AdminCommand.Command(plugin, message, players, player);
                        }
            }
        }
       if (message[0].equalsIgnoreCase("/shop")) {
            //shop 35:15 3
            if (message[1].equalsIgnoreCase("debug")) {
                player.DebugV();
                players.sendMessage(player.Server() + "Shop Debug : " + player.Debug());
            } else {
                String[] itemid = message[2].split(":");
                if (sdebug) {
                    players.sendMessage(color.RED + "ItemID:" + itemid[0] + " Item meny:" + itemid[1] + " set" + message[3]);
                }
            }
        }
    }
}
