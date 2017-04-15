package br.com.devmastersouza.powerfulcookie;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by DevMasterSouza - email: devmastersouza@gmail.com
 */
public final class PowerfulCookie extends JavaPlugin {

    protected static String cookiePrefix;
    protected static List<Cookie> cookies;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        getCommand("powerfulcookie").setExecutor((sender, command, label, args) -> {
            if(args.length == 0) {
                sender.sendMessage("§a------ §6§lPowerfulCookie §a------");
                sender.sendMessage("§aCreate by §bDevMasterSouza");
                sender.sendMessage("§aCommands:");
                sender.sendMessage("§c/pc reload - §aRELOAD CONFIG");
                sender.sendMessage("§c/pc getcookie [name] - §aGET COOKIE ITEM");
                sender.sendMessage("§a------ §6§lPowerfulCookie §a------");
            }
            if(args.length > 0) {
                if(args[0].equalsIgnoreCase("reload")) {

                }
                if(args[0].equalsIgnoreCase("getcookie")) {
                    if(args.length > 1) {
                        
                    }else{
                        sender.sendMessage(ChatColor.RED + "use /pc getcookie [name]");
                    }
                }
            }
            return true;
        });
    }

    public void load() {
        cookiePrefix = ChatColor.translateAlternateColorCodes('&', getConfig().getString("cookiePrefix"));
        for(String cookiename : getConfig().getConfigurationSection("cookies").getKeys(false)) {
            String prefix = "cookies." + cookiename + ".";
            List<String> l1 = getConfig().getStringList(prefix + "effects");
            List<PotionEffect> pel = new ArrayList<>();
            for(String str : l1) {
                PotionEffect pe = Util.potionEffectFromString(str);
                if(pe != null) {
                    pel.add(pe);
                }
            }
            Cookie cookie = new Cookie(cookiename, pel);
            if(getConfig().contains(prefix + "customLore")) {
                List<String> l2 = getConfig().getStringList(prefix + "customLore");
                List<String> coloredl2 = new ArrayList<>();
                for(String str : l2) {
                    coloredl2.add(ChatColor.translateAlternateColorCodes('&', str));
                }
                cookie.setCustomLore(coloredl2);
            }
            if(getConfig().contains(prefix + "eatMessage")) {
                cookie.setEatMessage(ChatColor.translateAlternateColorCodes('&',getConfig().getString(prefix + "eatMessage")));
            }
            if(getConfig().contains(prefix + "eatSound")) {
                for(Sound sound : Sound.values()) {
                    if(sound.name().equalsIgnoreCase(getConfig().getString(prefix + "eatSound"))) {
                        cookie.setEatSound(sound);
                        break;
                    }
                }
            }
            if(getConfig().contains(prefix + "broadcastSound")) {
                cookie.setBroadcastSound(getConfig().getBoolean(prefix + "broadcastSound"));
            }
            if(getConfig().contains(prefix + "eatBroadcastMessage")) {
                cookie.setEatBroadcastMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString(prefix + "eatBroadcastMessage")));
            }
            cookies.add(cookie);
        }
    }

    public void unload() {
        cookies.clear();
        cookiePrefix = null;
    }

    @Override
    public void onDisable() {
    }
}
