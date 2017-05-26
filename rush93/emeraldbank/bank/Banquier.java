package rush93.emeraldbank.bank;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Banquier implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UUID id;
	public LocationSeri l;
	public Banquier(Location l,String name){

		LivingEntity entity = (LivingEntity) l.getWorld().spawnEntity(l, EntityType.VILLAGER);
		entity.setCustomName(name);
		entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 99999, 99999));
		this.id=entity.getUniqueId();
		this.l = new LocationSeri(l);
	}
	
	public Banquier(Location l){
		this(l,ChatColor.GREEN + "Banquier");
	}
	
	public Banquier(UUID id){
		this.id = id;
	}
	
	public UUID getId(){
		return id;
	}
	
	@Override
	public boolean equals(Object o){
		return o instanceof Banquier && this.id.equals(((Banquier)o).id);
	}
	
	public void teleport(){
		if(this.l == null ){
			return;
		}
		LivingEntity b = (LivingEntity) getVillager();
		if(b == null ){
			return;
		}
		b.teleport(this.l.getLocation());
		b.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 99999, 99999));
	}
	
	@Override
	public String toString(){
		return "id:"+this.id;
	}
	
	public Villager getVillager(){
		if(this.l == null ){
		 return null;
		}
		List<Villager> v = (List<Villager>) Bukkit.getWorld(this.l.getWolrd()).getEntitiesByClass(Villager.class);
		for (int i = 0; i < v.size(); i++) {
			if(v.get(i).getUniqueId().equals(this.id)){
				return v.get(i);
			}
		}
		return null;
		
	}
	
}
