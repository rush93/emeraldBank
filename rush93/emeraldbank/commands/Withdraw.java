package rush93.emeraldbank.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush93.emeraldbank.EmeraldBank;
import rush93.emeraldbank.Utils;

public class Withdraw extends Commande{

	public Withdraw(EmeraldBank plugin) {
		super("withdraw", plugin);
	}

	@Override
	public boolean run(CommandSender sender, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage(Utils.HelpNotAPlayer);
			return false;
		}
		plugin.bank.openWithdraw((Player)sender);
		return true;
	}

	@Override
	public boolean getHelp(CommandSender sender) {
		sender.sendMessage(ChatColor.GOLD+"/eb withdraw "+ChatColor.AQUA+Utils.HelpWithdraw);
		return false;
	}

}
