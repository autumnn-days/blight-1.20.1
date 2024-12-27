package net.statdefender.blight.world;

import net.minecraft.block.Block;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.DarkOakFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.DarkOakTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.statdefender.blight.Blight;
import net.statdefender.blight.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> RUBY_ORE_KEY = registerKey("ruby_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> NETHER_RUBY_ORE_KEY = registerKey("nether_ruby_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> END_RUBY_ORE_KEY = registerKey("end_ruby_ore");

    public static final RegistryKey<ConfiguredFeature<?, ?>> CHESTNUT_KEY = registerKey("chestnut");

    public static final RegistryKey<ConfiguredFeature<?, ?>> LARGE_CHESTNUT_KEY =
            RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Blight.MOD_ID, "large_chestnut"));




    public static void boostrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplacables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplacables = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endReplacables = new BlockMatchRuleTest(Blocks.END_STONE);

        List<OreFeatureConfig.Target> overworldRubyOres =
                List.of(OreFeatureConfig.createTarget(stoneReplacables, Blocks.SLIME_BLOCK.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplacables, Blocks.SLIME_BLOCK.getDefaultState()));

        List<OreFeatureConfig.Target> netherRubyOres =
                List.of(OreFeatureConfig.createTarget(netherReplacables, Blocks.SLIME_BLOCK.getDefaultState()));

        List<OreFeatureConfig.Target> endRubyOres =
                List.of(OreFeatureConfig.createTarget(endReplacables, Blocks.SLIME_BLOCK.getDefaultState()));

        register(context, RUBY_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldRubyOres, 12));
        register(context, NETHER_RUBY_ORE_KEY, Feature.ORE, new OreFeatureConfig(netherRubyOres, 12));
        register(context, END_RUBY_ORE_KEY, Feature.ORE, new OreFeatureConfig(endRubyOres, 12));



        register(context, CHESTNUT_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.HOLLOWED_LOG),
                new DarkOakTrunkPlacer(5, 4, 3),

                BlockStateProvider.of(ModBlocks.HOLLOWED_LEAVES),
                new DarkOakFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(1)),

                new TwoLayersFeatureSize(1, 0, 2)).build());
    }

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        context.register(
                LARGE_CHESTNUT_KEY,
                new ConfiguredFeature<>(Feature.TREE, new TreeFeatureConfig.Builder(
                        BlockStateProvider.of(ModBlocks.HOLLOWED_LOG),
                        new StraightTrunkPlacer(10, 12, 5),
                        BlockStateProvider.of(ModBlocks.HOLLOWED_LEAVES),
                        new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                        new TwoLayersFeatureSize(1, 0, 2)
                ).build())
        );
    }
    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Blight.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}