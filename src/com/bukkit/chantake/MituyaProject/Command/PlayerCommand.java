/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bukkit.chantake.MituyaProject.Command;

import com.bukkit.chantake.MituyaProject.MituyaProject;
import com.bukkit.chantake.MituyaProject.Player.PlayerInstance;
import com.bukkit.chantake.MituyaProject.tool.Tools;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

/**
 *
 * @author chantake
 */
public class PlayerCommand {

    private static ChatColor color;

    public static boolean Command(MituyaProject plugin, String[] message, Player players, PlayerInstance player) {
        boolean re = true;
        if (message[0].equalsIgnoreCase("/clearinventory") || message[0].equalsIgnoreCase("/cli")) {
            players.getInventory().clear();
        } else if (message[0].equalsIgnoreCase("/gobaku")) {
            player.setGobaku();
            players.sendMessage(color.YELLOW + "GobakuBousi : " + plugin.getColor().DARK_RED + player.getGobaku());
        } else if (message[0].equalsIgnoreCase("/heal") || message[0].equalsIgnoreCase("/h")) {
            int tax = 100;
            int mine = 70;
            int heal = 20 - players.getHealth();
            int tmine = (heal * mine) + tax;
            players.setHealth(20);
            player.gainMine(tmine);
            player.gainTax(tax);
            players.sendMessage(plugin.getColor().AQUA + "[Heal]" + plugin.getColor().LIGHT_PURPLE + " Heal : " + plugin.getColor().YELLOW + heal + plugin.getColor().DARK_AQUA + "  Lost : " + plugin.getColor().YELLOW + tmine + "Mine " + plugin.getColor().DARK_AQUA + " Tax : " + plugin.getColor().YELLOW + tax + "Mine");
        } else if (message[0].equalsIgnoreCase("/shop")) {
            //shop 35:15 3
            if (message[1].equalsIgnoreCase("debug")) {
                player.DebugV();
                players.sendMessage(player.Server() + "Shop Debug : " + player.Debug());
            } else {
                String[] itemid = message[2].split(":");
                if (false) {
                    players.sendMessage(plugin.getColor().RED + "ItemID:" + itemid[0] + " Item meny:" + itemid[1] + " set" + message[3]);
                }
            }
        } else if (message[0].equalsIgnoreCase("buy")) {
        } else if (message[0].equalsIgnoreCase("/tax")) {
            if (message[1].equalsIgnoreCase("total")) {
            } else if (message[1].equalsIgnoreCase("top")) {
            } else if (message[1].equalsIgnoreCase("rank")) {
            } else if (message[1].equalsIgnoreCase("?") || message[1].equalsIgnoreCase("help")) {
            } else {
            }
        } else if (message[0].equalsIgnoreCase("/account") || message[0].equalsIgnoreCase("/player")) {
            if (message[1].equalsIgnoreCase("info")) {
                players.sendMessage(color.LIGHT_PURPLE+"*************************Player Info*************************");
                players.sendMessage("Name : "+players.getName());
                players.sendMessage("Display Name : "+players.getDisplayName());
                players.sendMessage("Health : "+players.getHealth()+"/20");
                players.sendMessage("Permission :  Rank "+player.getRank());
            } else if (message[1].equalsIgnoreCase("password")) {
                if (player.getPassInput()) {
                    if (plugin.getTools().CheckPass(message[2])) {
                        players.sendMessage(plugin.getColor().RED + "kigouha tukaemasen><");
                    } else if (message[2].length() <= 6) {
                        players.sendMessage("password ha 6mozi izyou ni sitekudasai");
                    } else if (plugin.tool.CheckPass(message[2]) && player.getAccTask() != 1) {
                        players.sendMessage(plugin.getColor().RED + "kigou ha tukaemasen");
                    } else {
                        if (player.getAccTask() == 1) {
                            players.sendMessage(plugin.getColor().YELLOW + "Pass :" + plugin.getTools().PassWord(player.getTask().get("pass").toString()));
                            players.sendMessage(plugin.getColor().YELLOW + "RePass : " + plugin.getTools().PassWord(message[2]));
                            if (player.getTask().get("pass").toString().equalsIgnoreCase(message[2])) {
                                players.sendMessage("Attestation success.(ninsyou seikou)");
                                // player.setAccAtt(message[2]);
                            }
                            player.setAccTask(0);
                        } else {
                            players.sendMessage(plugin.getColor().YELLOW + "Pass : " + plugin.tool.PassWord(message[2]));
                            player.getTask().put("pass", message[2]);
                            players.sendMessage(plugin.getColor().BLUE + "mou itido nyuuryoku sitekudasai");
                            player.setAccTask(1);
                        }

                    }
                    plugin.getTools().PassWord(null);
                } else if (player.getPass()) {
                    players.sendMessage(plugin.getColor().AQUA + "This Account has attested it.(ninsyou zumi)");
                } else {
                    players.sendMessage(plugin.getColor().RED + "This account has not attested it.(ninsyou siteimasen)");
                    players.sendMessage(plugin.getColor().RED + "Please attest it. ''" + plugin.getColor().WHITE + "/account password (" + plugin.getColor().DARK_RED + "PassWord" + plugin.getColor().WHITE + ")''");
                    player.setPassInput(true);
                }
            }
        } else if (message[0].equalsIgnoreCase("/item")) {
            if (message[1].equalsIgnoreCase("gain")) {
            }
        } else if (message[0].equalsIgnoreCase("/time") || message[0].equalsIgnoreCase("/day")) {
            if (message[1].equalsIgnoreCase("get")) {
                players.sendMessage("Test : " + players.getWorld().getTime());
                players.sendMessage(plugin.getServer().getName());
                players.sendMessage(plugin.getServer().getWorlds().toString());
            }
        } else if (message[0].equalsIgnoreCase("/info") || message[0].equalsIgnoreCase("/serverinfo")) {
            if (message[0].equalsIgnoreCase("/serverinfo") || message[1].equalsIgnoreCase("server")) {
                players.sendMessage(plugin.getColor().BLUE + "*************************Sever Info*************************");
                players.sendMessage("Server Name : 328mssMinecraftServer");
                players.sendMessage("Contory : Japan");
                players.sendMessage("Server HP : http://328mss.com/");
                players.sendMessage("Minecraft version : " + plugin.getServer().getVersion());
                players.sendMessage("Server Admins : chantake fumitti");
                players.sendMessage("Server SuperGMs : yukaseri");
                players.sendMessage("Server GMs : P1ut0 reafa signa");
                players.sendMessage("E-Mail : info@328mss.com");
            } else if (message[1].equalsIgnoreCase("player")) {
                players.sendMessage(plugin.getColor().AQUA + "*************************Players Info************************");
                players.sendMessage("Online : " + plugin.getServer().getOnlinePlayers().length);
                players.sendMessage("MaxPlayers : " + plugin.getServer().getMaxPlayers());
            } else if (message[1].equalsIgnoreCase("players")) {
                String t = "Online Players(" + plugin.getColor().RED + plugin.getServer().getOnlinePlayers().length + plugin.getColor().WHITE + ") ";
                for (Player needle : plugin.getServer().getOnlinePlayers()) {
                    t += needle.getName() + ", ";
                }
                players.sendMessage(t);
            } else if (message[1].equalsIgnoreCase("all")) {
                String[] t = {"/info", "server"};
                PlayerCommand.Command(plugin, t, players, player);
                t[1] = "player";
                PlayerCommand.Command(plugin, t, players, player);
            }
        } else {
            return false;
        }
        return true;
    }
}
