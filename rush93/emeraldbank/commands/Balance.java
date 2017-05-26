package rush93.emeraldbank.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush93.emeraldbank.EmeraldBank;
import rush93.emeraldbank.Utils;
import rush93.emeraldbank.bank.Compte;

public class Balance extends Commande{

	public Balance(EmeraldBank plugin) {
		super("balance", plugin);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean run(CommandSender sender, String[] args) {
		Player p;
		if(args.length >= 2 ){
			p = Bukkit.getPlayer(args[1]);
			if(p==null){
				sender.sendMessage(Utils.MessageErrorNameNotAPlayer);
				return false;
			}
		} else {
			if(!(sender instanceof Player)){
				sender.sendMessage(Utils.HelpNotAPlayer);
				return false;
			}
			p = (Player)sender;
		}
		Compte c = this.plugin.bank.getComptes(p);
		if(c == null ){
			sender.sendMessage(Utils.MessageErrorNoAccount);
		}
		String message = Utils.MessagePlayerBalance.replaceAll("%p",p.getName()).replaceAll("%s",c.getSolde()+"");
		sender.sendMessage(message);
		return true;
	}

	@Override
	public boolean getHelp(CommandSender sender) {
		sender.sendMessage(ChatColor.GOLD+"/eb balance [name] "+ChatColor.AQUA+Utils.HelpBalance);
		return true;
	}

}
