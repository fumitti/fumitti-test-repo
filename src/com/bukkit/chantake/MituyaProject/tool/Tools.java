/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bukkit.chantake.MituyaProject.tool;

import java.util.regex.Pattern;

/**
 *
 * @author chantake-mac
 */
public class Tools {
    public static HexTool hextool;

      public String PassWord(String s){
        int l = s.length();
        String t="";
        for(int i=0;i<l;i++){
            t += "*";
        }
        return t;
    }

      public  boolean CheckChar(String s,char c) {
          boolean b = false;
          for(int i = 0;i<s.length();i++){
              if(s.charAt(i) == c){
                  b = true;
                  break;
              }
          }
          return b;
      }

      public boolean CheckPass(String s){
          String hankaku = "\\p{Punct}"; //半角記号
          String zenkaku = "！”＃＄％＆’（）＝～｜‘｛＋＊｝＜＞？＿－＾￥＠「；：」、。・"; //全角記号
          return Pattern.compile("["+hankaku+zenkaku+"]+").matcher(s).matches();
      }

      public String ChatColor(String[] t){
          String text = "";
          for(int i=0;i<t.length;i++) {
                text += t[i];
            }

          return text;
      }

     

      public static HexTool getHex(){
          return hextool;
      }
}
