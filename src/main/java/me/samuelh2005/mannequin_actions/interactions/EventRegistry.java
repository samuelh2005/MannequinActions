package me.samuelh2005.mannequin_actions.interactions;

import java.util.HashMap;
import java.util.Map;

import me.samuelh2005.mannequin_actions.MannequinActions;
import net.minecraft.resources.Identifier;

public class EventRegistry {
    private static EventRegistry INSTANCE;

    public static void initialise() {
        MannequinActions.LOGGER.info("Initialising EventRegistry");
        if (INSTANCE != null) {
            throw new IllegalStateException("EventRegistry is already initialised");
        }
        INSTANCE = new EventRegistry();
    }

    public static EventRegistry getInstance() {
        if (INSTANCE == null) {
            throw new IllegalStateException("EventRegistry is not initialised");
        }
        return INSTANCE;
    }

    private final Map<Identifier, Class<? extends Event>> events = new HashMap<>();
    private final Map<Class<? extends Event>, Identifier> eventIds = new HashMap<>();
    private final Map<Identifier, EventAction<? extends Event>> actions = new HashMap<>();
    private final Map<EventAction<? extends Event>, Identifier> actionIds = new HashMap<>();

    // -------------------------
    // EVENTS
    // -------------------------

    public <T extends Event> Identifier registerEvent(Identifier id, Class<T> eventType) {
        if (events.containsKey(id)) {
            throw new IllegalStateException("Event already registered: " + id);
        }

        events.put(id, eventType);
        eventIds.put(eventType, id);
        return id;
    }

    public boolean isEventRegistered(Identifier id) {
        return events.containsKey(id);
    }

    public Class<? extends Event> getEvent(Identifier id) {
        return events.get(id);
    }

    public Identifier getEventId(Class<? extends Event> eventType) {
        return eventIds.get(eventType);
    }

    // -------------------------
    // ACTIONS
    // -------------------------

    public <T extends Event> Identifier registerAction(Identifier id, EventAction<T> action) {
        if (actions.containsKey(id)) {
            throw new IllegalStateException("Action already registered: " + id);
        }

        actions.put(id, action);
        actionIds.put(action, id);
        return id;
    }

    public Identifier getActionId(EventAction<? extends Event> action) {
        return actionIds.get(action);
    }

    @SuppressWarnings("unchecked")
    public <T extends Event> EventAction<T> getAction(Identifier id) {
        return (EventAction<T>) actions.get(id);
    }

    public boolean isActionRegistered(Identifier id) {
        return actions.containsKey(id);
    }
}