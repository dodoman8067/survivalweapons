package kro.dodoworld.survivalweapons.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerDragonSwordEffectEvent extends PlayerEvent implements Cancellable {
    private final HandlerList handlerList = new HandlerList();
    private boolean damageEntity;
    private boolean spawnParticles;
    private boolean playSound;
    private boolean cancel = false;
    public PlayerDragonSwordEffectEvent(@NotNull Player who, boolean damageEntity, boolean spawnParticles, boolean playSound) {
        super(who);
        this.damageEntity = damageEntity;
        this.spawnParticles = spawnParticles;
        this.playSound = playSound;
    }

    public void setDamageEntity(boolean damageEntity) {
        this.damageEntity = damageEntity;
    }

    public boolean shouldDamageEntity() {
        return damageEntity;
    }

    public boolean shouldSpawnParticles() {
        return spawnParticles;
    }

    public void setSpawnParticles(boolean spawnParticles) {
        this.spawnParticles = spawnParticles;
    }

    public boolean shouldPlaySound() {
        return playSound;
    }

    public void setPlaySound(boolean playSound) {
        this.playSound = playSound;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
