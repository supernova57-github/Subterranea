package supernova57.subterranea.event;

import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import supernova57.subterranea.registry.SBTRParticleTypeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SBTRCommonModEventHandler {

	@SubscribeEvent
	public static void particleRegistryTasks(final RegistryEvent.Register<ParticleType<?>> event) {
		SBTRParticleTypeRegistry.modifyParticles();
	}
	
}
