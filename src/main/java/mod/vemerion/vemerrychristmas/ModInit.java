package mod.vemerion.vemerrychristmas;

import mod.vemerion.vemerrychristmas.block.ChristmasStockingBlock;
import mod.vemerion.vemerrychristmas.blockentity.ChristmasStockingBlockEntity;
import mod.vemerion.vemerrychristmas.entity.ChristmasTreeEntity;
import mod.vemerion.vemerrychristmas.item.ChristmasTreeItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModInit implements ModInitializer {

	public static final String MODID = "vemerry-christmas";

	public static final Block CHRISTMAS_STOCKING_BLOCK = Registry.register(Registry.BLOCK,
			new Identifier(MODID, "christmas_stocking_block"), new ChristmasStockingBlock(FabricBlockSettings
					.of(Material.SUPPORTED).noCollision().breakInstantly().sounds(BlockSoundGroup.WOOL)));

	public static final Block CHRISTMAS_TREE_LIGHT_BLOCK = Registry.register(Registry.BLOCK,
			new Identifier(MODID, "christmas_tree_light_block"), new Block(
					AbstractBlock.Settings.of(Material.SUPPORTED).noCollision().breakInstantly().luminance((state) -> {
						return 14;
					})));

	public static final Item CHRISTMAS_STOCKING_ITEM = Registry.register(Registry.ITEM,
			new Identifier(MODID, "christmas_stocking_item"),
			new BlockItem(CHRISTMAS_STOCKING_BLOCK, new Item.Settings().group(ItemGroup.SEARCH)));

	public static final Item CHRISTMAS_TREE_ITEM = Registry.register(Registry.ITEM,
			new Identifier(MODID, "christmas_tree_item"),
			new ChristmasTreeItem(new Item.Settings().group(ItemGroup.SEARCH)));

	public static final BlockEntityType<ChristmasStockingBlockEntity> CHRISTMAS_STOCKING_BLOCK_ENTITY = Registry
			.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MODID, "christmas_stocking_block_entity"),
					BlockEntityType.Builder.create(ChristmasStockingBlockEntity::new, CHRISTMAS_STOCKING_BLOCK)
							.build(null));

	public static final EntityType<ChristmasTreeEntity> CHRISTMAS_TREE_ENTITY = Registry.register(Registry.ENTITY_TYPE,
			new Identifier(MODID, "christmas_tree_entity"),
			FabricEntityTypeBuilder.create(SpawnGroup.MISC, ChristmasTreeEntity::new)
					.dimensions(EntityDimensions.fixed(1.2f, 2.85f)).build());

	@Override
	public void onInitialize() {
	}

}
