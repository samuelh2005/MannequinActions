package me.samuelh2005.mannequin_actions.interactions;

import java.util.*;

import net.minecraft.resources.Identifier;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;

public class EntityHandler {
    public record EntityDefinition(
        Map<Identifier, Identifier> eventBindings
    ){}

    private static final Map<Entity, EntityDefinition> bindings = new HashMap<>();

    public static <T extends Event> void bindEvents(EntityDefinition definitions, Entity entity) {
        bindings.put(entity, definitions);
    }

    public static void unbindEvents(Entity entity) {
        bindings.remove(entity);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Event> InteractionResult emit(Entity entity, T event) {
        EntityDefinition definitions = bindings.get(entity);
        if (definitions == null) return InteractionResult.PASS;

        Identifier eventId = EventRegistry.getInstance().getEventId(event.getClass());
        if (eventId == null) return InteractionResult.PASS;
        Identifier actionId = definitions.eventBindings.get(eventId);
        if (actionId == null) return InteractionResult.PASS;
        EventAction<? extends Event> action = EventRegistry.getInstance().getAction(actionId);
        if (action == null) return InteractionResult.PASS;

        InteractionResult result = ((EventAction<T>) action).execute(event);
        if (result != InteractionResult.PASS) {
            return result;
        }
        return InteractionResult.PASS;
    }
}
