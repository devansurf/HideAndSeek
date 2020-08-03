package me.devsdevelop.hide_and_seek.players;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.devsdevelop.hide_and_seek.Hide_And_Seek;
import me.devsdevelop.hide_and_seek.utils.Utils;



public class PlayerAssign {

	private ItemStack[] items = {new ItemStack(Material.DIAMOND_PICKAXE, 1), new ItemStack(Material.COMPASS, 1),
			new ItemStack(Material.COOKED_BEEF, 64), Utils.createCustomArmor(Material.LEATHER_BOOTS, "black"), Utils.createCustomArmor(Material.LEATHER_LEGGINGS, "black")
			, Utils.createCustomArmor(Material.LEATHER_CHESTPLATE, "black"), Utils.createCustomArmor(Material.LEATHER_HELMET, "black")};
	
	private ItemStack[] hiderItems = {Utils.createCustomArmor(Material.LEATHER_BOOTS, "white"),Utils.createCustomArmor(Material.LEATHER_LEGGINGS, "white"),
			Utils.createCustomArmor(Material.LEATHER_CHESTPLATE, "white"), Utils.createCustomArmor(Material.LEATHER_HELMET, "white") };
	
	private Hide_And_Seek plugin;
	public List<Player> playerList;
	public List<Player> seekers;
	private Player hider;
	
	public PlayerAssign(Hide_And_Seek plugin) {
		this.plugin = plugin;
		playerList = new ArrayList<>(Bukkit.getOnlinePlayers());
		seekers =  new ArrayList<>();
	}
	
	public void AssignPlayers() {
		Random r = new Random();
		int rand = r.nextInt(playerList.size()); // gets a random index in playerList	
		addPlayerBuffs(playerList); 			 // gives all players the starting buffs
		
		for (int  i = 0; i < playerList.size();i++) { // assigns everyone
			
			if (i == rand) {
				hider = playerList.get(i);
			}
			else {
				seekers.add(playerList.get(i));
			}
		}
	}
	public void AssignPlayerItems() {
		
		for (Player player : seekers){
			for (int i = 0; i < items.length;i++) {
				player.getInventory().addItem(items[i]); // adds to the inventory the items listed
			}
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, plugin.getMainCount()*20, 1)); // mainCount times the tickrate, 1 represents the amplifier
			player.updateInventory();
		}
		for (int i = 0; i < hiderItems.length;i++) {
			hider.getInventory().addItem(hiderItems[i]); 
		}
		hider.updateInventory();
	}
	
	public void ClearPlayerInventories() {
		for (Player player : playerList) {
			player.getInventory().clear();
		}
	}
	public boolean hasHider() {
		if (hider == null) {
			return false;
		}
		return true;
	}
	public Player getHider() {
		return hider;
	}

	public void endGracePeriod() {
		removePlayerBuffs(playerList);
	}
	public void addPlayerBuffs(List<Player> players) {
		for (Player player : players) {
			player.setHealth(20D);  // set health to max
			player.setFoodLevel(20); // set food to max
			player.setAbsorptionAmount(20); // give all players absorption
			player.setSaturation(40f); // give all players extra saturation
		}
	}
	public void removePlayerBuffs(List<Player> players) { // remove extra buffs given by the gracePeriod
		for (Player player : players) {
			player.setAbsorptionAmount(0); 
			player.setSaturation(0f);
		}
	}
}
