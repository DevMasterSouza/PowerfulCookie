package br.com.devmastersouza.powerfulcookie;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Create by DevMasterSouza - email: devmastersouza@gmail.com
 */
public class EatCookieEvent extends Event implements Cancellable{

    private static HandlerList handlerList = new HandlerList();
    private boolean cancelled = false;

    private Player player;
    private Cookie cookie;

    public EatCookieEvent(Player player, Cookie cookie) {
        this.player = player;
        this.cookie = cookie;
    }

    public Player getPlayer() {
        return player;
    }

    public Cookie getCookie() {
        return cookie;
    }

    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {return handlerList;}
}
