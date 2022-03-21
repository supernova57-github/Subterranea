package supernova57.subterranea.block;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.VoxelShape;
import supernova57.subterranea.block.state.EssenceFlowerVineStages;
import supernova57.subterranea.registry.SBTRBlockRegistry;

public class EssenceFlowerVinesEndBlock extends GrowingPlantHeadBlock {
	
	public static final BooleanProperty DIRECTLY_ATTACHED = BooleanProperty.create("directly_attached");
	public static final EnumProperty<EssenceFlowerVineStages> STAGE = EnumProperty.create("stage", EssenceFlowerVineStages.class);
	public static final BooleanProperty POLLINATED = BooleanProperty.create("pollinated");
	
	public static final VoxelShape SHAPE = Block.box(1.0D, 8.0D, 1.0D, 15.0D, 16.0D, 15.0D);

	public EssenceFlowerVinesEndBlock(BlockBehaviour.Properties properties) {
		super(properties, Direction.DOWN, SHAPE, false, 0.1D);
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(DIRECTLY_ATTACHED, false)
				.setValue(STAGE, EssenceFlowerVineStages.IMMATURE)
				.setValue(AGE, 0)
				.setValue(POLLINATED, false));
	}
	
	
	/**
	 * Get the "body" (non-growing) counterpart of this growing Essence Vine block.
	 * 
	 * @return The "body" (non-growing) counterpart of this growing Essence Vine block.
	 */
	@Override
	protected Block getBodyBlock() {
		return SBTRBlockRegistry.ESSENCE_FLOWER_PLANT.get();
	}
	
	
	/** 
	 * Add blockstates!
	 * 
	 * @param builder - the blockstate builder for this block.
	 */
	@Override
	protected void createBlockStateDefinition (StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(DIRECTLY_ATTACHED, STAGE, AGE, POLLINATED);
	}
	
	
	/**
	 *  Get the correct state to use for this block upon placement.
	 *  
	 *  @param context - carries information about the nature of the placement.
	 */
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		
		// If this block is attached to a solid block above...
		if (isAttached(context.getClickedPos().relative(Direction.UP), context.getLevel())) {
			// Update this block to use the "attached" state and model.
			return this.defaultBlockState().setValue(DIRECTLY_ATTACHED, true);
		}
		
		// Otherwise, just keep the block using its default state.
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
		
		// If the block below is the updated block, that block is now a vine block, and the block above is solid...
		if (facing == Direction.DOWN && isEssenceVine(facingState) 
				&& this.isAttached(currentPos.relative(Direction.UP), world)) {
			// Update this block to be its "body" (non-growing) counterpart, using the "attached" state and model.
			return this.getBodyBlock().defaultBlockState().setValue(HangingVinesBlock.DIRECTLY_ATTACHED, true);
		}
		
		// Otherwise, proceed as normal.
		return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}
	
	
	
	/**
	 * Update the block when selected by a random tick.
	 * 
	 * @param state - the current blockstate of this specific vine block.
	 * @param server - the server-side world this logic is being run on.
	 * @param pos - the position of this block.
	 * @param random - a random number generator.
	 */
	@Override
	public void randomTick(BlockState state, ServerLevel server, BlockPos pos, Random random) {
		
		// If this block is "mature"...
		if (state.getValue(STAGE) == EssenceFlowerVineStages.MATURE) {
			// Proceed as normal.
			super.randomTick(state, server, pos, random);
			
		} else { // Otherwise...
			
			// On 1 out of 10 ticks, advance the stage of this block by one.
			if (random.nextDouble() < 0.1D) {
				server.setBlockAndUpdate(pos, state.cycle(STAGE));
			}
		}
	}
	
	
	
	/**
	 * Determine whether this block can keep growing into another block.
	 * 
	 * @param state - the candidate block this block wants to grow into.
	 * @return Whether the candidate block can be grown into.
	 */
	@Override
	protected boolean canGrowInto(BlockState state) {
		// Just use the same criteria as Weeping and Twisting Vines.
		return NetherVines.isValidGrowthState(state);
	}
	
	
	/**
	 * Carry out a bonemealing of the vine.
	 * 
	 * @param server - the server-side world this logic is being run on.
	 * @param random - a random number generator.
	 * @param pos - the position of this block.
	 * @param state - the blockstate of this block.
	 */
	@Override
	public void performBonemeal(ServerLevel server, Random random, BlockPos pos, BlockState state) {
		// Proceed as normal, but ensure the new end block uses the "unattached" state and model.
		super.performBonemeal(server, random, pos, state.setValue(DIRECTLY_ATTACHED, false));
	}

	
	/**
	 * Determine how far this vine should grow when bonemealed.
	 * 
	 * @param random - a random number generator.
	 * @return The number of blocks the vine should grow.
	 */
	@Override
	protected int getBlocksToGrowWhenBonemealed(Random random) {
		// Just use the same logic as Weeping and Twisting Vines.
		return NetherVines.getBlocksToGrowWhenBonemealed(random);
	}
	
	
	
	
	/**
	 * Check whether an Essence Vine block is attached to a solid surface.
	 * 
	 * @param pos - The position of the block to be checked for solidity.
	 * @param world - current world (used to get the blockstate at the position).
	 * @return Whether the Essence Vine block is attached to a solid surface.
	 */
	private boolean isAttached (BlockPos blockpos, LevelReader world) {
		return (world.getBlockState(blockpos).isFaceSturdy(world, blockpos, Direction.DOWN) 
				&& !isEssenceVine(world.getBlockState(blockpos)));	
	}
	
	
	/**
	 * Check whether a blockstate is a type of Essence Vine.
	 * 
	 * @param state - the blockstate to check.
	 * @return Whether the given blockstate is a type of Essence Vine.
	 */
	private boolean isEssenceVine(BlockState state) {
		return state.is(this) || state.is(this.getBodyBlock());
	}

}
