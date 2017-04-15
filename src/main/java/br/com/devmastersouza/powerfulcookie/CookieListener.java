package br.com.devmastersouza.powerfulcookie;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

/**
 * Project Others Projects.
 * Create by DevMasterSouza - email: devmastersouza@gmail.com
 */
public class CookieListener implements Listener {
    @EventHandler
    public void onPlayerEat(PlayerItemConsumeEvent event) {
        ItemStack item = event.getItem();
        if(item != null){
            if(item.getItemMeta() != null) {
                if(item.getItemMeta().getDisplayName() != null) {
                    if(ChatColor.stripColor(item.getItemMeta().getDisplayName())
                            .startsWith(ChatColor.stripColor(PowerfulCookie.cookiePrefix))){
                        String cookiename = ChatColor.stripColor(item.getItemMeta().getDisplayName()).replaceAll(ChatColor.stripColor(PowerfulCookie.cookiePrefix), "");
                        Cookie cookie = Cookie.getCookieByName(cookiename);
                        for(Effect effect : cookie.getEffects()) {
                            event.getPlayer().addPotionEffect(new PotionEffect(effect.getType(), effect.getDuration(), effect.getTier()), true);
                        }
                        if(cookie.getEatMessage() != null) {
                            event.getPlayer().sendMessage(cookie.getEatMessage());
                        }
                        if(cookie.getEatBroadcastMessage() != null) {
                            for(Player online : Util.getOnlinePlayers()) {
                                online.sendMessage(cookie.getEatBroadcastMessage().replaceAll("<player>", event.getPlayer().getName()));
                            }
                        }
                        if(cookie.getEatSound() != null) {
                            event.getPlayer().playSound(event.getPlayer().getLocation(),cookie.getEatSound(), 1F,1F);
                            if(cookie.isBroadcastSound()) {
                                for(Player online : Util.getOnlinePlayers()) {
                                    online.playSound(online.getLocation(),cookie.getEatSound(), 1F,1F);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
