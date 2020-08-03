package me.devsdevelop.hide_and_seek.config;

import me.devsdevelop.hide_and_seek.Hide_And_Seek;

public class Config {
	
	private Integer graceTime, mainTime; // loaded from config
	private Hide_And_Seek plugin;
	
	public Config (Hide_And_Seek plugin) {
		this.plugin = plugin;
	}
	
	public Integer getGraceTime() { graceTime = plugin.getConfig().getInt("Grace_Period", 180); return graceTime + 1;}
	public Integer getMainTime() { mainTime = plugin.getConfig().getInt("Main_Period", 360); return mainTime + 1;}
	public String getFinalMessage() {return plugin.getConfig().getString("Final_Second", "&cGame Over");}
	public String getFinalGraceMessage() {return plugin.getConfig().getString("Final_Graceperiod_Second", "Grace period ended").
			replace("<player>", plugin.playerAssign.getHider().getName());}
	public String getStartMessage() {return plugin.getConfig().getString("Start_Message", "&cGame has Started!").
			replace("<graceTime>", ConvertToMinute(getGraceTime()-1).toString());}
	
	
	public String getTimeMessages(Integer time) { // messages for the main period
		Integer minute = -1;
		minute = ConvertToMinute(time);
		if (time % 60 == 0 && time != 0) { // check if minute marks has been reached
			
			// change in the future:
			if (minute.equals(5)) {
				return plugin.getConfig().getString("Minute.5").replace("<player>", plugin.playerAssign.getHider().getName()).
						replace("<time>", minute.toString());
			}
			else if (minute.equals(4)) {
				return plugin.getConfig().getString("Minute.4").replace("<player>", plugin.playerAssign.getHider().getName()).
						replace("<time>", minute.toString());
			}
			else if (minute.equals(3)) {
				return plugin.getConfig().getString("Minute.3").replace("<player>", plugin.playerAssign.getHider().getName()).
						replace("<time>", minute.toString());
			}
			else if (minute.equals(2)) {
				return plugin.getConfig().getString("Minute.2").replace("<player>", plugin.playerAssign.getHider().getName()).
						replace("<time>", minute.toString());
			}
			else if (minute.equals(1)) {
				return plugin.getConfig().getString("Minute.1").replace("<player>", plugin.playerAssign.getHider().getName()).
						replace("<time>", minute.toString());
			}
			else {
				return plugin.getConfig().getString("Minute_Countdown_Format").replace("<time>", minute.toString());
			}
			// must change!!!
		}
		else{
			if (time == 0) {
				return getFinalMessage();
			}
			return plugin.getConfig().getString("Final_Countdown_Format").replace("<player>", plugin.playerAssign.getHider().getName()).
					replace("<time>", minute.toString());
		}
	}
	
	public String getGraceTimeMessages(Integer time) { // messages for the grace period
		if (time % 60 == 0 && time != 0) {
			Integer minute = ConvertToMinute(time);
			return plugin.getConfig().getString("Minute_Countdown_Format").replace("<time>", minute.toString());
		}
		else {
			if (time == 11) {
				return plugin.getConfig().getString("End_GracePeriod");
			}
			if (time == 0) {
				return getFinalGraceMessage();
			}	
		
			return plugin.getConfig().getString("Final_Countdown_Format").replace("<time>", time.toString());
		}
		
	}
	private Integer ConvertToMinute(Integer i) {
		if (i % 60 == 0) {
			return i/60;
		}
		return -1;
	}
	

}
