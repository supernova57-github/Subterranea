package supernova57.subterranea.block;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraftforge.registries.RegistryObject;

public class SBTRTorchBlock extends TorchBlock {
	
	public SBTRTorchBlock(Properties properties, RegistryObject<SimpleParticleType> deferredParticle) {
		super(properties, ParticleTypes.SMOKE);
	}

	public void modifyParticlesAfterConstruction(ParticleOptions particle) {
		this.flameParticle = particle;
	}
	

}
