package me.samuelh2005.mannequin_actions.interactions;

import net.minecraft.world.InteractionResult;

@FunctionalInterface
public interface EventAction<T extends Event> {
    InteractionResult execute(T event);
}
