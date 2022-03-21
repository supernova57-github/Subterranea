package supernova57.subterranea.registry;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import supernova57.subterranea.block.EnchantedShaleTileBlock;
import supernova57.subterranea.block.EssenceFlowerVinesBlock;
import supernova57.subterranea.block.EssenceFlowerVinesEndBlock;
import supernova57.subterranea.block.HangingVinesBlock;
import supernova57.subterranea.block.HangingVinesEndBlock;
import supernova57.subterranea.block.SBTRLanternBlock;
import supernova57.subterranea.block.SBTRTorchBlock;
import supernova57.subterranea.block.SBTRWallTorchBlock;
import supernova57.subterranea.block.UnlitLanternBlock;
import supernova57.subterranea.block.UnlitTorchBlock;
import supernova57.subterranea.block.UnlitWallTorchBlock;
import supernova57.subterranea.main.Subterranea;

@SuppressWarnings("deprecation")
public class SBTRBlockRegistry {

	public static final RegistryObject<HangingVinesEndBlock> HANGING_VINES = Subterranea.BLOCKS.register("hanging_vines", () -> new HangingVinesEndBlock(BlockBehaviour.Properties.of(Material.PLANT).randomTicks().noCollission().instabreak().sound(SoundType.WET_GRASS)));
	public static final RegistryObject<HangingVinesBlock> HANGING_VINES_PLANT = Subterranea.BLOCKS.register("hanging_vines_plant", () -> new HangingVinesBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.WET_GRASS)));
	
	public static final RegistryObject<EssenceFlowerVinesEndBlock> ESSENCE_FLOWER = Subterranea.BLOCKS.register("essence_flower", () -> new EssenceFlowerVinesEndBlock(BlockBehaviour.Properties.of(Material.PLANT).randomTicks().noCollission().instabreak().sound(SoundType.AZALEA_LEAVES).lightLevel((state) -> {return state.getValue(EssenceFlowerVinesEndBlock.STAGE).getEmittedLightLevel();})));
	public static final RegistryObject<EssenceFlowerVinesBlock> ESSENCE_FLOWER_PLANT = Subterranea.BLOCKS.register("essence_flower_plant", () -> new EssenceFlowerVinesBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.AZALEA_LEAVES)));
	
	public static final RegistryObject<Block> LIMESTONE = Subterranea.BLOCKS.register("limestone", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_WHITE).requiresCorrectToolForDrops().strength(1.25F, 5.0F)));
	
	public static final RegistryObject<Block> SANDSTONE = Subterranea.BLOCKS.register("sandstone", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND).requiresCorrectToolForDrops().strength(1.0F, 4.0F)));
	
	public static final RegistryObject<Block> SHALE = Subterranea.BLOCKS.register("shale", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(1.0F, 4.0F).sound(SoundType.DEEPSLATE)));
	public static final RegistryObject<SlabBlock> SHALE_SLAB = Subterranea.BLOCKS.register("shale_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(SHALE.get())));
	public static final RegistryObject<StairBlock> SHALE_STAIRS = Subterranea.BLOCKS.register("shale_stairs", () -> new StairBlock(SHALE.get().defaultBlockState(), BlockBehaviour.Properties.copy(SHALE.get())));
	
	public static final RegistryObject<Block> SHALE_TILES = Subterranea.BLOCKS.register("shale_tiles", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(1.0F, 4.0F).sound(SoundType.DEEPSLATE)));
	public static final RegistryObject<SlabBlock> SHALE_TILE_SLAB = Subterranea.BLOCKS.register("shale_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(SHALE_TILES.get())));
	public static final RegistryObject<StairBlock> SHALE_TILE_STAIRS = Subterranea.BLOCKS.register("shale_tile_stairs", () -> new StairBlock(SHALE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.copy(SHALE_TILES.get())));
	
	public static final RegistryObject<Block> CHISELED_SHALE_TILES_BODY = Subterranea.BLOCKS.register("chiseled_shale_tiles_body", () -> new Block(BlockBehaviour.Properties.copy(SHALE_TILES.get())));
	public static final RegistryObject<Block> CHISELED_SHALE_TILES_SPIRIT = Subterranea.BLOCKS.register("chiseled_shale_tiles_spirit", () -> new Block(BlockBehaviour.Properties.copy(SHALE_TILES.get())));
	public static final RegistryObject<Block> CHISELED_SHALE_TILES_SOUL = Subterranea.BLOCKS.register("chiseled_shale_tiles_soul", () -> new Block(BlockBehaviour.Properties.copy(SHALE_TILES.get())));
	
	public static final RegistryObject<EnchantedShaleTileBlock> ENCHANTED_CHISELED_SHALE_TILES = Subterranea.BLOCKS.register("enchanted_chiseled_shale_tiles", () -> new EnchantedShaleTileBlock(BlockBehaviour.Properties.copy(SHALE_TILES.get()).lightLevel((state) -> {return state.getValue(EnchantedShaleTileBlock.LIGHT_LEVEL);})));
	
	public static final RegistryObject<Block> SLIGHTLY_MOSSY_SHALE_TILES = Subterranea.BLOCKS.register("slightly_mossy_shale_tiles", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(1.0F, 4.0F).sound(SoundType.DEEPSLATE)));
	public static final RegistryObject<SlabBlock> SLIGHTLY_MOSSY_SHALE_TILE_SLAB = Subterranea.BLOCKS.register("slightly_mossy_shale_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(SLIGHTLY_MOSSY_SHALE_TILES.get())));
	public static final RegistryObject<StairBlock> SLIGHTLY_MOSSY_SHALE_TILE_STAIRS = Subterranea.BLOCKS.register("slightly_mossy_shale_tile_stairs", () -> new StairBlock(SLIGHTLY_MOSSY_SHALE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.copy(SLIGHTLY_MOSSY_SHALE_TILES.get())));
	
	public static final RegistryObject<Block> MOSSY_SHALE_TILES = Subterranea.BLOCKS.register("mossy_shale_tiles", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GREEN).requiresCorrectToolForDrops().strength(1.0F, 4.0F).sound(SoundType.DEEPSLATE)));
	public static final RegistryObject<SlabBlock> MOSSY_SHALE_TILE_SLAB = Subterranea.BLOCKS.register("mossy_shale_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(MOSSY_SHALE_TILES.get())));
	public static final RegistryObject<StairBlock> MOSSY_SHALE_TILE_STAIRS = Subterranea.BLOCKS.register("mossy_shale_tile_stairs", () -> new StairBlock(MOSSY_SHALE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.copy(MOSSY_SHALE_TILES.get())));
	
	public static final RegistryObject<Block> CRACKED_SHALE_TILES = Subterranea.BLOCKS.register("cracked_shale_tiles", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(1.0F, 4.0F).sound(SoundType.DEEPSLATE)));
	public static final RegistryObject<SlabBlock> CRACKED_SHALE_TILE_SLAB = Subterranea.BLOCKS.register("cracked_shale_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(CRACKED_SHALE_TILES.get())));
	public static final RegistryObject<StairBlock> CRACKED_SHALE_TILE_STAIRS = Subterranea.BLOCKS.register("cracked_shale_tile_stairs", () -> new StairBlock(CRACKED_SHALE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.copy(CRACKED_SHALE_TILES.get())));
	
	public static final RegistryObject<Block> SHALE_BRICKS = Subterranea.BLOCKS.register("shale_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(1.0F, 4.0F).sound(SoundType.DEEPSLATE_BRICKS)));
	public static final RegistryObject<SlabBlock> SHALE_BRICK_SLAB = Subterranea.BLOCKS.register("shale_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(SHALE_BRICKS.get())));
	public static final RegistryObject<StairBlock> SHALE_BRICK_STAIRS = Subterranea.BLOCKS.register("shale_brick_stairs", () -> new StairBlock(SHALE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(SHALE_BRICKS.get())));
	public static final RegistryObject<WallBlock> SHALE_BRICK_WALL = Subterranea.BLOCKS.register("shale_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(SHALE_BRICKS.get())));
	
	public static final RegistryObject<Block> MOSSY_SHALE_BRICKS = Subterranea.BLOCKS.register("mossy_shale_bricks", () -> new Block(BlockBehaviour.Properties.copy(SHALE_BRICKS.get())));
	public static final RegistryObject<SlabBlock> MOSSY_SHALE_BRICK_SLAB = Subterranea.BLOCKS.register("mossy_shale_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(SHALE_BRICKS.get())));
	public static final RegistryObject<StairBlock> MOSSY_SHALE_BRICK_STAIRS = Subterranea.BLOCKS.register("mossy_shale_brick_stairs", () -> new StairBlock(SHALE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(SHALE_BRICKS.get())));
	public static final RegistryObject<WallBlock> MOSSY_SHALE_BRICK_WALL = Subterranea.BLOCKS.register("mossy_shale_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(SHALE_BRICKS.get())));
	
	public static final RegistryObject<Block> CRACKED_SHALE_BRICKS = Subterranea.BLOCKS.register("cracked_shale_bricks", () -> new Block(BlockBehaviour.Properties.copy(SHALE_BRICKS.get())));
	public static final RegistryObject<SlabBlock> CRACKED_SHALE_BRICK_SLAB = Subterranea.BLOCKS.register("cracked_shale_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(SHALE_BRICKS.get())));
	public static final RegistryObject<StairBlock> CRACKED_SHALE_BRICK_STAIRS = Subterranea.BLOCKS.register("cracked_shale_brick_stairs", () -> new StairBlock(SHALE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(SHALE_BRICKS.get())));
	public static final RegistryObject<WallBlock> CRACKED_SHALE_BRICK_WALL = Subterranea.BLOCKS.register("cracked_shale_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(SHALE_BRICKS.get())));

	
	public static final RegistryObject<SBTRLanternBlock> RED_PHOSPHORUS_LANTERN = Subterranea.BLOCKS.register("red_phosphorus_lantern", () -> new SBTRLanternBlock(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel((state) -> {return 13;})));
	public static final RegistryObject<UnlitLanternBlock> UNLIT_RED_PHOSPHORUS_LANTERN = Subterranea.BLOCKS.register("unlit_red_phosphorus_lantern", () -> new UnlitLanternBlock(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN), RED_PHOSPHORUS_LANTERN.get()));
			
	public static final RegistryObject<SBTRTorchBlock> RED_PHOSPHORUS_TORCH = Subterranea.BLOCKS.register("red_phosphorus_torch", () -> new SBTRTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().sound(SoundType.WOOD).lightLevel((state) -> {return 12;}), SBTRParticleTypeRegistry.PHOSPHORUS_FLAME));
	public static final RegistryObject<UnlitTorchBlock> UNLIT_RED_PHOSPHORUS_TORCH = Subterranea.BLOCKS.register("unlit_red_phosphorus_torch", () -> new UnlitTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().sound(SoundType.WOOD), RED_PHOSPHORUS_TORCH.get()));
	public static final RegistryObject<SBTRWallTorchBlock> RED_PHOSPHORUS_WALL_TORCH = Subterranea.BLOCKS.register("red_phosphorus_wall_torch", () -> new SBTRWallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().sound(SoundType.WOOD).lightLevel((state) -> {return 12;}), SBTRParticleTypeRegistry.PHOSPHORUS_FLAME));
	public static final RegistryObject<UnlitWallTorchBlock> UNLIT_RED_PHOSPHORUS_WALL_TORCH = Subterranea.BLOCKS.register("unlit_red_phosphorus_wall_torch", () -> new UnlitWallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().sound(SoundType.WOOD), RED_PHOSPHORUS_WALL_TORCH.get()));
	
	public static void register() {
		Subterranea.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	public static void setRenderTypes() {
		ItemBlockRenderTypes.setRenderLayer(HANGING_VINES.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(HANGING_VINES_PLANT.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(UNLIT_RED_PHOSPHORUS_LANTERN.get(), (renderType) -> {return renderType == RenderType.solid() || renderType == RenderType.translucent();});
		ItemBlockRenderTypes.setRenderLayer(RED_PHOSPHORUS_LANTERN.get(), (renderType) -> {return renderType == RenderType.solid() || renderType == RenderType.translucent() || renderType == RenderType.cutout();});
		ItemBlockRenderTypes.setRenderLayer(UNLIT_RED_PHOSPHORUS_TORCH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(UNLIT_RED_PHOSPHORUS_WALL_TORCH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(RED_PHOSPHORUS_TORCH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(RED_PHOSPHORUS_WALL_TORCH.get(), RenderType.cutout());
	}
	

}
