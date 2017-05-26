package rush93.emeraldbank.bank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import rush93.emeraldbank.Utils;
import rush93.emeraldbank.items.InventoryHelper;

public class Bank implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ArrayList<Compte> comptes;
	public ArrayList<Banquier> banquiers;
	
	public Bank(ArrayList<Compte> comptes,ArrayList<Banquier> banquiers){
		this.comptes = comptes;
		this.banquiers = banquiers;
	}
	
	public void addCompte(Compte c){
		this.comptes.add(c);
	}
	
	public Compte getComptes(Player p){
		return this.comptes.get(this.comptes.indexOf(new Compte(p)));
	}
	
	public boolean aUnCompte(Player p){
		return this.comptes.contains( new Compte(p));
	}
	
	public void addBanquier(Banquier b){
		this.banquiers.add(b);
	}
	
	public void removeBanquier(Entity e){
		if(this.esBanquier(e.getUniqueId())){
			this.banquiers.remove(new Banquier(e.getUniqueId()));
			((LivingEntity)e).remove();
		}
	}
	
	public boolean esBanquier(UUID id){
		return banquiers.contains(new Banquier(id));
	}
	
	public int getBanquierId(Banquier b){
		return this.banquiers.indexOf(b);
	}

	public void openBank(Player p, int banqid) {
		Compte c = this.getComptes(p);
		p.openInventory(InventoryHelper.getBankInventory(Utils.isAdmin(p), p.getName(),banqid,c.getSolde()));
	}
	public void openDeposit(Player p) {
		p.openInventory(InventoryHelper.getDepositInventory(Utils.isAdmin(p), p.getName()));
	}
	public void openWithdraw(Player p) {
		p.openInventory(InventoryHelper.getWithdrawInventory(Utils.isAdmin(p), p.getName(),this.getComptes(p).getSolde()));
	}
	@Override
	public String toString(){
		return "compte:" + this.comptes.toString() + "Banquiers:" + this.banquiers;
	}

	public ArrayList<Compte> getTop() {
		Collections.sort(this.comptes);
		return comptes;
	}
 	
}
