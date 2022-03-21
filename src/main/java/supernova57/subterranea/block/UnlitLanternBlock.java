package supernova57.subterranea.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FireChargeItem;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class UnlitLanternBlock extends LanternBlock {
	
	private final Block litLantern;
	
	protected static final VoxelShape SHAPE = Shapes.or(Block.box(3.0D, 0.0D, 3.0D, 13.0D, 1.0D, 13.0D), Block.box(4.0D, 1.0D, 4.0D, 12.0D, 12.0D, 12.0D), Block.box(3.0D, 10.0D, 3.0D, 13.0D, 11.0D, 13.0D));

	public UnlitLanternBlock(BlockBehaviour.Properties properties, Block litLantern) {
		super(properties);
		this.litLantern = litLantern;
	}
	
	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (!player.getAbilities().mayBuild) {
			return InteractionResult.PASS;
	    } else {
	    	ItemStack heldItemStack = player.getItemInHand(hand);
	    	Item heldItem = heldItemStack.getItem();
	    	
	    	if (heldItem instanceof FlintAndSteelItem || heldItem instanceof FireChargeItem) {
	    		if (!state.getValue(WATERLOGGED)) {
	    			if (heldItem instanceof FlintAndSteelItem) {
	    				world.playSound(player, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, world.random.nextFloat() * 0.4F + 0.8F);
	    				if (player != null) {
	    					// 1 is the amount by which to damage the item.
	    					heldItemStack.hurtAndBreak(1, player, user -> user.broadcastBreakEvent(hand));
	    				}
	    			} else {
	    				world.playSound(player, pos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1.0F, (world.random.nextFloat() - world.random.nextFloat()) * 0.2F + 1.0F);
	    				if (!player.isCreative()) {
	    					heldItemStack.setCount(heldItemStack.getCount() - 1);
	    				}
	    			}
	    			world.setBlockAndUpdate(pos, litLantern.defaultBlockState().setValue(HANGING, state.getValue(HANGING)).setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
	    			return InteractionResult.sidedSuccess(world.isClientSide);
	    		} else {
	    			player.swing(hand);
    				world.playSound(player, pos, SoundEvents.LANTERN_HIT, SoundSource.BLOCKS, 0.5F, world.random.nextFloat() * 0.4F + 0.8F);
	    			return InteractionResult.FAIL;
	    		}
	    	}
	    	return InteractionResult.PASS;
	    }
	}
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext collisionContext) {
		return SHAPE;	   
	}

	public Block getLitLantern() {
		return this.litLantern;
	}
	
}
