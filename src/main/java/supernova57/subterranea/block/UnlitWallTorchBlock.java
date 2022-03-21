package supernova57.subterranea.block;

import java.util.Random;

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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class UnlitWallTorchBlock extends WallTorchBlock {
		
	private final Block litTorch;

	public UnlitWallTorchBlock(Properties properties, Block litTorch) {
		super(properties, null);
		this.litTorch = litTorch;
	}
	
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		if (!player.getAbilities().mayBuild) {
			return InteractionResult.PASS;
	    } else {
	    	ItemStack heldItemStack = player.getItemInHand(handIn);
	    	Item heldItem = heldItemStack.getItem();
	    	
	    	if (heldItem instanceof FlintAndSteelItem || heldItem instanceof FireChargeItem) {
	    		if (heldItem instanceof FlintAndSteelItem) {
	    			worldIn.playSound(player, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, worldIn.random.nextFloat() * 0.4F + 0.8F);
	    			if (player != null) {
	    				heldItemStack.hurtAndBreak(1, player, playerIn -> playerIn.broadcastBreakEvent(handIn));
	    			}
	    		} else {
	    			worldIn.playSound(player, pos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1.0F, (worldIn.random.nextFloat() - worldIn.random.nextFloat()) * 0.2F + 1.0F);
	    			if (!player.isCreative()) {
	    				heldItemStack.setCount(heldItemStack.getCount() - 1);
	    			}
	    		}
	    		worldIn.setBlockAndUpdate(pos, litTorch.defaultBlockState().setValue(FACING, state.getValue(FACING)));
	    		return InteractionResult.sidedSuccess(worldIn.isClientSide);
	    	}
	    	return InteractionResult.PASS;
	    }
	}
	
	@Override
	public void animateTick(BlockState p_58128_, Level p_58129_, BlockPos p_58130_, Random p_58131_) {}
	
	public Block getLitTorch() {
		return this.litTorch;
	}
	
}
