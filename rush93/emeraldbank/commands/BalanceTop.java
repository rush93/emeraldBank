package rush93.emeraldbank.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush93.emeraldbank.EmeraldBank;
import rush93.emeraldbank.Utils;
import rush93.emeraldbank.bank.Compte;

public class BalanceTop extends Commande {

	public BalanceTop(EmeraldBank plugin) {
		super("top", plugin);
	}

	@Override
	public boolean run(CommandSender sender, String[] args) {
		int page = 1;
		if(args.length >= 2){
			try {
				page = Integer.parseInt(args[1]);
			} catch (Exception e) {
				sender.sendMessage(Utils.MessagePositiveInteger);
				return false;
			}
			if(page < 0 ){
				sender.sendMessage(Utils.MessagePositiveInteger);
			}
		}
		ArrayList<Compte> comptes = this.plugin.bank.getTop();
		int totalPage = (int)(Math.ceil((comptes.size()+0.0000)/(Utils.TopTotalPerPage+0.0000)));
		if(page > totalPage){
			String message = Utils.MessageLessThan.replaceAll("%i",totalPage+"");
			sender.sendMessage(message);
		}
		String message = Utils.TopFirstLine.replaceAll("%c",page+"").replaceAll("%t",totalPage+"");
		sender.sendMessage(message);
		for(int i=Utils.TopTotalPerPage*(page-1); i < comptes.size() && i < Utils.TopTotalPerPage*(page); i++){
			Player p = Bukkit.getPlayer(comptes.get(i).getUniquePlayerId());
			String playerName;
			if(p==null){
				playerName=comptes.get(i).getPlayerName();
			}else{
				playerName = p.getName();
				if(p.getName() !=comptes.get(i).getPlayerName() )
					comptes.get(i).setPlayerName(p.getName());
			}
			message = Utils.MessagePlayerTop.replaceAll("%i",(i+1)+"").replaceAll("%p",playerName).replaceAll("%s",comptes.get(i).getSolde()+"");
			sender.sendMessage(message);
		}
		return false;
	}

	@Override
	public boolean getHelp(CommandSender sender) {
		sender.sendMessage(ChatColor.GOLD + "/eb top [page] "+ChatColor.AQUA + Utils.HelpBalanceTop);
		return true;
	}

}
