/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bukkit.chantake.MituyaProject.Command;

import com.bukkit.chantake.MituyaProject.MituyaProject;
import com.bukkit.chantake.MituyaProject.Parameter.Parameter;
import com.bukkit.chantake.MituyaProject.Perfomance.PerfomanceCheck;
import com.bukkit.chantake.MituyaProject.Player.PlayerInstance;
import com.bukkit.chantake.MituyaProject.tool.HexTool;
import org.bukkit.entity.Player;

/**
 *
 * @author chantake
 */
public class AdminCommand {
    public static Parameter parameter;

    public static boolean Command(MituyaProject plugin, String[] message, Player players, PlayerInstance player) {
        boolean re = true;
        if (message[0].equalsIgnoreCase("/cpu")) {
            players.sendMessage(plugin.getColor().DARK_PURPLE + "[System]CPU use:" + String.valueOf(PerfomanceCheck.getCpuUsage()) + "%");
        } else if (message[0].equalsIgnoreCase("/memory")) {
            players.sendMessage(PerfomanceCheck.getMemoryInfo());
        } else if (message[0].equalsIgnoreCase("/systeminfo")) {
            players.sendMessage(plugin.getColor().DARK_PURPLE + "[System]CPU use:" + String.valueOf(PerfomanceCheck.getCpuUsage()) + "%");
            players.sendMessage(PerfomanceCheck.getMemoryInfo());
        } else if (message[0].equalsIgnoreCase("/setting") || message[0].equalsIgnoreCase("/activate")) {
            if(message[1].equalsIgnoreCase("")) {
                players.sendMessage("Activate key type please.");
            } else if(message[1].length() < 5) {
                players.sendMessage("key is too short.");
            } else if(Parameter.AdminKey.equals(HexTool.hexSha1(message[1]))) {
                player.setActivate(true);
                players.sendMessage("Activate Complete.");
            }
        } else {
            return re;
        }
        return re;
    }
}
