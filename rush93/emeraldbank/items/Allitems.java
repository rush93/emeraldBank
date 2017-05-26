package rush93.emeraldbank.items;

import org.bukkit.Material;

import rush93.emeraldbank.Utils;	

public class Allitems {

	public static ItemCreator Quit = new ItemCreator(Material.BARRIER, (short)0, 1, Utils.ItemQuit);
	public static ItemCreator Withdraw = new ItemCreator(Material.EMERALD, (short)0, 1, Utils.ItemWithdraw);
	public static ItemCreator Deposit = new ItemCreator(Material.EMERALD, (short)0, 1, Utils.ItemDeposit);
	
	public static ItemCreator AdminRename = new ItemCreator(Material.NAME_TAG, (short)0, 1, Utils.ItemRename);
	public static ItemCreator AdminRemove = new ItemCreator(Material.STAINED_GLASS_PANE, (short)14, 1, Utils.ItemRemove);
	
	public static void refresh() {
		Quit = new ItemCreator(Material.BARRIER, (short)0, 1, Utils.ItemQuit);
		Withdraw = new ItemCreator(Material.EMERALD, (short)0, 1, Utils.ItemWithdraw);
		Deposit = new ItemCreator(Material.EMERALD, (short)0, 1, Utils.ItemDeposit);
		
		AdminRename = new ItemCreator(Material.NAME_TAG, (short)0, 1, Utils.ItemRename);
		AdminRemove = new ItemCreator(Material.STAINED_GLASS_PANE, (short)14, 1, Utils.ItemRemove);
	}
	
}
