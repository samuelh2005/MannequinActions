package me.samuelh2005.mannequin_actions;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTabs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.samuelh2005.mannequin_actions.interactions.EventRegistry;
import me.samuelh2005.mannequin_actions.interactions.MannequinEvents;
import me.samuelh2005.mannequin_actions.items.ModItems;

public class MannequinActions implements ModInitializer {
	public static final String MOD_ID = "mannequin-actions";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.initialise();
		EventRegistry.initialise();
		MannequinEvents.initialise();
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.OP_BLOCKS).register((output) -> {
			output.accept(ModItems.MANNEQUIN_SPAWN_EGG);
			output.accept(ModItems.MANNEQUIN_EDITOR);
		});
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}