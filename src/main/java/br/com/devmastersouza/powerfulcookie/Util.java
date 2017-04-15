package br.com.devmastersouza.powerfulcookie;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Project Others Projects.
 * Create by DevMasterSouza - email: devmastersouza@gmail.com
 */
public class Util {
    public static String potionEffectToString(PotionEffect potion) {
        return potion.getType().getName() + ":" + potion.getAmplifier() + ":" + potion.getDuration();
    }
    public static PotionEffect potionEffectFromString(String string) {
        try {
            String[] args = string.split(":");
            for (PotionEffectType type : PotionEffectType.values()) {
                if(isInt(args[0])) {
                    if(Integer.parseInt(args[0]) == type.getId()) {
                        return type.createEffect(Integer.parseInt(args[2]), Integer.parseInt(args[1]));
                    }
                }else{
                    if(args[0].equalsIgnoreCase(type.getName())) {
                        return type.createEffect(Integer.parseInt(args[2]), Integer.parseInt(args[1]));
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
}
