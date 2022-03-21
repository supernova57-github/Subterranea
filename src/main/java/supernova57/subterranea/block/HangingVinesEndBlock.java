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
import net.minecraft.world.phys.shapes.VoxelShape;
import supernova57.subterranea.registry.SBTRBlockRegistry;

public class HangingVinesEndBlock extends GrowingPlantHeadBlock {
	
	public static final BooleanProperty DIRECTLY_ATTACHED = BooleanProperty.create("directly_attached");
	public static final VoxelShape SHAPE = Block.box(1.0D, 8.0D, 1.0D, 15.0D, 16.0D, 15.0D);

	public HangingVinesEndBlock(BlockBehaviour.Properties properties) {
		super(properties, Direction.DOWN, SHAPE, false, 0.1D);
		this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(DIRECTLY_ATTACHED, false));
	}
	
	/** Makes block states real! */
	
	@Override
	protected void createBlockStateDefinition (StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE, DIRECTLY_ATTACHED);
	}
	
	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
		
		if (facing == Direction.DOWN && this.isAttached(currentPos.relative(Direction.UP), worldIn)) {
			return this.getBodyBlock().defaultBlockState().setValue(HangingVinesBlock.DIRECTLY_ATTACHED, true);
		}
		return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}
	
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		
		BlockState defaultstate = this.defaultBlockState();
		if (this.isAttached(context.getClickedPos().relative(Direction.UP), context.getLevel())) {
			return defaultstate.setValue(DIRECTLY_ATTACHED, true);
		}
		return defaultstate;
	}
	
	private boolean isAttached (BlockPos blockpos, LevelReader worldIn) {
		return (worldIn.getBlockState(blockpos).isFaceSturdy(worldIn, blockpos, Direction.DOWN) && !worldIn.getBlockState(blockpos).is(this.getBodyBlock()) && !worldIn.getBlockState(blockpos).is(this));		
	}

	@Override
	protected int getBlocksToGrowWhenBonemealed(Random rand) {
		return NetherVines.getBlocksToGrowWhenBonemealed(rand);
	}

	@Override
	protected boolean canGrowInto(BlockState state) {
		return NetherVines.isValidGrowthState(state);
	}
	
	@Override
	public void performBonemeal(ServerLevel server, Random random, BlockPos pos, BlockState state) {
		super.performBonemeal(server, random, pos, state.setValue(DIRECTLY_ATTACHED, false));
	}

	@Override
	protected Block getBodyBlock() {
		return SBTRBlockRegistry.HANGING_VINES_PLANT.get();
	}

}
