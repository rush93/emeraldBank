package rush93.emeraldbank.bank;

import java.io.Serializable;
import java.util.Comparator;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Compte implements Serializable, Comparable<Compte>, Comparator<Compte>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String playerName;
	
	private long solde;
	
	private UUID uniquePlayerId;
	
	public Compte(Player p,long solde){
		this.playerName = p.getName();
		this.uniquePlayerId = p.getUniqueId();
		this.solde = solde;
	}
	
	public Compte(Player p){
		this(p,0);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uniquePlayerId == null) ? 0 : uniquePlayerId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compte other = (Compte) obj;
		if (uniquePlayerId == null) {
			if (other.uniquePlayerId != null)
				return false;
		} else if (!uniquePlayerId.equals(other.uniquePlayerId))
			return false;
		return true;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public boolean deposit(long nb){
		
		if(nb < 0){
			return false;
		}
		
		this.solde+=nb;
		return true;
	}
	
	public boolean withdraw(long nb){
		
		if(nb < 0){
			return false;
		}
		
		if(this.solde - nb < 0){
			return false;
		}
		
		this.solde-=nb;
		return true;
	}
	
	public long getSolde() {
		return solde;
	}

	public void setSolde(long solde){
		this.solde = solde;
	}
	
	public Player getPlayer(){
		return Bukkit.getPlayer(this.uniquePlayerId);
	}
	
	public UUID getUniquePlayerId() {
		return uniquePlayerId;
	}

	public void setUniquePlayerId(UUID uniquePlayerId) {
		this.uniquePlayerId = uniquePlayerId;
	}

	@Override
	public int compare(Compte o1, Compte o2) {
		
		return (int) (o2.solde - o1.solde);
	}

	@Override
	public int compareTo(Compte o) {
		return (int) (o.solde - this.solde);
	}
	
	
}
