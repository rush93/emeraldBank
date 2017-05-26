package rush93.emeraldbank;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import rush93.emeraldbank.bank.Bank;
import rush93.emeraldbank.bank.Banquier;
import rush93.emeraldbank.bank.Compte;
import rush93.emeraldbank.commands.AddBanquier;
import rush93.emeraldbank.commands.Balance;
import rush93.emeraldbank.commands.BalanceTop;
import rush93.emeraldbank.commands.Commande;
import rush93.emeraldbank.commands.Deposit;
import rush93.emeraldbank.commands.Give;
import rush93.emeraldbank.commands.ReloadCommand;
import rush93.emeraldbank.commands.Set;
import rush93.emeraldbank.commands.Take;
import rush93.emeraldbank.commands.Withdraw;
import rush93.emeraldbank.events.BanquierEvent;
import rush93.emeraldbank.events.ChatEvent;
import rush93.emeraldbank.events.InventoryEvent;


public class EmeraldBank extends JavaPlugin {
	public CommandSender console;
	public final static String Admin_Perm = "emeraldbank.admin";
	public static String prefix = "";
	public static ArrayList<Commande> COMMANDES;
	public Bank bank;
	public ChatEvent pc;
	
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args)
	{
		ArrayList<String> liste = new ArrayList<>();
		if(args.length==0){
			liste.add("ebank");
			return liste;
		}
		
		if(args.length==1){
			String label = args[0];
			for (int i = 0; i < COMMANDES.size(); i++) {
				if(label.length()<= COMMANDES.get(i).label.length() && COMMANDES.get(i).label.substring(0, label.length()).equalsIgnoreCase(label) && COMMANDES.get(i).canExecute(sender)){
					liste.add(COMMANDES.get(i).label);
				}
			}
		}else{
			for (int i = 0; i < COMMANDES.size(); i++) {
				if(args[0].equalsIgnoreCase(COMMANDES.get(i).label) && COMMANDES.get(i).canExecute(sender)){
					return COMMANDES.get(i).getTab(sender, args);
				}
			}
		}
		return liste;
	}
	@Override
	public void onEnable(){
		console = this.getServer().getConsoleSender();
		
		PluginManager pm = this.getServer().getPluginManager();
		pm.addPermission(new Permission(Admin_Perm));
		if(loadConfig()){
			prefix = Utils.Prefix;
			initCommandes();
			loadBank();
			registerEvent();
			Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
				
				@Override
				public void run() {
					Utils.save(bank, new File(getDataFolder(),"Bank.dat"));
				}
			}, Utils.AutoSaveTime, Utils.AutoSaveTime);
			if(Utils.SuperFreezeNpc){
				Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
					
					@Override
					public void run() {
						ArrayList<Banquier> banquiers = bank.banquiers;
						for (Banquier banquier : banquiers) {
							banquier.teleport();
						}
					}
				}, 20, 20);
			}
			getServer().getConsoleSender().sendMessage(ChatColor.YELLOW+"BONJOUR ! j'aime les bananes");
		}
	}
	
	private void registerEvent(){
		new BanquierEvent(this);
		new InventoryEvent(this);
		this.pc = new ChatEvent(this);
	}
	
	public boolean loadConfig(){
		try{
			getConfig().options().copyDefaults(true);
			saveDefaultConfig();
			Utils.loadConfig(this);
		}catch(Exception e){
			this.console.sendMessage(prefix + ""+ ChatColor.RED +"Error ! in config.yml please fix it !\n");
			return false;
		}
		return true;
	}
	
	private void initCommandes() {
		EmeraldBank.COMMANDES = new ArrayList<>();
		EmeraldBank.COMMANDES.add(new AddBanquier(this));
		EmeraldBank.COMMANDES.add(new BalanceTop(this));
		EmeraldBank.COMMANDES.add(new Balance(this));
		EmeraldBank.COMMANDES.add(new Give(this));
		EmeraldBank.COMMANDES.add(new Set(this));
		EmeraldBank.COMMANDES.add(new Take(this));
		EmeraldBank.COMMANDES.add(new ReloadCommand(this));
		EmeraldBank.COMMANDES.add(new Deposit(this));
		EmeraldBank.COMMANDES.add(new Withdraw(this));
	}
	
	private void loadBank() {
		File dir = getDataFolder();
		if(!dir.exists())
			if(!dir.mkdir())
				console.sendMessage("Création du dossier impossible");
		Object o = Utils.load(new File(getDataFolder(),"Bank.dat"));
			
		if(o == null)
			this.bank = new Bank(new ArrayList<Compte>(), new ArrayList<Banquier>());
		else
			this.bank = (Bank)o;
	}
	@Override
	public void onDisable(){
		Utils.save(this.bank, new File(getDataFolder(),"Bank.dat"));
	}
	
	public boolean onCommand(CommandSender p, Command commande, String commandLabel, String[] args){
		if(commandLabel.equalsIgnoreCase("eb") || commandLabel.equalsIgnoreCase("ebank")){
			if(args.length < 1){
				EmeraldBank.affHelp(p);
				return false;
			}
			for (int i = 0; i < COMMANDES.size(); i++) {
				if(COMMANDES.get(i).label.equalsIgnoreCase(args[0])){
					return COMMANDES.get(i).run(p, args);
				}
			}
			EmeraldBank.affHelp(p);
		}
		return false;
		
	}

	public static void affHelp(CommandSender p){
		p.sendMessage(ChatColor.GOLD + "~~" + ChatColor.AQUA + " Emerald Bank " + ChatColor.GOLD + "~~");
		p.sendMessage(ChatColor.GRAY +"Plugin made by : rush93");
		boolean showOne=false;
		for (int i = 0; i < COMMANDES.size(); i++) {
			if(COMMANDES.get(i).getHelp(p) && !showOne){
				showOne = true;
			}
		}
		if(!showOne){
			p.sendMessage(Utils.HelpNoPermission);
		}
	}
	
}
