package me.devsdevelop.hide_and_seek.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.devsdevelop.hide_and_seek.Hide_And_Seek;
import me.devsdevelop.hide_and_seek.utils.Utils;

public class OnPlayerDeath implements Listener{

	private Hide_And_Seek plugin;
	
	public OnPlayerDeath(Hide_And_Seek plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void PlayerDeathEvent(PlayerDeathEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if (plugin.isInitialized() && player.getName().equalsIgnoreCase(plugin.playerAssign.getHider().getName())) { // if player is hider
				plugin.Stop();
				Bukkit.broadcastMessage(Utils.chat("&aThe &fSeekers &ahave won the game!"));
			}
		}
	}
}
