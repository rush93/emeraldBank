package rush93.emeraldbank.items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class ItemCreator {
	public ItemStack item;
	
	public ItemCreator(Material material,short damage,int nb){
		this.item = new ItemStack(material, nb, damage);
	}
	public ItemCreator(Material material,short damage,int nb,String name){
		this(material,damage,nb,name,new ArrayList<String>());		
	}
	public ItemCreator(Material material,short damage,int nb,String name,ArrayList<String> lore){
		this.item = new ItemStack(material, nb, damage);
		ItemMeta im = this.item.getItemMeta();
		im.setDisplayName(name);
		im.setLore(lore);
		this.item.setItemMeta(im);
	}
	
	public ItemStack getItemStack(){
		return item;
	}
	
	public boolean isSameAs(ItemStack item){
		if(item!=null && item.getAmount() == this.item.getAmount())
			if(item.getItemMeta().getDisplayName().equals(this.item.getItemMeta().getDisplayName()))
				return true;
		return false;
	}
	
	public ItemCreator setMaterial(Material m,short data){
		this.item.setType(m);
		this.item.setDurability(data);
		return this;
	}
	
	public ItemCreator setName(String n){
		ItemMeta im = this.item.getItemMeta();
		im.setDisplayName(n);
		item.setItemMeta(im);
		return this;
	}
}
