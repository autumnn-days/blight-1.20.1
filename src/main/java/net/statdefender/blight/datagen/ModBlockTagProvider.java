package net.statdefender.blight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.statdefender.blight.block.ModBlocks;
import net.statdefender.blight.util.ModTags;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(ModBlocks.HOLLOWED_LOG);

        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(ModBlocks.HOLLOWED_LEAVES);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL);


        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL);


        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL);


        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("fabric", "needs_tool_level_4")));


        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("fabric", "needs_tool_level_5")));


        getOrCreateTagBuilder(BlockTags.FENCES);
               // .add(ModBlocks.RUBY_FENCE);
        getOrCreateTagBuilder(BlockTags.FENCE_GATES);
              //  .add(ModBlocks.RUBY_FENCE_GATE);
        getOrCreateTagBuilder(BlockTags.WALLS);
              //  .add(ModBlocks.RUBY_WALL);

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.HOLLOWED_LOG);
    }
}