package me.devsdevelop.hide_and_seek.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.devsdevelop.hide_and_seek.Hide_And_Seek;
import me.devsdevelop.hide_and_seek.utils.Utils;



public class StartCommand implements CommandExecutor{

	private Hide_And_Seek plugin;
	
	public StartCommand(Hide_And_Seek plugin) {
		this.plugin = plugin;
		plugin.getCommand("hide_n_seek").setExecutor(this);
	}
	
	public boolean onCommand(CommandSender sender,  Command cmd, String label,
			String[] args) {

		if (!(sender instanceof Player)) {
			// if not a player
			return true;
		}
		Player player = (Player) sender;
		
		if (args[0].equalsIgnoreCase("start")) {
			Bukkit.broadcastMessage(Utils.chat("&2" + player.getName() + " &ahas issued the command &bStart"));
			plugin.initialize();
		}
		else if (args[0].equalsIgnoreCase("stop")) {
			Bukkit.broadcastMessage(Utils.chat("&2" + player.getName() + " &ahas issued the command &bStop"));
			plugin.Stop();
		}
		else {
			plugin.Stop();
			plugin.initialize();
		}
		return false;

	}
}
