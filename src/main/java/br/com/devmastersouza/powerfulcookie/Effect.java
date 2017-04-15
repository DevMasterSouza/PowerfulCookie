package br.com.devmastersouza.powerfulcookie;

import org.bukkit.potion.PotionEffectType;

/**
 * Project Others Projects.
 * Create by DevMasterSouza - email: devmastersouza@gmail.com
 */
public class Effect {
    private PotionEffectType type;
    private int duration;
    private int tier;

    public Effect(PotionEffectType type, int duration, int tier) {
        this.type = type;
        this.duration = duration;
        this.tier = tier;
    }

    public PotionEffectType getType() {
        return type;
    }

    public int getDuration() {
        return duration;
    }

    public int getTier() {
        return tier;
    }
}
