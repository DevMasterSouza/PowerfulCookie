package br.com.devmastersouza.powerfulcookie;

import org.bukkit.potion.PotionEffect;

import java.util.List;

/**
 * Create by DevMasterSouza - email: devmastersouza@gmail.com
 */
public class Cookie {

    private String name;
    private List<PotionEffect> effects;

    public Cookie(String name, List<PotionEffect> effects) {
        this.name = name;
        this.effects = effects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PotionEffect> getEffects() {
        return effects;
    }

    public void setEffects(List<PotionEffect> effects) {
        this.effects = effects;
    }

    public static Cookie getCookieByName(String name) {
        for(Cookie cookie : PowerfulCookie.cookies){
            if(name.equalsIgnoreCase(cookie.getName())) return cookie;
        }
        return null;
    }
}
