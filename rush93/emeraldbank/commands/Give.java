package rush93.emeraldbank.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush93.emeraldbank.EmeraldBank;
import rush93.emeraldbank.Utils;
import rush93.emeraldbank.bank.Compte;

public class Give extends Commande{

	public Give(EmeraldBank plugin) {
		super("give", plugin);
	}

	@Override
	public boolean run(CommandSender sender, String[] args) {
		if(!Utils.isAdmin(sender)){
			sender.sendMessage(Utils.MessageErrorNoPermission);
			return false;
		}
		if(args.length < 3){
			sender.sendMessage(Utils.MessageErrorNotArgument);
			this.getHelp(sender);
			return false;
		}
		@SuppressWarnings("deprecation")
		Player p = Bukkit.getPlayer(args[1]);
		if(p==null){
			sender.sendMessage(Utils.MessageErrorNameNotAPlayer);
			return false;
		}
		int amount = 0;
		try {
			amount = Integer.parseInt(args[2]);
		} catch (Exception e) {
			sender.sendMessage(Utils.MessagePositiveInteger);
			return false;
		}
		if(amount <= 0){
			sender.sendMessage(Utils.MessagePositiveInteger);
			return false;
		}
		Compte c = this.plugin.bank.getComptes(p);
		if(c==null){
			sender.sendMessage(Utils.MessageErrorNoAccount);
			return false;
		}
		c.deposit(amount);
		String message = Utils.MessageGiveSuccess.replaceAll("%s",amount+"").replaceAll("%p",p.getName());
		sender.sendMessage(message);
		return true;
	}
	public boolean canExecute(CommandSender sender) {
		return Utils.isAdmin(sender);
	}
	@Override
	public boolean getHelp(CommandSender sender) {
		if(Utils.isAdmin(sender)){
			sender.sendMessage(ChatColor.GOLD+"/eb give <name> <amount> "+ChatColor.AQUA+Utils.HelpGive);
			return true;
		}
		return false;
	}

}
