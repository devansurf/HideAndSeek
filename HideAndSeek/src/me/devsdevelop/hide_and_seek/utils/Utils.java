package me.devsdevelop.hide_and_seek.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import net.md_5.bungee.api.ChatColor;

public class Utils {

	public static String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	public static ItemStack createItem
	(Inventory inv, String materialName, int amount, int invSlot, String displayName, String... loreString ) {
		/*String... is an array that carries \n between each string */
		
		ItemStack item;
		List<String> lore = new ArrayList<String>();
		
		item = new ItemStack(Material.getMaterial(materialName), amount);
		
		ItemMeta meta  = item.getItemMeta();
		meta.setDisplayName(Utils.chat(displayName));
		for (String s : loreString) {
			lore.add(Utils.chat(s));	
		}
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(invSlot, item);
		
		return item;
	}
	public static ItemStack createCustomArmor(Material leatherPiece, String colorName) {
		ItemStack item = new ItemStack(leatherPiece);
		LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
		if (colorName.equalsIgnoreCase("black"))
			meta.setColor(Color.BLACK);
		else if (colorName.equalsIgnoreCase("white"))
			meta.setColor(Color.WHITE);
		item.setItemMeta(meta);
		return item;
		
	}

}
