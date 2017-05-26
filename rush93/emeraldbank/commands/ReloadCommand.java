package rush93.emeraldbank.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import rush93.emeraldbank.EmeraldBank;
import rush93.emeraldbank.Utils;

public class ReloadCommand extends Commande{

	public ReloadCommand(EmeraldBank plugin) {
		super("reload", plugin);
	}

	@Override
	public boolean run(CommandSender sender, String[] args) {
		if(Utils.isAdmin(sender)){
			this.plugin.reloadConfig();
			System.out.println("ici");
			Utils.loadConfig(this.plugin);
			sender.sendMessage(Utils.MessageReloadSuccess);
		}else{
			sender.sendMessage(Utils.MessageErrorNoPermission);
		}
		return false;
	}
	public boolean canExecute(CommandSender sender) {
		return Utils.isAdmin(sender);
	}
	
	@Override
	public boolean getHelp(CommandSender sender) {
		if(Utils.isAdmin(sender)){
			sender.sendMessage(ChatColor.GOLD + "/eb reload "+ChatColor.AQUA+ Utils.HelpReload);
		}
		return false;
	}

}
