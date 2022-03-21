package supernova57.subterranea.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.VoxelShape;
import supernova57.subterranea.registry.SBTRBlockRegistry;

public class EssenceFlowerVinesBlock extends GrowingPlantBodyBlock {
	
	public static final BooleanProperty DIRECTLY_ATTACHED = BooleanProperty.create("directly_attached");
	public static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

	public EssenceFlowerVinesBlock(BlockBehaviour.Properties properties) {
		super(properties, Direction.DOWN, SHAPE, false);
		this.registerDefaultState(this.stateDefinition.any().setValue(DIRECTLY_ATTACHED, false));
	}
	
	
	
	/**
	 * Get the "end" (growing) counterpart of this non-growing Essence Vine block.
	 * 
	 * @return The "end" (growing) counterpart of this non-growing Essence Vine block.
	 */
	@Override
	protected GrowingPlantHeadBlock getHeadBlock() {
		return (GrowingPlantHeadBlock) SBTRBlockRegistry.ESSENCE_FLOWER.get();
	}
	
	
	
	/** 
	 * Add blockstates!
	 * 
	 * @param builder - the blockstate builder for this block.
	 */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(DIRECTLY_ATTACHED);
	}
	
	
	/**
	 *  Get the correct state to use for this block upon placement.
	 *  
	 *  @param context - carries information about the nature of the placement.
	 */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {

		/*
		 *  If the block would be attached to a solid block, use the "directly attached"
		 *  blockstate and model.
		 */
		if (this.isAttached(context.getClickedPos().relative(Direction.UP), context.getLevel())) {
			return this.defaultBlockState().setValue(DIRECTLY_ATTACHED, true);
		}
		
		// Otherwise, just use the default BlockState and model.
		return this.defaultBlockState();
	}
	
	
	
	/**
	 * Update the state of this block based upon the state of another block that has itself just been updated.
	 * 
	 * @param state - the current blockstate of this block.
	 * @param facing - the direction in which the other block is located.
	 * @param facingState - the state of the other block.
	 * @param world - the current world (used to get information about blockstates).
	 * @param currentPos - the position of this block.
	 * @param facingPos - the position of the other block.
	 * @return The new blockstate to be used.
	 */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, 
			BlockPos currentPos, BlockPos facingPos) {
		
		// If the updated neighbor is the block below (and that block is now a vine block)...
		if (facing == Direction.DOWN && isEssenceVine(facingState)) {
			// Update the block to be the end of the vine.
			return this.getHeadBlock().defaultBlockState()
				.setValue(HangingVinesEndBlock.DIRECTLY_ATTACHED, this.isAttached(currentPos.relative(Direction.UP), world));
		}
		
		// Otherwise, proceed as normal.
		return super.updateShape(state, facing, facingState, world, currentPos, facingPos);		
	}
	
	
	
	
	
	/**
	 * Check whether an Essence Vine block is attached to a solid surface.
	 * 
	 * @param pos - The position of the block to be checked for solidity.
	 * @param world - current world (used to get the blockstate at the position).
	 * @return Whether the Essence Vine block is attached to a solid surface.
	 */
	private boolean isAttached(BlockPos pos, LevelReader world) {
		return world.getBlockState(pos).isFaceSturdy(world, pos, Direction.DOWN) && !isEssenceVine(world.getBlockState(pos));		
	}
	

	/**
	 * Check whether a blockstate is a type of Essence Vine.
	 * 
	 * @param state - the blockstate to check.
	 * @return Whether the given blockstate is a type of Essence Vine.
	 */
	private boolean isEssenceVine(BlockState state) {
		return state.is(this) || state.is(this.getHeadBlock());
	}
	
	

}
