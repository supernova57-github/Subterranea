package supernova57.subterranea.registry;

import javax.annotation.Nullable;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import supernova57.subterranea.entity.projectile.SnowflakeEntity;

public class SBTRDamageSources {

	public static final DamageSource FROSTBITE = new DamageSource("frostbite").bypassArmor().setMagic();
	
	public static DamageSource snowflake(SnowflakeEntity snowflake, @Nullable Entity entity) {
		return (new IndirectEntityDamageSource("snowflake", snowflake, entity)).setProjectile();
	}
	
}
