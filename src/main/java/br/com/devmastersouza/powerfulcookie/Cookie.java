package br.com.devmastersouza.powerfulcookie;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * Create by DevMasterSouza - email: devmastersouza@gmail.com
 */
public class Cookie {

    private String name;
    private List<Effect> effects;
    private List<String> customLore = null;
    private String eatMessage = null;
    private Sound eatSound = null;
    private boolean broadcastSound = false;
    private String eatBroadcastMessage = null;
    private String eatRadiusMessage = null;
    private int messageRadius = 0;
    private int soundRadius = 0;

    public Cookie(String name, List<Effect> effects) {
        this.name = name;
        this.effects = effects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public void setEffects(List<Effect> effects) {
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

    public String getEatRadiusMessage() {
        return eatRadiusMessage;
    }

    public void setEatRadiusMessage(String eatRadiusMessage) {
        this.eatRadiusMessage = eatRadiusMessage;
    }

    public int getMessageRadius() {
        return messageRadius;
    }

    public void setMessageRadius(int messageRadius) {
        this.messageRadius = messageRadius;
    }

    public int getSoundRadius() {
        return soundRadius;
    }

    public void setSoundRadius(int soundRadius) {
        this.soundRadius = soundRadius;
    }

    public ItemStack getCookie() {
        ItemStack item = new ItemStack(Material.COOKIE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(PowerfulCookie.cookiePrefix + name);
        if(customLore != null) meta.setLore(customLore);
        item.setItemMeta(meta);
        return item;
    }

    public static Cookie getCookieByName(String name) {
        for(Cookie cookie : PowerfulCookie.cookies){
            if(name.equalsIgnoreCase(cookie.getName())) return cookie;
        }
        return null;
    }
}
