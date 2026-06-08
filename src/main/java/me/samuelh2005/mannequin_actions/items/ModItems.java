package me.samuelh2005.mannequin_actions.items;

import java.util.function.Function;

import eu.pb4.polymer.core.api.item.PolymerSpawnEggItem;
import me.samuelh2005.mannequin_actions.MannequinActions;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ModItems {
    public static final PolymerSpawnEggItem MANNEQUIN_SPAWN_EGG;
    public static final MannequinEditorItem MANNEQUIN_EDITOR;

	public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
		// Create the item key.
		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, MannequinActions.id(name));

		// Create the item instance.
		T item = itemFactory.apply(settings.setId(itemKey));

		// Register the item.
		Registry.register(BuiltInRegistries.ITEM, itemKey, item);

		return item;
	}

    static {
        MANNEQUIN_SPAWN_EGG = register("mannequin_spawn_egg", (settings) -> new PolymerSpawnEggItem(Items.VILLAGER_SPAWN_EGG, settings), new Item.Properties().spawnEgg(EntityType.MANNEQUIN));
        MANNEQUIN_EDITOR = register("mannequin_editor", MannequinEditorItem::new, new Item.Properties());
    }

    public static void initialise() {
        // This method is intentionally left blank. It is used to trigger the static initializer.
        MannequinActions.LOGGER.info("Initialising ModItems...");
    }
}
