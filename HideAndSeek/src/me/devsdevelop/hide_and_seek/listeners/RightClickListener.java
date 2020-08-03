package me.devsdevelop.hide_and_seek.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.devsdevelop.hide_and_seek.Hide_And_Seek;
import me.devsdevelop.hide_and_seek.utils.Utils;

public class RightClickListener implements Listener{

	private Hide_And_Seek plugin;
	public RightClickListener(Hide_And_Seek plugin) { 
		this.plugin = plugin;
	}
	@EventHandler
    public void onRightClick(PlayerInteractEvent event){
		
        Player player = event.getPlayer();
        
        if (plugin.playerAssign.hasHider()) {
            Player target = plugin.playerAssign.getHider();
            
            if((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) 
            		&& player.getInventory().getItemInMainHand().getType() == Material.COMPASS){
                player.setCompassTarget(target.getLocation());
                player.sendMessage(Utils.chat("&6Pointing to the player:&b " + target.getName()));
            }
        }
        else {
        	event.setCancelled(true);
        }
        	
    
    }
}
