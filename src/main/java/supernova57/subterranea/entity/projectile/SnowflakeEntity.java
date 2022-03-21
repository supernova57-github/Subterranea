package supernova57.subterranea.entity.projectile;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import supernova57.subterranea.registry.SBTRBlockRegistry;
import supernova57.subterranea.registry.SBTRDamageSources;
import supernova57.subterranea.registry.SBTREffectRegistry;
import supernova57.subterranea.registry.SBTREntityTypeRegistry;
import supernova57.subterranea.registry.SBTRParticleTypeRegistry;
import supernova57.subterranea.util.MathUtils;

public class SnowflakeEntity extends Projectile {
		
	//Extinguishable blocks that are NOT candles! (Only candles have a "lit" blockstate that can be easily modified).
	private static final Set<Block> EXTINGUISHABLE_BLOCKS = ImmutableSet.of(
		Blocks.FIRE, 
		Blocks.SOUL_FIRE, 
		Blocks.CAMPFIRE, 
		Blocks.SOUL_CAMPFIRE,
		SBTRBlockRegistry.RED_PHOSPHORUS_LANTERN.get(),
		SBTRBlockRegistry.RED_PHOSPHORUS_TORCH.get(), 
		SBTRBlockRegistry.RED_PHOSPHORUS_WALL_TORCH.get()
	);

	
	//Client-side
	public SnowflakeEntity(EntityType<? extends SnowflakeEntity> entity, Level world) {
		super(entity, world);
	}
	
	//Server-side 
	public SnowflakeEntity(LivingEntity owner, Level world) {
		super(SBTREntityTypeRegistry.SNOWFLAKE.get(), world);
		this.setOwner(owner);
		this.setPos(owner.getX(), owner.getY() + 2.5D, owner.getZ());
	}

	@Override
	public void tick() {
		super.tick();
		if (!this.level.isClientSide()) {
			for(int i = 0; i < 3; i++) {
				((ServerLevel)this.level).sendParticles(SBTRParticleTypeRegistry.SNOWFLAKE.get(), this.getX() + this.random.nextDouble() - 0.5, this.getY() + 0.6 + this.random.nextDouble() - 1, this.getZ() + this.random.nextDouble() - 0.5, 1, 0.0D, 0.0D, 0.0D, 0.0D);
			}		
		}		

		this.onHit(ProjectileUtil.getHitResult(this, this::canHitEntity));	      
		
		this.setPos(this.getX() + this.getDeltaMovement().x(), this.getY() + this.getDeltaMovement().y(), this.getZ() + this.getDeltaMovement().z());
		this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.01D, 0.0D));

	}
	
	@Override
	protected void onHit(HitResult result) {
		super.onHit(result);
		
		boolean isInWater = (result.getType() == HitResult.Type.MISS && result instanceof BlockHitResult 
							&& (this.level.getBlockState(((BlockHitResult) result).getBlockPos())).getBlock().equals(Blocks.WATER));
		
		if (isInWater) this.onHitBlock((BlockHitResult) result);
			
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		super.onHitEntity(result);
		if (!this.level.isClientSide()) {
			
			Entity owner = this.getOwner();
			Entity hitEntity = result.getEntity();
			
			hitEntity.hurt(SBTRDamageSources.snowflake(this, owner), 4.0F);

			if (hitEntity instanceof LivingEntity) {
				if (!((hitEntity instanceof Player) && ((Player)hitEntity).getAbilities().invulnerable)) {
					((LivingEntity)hitEntity).addEffect(new MobEffectInstance(SBTREffectRegistry.FROSTBITE.get(), 150));
				}
			}

			if (!hitEntity.is(owner)) spreadSnowAndIce(new BlockPos(result.getLocation().x(), result.getLocation().y(), result.getLocation().z()));
			
		}
	}
	
	
	@Override
	protected void onHitBlock(BlockHitResult result) {
		super.onHitBlock(result);

		spreadSnowAndIce(result.getBlockPos());
	}
	
	@Override
	public boolean isOnFire() {
		return false;
	}
	
	public Packet<?> getAddEntityPacket() {
		return net.minecraftforge.network.NetworkHooks.getEntitySpawningPacket(this);
	}
	
	private void spreadSnowAndIce(BlockPos collisionPos) {
		Level world = this.level;

		for (int i = -7; i <= 7; i++) {
			for (int j = -7; j <= 7; j++) {
				for (int k = -1; k <= 1; k++) {
					BlockPos pos = new BlockPos(collisionPos.getX() + i, collisionPos.getY() + k, collisionPos.getZ() + j);
					BlockState state = world.getBlockState(pos);
					Block block = state.getBlock();
					double distanceFromImpact = Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2));
					double rand = world.random.nextDouble();
					
					if (rand < 7/(Math.pow(distanceFromImpact, 2))) { // && this.random.nextBoolean()
						if (!world.isClientSide()) {
							((ServerLevel)this.level).sendParticles(SBTRParticleTypeRegistry.SNOWFLAKE.get(), pos.getX() + this.random.nextDouble() - 0.5, pos.getY() + 0.6 + this.random.nextDouble(), pos.getZ() + this.random.nextDouble() - 0.5, 1, 0.0D, 0.0D, 0.0D, 0.0D);
						}
						
						if (rand < 5/(Math.pow(distanceFromImpact, 2))) {
							if (!world.isClientSide() && world.getBlockState(pos.relative(Direction.UP)).isAir() && block == Blocks.WATER && state.getValue(LiquidBlock.LEVEL) == 0) {
								world.setBlock(pos, Blocks.ICE.defaultBlockState(), 2);
							}
							else if (!world.isClientSide() && state.isFaceSturdy(world, pos, Direction.UP) && !(block instanceof IceBlock) && world.getBlockState(pos.relative(Direction.UP)).isAir()) {
								world.setBlock(pos.relative(Direction.UP), Blocks.SNOW.defaultBlockState(), 2);
							}
							else if (EXTINGUISHABLE_BLOCKS.contains(block)) {
								
					    		world.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, (world.random.nextFloat() - world.random.nextFloat()) * 0.2F + 1.0F);
								switch (block.getRegistryName().getPath()) {
									case "fire":
										world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
										break;
									case "soul_fire":
										world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
										break;
									case "campfire":
										world.setBlock(pos, state.setValue(CampfireBlock.LIT, false), 3);
										break;
									case "soul_campfire":
										world.setBlock(pos, state.setValue(CampfireBlock.LIT, false), 3);
										break;
									case "red_phosphorus_lantern":
										world.setBlock(pos, SBTRBlockRegistry.UNLIT_RED_PHOSPHORUS_LANTERN.get().defaultBlockState().setValue(LanternBlock.HANGING, state.getValue(LanternBlock.HANGING)).setValue(LanternBlock.WATERLOGGED, state.getValue(LanternBlock.WATERLOGGED)), 3);
										break;
									case "red_phosphorus_torch":
										world.setBlock(pos, SBTRBlockRegistry.UNLIT_RED_PHOSPHORUS_TORCH.get().defaultBlockState(), 3);
										break;
									case "red_phosphorus_wall_torch":
										world.setBlock(pos, SBTRBlockRegistry.UNLIT_RED_PHOSPHORUS_WALL_TORCH.get().defaultBlockState().setValue(WallTorchBlock.FACING, state.getValue(WallTorchBlock.FACING)), 3);
										break;
									default:
										break;
								}
							} else if (block instanceof AbstractCandleBlock) {
								world.setBlock(pos, block.defaultBlockState().setValue(AbstractCandleBlock.LIT, false), 3);
							}
						}
					}					
				}
			}	
		}
		this.kill();
	}
	

	@Override
	public boolean isPickable() {
		return true;
	}
	
	@Override
	public boolean hurt(DamageSource damageSource, float p_70097_2_) {
        if (!this.level.isClientSide) {
        	this.kill();
       		for (int i = 0; i < 100; i++) {
        		double randomSphericalX = MathUtils.sphericalToCartesian(this.random.nextDouble() * 1.5D, this.random.nextDouble() * 2.0D * Math.PI, this.random.nextDouble() * 2.0D * Math.PI, 'x');
        		double randomSphericalY = MathUtils.sphericalToCartesian(this.random.nextDouble() * 1.5D, this.random.nextDouble() * 2.0D * Math.PI, this.random.nextDouble() * 2.0D * Math.PI, 'y');
        		double randomSphericalZ = MathUtils.sphericalToCartesian(this.random.nextDouble() * 1.5D, this.random.nextDouble() * 2.0D * Math.PI, this.random.nextDouble() * 2.0D * Math.PI, 'z');
        		((ServerLevel)this.level).sendParticles(SBTRParticleTypeRegistry.SNOWFLAKE.get(), this.getX() + randomSphericalX, this.getY() + randomSphericalY, this.getZ() + randomSphericalZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
        	}
       	}
		return damageSource.isProjectile() ? true : super.hurt(damageSource, p_70097_2_);
	}

	@Override
	public void kill() {
		super.kill();
		this.playSound(SoundEvents.GLASS_BREAK, 1.0F, this.level.random.nextFloat() * 0.4F + 0.8F);
	}

	@Override
	protected void defineSynchedData() {}
	
	
}
