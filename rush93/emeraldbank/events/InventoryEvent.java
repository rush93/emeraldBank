package rush93.emeraldbank.events;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import rush93.emeraldbank.EmeraldBank;
import rush93.emeraldbank.Utils;
import rush93.emeraldbank.bank.Compte;
import rush93.emeraldbank.items.Allitems;
import rush93.emeraldbank.items.InventoryHelper;

public class InventoryEvent implements Listener{
	private EmeraldBank plugin;
	

	public InventoryEvent(EmeraldBank plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onClickMenu(InventoryClickEvent event) {
		String inventoryName = Utils.InventoryBank;
		inventoryName = inventoryName.replaceAll("%p", event.getWhoClicked().getName());
		Compte c = this.plugin.bank.getComptes((Player)event.getWhoClicked());
		inventoryName = inventoryName.replaceAll("%s", c.getSolde()+"");
		inventoryName = InventoryHelper.getInventoryName(inventoryName);
		if(event.getInventory().getName().equals(inventoryName)){
			if(event.isShiftClick()){
				event.setCancelled(true);
			}
			else if(event.getRawSlot() <  event.getView().getTopInventory().getSize()){
				event.setCancelled(true);
				
				if(Allitems.Quit.isSameAs(event.getCurrentItem()))
						event.getWhoClicked().closeInventory();
				else if(Allitems.Deposit.isSameAs(event.getCurrentItem())){
					plugin.bank.openDeposit((Player)event.getWhoClicked());
				}else if(Allitems.Withdraw.isSameAs(event.getCurrentItem())){
					plugin.bank.openWithdraw((Player)event.getWhoClicked());
				}else if(Allitems.AdminRename.isSameAs(event.getCurrentItem())){
					String[] ar = event.getCurrentItem().getItemMeta().getDisplayName().split("-");
					int BanquierId = Integer.parseInt(ar[ar.length-1]);
					this.plugin.pc.players.put(event.getWhoClicked().getUniqueId(), BanquierId);
					((Player)event.getWhoClicked()).sendMessage(Utils.MessageEnterPnjName);
					event.getWhoClicked().closeInventory();
				}else if(Allitems.AdminRemove.isSameAs(event.getCurrentItem())){

					String[] ar = event.getCurrentItem().getItemMeta().getDisplayName().split("-");
					int BanquierId = Integer.parseInt(ar[ar.length-1]);

					LivingEntity ent = this.plugin.bank.banquiers.get(BanquierId).getVillager();
					this.plugin.bank.removeBanquier(ent);
					((Player)event.getWhoClicked()).sendMessage(Utils.MessagePnjRemoved);
					event.getWhoClicked().closeInventory();
				}
				
			}
			return;
		}
		
		inventoryName = Utils.InventoryWithdraw;
		inventoryName = inventoryName.replaceAll("%p", event.getWhoClicked().getName());

		inventoryName = InventoryHelper.getInventoryName(inventoryName);
		String inventoryName2 = Utils.InventoryDeposit;
		inventoryName2 = inventoryName2.replaceAll("%p", event.getWhoClicked().getName());

		inventoryName2 = InventoryHelper.getInventoryName(inventoryName2);
		if(event.getInventory().getName().equals(inventoryName) || event.getInventory().getName().equals(inventoryName2)){
			if (event.getCurrentItem() !=null) {
				if(event.isShiftClick()){
					if(!(event.getCurrentItem() !=null && event.getCurrentItem().getType().equals(Material.EMERALD) && ((Utils.CustomName && event.getCurrentItem().getItemMeta() != null && event.getCurrentItem().getItemMeta().getDisplayName()!=null && event.getCurrentItem().getItemMeta().getDisplayName().equals(Utils.EmeraldName)) || !Utils.CustomName))){
						event.setCancelled(true);
					}
				}

				if(event.getRawSlot() <  event.getView().getTopInventory().getSize()){
					if(!(event.getCursor() != null && event.getCursor().getType().equals(Material.EMERALD) && ((Utils.CustomName && event.getCursor().getItemMeta() != null && event.getCursor().getItemMeta().getDisplayName()!=null && event.getCursor().getItemMeta().getDisplayName().equals(Utils.EmeraldName)) || !Utils.CustomName))){
						if(!event.getCursor().getType().equals(Material.AIR)){
							event.setCancelled(true);
						}
					}
				}
			}
			return ;
		}
		
		
	}
	@EventHandler
	public void inventoryClose(InventoryCloseEvent e){
		String inventoryName = Utils.InventoryDeposit;
		inventoryName = inventoryName.replaceAll("%p", e.getPlayer().getName());
		inventoryName = InventoryHelper.getInventoryName(inventoryName);
		if(e.getInventory().getName().equals(inventoryName)){
			long total = 0;
			ItemStack[] ims = e.getInventory().getContents();
			boolean error = false;
			for(int i=0; i < ims.length; i++){
				if(ims[i]!=null && ims[i].getType().equals(Material.EMERALD)){
					total+=ims[i].getAmount();
				} else if(ims[i]!=null && !ims[i].getType().equals(Material.AIR)){
					error=true;
					e.getPlayer().getInventory().addItem(ims[i]);
				}
			}
			if(error){
				((Player)e.getPlayer()).sendMessage(Utils.MessageNotForgetItem);
			}
			this.plugin.bank.getComptes(((Player)e.getPlayer())).deposit(total);
			String message = Utils.MessageDepositSuccess.replaceAll("%d",total+"");
			((Player)e.getPlayer()).sendMessage(message);
		}
		
		inventoryName = Utils.InventoryWithdraw;
		inventoryName = inventoryName.replaceAll("%p", e.getPlayer().getName());
		inventoryName = InventoryHelper.getInventoryName(inventoryName);
		if(e.getInventory().getName().equals(inventoryName)){
			long total = 0,curentMoney;
			Compte c = this.plugin.bank.getComptes((Player)e.getPlayer());
			if(c.getSolde() >= 54*64){
				curentMoney = 54*64;
			}else{
				curentMoney = c.getSolde();
			}
			ItemStack[] ims = e.getInventory().getContents();
			boolean error = false;
			for(int i=0; i < ims.length; i++){
				if(ims[i]!=null && ims[i].getType().equals(Material.EMERALD)){
					total+=ims[i].getAmount();
				} else if(ims[i]!=null && !ims[i].getType().equals(Material.AIR)){
					error=true;
					e.getPlayer().getInventory().addItem(ims[i]);
				}
			}
			if(error){
				((Player)e.getPlayer()).sendMessage(Utils.MessageNotForgetItem);
			}
			if(curentMoney<total){
				this.plugin.bank.getComptes(((Player)e.getPlayer())).deposit(total-curentMoney);
				String message = Utils.MessageDepositSuccess.replaceAll("%d",(total-curentMoney)+"");
				((Player)e.getPlayer()).sendMessage(message);
			} else {
				this.plugin.bank.getComptes(((Player)e.getPlayer())).withdraw(curentMoney-total);
				String message = Utils.MessageWithdrawSuccess.replaceAll("%d",(curentMoney-total)+"");
				((Player)e.getPlayer()).sendMessage(message);
			}
		}
	}

	
}
