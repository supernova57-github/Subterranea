package supernova57.subterranea.registry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import supernova57.subterranea.client.particle.SnowflakeParticle;
import supernova57.subterranea.main.Reference;
import supernova57.subterranea.main.Subterranea;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SBTRParticleTypeRegistry {

	public static final RegistryObject<SimpleParticleType> SNOWFLAKE = Subterranea.PARTICLES.register("snowflake", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> PHOSPHORUS_FLAME = Subterranea.PARTICLES.register("phosphorus_flame", () -> new SimpleParticleType(false));
	
	public static void register() {
		Subterranea.PARTICLES.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	@SubscribeEvent
	public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
		@SuppressWarnings("resource")
		ParticleEngine particleEngine = Minecraft.getInstance().particleEngine;
		
		particleEngine.register(SNOWFLAKE.get(), SnowflakeParticle.SnowflakeBuilder::new);
		particleEngine.register(PHOSPHORUS_FLAME.get(), FlameParticle.Provider::new);
	}
	
	public static void modifyParticles() {
		SBTRBlockRegistry.RED_PHOSPHORUS_TORCH.get().modifyParticlesAfterConstruction(PHOSPHORUS_FLAME.get());
		SBTRBlockRegistry.RED_PHOSPHORUS_WALL_TORCH.get().modifyParticlesAfterConstruction(PHOSPHORUS_FLAME.get());
	}
	


}
