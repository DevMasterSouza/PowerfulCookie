package br.com.devmastersouza.powerfulcookie;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Create by DevMasterSouza - email: devmastersouza@gmail.com
 */
public class Util {

    private static Method _getOnliePlayers;
    private static Method _Poti;
    static {
        try {
            _getOnliePlayers = Bukkit.class.getMethod("getOnlinePlayers");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static String potionEffectToString(PotionEffect potion) {
        return potion.getType().getName() + ";" + potion.getAmplifier() + ";" + potion.getDuration();
    }

    public static boolean startWithIgnorecase(String string, String regex) {
        return string.toLowerCase().startsWith(regex.toLowerCase());
    }

    public static Effect potionEffectFromString(String string) {
        try {
            String[] args = string.split(";");
            for (PotionEffectType type : PotionEffectType.values()) {
                if(type == null) continue;
                if(isInt(args[0])) {
                    if(Integer.parseInt(args[0]) == type.getId()) {
                        return new Effect(type, Integer.parseInt(args[2].trim())*20, Integer.parseInt(args[1].trim()));
                    }
                }else{
                    if(args[0].equalsIgnoreCase(type.getName())) {
                        return new Effect(type,Integer.parseInt(args[2].trim())*20, Integer.parseInt(args[1].trim()));
                    }
                }
            }
        }catch (Exception e){}
        return null;
    }
    public static boolean isInt(String string) {
        try{
            Integer.parseInt(string);
            return true;
        }catch (NumberFormatException e) {return false;}
    }

    //OLD and NEW getOnlinePlayers support
    public static List<Player> getOnlinePlayers() {
        try {
            List<Player> players = new ArrayList<>();
            if(_getOnliePlayers.getReturnType().getName().equalsIgnoreCase(Player[].class.getName())) {
                for(Player player : (Player[])_getOnliePlayers.invoke(null)) {
                    players.add(player);
                }
            }else{
                for(Player player : (Collection<Player>)_getOnliePlayers.invoke(null)) {
                    players.add(player);
                }
            }
            return players;
        }catch (Exception e){return null;}
    }
}
