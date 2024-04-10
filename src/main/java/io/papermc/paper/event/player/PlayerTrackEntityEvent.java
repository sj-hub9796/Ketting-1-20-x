package io.papermc.paper.event.player;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * Is called when a {@link Player} tracks an {@link Entity}.
 * <p>
 * If cancelled entity is not shown to the player and interaction in both directions is not possible.
 * <p>
 * Adding or removing entities from the world at the point in time this event is called is completely
 * unsupported and should be avoided.
 */
public class PlayerTrackEntityEvent extends PlayerEvent implements Cancellable {

	private static final HandlerList HANDLER_LIST = new HandlerList();

	private final Entity entity;
	private boolean cancelled;

	@ApiStatus.Internal
	public PlayerTrackEntityEvent(@NotNull Player player, @NotNull Entity entity) {
		super(player);
		this.entity = entity;
	}

	/**
	 * Gets the entity that will be tracked
	 *
	 * @return the entity tracked
	 */
	@NotNull
	public Entity getEntity() {
		return this.entity;
	}

	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}

	@NotNull
	public static HandlerList getHandlerList() {
		return HANDLER_LIST;
	}

	@NotNull
	@Override
	public HandlerList getHandlers() {
		return HANDLER_LIST;
	}
}
