package supernova57.subterranea.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import supernova57.subterranea.item.FrostScepterItem;
import supernova57.subterranea.main.Subterranea;

public class SBTRItemRegistry {
	
	public static final CreativeModeTab SUBTERRANEA_BLOCKS = new CreativeModeTab("subterranea_blocks") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(MOSSY_SHALE_BRICKS.get());
		}	
	};
	
	public static final CreativeModeTab SUBTERRANEA_ITEMS = new CreativeModeTab("subterranea_items") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(SCEPTER_OF_FROST.get());
		}
	};
	

	public static final RegistryObject<Item> HANGING_VINES = Subterranea.ITEMS.register("hanging_vines", () -> new BlockItem(SBTRBlockRegistry.HANGING_VINES.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	
	public static final RegistryObject<Item> ESSENCE_FLOWER = Subterranea.ITEMS.register("essence_flower", () -> new BlockItem(SBTRBlockRegistry.ESSENCE_FLOWER.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	
	public static final RegistryObject<Item> LIMESTONE = Subterranea.ITEMS.register("limestone", () -> new BlockItem(SBTRBlockRegistry.LIMESTONE.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	
	public static final RegistryObject<Item> SANDSTONE = Subterranea.ITEMS.register("sandstone", () -> new BlockItem(SBTRBlockRegistry.SANDSTONE.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	
	public static final RegistryObject<Item> SHALE = Subterranea.ITEMS.register("shale", () -> new BlockItem(SBTRBlockRegistry.SHALE.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	public static final RegistryObject<Item> SHALE_SLAB = Subterranea.ITEMS.register("shale_slab", () -> new BlockItem(SBTRBlockRegistry.SHALE_SLAB.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	public static final RegistryObject<Item> SHALE_STAIRS = Subterranea.ITEMS.register("shale_stairs", () -> new BlockItem(SBTRBlockRegistry.SHALE_STAIRS.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	
	public static final RegistryObject<Item> SHALE_TILES = Subterranea.ITEMS.register("shale_tiles", () -> new BlockItem(SBTRBlockRegistry.SHALE_TILES.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	public static final RegistryObject<Item> SHALE_TILE_SLAB = Subterranea.ITEMS.register("shale_tile_slab", () -> new BlockItem(SBTRBlockRegistry.SHALE_TILE_SLAB.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	public static final RegistryObject<Item> SHALE_TILE_STAIRS = Subterranea.ITEMS.register("shale_tile_stairs", () -> new BlockItem(SBTRBlockRegistry.SHALE_TILE_STAIRS.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	
	public static final RegistryObject<Item> CHISELED_SHALE_TILES_BODY = Subterranea.ITEMS.register("chiseled_shale_tiles_body", () -> new BlockItem(SBTRBlockRegistry.CHISELED_SHALE_TILES_BODY.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	public static final RegistryObject<Item> CHISELED_SHALE_TILES_SPIRIT = Subterranea.ITEMS.register("chiseled_shale_tiles_spirit", () -> new BlockItem(SBTRBlockRegistry.CHISELED_SHALE_TILES_SPIRIT.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	public static final RegistryObject<Item> CHISELED_SHALE_TILES_SOUL = Subterranea.ITEMS.register("chiseled_shale_tiles_soul", () -> new BlockItem(SBTRBlockRegistry.CHISELED_SHALE_TILES_SOUL.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	
	public static final RegistryObject<Item> ENCHANTED_CHISELED_SHALE_TILES = Subterranea.ITEMS.register("enchanted_chiseled_shale_tiles", () -> new BlockItem(SBTRBlockRegistry.ENCHANTED_CHISELED_SHALE_TILES.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	
	public static final RegistryObject<Item> SLIGHTLY_MOSSY_SHALE_TILES = Subterranea.ITEMS.register("slightly_mossy_shale_tiles", () -> new BlockItem(SBTRBlockRegistry.SLIGHTLY_MOSSY_SHALE_TILES.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	public static final RegistryObject<Item> SLIGHTLY_MOSSY_SHALE_TILE_SLAB = Subterranea.ITEMS.register("slightly_mossy_shale_tile_slab", () -> new BlockItem(SBTRBlockRegistry.SLIGHTLY_MOSSY_SHALE_TILE_SLAB.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	public static final RegistryObject<Item> SLIGHTLY_MOSSY_SHALE_TILE_STAIRS = Subterranea.ITEMS.register("slightly_mossy_shale_tile_stairs", () -> new BlockItem(SBTRBlockRegistry.SLIGHTLY_MOSSY_SHALE_TILE_STAIRS.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	
	public static final RegistryObject<Item> MOSSY_SHALE_TILES = Subterranea.ITEMS.register("mossy_shale_tiles", () -> new BlockItem(SBTRBlockRegistry.MOSSY_SHALE_TILES.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	public static final RegistryObject<Item> MOSSY_SHALE_TILE_SLAB = Subterranea.ITEMS.register("mossy_shale_tile_slab", () -> new BlockItem(SBTRBlockRegistry.MOSSY_SHALE_TILE_SLAB.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	public static final RegistryObject<Item> MOSSY_SHALE_TILE_STAIRS = Subterranea.ITEMS.register("mossy_shale_tile_stairs", () -> new BlockItem(SBTRBlockRegistry.MOSSY_SHALE_TILE_STAIRS.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	
	public static final RegistryObject<Item> CRACKED_SHALE_TILES = Subterranea.ITEMS.register("cracked_shale_tiles", () -> new BlockItem(SBTRBlockRegistry.CRACKED_SHALE_TILES.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	public static final RegistryObject<Item> CRACKED_SHALE_TILE_SLAB = Subterranea.ITEMS.register("cracked_shale_tile_slab", () -> new BlockItem(SBTRBlockRegistry.CRACKED_SHALE_TILE_SLAB.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	public static final RegistryObject<Item> CRACKED_SHALE_TILE_STAIRS = Subterranea.ITEMS.register("cracked_shale_tile_stairs", () -> new BlockItem(SBTRBlockRegistry.CRACKED_SHALE_TILE_STAIRS.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	
	public static final RegistryObject<Item> SHALE_BRICKS = Subterranea.ITEMS.register("shale_bricks", () -> new BlockItem(SBTRBlockRegistry.SHALE_BRICKS.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	public static final RegistryObject<Item> SHALE_BRICK_SLAB = Subterranea.ITEMS.register("shale_brick_slab", () -> new BlockItem(SBTRBlockRegistry.SHALE_BRICK_SLAB.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	public static final RegistryObject<Item> SHALE_BRICK_STAIRS = Subterranea.ITEMS.register("shale_brick_stairs", () -> new BlockItem(SBTRBlockRegistry.SHALE_BRICK_STAIRS.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	public static final RegistryObject<Item> SHALE_BRICK_WALL = Subterranea.ITEMS.register("shale_brick_wall", () -> new BlockItem(SBTRBlockRegistry.SHALE_BRICK_WALL.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	
	public static final RegistryObject<Item> MOSSY_SHALE_BRICKS = Subterranea.ITEMS.register("mossy_shale_bricks", () -> new BlockItem(SBTRBlockRegistry.MOSSY_SHALE_BRICKS.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	public static final RegistryObject<Item> MOSSY_SHALE_BRICK_SLAB = Subterranea.ITEMS.register("mossy_shale_brick_slab", () -> new BlockItem(SBTRBlockRegistry.MOSSY_SHALE_BRICK_SLAB.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	public static final RegistryObject<Item> MOSSY_SHALE_BRICK_STAIRS = Subterranea.ITEMS.register("mossy_shale_brick_stairs", () -> new BlockItem(SBTRBlockRegistry.MOSSY_SHALE_BRICK_STAIRS.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	public static final RegistryObject<Item> MOSSY_SHALE_BRICK_WALL = Subterranea.ITEMS.register("mossy_shale_brick_wall", () -> new BlockItem(SBTRBlockRegistry.MOSSY_SHALE_BRICK_WALL.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	
	public static final RegistryObject<Item> CRACKED_SHALE_BRICKS = Subterranea.ITEMS.register("cracked_shale_bricks", () -> new BlockItem(SBTRBlockRegistry.CRACKED_SHALE_BRICKS.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	public static final RegistryObject<Item> CRACKED_SHALE_BRICK_SLAB = Subterranea.ITEMS.register("cracked_shale_brick_slab", () -> new BlockItem(SBTRBlockRegistry.CRACKED_SHALE_BRICK_SLAB.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	public static final RegistryObject<Item> CRACKED_SHALE_BRICK_STAIRS = Subterranea.ITEMS.register("cracked_shale_brick_stairs", () -> new BlockItem(SBTRBlockRegistry.CRACKED_SHALE_BRICK_STAIRS.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	public static final RegistryObject<Item> CRACKED_SHALE_BRICK_WALL = Subterranea.ITEMS.register("cracked_shale_brick_wall", () -> new BlockItem(SBTRBlockRegistry.CRACKED_SHALE_BRICK_WALL.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	

	public static final RegistryObject<Item> RED_PHOSPHORUS_LANTERN = Subterranea.ITEMS.register("red_phosphorus_lantern", () -> new BlockItem(SBTRBlockRegistry.RED_PHOSPHORUS_LANTERN.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	public static final RegistryObject<Item> UNLIT_RED_PHOSPHORUS_LANTERN = Subterranea.ITEMS.register("unlit_red_phosphorus_lantern", () -> new BlockItem(SBTRBlockRegistry.UNLIT_RED_PHOSPHORUS_LANTERN.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));

	public static final RegistryObject<StandingAndWallBlockItem> UNLIT_RED_PHOSPHORUS_TORCH = Subterranea.ITEMS.register("unlit_red_phosphorus_torch", () -> new StandingAndWallBlockItem(SBTRBlockRegistry.UNLIT_RED_PHOSPHORUS_TORCH.get(), SBTRBlockRegistry.UNLIT_RED_PHOSPHORUS_WALL_TORCH.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	public static final RegistryObject<StandingAndWallBlockItem> RED_PHOSPHORUS_TORCH = Subterranea.ITEMS.register("red_phosphorus_torch", () -> new StandingAndWallBlockItem(SBTRBlockRegistry.RED_PHOSPHORUS_TORCH.get(), SBTRBlockRegistry.RED_PHOSPHORUS_WALL_TORCH.get(), new Item.Properties().tab(SUBTERRANEA_BLOCKS)));
	
	public static final RegistryObject<Item> SCEPTER_OF_FROST = Subterranea.ITEMS.register("scepter_of_frost", () -> new FrostScepterItem((new Item.Properties()).tab(SUBTERRANEA_ITEMS).stacksTo(1).durability(20)));
	

	public static void register () {
		Subterranea.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	
}
