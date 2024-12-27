package net.statdefender.blight;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.statdefender.blight.block.ModBlocks;
import net.statdefender.blight.datagen.ModWorldGenerator;
import net.statdefender.blight.item.ModItemGroups;
import net.statdefender.blight.item.ModItems;
import net.statdefender.blight.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Blight implements ModInitializer {
	public static final String MOD_ID = "blight";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModWorldGeneration.generateModWorldGen();

		StrippableBlockRegistry.register((ModBlocks.HOLLOWED_LOG), ModBlocks.HOLLOWED_LOG);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.HOLLOWED_LEAVES, 1, 1);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.HOLLOWED_LOG, 1, 1);
	}
}