package supernova57.subterranea.util;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fml.DistExecutor;

public class ClientSafeRunnableUtils {

	// Currently unused.
	public static DistExecutor.SafeRunnable displayItemActivation(ItemStack stack) {
		
		return new DistExecutor.SafeRunnable() {

			private static final long serialVersionUID = -7983720681647162443L;

			@SuppressWarnings("resource")
			@Override
			public void run() {
				Minecraft.getInstance().gameRenderer.displayItemActivation(stack);
			}
		};
	}
	
	// Currently unused (was implemented for a removed feature). May be useful for the future.
	public static DistExecutor.SafeRunnable addBlockHitEffects(BlockHitResult result) {
		return new DistExecutor.SafeRunnable() {

			private static final long serialVersionUID = -6106665191353188277L;

			@SuppressWarnings("resource")
			@Override
			public void run() {
				Minecraft.getInstance().particleEngine.addBlockHitEffects(result.getBlockPos(), result);
			}
		};
	}
	

}
