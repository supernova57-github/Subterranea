package supernova57.subterranea.event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import supernova57.subterranea.registry.SBTREffectRegistry;

@Mod.EventBusSubscriber()
public class SBTRCommonEventHandler {

	@SubscribeEvent
	public static void applyEffects(LivingUpdateEvent event) {
		LivingEntity entity = event.getEntityLiving();
		
		if (entity != null && entity.getEffect(SBTREffectRegistry.FROSTBITE.get()) != null && entity.canFreeze()) {
			entity.setTicksFrozen(200);	
		}
	}
	
	// Borrowed from TelepathicGrunt's StructureTutorialMod!
	@SuppressWarnings("unused")
	@SubscribeEvent
	public static void registerOverworldBiomeSpawns(BiomeLoadingEvent event) {
		MobSpawnSettingsBuilder spawns = event.getSpawns();
		//builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(SBTREntityTypeRegistry.MOUNTAIN_ILLAGER.get(), 6, 1, 3));
	}
	
	
}
