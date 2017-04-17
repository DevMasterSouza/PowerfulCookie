package br.com.devmastersouza.powerfulcookie;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

/**
 * Create by DevMasterSouza - email: devmastersouza@gmail.com
 */
public class CookieListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onPlayerEat(PlayerItemConsumeEvent event) {
        ItemStack item = event.getItem();
        if(item != null){
            if(item.getItemMeta() != null) {
                if(item.getItemMeta().getDisplayName() != null) {
                    if(ChatColor.stripColor(item.getItemMeta().getDisplayName())
                            .startsWith(ChatColor.stripColor(PowerfulCookie.cookiePrefix))){
                        String cookiename = ChatColor.stripColor(item.getItemMeta().getDisplayName()).replaceAll(ChatColor.stripColor(PowerfulCookie.cookiePrefix), "");
                        Cookie cookie = Cookie.getCookieByName(cookiename);
                        if(cookie != null) {
                            EatCookieEvent ece = new EatCookieEvent(event.getPlayer(), cookie);
                            Bukkit.getPluginManager().callEvent(ece);
                            if(!ece.isCancelled()) {
                                for (Effect effect : cookie.getEffects()) {
                                    event.getPlayer().addPotionEffect(new PotionEffect(effect.getType(), effect.getDuration(), effect.getTier()-1), true);
                                }
                                if (cookie.getEatMessage() != null) {
                                    event.getPlayer().sendMessage(cookie.getEatMessage());
                                }
                                if (cookie.getEatBroadcastMessage() != null) {
                                    Util.getOnlinePlayers().forEach(o -> o.sendMessage(cookie.getEatBroadcastMessage().replaceAll("<player>", event.getPlayer().getName())));
                                }
                                if(cookie.getEatRadiusMessage() != null) {
                                    for(Entity en : event.getPlayer().getNearbyEntities(cookie.getMessageRadius(),cookie.getMessageRadius(),cookie.getMessageRadius())){
                                        if(en instanceof Player) ((Player)en).sendMessage(cookie.getEatRadiusMessage().replaceAll("<player>", event.getPlayer().getName()));
                                    }
                                }
                                if (cookie.getEatSound() != null) {
                                    event.getPlayer().playSound(event.getPlayer().getLocation(), cookie.getEatSound(), 1F, 1F);
                                    if (cookie.getSoundRadius() > 0) {
                                        for (Entity en : event.getPlayer().getNearbyEntities(cookie.getSoundRadius(), cookie.getSoundRadius(), cookie.getSoundRadius())) {
                                            if (en instanceof Player)
                                                ((Player) en).playSound(((Player) en).getLocation(), cookie.getEatSound(), 1F, 1F);
                                        }
                                    }
                                    if (cookie.isBroadcastSound()) {
                                        Util.getOnlinePlayers().forEach(o -> o.playSound(o.getLocation(), cookie.getEatSound(), 1F, 1F));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
