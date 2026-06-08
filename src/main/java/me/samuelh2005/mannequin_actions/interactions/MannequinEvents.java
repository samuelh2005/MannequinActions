package me.samuelh2005.mannequin_actions.interactions;

import me.samuelh2005.mannequin_actions.MannequinActions;
import net.minecraft.resources.Identifier;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.decoration.Mannequin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public final class MannequinEvents {
    private MannequinEvents() {
        // Private constructor to prevent instantiation
    }

    public static record PlayerInteractEvent(Player player, Mannequin mannequin, InteractionHand hand, Vec3 location) implements Event {
    }

    public static final Identifier PLAYER_INTERACT;

    static {
        PLAYER_INTERACT = EventRegistry.getInstance().registerEvent(MannequinActions.id("player_interact"), PlayerInteractEvent.class);
    }

    public static void initialise() {
        // This method is intentionally left blank. It is used to trigger the static initialiser.
        MannequinActions.LOGGER.info("Initialising MannequinEvents");
    }
}
