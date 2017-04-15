package br.com.devmastersouza.powerfulcookie;

import org.bukkit.Sound;
import org.bukkit.potion.PotionEffect;

import java.util.List;

/**
 * Create by DevMasterSouza - email: devmastersouza@gmail.com
 */
public class Cookie {

    private String name;
    private List<PotionEffect> effects;
    private List<String> customLore = null;
    private String eatMessage = null;
    private Sound eatSound = null;
    private boolean broadcastSound = false;
    private String eatBroadcastMessage = null;

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

    public Cookie(List<String> customLore) {
        this.customLore = customLore;
    }

    public List<String> getCustomLore() {
        return customLore;
    }

    public void setCustomLore(List<String> customLore) {
        this.customLore = customLore;
    }

    public String getEatMessage() {
        return eatMessage;
    }

    public void setEatMessage(String eatMessage) {
        this.eatMessage = eatMessage;
    }

    public Sound getEatSound() {
        return eatSound;
    }

    public void setEatSound(Sound eatSound) {
        this.eatSound = eatSound;
    }

    public boolean isBroadcastSound() {
        return broadcastSound;
    }

    public void setBroadcastSound(boolean broadcastSound) {
        this.broadcastSound = broadcastSound;
    }

    public String getEatBroadcastMessage() {
        return eatBroadcastMessage;
    }

    public void setEatBroadcastMessage(String eatBroadcastMessage) {
        this.eatBroadcastMessage = eatBroadcastMessage;
    }

    public static Cookie getCookieByName(String name) {
        for(Cookie cookie : PowerfulCookie.cookies){
            if(name.equalsIgnoreCase(cookie.getName())) return cookie;
        }
        return null;
    }
}
