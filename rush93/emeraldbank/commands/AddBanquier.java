package rush93.emeraldbank.commands;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush93.emeraldbank.EmeraldBank;
import rush93.emeraldbank.Utils;
import rush93.emeraldbank.bank.Banquier;

public class AddBanquier extends Commande{

	
	
	public AddBanquier(EmeraldBank plugin) {
		super("spawn", plugin);
	}
	
	public boolean canExecute(CommandSender sender) {
		return Utils.isAdmin(sender);
	}
	@Override
	public boolean run(CommandSender sender, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage(Utils.HelpNotAPlayer);
			return false;
		}
		if(!Utils.isAdmin(sender)){
			sender.sendMessage(Utils.MessageErrorNoPermission);
			return false;
		}
		Player p = (Player)sender;
		Banquier b;
		if(args.length >= 2){
			String[] name = Arrays.copyOfRange(args, 1, args.length);
			b = new Banquier(p.getLocation(),Utils.FormatColorMinecraft(String.join(" ", name)));
		} else {
			b = new Banquier(p.getLocation());
		}
		plugin.bank.addBanquier(b);
		return false;
	}

	@Override
	public boolean getHelp(CommandSender p) {
		if(Utils.isAdmin(p)){
			p.sendMessage(ChatColor.GOLD + "/eb spawn [name] "+ChatColor.AQUA + Utils.HelpAddPnj);
			return true;
		}
		return false;
	}

}
