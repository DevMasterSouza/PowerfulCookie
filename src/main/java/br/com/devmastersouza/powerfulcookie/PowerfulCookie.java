package br.com.devmastersouza.powerfulcookie;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by DevMasterSouza - email: devmastersouza@gmail.com
 */
public final class PowerfulCookie extends JavaPlugin {

    protected static String cookiePrefix;
    protected static List<Cookie> cookies = new ArrayList<>();
    private String nopermission, onlyingame, configreloaded, cookiedontexist, cookiereceived;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        load();
        nopermission = ChatColor.translateAlternateColorCodes('&',getConfig().getString("msg.nopermission"));
        onlyingame = ChatColor.translateAlternateColorCodes('&',getConfig().getString("msg.onlyingame"));
        configreloaded = ChatColor.translateAlternateColorCodes('&',getConfig().getString("msg.configreloaded"));
        cookiedontexist = ChatColor.translateAlternateColorCodes('&',getConfig().getString("msg.cookiedontexist"));
        cookiereceived = ChatColor.translateAlternateColorCodes('&',getConfig().getString("msg.cookiereceived"));

        getServer().getPluginManager().registerEvents(new CookieListener(), this);

        Glow.registerGlow();

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
                    if(sender.hasPermission("PowerfulCookie.cmd.reload")) {
                        unload();
                        reloadConfig();
                        load();
                        sender.sendMessage(configreloaded);
                    }else{
                        sender.sendMessage(nopermission);
                    }
                }
                if(args[0].equalsIgnoreCase("getcookie")) {
                    if(args.length > 1) {
                        if(sender instanceof Player) {
                            if(sender.hasPermission("PowerfulCookie.cmd.getcookie")) {
                                Player player = (Player) sender;
                                Cookie cookie = Cookie.getCookieByName(args[1]);
                                if(cookie != null) {
                                    player.getInventory().addItem(cookie.getCookie());
                                    sender.sendMessage(cookiereceived.replaceAll("<name>", cookie.getName()));
                                }else{
                                    sender.sendMessage(cookiedontexist);
                                }
                            }else{
                                sender.sendMessage(nopermission);
                            }
                        }else{
                            sender.sendMessage(onlyingame);
                        }
                    }else{
                        sender.sendMessage(ChatColor.RED + "Use /pc getcookie [name]");
                    }
                }
            }
            return true;
        });
        getCommand("powerfulcookie").setTabCompleter((sender, command, alias, args) -> {
            if(args.length > 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    return Collections.emptyList();
                }
                if(args[0].equalsIgnoreCase("getcookie")) {
                    if (sender.hasPermission("PowerfulCookie.cmd.getcookie")) {
                        return cookies.stream().filter(c -> Util.startWithIgnorecase(c.getName(), args[1])).map(Cookie::getName).collect(Collectors.toList());
                    }
                }
            }else if(args.length > 0){
                return Arrays.asList("reload", "getcookie").stream().filter(s -> Util.startWithIgnorecase(s,args[0])).collect(Collectors.toList());
            }
            return Collections.emptyList();
        });
    }

    public void load() {
        cookiePrefix = ChatColor.translateAlternateColorCodes('&', getConfig().getString("cookiePrefix"));
        for(String cookiename : getConfig().getConfigurationSection("cookies").getKeys(false)) {
            String prefix = "cookies." + cookiename + ".";
            List<String> l1 = getConfig().getStringList(prefix + "effects");
            List<Effect> pel = new ArrayList<>();
            for(String str : l1) {
                Effect pe = Util.potionEffectFromString(str);
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
                cookie.setEatMessage(ChatColor.translateAlternateColorCodes('&',getConfig().getString(prefix + "eatMessage")).replaceAll("<br>", "\n"));
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
                cookie.setEatBroadcastMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString(prefix + "eatBroadcastMessage")).replaceAll("<br>", "\n"));
            }
            if(getConfig().contains(prefix + "eatRadiusMessage")) {
                cookie.setEatRadiusMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString(prefix + "eatRadiusMessage")).replaceAll("<br>", "\n"));
            }
            if(getConfig().contains(prefix + "messageRadius")) {
                cookie.setMessageRadius(getConfig().getInt(prefix + "messageRadius"));
            }
            if(getConfig().contains(prefix + "soundRadius")) {
                cookie.setSoundRadius(getConfig().getInt(prefix + "soundRadius"));
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
