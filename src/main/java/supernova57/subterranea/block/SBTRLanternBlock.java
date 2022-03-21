package supernova57.subterranea.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SBTRLanternBlock extends LanternBlock {
	
	protected static final VoxelShape SHAPE = Shapes.or(Block.box(3.0D, 0.0D, 3.0D, 13.0D, 1.0D, 13.0D), Block.box(4.0D, 1.0D, 4.0D, 12.0D, 12.0D, 12.0D), Block.box(3.0D, 10.0D, 3.0D, 13.0D, 11.0D, 13.0D));

	public SBTRLanternBlock(Properties properties) {
		super(properties);	
	}
	
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext collisionContext) {
		return SHAPE;
	}

}
