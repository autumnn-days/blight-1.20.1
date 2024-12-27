package net.statdefender.blight.world.tree;

import net.minecraft.block.Blocks;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.registry.RegistryKey;
import net.statdefender.blight.block.ModBlocks;
import net.statdefender.blight.world.ModConfiguredFeatures;
import org.jetbrains.annotations.Nullable;

public class ChestnutSaplingGenerator extends SaplingGenerator {
    @Nullable
    @Override
    protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        // Only return the feature if it's in a 2x2 configuration
        return null; // Prevent default single sapling growth
    }

    @Override
    public boolean generate(ServerWorld world, net.minecraft.world.gen.chunk.ChunkGenerator chunkGenerator, BlockPos pos, net.minecraft.block.BlockState state, Random random) {
        // Check for 2x2 sapling configuration
        BlockPos basePos = get2x2BasePos(world, pos);
        if (basePos != null) {
            System.out.println("2x2 detected! Generating tree at: " + basePos); // Debug log

            // Retrieve the configured feature from the registry
            ConfiguredFeature<?, ?> feature = world.getRegistryManager()
                    .get(net.minecraft.registry.RegistryKeys.CONFIGURED_FEATURE)
                    .get(ModConfiguredFeatures.CHESTNUT_KEY);

            if (feature != null) {
                // Clear the 2x2 saplings
                clearSaplings(world, basePos);
                boolean success = feature.generate(world, chunkGenerator, random, basePos); // Use adjusted position
                if (success) {
                    System.out.println("Tree generated successfully!");
                } else {
                    System.out.println("Tree generation failed!");
                    addSaplings(world, basePos);
                }
                return success;
            } else {
                System.out.println("Feature not found!");
            }
        } else {
            System.out.println("Not a 2x2 configuration at: " + pos); // Debug log
        }

        return false; // Prevent growth if not 2x2
    }

    private BlockPos get2x2BasePos(ServerWorld world, BlockPos pos) {
        // Try checking 4 possible 2x2 patterns based on starting positions
        for (int x = 0; x <= 1; x++) {
            for (int z = 0; z <= 1; z++) {
                // Move to a possible bottom-left position of the 2x2
                BlockPos startPos = pos.add(-x, 0, -z);

                // Debugging output
                System.out.println("Checking 2x2 starting at: " + startPos);

                // Check all 4 blocks in the 2x2 grid
                if (world.getBlockState(startPos).getBlock() == ModBlocks.HOLLOWED_SAPLING &&
                        world.getBlockState(startPos.east()).getBlock() == ModBlocks.HOLLOWED_SAPLING &&
                        world.getBlockState(startPos.south()).getBlock() == ModBlocks.HOLLOWED_SAPLING &&
                        world.getBlockState(startPos.south().east()).getBlock() == ModBlocks.HOLLOWED_SAPLING) {

                    // Debug output confirming a match
                    System.out.println("2x2 saplings found at: " + startPos);
                    return startPos;
                }
            }
        }

        // No valid 2x2 configuration found
        System.out.println("No 2x2 saplings found near: " + pos);
        return null;
    }

    private void clearSaplings(ServerWorld world, BlockPos basePos) {
        // Remove all saplings in the 2x2 grid
        world.removeBlock(basePos, false);
        world.removeBlock(basePos.east(), false);
        world.removeBlock(basePos.south(), false);
        world.removeBlock(basePos.south().east(), false);
        System.out.println("Cleared saplings at: " + basePos);
    }

    private void addSaplings(ServerWorld world, BlockPos basePos) {
        // Remove all saplings in the 2x2 grid
        world.setBlockState(basePos, ModBlocks.HOLLOWED_SAPLING.getDefaultState(), 3);
        world.setBlockState(basePos.east(), ModBlocks.HOLLOWED_SAPLING.getDefaultState(), 3);
        world.setBlockState(basePos.south(), ModBlocks.HOLLOWED_SAPLING.getDefaultState(), 3);
        world.setBlockState(basePos.south().east(), ModBlocks.HOLLOWED_SAPLING.getDefaultState(), 3);
        System.out.println("Re-added saplings at: " + basePos);
    }
}
