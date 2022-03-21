package supernova57.subterranea.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;

public class SnowflakeParticle extends SimpleAnimatedParticle {

	private SnowflakeParticle(ClientLevel clientWorld, double xInitial, double yInitial, double zInitial, double xMotion, double yMotion, double zMotion, SpriteSet spriteList) {
		super(clientWorld, xInitial, yInitial, zInitial, spriteList, -0.001F);
		this.xd = xMotion;
		this.yd = yMotion;
		this.zd = zMotion;
		this.quadSize *= 0.7;
		this.lifetime = 20 + this.random.nextInt(20);
		this.friction = 0.75F;
		this.setSpriteFromAge(this.sprites);
	}
	
	public static class SnowflakeBuilder implements ParticleProvider<SimpleParticleType> {
		
		private final SpriteSet spriteList;
		
		public SnowflakeBuilder(SpriteSet spriteList) {
			this.spriteList = spriteList;
		}

		@Override
		public Particle createParticle(SimpleParticleType particle, ClientLevel clientWorld, double xInitial, double yInitial, double zInitial, double xMotion, double yMotion, double zMotion) {
			return new SnowflakeParticle(clientWorld, xInitial, yInitial, zInitial, xMotion, yMotion, zMotion, this.spriteList);
		}
	}

}
