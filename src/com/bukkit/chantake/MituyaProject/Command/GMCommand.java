/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bukkit.chantake.MituyaProject.Command;

import com.bukkit.chantake.MituyaProject.MituyaProject;
import com.bukkit.chantake.MituyaProject.Player.PlayerInstance;
import org.bukkit.entity.Player;

/**
 *
 * @author chantake
 */
public class GMCommand {

    public static boolean Command(MituyaProject plugin, String[] message, Player players, PlayerInstance player) {
        if(message[0].equalsIgnoreCase("/say")) {
            String t = "";
            for(int i=0;i<message.length;i++) {
                t += message[i];
            }
            plugin.getServer().broadcastMessage(t);
        } else {
            return false;
        }
        return true;
    }

}
