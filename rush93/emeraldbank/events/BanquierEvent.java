package rush93.emeraldbank.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import rush93.emeraldbank.EmeraldBank;
import rush93.emeraldbank.bank.Banquier;
import rush93.emeraldbank.bank.Compte;

public class BanquierEvent implements Listener{
	private EmeraldBank plugin;
	

	public BanquierEvent(EmeraldBank plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}
	@EventHandler
	public void onNewPlayerCreateCompte(PlayerJoinEvent e){
		if(!plugin.bank.aUnCompte(e.getPlayer())){
			plugin.bank.addCompte(new Compte(e.getPlayer()));
		}
	}
	
	@EventHandler
	public void onBanquierHurt(EntityDamageEvent e){
		if( plugin.bank.esBanquier( e.getEntity().getUniqueId() ) ){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerClickOnBanquier(PlayerInteractEntityEvent e){
		
		Entity ent = e.getRightClicked();
		if(ent instanceof LivingEntity){
			LivingEntity v = (LivingEntity)ent;
			if(plugin.bank.esBanquier(v.getUniqueId())){
				
				Player p = e.getPlayer();
				if( !plugin.bank.aUnCompte( p ) ){
					plugin.bank.addCompte(new Compte(p));
				}
				
				e.setCancelled(true);
				int banquier = plugin.bank.getBanquierId(new Banquier(v.getUniqueId()));
				plugin.bank.openBank(p, banquier);
				
			}
		}
	}
	
}
