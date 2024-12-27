package net.statdefender.blight.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.item.Item;
import net.statdefender.blight.Blight;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.statdefender.blight.world.tree.ChestnutSaplingGenerator;

public class ModBlocks {
    public static final Block HOLLOWED_LOG = registerBlock("hollowed_log",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));

    public static final Block HOLLOWED_LEAVES = registerBlock("hollowed_leaves",
            new Block(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES)));

    public static final Block HOLLOWED_SAPLING = registerBlock("hollowed_sapling",
            new SaplingBlock(new ChestnutSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.DARK_OAK_SAPLING)));


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Blight.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Blight.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        Blight.LOGGER.info("Registering ModBlocks for " + Blight.MOD_ID);
    }
}