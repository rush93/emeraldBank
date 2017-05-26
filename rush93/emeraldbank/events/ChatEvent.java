package rush93.emeraldbank.events;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import rush93.emeraldbank.EmeraldBank;
import rush93.emeraldbank.Utils;
import rush93.emeraldbank.bank.Banquier;

public class ChatEvent implements Listener{
	private EmeraldBank plugin;
	public HashMap<UUID,Integer> players;

	public ChatEvent(EmeraldBank plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
		this.players = new HashMap<UUID,Integer>();
	}
	
	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void chatEvent(AsyncPlayerChatEvent e){
		if(players.containsKey(e.getPlayer().getUniqueId())) {
			Banquier b = this.plugin.bank.banquiers.get(players.get(e.getPlayer().getUniqueId()));
			LivingEntity ent = b.getVillager();
			ent.setCustomName(Utils.FormatColorMinecraft(e.getMessage()));
			e.setCancelled(true);
			e.getPlayer().sendMessage(Utils.MessagePnjRenamed);
			this.players.remove(e.getPlayer().getUniqueId());
		}
	}

}
