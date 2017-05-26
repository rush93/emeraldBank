package rush93.emeraldbank.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

import rush93.emeraldbank.Utils;

public class InventoryHelper {
	
	public static Inventory getBankInventory(boolean isAdmin,String playerName,int banquierId, long balance){
		String invName  = Utils.InventoryBank;
		invName = invName.replaceAll("%p", playerName);
		invName = invName.replaceAll("%s", balance+"");
		invName  = getInventoryName(invName);
		Inventory inv = Bukkit.createInventory(null, 9,invName);
		
		inv.addItem(Allitems.Deposit.getItemStack());
		inv.addItem(Allitems.Withdraw.getItemStack());
		if(isAdmin){
			inv.setItem(6,Allitems.AdminRename.setName(Utils.ItemRename+ChatColor.BLACK+"-"+banquierId).getItemStack());
			inv.setItem(7,Allitems.AdminRemove.setName(Utils.ItemRemove+ChatColor.BLACK+"-"+banquierId).getItemStack());
		}
		inv.setItem(8,Allitems.Quit.getItemStack());
		
		return inv;
	}
	public static Inventory getDepositInventory(boolean isAdmin,String playerName){
		String invName  = Utils.InventoryDeposit;
		invName = invName.replaceAll("%p", playerName);
		invName  = getInventoryName(invName);
		Inventory inv = Bukkit.createInventory(null, 54,invName);
		
		return inv;
	}
	public static Inventory getWithdrawInventory(boolean isAdmin,String playerName,long nbEmerald){
		String invName  = Utils.InventoryWithdraw;
		invName = invName.replaceAll("%p", playerName);
		invName  = getInventoryName(invName);
		Inventory inv = Bukkit.createInventory(null, 54,invName);
		for(int i=0; i < Math.floor(nbEmerald/64) && i<54; i++){
			inv.addItem(Utils.getEmeralds(64));
		}
		if((int)nbEmerald%64>0){
			inv.addItem(Utils.getEmeralds((int)nbEmerald%64));
		}
		return inv;
	}
	public static String getInventoryName(String inventoryName){

		String version = Bukkit.getVersion();
		for(int i = 5; i <=8 ;i++){
			if(version.contains(" 1."+i) && inventoryName.length() >=32){
				inventoryName = inventoryName.substring(0, 32);
			}
		}
		return inventoryName;
	}
}
