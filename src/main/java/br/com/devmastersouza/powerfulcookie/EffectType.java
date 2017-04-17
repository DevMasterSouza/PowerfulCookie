package br.com.devmastersouza.powerfulcookie;

import org.bukkit.potion.PotionEffectType;

/**
 * Create by DevMasterSouza - email: devmastersouza@gmail.com
 */
public enum EffectType {
    SPEED(1), SLOW(2), FAST_DIGGING(3), SLOW_DIGGING(4), INCREASE_DAMAGE(5), HEAL(6), HARM(7), JUMP(8),
    CONFUSION(9), REGENERATION(10), DAMAGE_RESISTANCE(11), FIRE_RESISTANCE(12), WATER_BREATHING(13),
    INVISIBILITY(14), BLINDNESS(15), NIGHT_VISION(16);
    int id;

    EffectType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public PotionEffectType getType() {
        return PotionEffectType.getById(id);
    }
}
