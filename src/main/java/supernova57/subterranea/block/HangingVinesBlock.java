package supernova57.subterranea.block;

import javax.annotation.Nullable;

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

public class HangingVinesBlock extends GrowingPlantBodyBlock {
	
	public static final BooleanProperty DIRECTLY_ATTACHED = BooleanProperty.create("directly_attached");
	public static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

	public HangingVinesBlock(BlockBehaviour.Properties properties) {
		super(properties, Direction.DOWN, SHAPE, false);
		this.registerDefaultState(this.stateDefinition.any().setValue(DIRECTLY_ATTACHED, false));
	}
	
	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		
		BlockState defaultstate = this.defaultBlockState();
		if (this.isAttached(context.getClickedPos().relative(Direction.UP), context.getLevel())) {
			return defaultstate.setValue(DIRECTLY_ATTACHED, true);
		}
		return defaultstate;
	}
	
	@Override
	public BlockState updateShape (BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		if (facing == Direction.DOWN && isNotHangingVines(facingState)) {
			return this.getHeadBlock().defaultBlockState().setValue(HangingVinesEndBlock.DIRECTLY_ATTACHED, this.isAttached(currentPos.relative(Direction.UP), world));	
		}
		return super.updateShape(state, facing, facingState, world, currentPos, facingPos);		
	}
	
	private boolean isAttached (BlockPos pos, LevelReader world) {
		return world.getBlockState(pos).isFaceSturdy(world, pos, Direction.DOWN) && isNotHangingVines(world.getBlockState(pos));		
	}
	
	private boolean isNotHangingVines(BlockState state) {
		return !state.is(this) && !state.is(this.getHeadBlock());
	}

	/** Makes block states real! */
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(DIRECTLY_ATTACHED);
	}
	
	@Override
	protected GrowingPlantHeadBlock getHeadBlock() {
		return (GrowingPlantHeadBlock) SBTRBlockRegistry.HANGING_VINES.get();
	}

}
