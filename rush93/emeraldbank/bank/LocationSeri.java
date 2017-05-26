package rush93.emeraldbank.bank;

import java.io.Serializable;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationSeri implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double x;
	private double y;
	private double z;
	
	private String wolrd;

	
	public LocationSeri(Location l ) {
		super();
		this.x = l.getX();
		this.y = l.getY();
		this.z = l.getZ();
		this.wolrd = l.getWorld().getName();
	}
	
	public LocationSeri(double x, double y, double z, String wolrd) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.wolrd = wolrd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((wolrd == null) ? 0 : wolrd.hashCode());
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	public Location getLocation(){
		return new Location(Bukkit.getWorld(this.wolrd), this.x, this.y, this.z);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocationSeri other = (LocationSeri) obj;
		if (wolrd == null) {
			if (other.wolrd != null)
				return false;
		} else if (!wolrd.equals(other.wolrd))
			return false;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public String getWolrd() {
		return wolrd;
	}

	public void setWolrd(String wolrd) {
		this.wolrd = wolrd;
	}
	
	
	
}
