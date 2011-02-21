/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bukkit.chantake.MituyaProject.Parameter;

import com.bukkit.chantake.MituyaProject.MituyaProject;
import com.bukkit.chantake.MituyaProject.tool.Tools;
import java.util.HashMap;

/**
 *
 * @author chantake-mac
 */
public class Parameter {
    public static Tools tool;

     public static String AdminKey;
     public static HashMap Map = new HashMap();
     public Parameter instance;

     public Parameter getInstance(){
         return instance;
     }
}
