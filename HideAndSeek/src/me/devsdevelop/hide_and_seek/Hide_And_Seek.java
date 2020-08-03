package me.devsdevelop.hide_and_seek;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import me.devsdevelop.hide_and_seek.commands.StartCommand;
import me.devsdevelop.hide_and_seek.config.Config;
import me.devsdevelop.hide_and_seek.listeners.OnPlayerDeath;
import me.devsdevelop.hide_and_seek.listeners.RightClickListener;
import me.devsdevelop.hide_and_seek.players.PlayerAssign;
import me.devsdevelop.hide_and_seek.utils.Utils;

public class Hide_And_Seek extends JavaPlugin{

	public PlayerAssign playerAssign;
	public Config config;
	public boolean isInitialized;
	public int mainCount, graceCount; // 300 seconds = 5 minutes
	int taskIdInitial = -1, taskIdMain = -1;
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		isInitialized = false;
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new RightClickListener(this), this);
		pm.registerEvents(new OnPlayerDeath(this), this);
		new StartCommand(this);
	}
	
	public void initialize() {

		isInitialized = true;
		playerAssign = new PlayerAssign(this);
		config = new Config(this);
		mainCount = config.getMainTime();
		graceCount = config.getGraceTime();
		playerAssign.AssignPlayers();
		playerAssign.ClearPlayerInventories();
		
		Bukkit.broadcastMessage(Utils.chat(config.getStartMessage()));
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();       
        taskIdInitial = scheduler.scheduleSyncRepeatingTask(this, new Runnable(){
            @Override
            public void run(){ 
            	graceCount --;
            	
            	 if (graceCount % 60 == 0 || graceCount <= 11) { // for every minute, or final countdown
            		 Bukkit.broadcastMessage(Utils.chat(config.getGraceTimeMessages(graceCount)));
            	 }
            
            	 if(graceCount <= 0) {
                  	 playerAssign.AssignPlayerItems(); //gives the players compasses and other stuff
                  	 playerAssign.endGracePeriod();
                  	 StartMainGame(); 
                  	 StopSchedule(taskIdInitial);  //cancels the task
              		               	
                  	  
                 } 
          	 	
                 
            }
        }, 0, 20L); // ticks ->// 20 ticks = 1 second//   
	}
	
	private void StartMainGame() {
		
		 BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
	        
	        taskIdMain = scheduler.scheduleSyncRepeatingTask(this, new Runnable(){
	            @Override
	            public void run(){   
	            	mainCount --;
	            	if (mainCount % 60 == 0 || mainCount <= 11) { // for every minute, or final countdown
	            		 Bukkit.broadcastMessage(Utils.chat(config.getTimeMessages(mainCount)));
	            	}
	            	if(mainCount == 0) { 
	                  	 StopSchedule(taskIdMain); //cancels the task
	                  	 Stop(); // allows intialized to be changed
	                } 
	            }
	        }, 0, 20L); // ticks ->// 20 ticks = 1 second//   
	}
	public void StopSchedule(int taskid) {
		
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
			scheduler.cancelTask(taskid);
		
	}
	public void Stop() {
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.cancelTask(taskIdInitial);
		scheduler.cancelTask(taskIdMain);
		isInitialized = false;
		
	}
	public int getMainCount() {
		return mainCount;
	}

	public boolean isInitialized() {
		return isInitialized;
	}
}
