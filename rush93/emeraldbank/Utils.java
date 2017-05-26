package rush93.emeraldbank;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

import rush93.emeraldbank.items.Allitems;
import rush93.emeraldbank.items.ItemCreator;

public class Utils {
	
	public static String HelpNoPermission;
	public static String HelpNotAPlayer;
	public static String HelpAddPnj;
	public static String HelpTake;
	public static String HelpSet;
	public static String HelpGive;
	public static String HelpBalance;
	public static String HelpReload;
	public static String HelpDeposit;
	public static String HelpWithdraw;
	
	public static String ItemDeposit;
	public static String ItemWithdraw;
	public static String ItemQuit;
	public static String ItemRename;
	public static String ItemRemove;
	public static String InventoryBank;
	public static String InventoryDeposit;
	public static String InventoryWithdraw;
	public static String MessageEnterPnjName;
	public static String MessagePnjRenamed;
	public static String MessagePnjRemoved;
	public static String MessageNotForgetItem;
	public static String MessageDepositSuccess;
	public static String MessageWithdrawSuccess;
	public static String MessageReloadSuccess;

	public static String MessageErrorNameNotAPlayer;
	public static String MessageErrorNoAccount;
	public static String MessageErrorNoPermission;
	public static String MessageErrorNotArgument;
	public static String MessageErrorReload;

	public static String MessagePlayerBalance;
	
	public static String MessageGiveSuccess;
	public static String MessageSetSuccess;
	public static String MessageTakeSuccess;
	
	public static int TopTotalPerPage;
	public static int AutoSaveTime;
	
	public static String MessagePositiveInteger;
	public static String MessageLessThan;
	public static String TopFirstLine;
	public static String MessagePlayerTop;
	public static String HelpBalanceTop;
	
	public static Boolean CustomName;
	public static Boolean SuperFreezeNpc;
	public static String EmeraldName;
	
	public static String Prefix;
	
	public static void save(Object o, File f) {
		try {
			if(!f.exists())
				f.createNewFile();
			
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(o);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Object load(File f){
	
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			Object result = ois.readObject();
			ois.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isAdmin(CommandSender p){
		return p.isOp() || p.hasPermission(EmeraldBank.Admin_Perm);
	}
	public static int isPositiveInt(String s){
		int nb=-1;
		try {
			nb = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return -1;
		}
		if(nb<0){
			return -1;
		}else{
			return nb;
		}
	}

	public static void loadConfig(EmeraldBank plugin) {
		HelpAddPnj = FormatColorMinecraft(plugin.getConfig().getString("help.addpnj"));
		HelpBalanceTop = FormatColorMinecraft(plugin.getConfig().getString("help.balanceTop"));
		HelpNoPermission = FormatColorMinecraft(plugin.getConfig().getString("help.errors.noPermission"));
		HelpTake = FormatColorMinecraft(plugin.getConfig().getString("help.take"));
		HelpSet = FormatColorMinecraft(plugin.getConfig().getString("help.set"));
		HelpGive = FormatColorMinecraft(plugin.getConfig().getString("help.give"));
		HelpBalance = FormatColorMinecraft(plugin.getConfig().getString("help.balance"));
		HelpReload = FormatColorMinecraft(plugin.getConfig().getString("help.reload"));
		HelpDeposit = FormatColorMinecraft(plugin.getConfig().getString("help.deposit"));
		HelpWithdraw = FormatColorMinecraft(plugin.getConfig().getString("help.withdraw"));

		ItemDeposit = FormatColorMinecraft(plugin.getConfig().getString("gui.items.deposit"));
		ItemWithdraw = FormatColorMinecraft(plugin.getConfig().getString("gui.items.withdraw"));
		ItemQuit = FormatColorMinecraft(plugin.getConfig().getString("gui.items.quit"));
		ItemRename = FormatColorMinecraft(plugin.getConfig().getString("gui.items.rename"));
		ItemRemove = FormatColorMinecraft(plugin.getConfig().getString("gui.items.remove"));
		InventoryBank = FormatColorMinecraft(plugin.getConfig().getString("gui.inventory.bank"));
		InventoryDeposit = FormatColorMinecraft(plugin.getConfig().getString("gui.inventory.deposit"));
		InventoryWithdraw = FormatColorMinecraft(plugin.getConfig().getString("gui.inventory.withdraw"));
		Prefix = FormatColorMinecraft(plugin.getConfig().getString("prefix"));
		EmeraldBank.prefix = Prefix;
		
		MessageEnterPnjName = EmeraldBank.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.enterPnjName"));
		MessagePnjRenamed = EmeraldBank.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.pnjRenamed"));
		MessagePnjRemoved = EmeraldBank.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.pnjRemoved"));
		MessageNotForgetItem = EmeraldBank.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.notForgetItem"));
		MessageDepositSuccess = EmeraldBank.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.depositSuccess"));
		MessageWithdrawSuccess = EmeraldBank.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.withdrawSuccess"));
		MessagePlayerBalance = EmeraldBank.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.playerBalance"));
		MessageGiveSuccess = EmeraldBank.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.giveSuccess"));
		MessageSetSuccess = EmeraldBank.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.setSuccess"));
		MessageTakeSuccess = EmeraldBank.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.takeSuccess"));
		MessageReloadSuccess = EmeraldBank.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.reloadSuccess"));
		
		HelpNotAPlayer = EmeraldBank.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.errors.notAPlayer"));
		MessageLessThan = EmeraldBank.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.errors.lessThan"));
		MessagePositiveInteger = EmeraldBank.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.errors.positiveInteger"));
		MessageErrorNameNotAPlayer = EmeraldBank.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.errors.nameNotAPlayer"));
		MessageErrorNoAccount = EmeraldBank.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.errors.noAccount"));
		MessageErrorNoPermission = EmeraldBank.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.errors.noPermission"));
		MessageErrorNotArgument = EmeraldBank.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.errors.notEnoughArgument"));
		MessageErrorReload = EmeraldBank.prefix + ChatColor.RESET + FormatColorMinecraft(plugin.getConfig().getString("messages.errors.reload"));
		
		TopFirstLine = FormatColorMinecraft(plugin.getConfig().getString("messages.top.firstLine"));
		MessagePlayerTop = FormatColorMinecraft(plugin.getConfig().getString("messages.top.line"));
		TopTotalPerPage =plugin.getConfig().getInt("topTotalPerPage");
		AutoSaveTime =plugin.getConfig().getInt("autoSaveTime");
		
		EmeraldName = FormatColorMinecraft(plugin.getConfig().getString("emeraldName"));
		CustomName = plugin.getConfig().getBoolean("customName");
		SuperFreezeNpc = plugin.getConfig().getBoolean("superFreezeNpc");
		
		Allitems.refresh();
		
	}
	
	public static String FormatColorMinecraft(String text){
		text = text.replace("&0", ChatColor.BLACK+"");
		text = text.replace("&1", ChatColor.DARK_BLUE+"");
		text = text.replace("&2", ChatColor.DARK_GREEN+"");
		text = text.replace("&3", ChatColor.DARK_AQUA+"");
		text = text.replace("&4", ChatColor.DARK_RED+"");
		text = text.replace("&5", ChatColor.DARK_PURPLE+"");
		text = text.replace("&6", ChatColor.GOLD+"");
		text = text.replace("&7", ChatColor.GRAY+"");
		text = text.replace("&8", ChatColor.DARK_GRAY+"");
		text = text.replace("&9", ChatColor.BLUE+"");
		text = text.replace("&a", ChatColor.GREEN+"");
		text = text.replace("&b", ChatColor.AQUA+"");
		text = text.replace("&c", ChatColor.RED+"");
		text = text.replace("&d", ChatColor.LIGHT_PURPLE+"");
		text = text.replace("&e", ChatColor.YELLOW+"");
		text = text.replace("&f", ChatColor.WHITE+"");
		text = text.replace("&k", ChatColor.MAGIC+"");
		text = text.replace("&l", ChatColor.BOLD+"");
		text = text.replace("&m", ChatColor.STRIKETHROUGH+"");
		text = text.replace("&n", ChatColor.UNDERLINE+"");
		text = text.replace("&o", ChatColor.ITALIC+"");
		text = text.replace("&r", ChatColor.RESET+"");
		return text;
	}
	
	public static ItemStack getEmeralds(int nb) {
		if(Utils.CustomName){
			return new ItemCreator(Material.EMERALD, (short)0, nb, Utils.EmeraldName).getItemStack();
		}
		return new ItemCreator(Material.EMERALD, (short)0, nb).getItemStack();
	}
}
