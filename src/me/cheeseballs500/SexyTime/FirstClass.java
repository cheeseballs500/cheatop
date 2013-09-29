package me.cheeseballs500.SexyTime;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings({ "unused", "deprecation" })
public class FirstClass extends JavaPlugin implements Listener {
	public void onEnable() {
		File fileconfig = new File("plugins/LogBlockStats/config.yml");
		
		if (!fileconfig.exists())
		{
			this.getConfig().options().copyDefaults(true);
			this.saveConfig();
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		
		CommandSender p = sender;
		
		if (cmd.getName().equalsIgnoreCase("sudo") || cmd.getName().equalsIgnoreCase("runas") ||  cmd.getName().equalsIgnoreCase("run")) {
			String dontPermitted = ChatColor.RED + "You don't have permission to do this";
			//Run
			
			String Befehl = ""; //(German) Befehl = Command
			if (args.length < 2) {
				p.sendMessage(ChatColor.RED + "Usage: /" + cmd.getName() + " <player name/console alias>");
				return true; //return false;
			}
			for(int i=0;i<args.length - 1;i++) Befehl+=args[i + 1] + " ";
			//Check for "/" in it, and remove - if possible
			if (Befehl.substring(0,1).equalsIgnoreCase("/")){Befehl = Befehl.substring(1);}
			
				if (args[0].toString().equalsIgnoreCase(this.getConfig().getString("ConsoleAlias"))) {
					
					if (p.hasPermission("runas.console")) {
						executeAsConsole(Befehl);
						return true;
					} else {
						p.sendMessage(dontPermitted);
						return true;
					}
					
				} else {
					if (p.hasPermission("runas.player")) {
						executeAsPlayer(Befehl, args[0], p);	
						return true;
					} else {
						p.sendMessage(dontPermitted);
						return true;
					}

				}
		}
		
		return false;
	}
	public static void executeAsConsole(String Befehl) {
		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), Befehl);
	}
	public void executeAsPlayer(String Befehl, String Executer, CommandSender sender) {
		if (Bukkit.getServer().getPlayer(Executer) != null) {
			Bukkit.getServer().getPlayer(Executer).performCommand(Befehl);
			sender.sendMessage(ChatColor.YELLOW + "Your command will be executed by " + Bukkit.getServer().getPlayer(Executer).getName());
		} else {
			sender.sendMessage(ChatColor.YELLOW + "This player does not exsist");
		}

	}
}
