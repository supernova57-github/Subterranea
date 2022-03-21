package supernova57.subterranea.block;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import supernova57.subterranea.block.state.EnchantedShaleTileSymbols;

public class EnchantedShaleTileBlock extends Block {
	
	public static final EnumProperty<EnchantedShaleTileSymbols> SYMBOL = EnumProperty.create("symbol", EnchantedShaleTileSymbols.class);
	public static final IntegerProperty LIGHT_LEVEL = IntegerProperty.create("light_level", 0, 15);
	public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
	
	public EnchantedShaleTileBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(SYMBOL, EnchantedShaleTileSymbols.BODY).setValue(LIGHT_LEVEL, 0).setValue(ACTIVE, false));
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(SYMBOL, LIGHT_LEVEL, ACTIVE);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand interaction, BlockHitResult result) {
		
		// If the player is able to interact with blocks...
		if (player.mayBuild()) {
			
			// Play the "Stonecutter" sound.
			world.playSound(null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, 1.0F);
			
			// Update the block to have the next symbol, glow at maximum light, and display the "active" texture.
			world.setBlock(pos, state.cycle(SYMBOL).setValue(LIGHT_LEVEL, 15).setValue(ACTIVE, true), Block.UPDATE_ALL);
			
			// Schedule a tick to start decreasing the glow in 5 ticks (0.25 seconds).
			world.scheduleTick(pos, this, 5);
			
			// Tell the client to swing the player's hand ("SUCCESS") and inform the server the action was completed ("CONSUME").
			return InteractionResult.sidedSuccess(world.isClientSide());		
		}	
		
		// If the player cannot interact with blocks, default to normal behavior.
		return super.use(state, world, pos, player, interaction, result);
		
	}
	
	@Override
	public void tick(BlockState state, ServerLevel world, BlockPos pos, Random rand) {
		
		// Create and initialize a local variable to store the block's current light level.
		int lightLevel = state.getValue(LIGHT_LEVEL);
		
		// If the block is glowing...
		if (lightLevel > 0) {
			
			/* 
			 * If the light level is 1, update the block to use the "inactive" texture.
			 * 
			 * In all cases, update the block to have a light level one less than before.
			 */
			if (lightLevel == 1) {
				world.setBlock(pos, state.setValue(LIGHT_LEVEL, 0).setValue(ACTIVE, false), Block.UPDATE_ALL);
			} else {
				world.setBlock(pos, state.setValue(LIGHT_LEVEL, lightLevel - 1), Block.UPDATE_ALL);
			}
			
			// After each update, schedule a tick to run this code again after 1 tick (0.05 seconds).
			world.scheduleTick(pos, this, 1);
		}
	}

}
