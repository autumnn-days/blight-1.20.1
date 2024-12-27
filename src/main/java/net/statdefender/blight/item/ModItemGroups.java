package net.statdefender.blight.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.statdefender.blight.Blight;
import net.statdefender.blight.block.ModBlocks;

public class ModItemGroups {

    public static final ItemGroup RUBY_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Blight.MOD_ID, "blight"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.blight"))
                    .icon(() -> new ItemStack(ModItems.RUBY)).entries((displayContext, entries) -> {
                        entries.add(ModItems.RUBY);
                        entries.add(ModBlocks.HOLLOWED_LOG);
                        entries.add(ModBlocks.HOLLOWED_SAPLING);
                        entries.add(ModBlocks.HOLLOWED_LEAVES);

                    }).build());

    public static void registerItemGroups() {
        Blight.LOGGER.info("Registering item groups for" + Blight.MOD_ID);
    }
}
