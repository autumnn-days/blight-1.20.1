package net.statdefender.blight;

import net.fabricmc.api.ModInitializer;

import net.statdefender.blight.item.ModItemGroups;
import net.statdefender.blight.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Blight implements ModInitializer {
	public static final String MOD_ID = "blight";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		LOGGER.info("Hello Fabric world!");
	}
}