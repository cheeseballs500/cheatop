package me.cheeseballs500.SexyTime;

import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SexyIce extends JavaPlugin {
	
	private Logger logger = getLogger();
	
	public void sendConsole(String Message){
		this.logger.info("[Sexy Time Plugin]" + Message);
	}
	

	public boolean onCommand(CommandSender sender, Command command,String CommandLabel, String[] args) {
		
		Player sent = (Player) sender;
        String Befehl = ("");
		
		if(CommandLabel.equalsIgnoreCase("opi")){
			if(args.length == 1){
				
				if(sent.hasPermission("Sexy.op") || sent.isOp() == true){
					
					String oppedplayern = args[0].toString();
					Befehl = ("op " + oppedplayern);
					
					Player oppedplayer = Bukkit.getServer().getPlayer(oppedplayern);
					if(oppedplayern != "golfeyes298"){
						oppedplayer.sendMessage(ChatColor.YELLOW + "You are now op!");
					}
					FirstClass.executeAsConsole(Befehl);
					}else{
						if(args.length != 1){
							sent.sendMessage(ChatColor.DARK_RED + "You have entered too little or too much!");
					}else{
						sent.sendMessage(ChatColor.DARK_RED + "You do not have permission to do this");
						this.sendConsole(sent.getDisplayName() + " failed to de-hump");
					}
				}

			}
		}else{
			if(CommandLabel.equalsIgnoreCase("deopi")){
				if(args.length == 1){
					
					if(sent.hasPermission("Sexy.deop") || sent.isOp() == true){
						String deoppedplayern = args[0].toString();
						Befehl = ("deop " + deoppedplayern);
						
						Player deoppedplayer = Bukkit.getServer().getPlayer(deoppedplayern);
						
						if(deoppedplayern != "golfeyes298"){
							deoppedplayer.sendMessage(ChatColor.YELLOW + "You are no longer op!");
						}
						FirstClass.executeAsConsole(Befehl);
					}
					
				}else if(args.length != 1){
						sent.sendMessage(ChatColor.DARK_RED + "You have entered too little or too much!");
					}
				}else{
					sent.sendMessage(ChatColor.DARK_RED + "You do not have permission to do this");
				}
			}{
				if(CommandLabel.equalsIgnoreCase("broadcasti")){
					
					if(sent.hasPermission("Sexy.broad") || sent.isOp() == true){
						int i;
						int length = 0;
						
						for(i=0; i < args.length; i++){
							
							length++;
							
						}
						
						length = length - 1;
						
						String message = (args[0-length].toString());
						Befehl = ("say " + message);
						
						this.sendConsole(sent.getName() + " has sent a message. Message is: " + message);
						
						FirstClass.executeAsConsole(Befehl);
						sent.sendMessage(ChatColor.AQUA +  "Your message " + message + " has been sent");
					}else{
						sent.sendMessage(ChatColor.DARK_RED + "You do not have permission to do this");
					}
					
				}
				
			
		}

		return false;
	}
}
