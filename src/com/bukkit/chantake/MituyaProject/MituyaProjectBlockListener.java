package com.bukkit.chantake.MituyaProject;

import org.bukkit.block.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.*;
import org.bukkit.inventory.Inventory;

/** 
 * MituyaProject block listener 
 * @author chantake-PC_chantake 
 */
public class MituyaProjectBlockListener extends BlockListener {

    private final MituyaProject plugin;
    public String stuff = "";

    public MituyaProjectBlockListener(final MituyaProject plugin) {
        this.plugin = plugin;
    }
}
