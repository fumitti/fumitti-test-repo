/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bukkit.chantake.MituyaProject.Player;

import com.bukkit.chantake.MituyaProject.Player.PlayerInstance;
import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 *
 * @author chantake-mac
 */
public class PlayerInstance {
    public static PlayerInstance instance = null;
    private boolean debug = false;
    public Player player = null;
    private boolean gobaku = true;
    private ChatColor color;
    private int rank;
    private boolean passInput = false;
    private int setacctask;
    private HashMap task = new HashMap();
    private boolean adminacti;


    public PlayerInstance(Player players) {
             player = players;
    }

     public boolean Debug(){
          return debug;
     }

     public void DebugV(){
         this.debug = !debug;
     }

    public void gainMine(int i) {
       //Mine操作
    }

    public void gainTax(int zeikin) {
       //税金振込み
    }

    public void setGobaku() {
        //誤爆反転
        gobaku = !gobaku;
    }

    public boolean getGobaku() {
        return gobaku;
    }

    public boolean getPass() {
        return true;
    }
    
    public String Server(){
        return color.DARK_PURPLE+"[Server]";
    }

    /* rank
         * 0 = default
         * 1 = VIP
         * 2 = GM
         * 3 = *
         * 4 = SuperGM
         * 5 = Admin
         */
    public int getRank() {
        return rank;
    }


    public void setPassInput(boolean b) {
        passInput = b;
    }

    public boolean getPassInput() {
        return passInput;
    }

    public void setAccTask(int i) {
        setacctask = i;
    }

    public int getAccTask(){
        return setacctask;
    }

    public HashMap getTask(){
        return task;
    }

    public void setAccAtt(String string) {

    }

    public boolean getActivate() {
        return adminacti;
    }
    

    public void setActivate(boolean b){
        adminacti = b;
    }
}
