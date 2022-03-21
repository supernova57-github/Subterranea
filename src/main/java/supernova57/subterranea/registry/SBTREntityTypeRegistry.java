package supernova57.subterranea.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import supernova57.subterranea.client.renderer.entity.SBTRModelLayers;
import supernova57.subterranea.client.renderer.entity.SnowflakeRenderer;
import supernova57.subterranea.client.renderer.entity.model.SnowflakeModel;
import supernova57.subterranea.entity.projectile.SnowflakeEntity;
import supernova57.subterranea.main.Reference;
import supernova57.subterranea.main.Subterranea;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SBTREntityTypeRegistry {


	public static final RegistryObject<EntityType<SnowflakeEntity>> SNOWFLAKE = Subterranea.ENTITIES.register("snowflake", () -> EntityType.Builder.<SnowflakeEntity>of(SnowflakeEntity::new, MobCategory.MISC).clientTrackingRange(8).sized(0.5F, 0.5F).build("snowflake"));
	
	// REMOVE
	/*
	public static final EntityType<MountainIllagerEntity> MOUNTAIN_ILLAGER_ENTITYTYPE = EntityType.Builder.<MountainIllagerEntity>of(MountainIllagerEntity::new, MobCategory.MONSTER).clientTrackingRange(8).sized(0.6F, 1.95F).build("millager");
	public static final RegistryObject<EntityType<MountainIllagerEntity>> MOUNTAIN_ILLAGER = Subterranea.ENTITIES.register("millager", () -> MOUNTAIN_ILLAGER_ENTITYTYPE);
	
	public static final EntityType<AnthropologistIllagerEntity> ANTHROPOLOGIST_ILLAGER_ENTITYTYPE = EntityType.Builder.<AnthropologistIllagerEntity>of(AnthropologistIllagerEntity::new, MobCategory.MONSTER).clientTrackingRange(8).sized(0.6F, 1.95F).build("anthropillagist");
	public static final RegistryObject<EntityType<AnthropologistIllagerEntity>> ANTHROPOLOGIST_ILLAGER = Subterranea.ENTITIES.register("anthropillagist", () -> ANTHROPOLOGIST_ILLAGER_ENTITYTYPE);
	
	*/
	
	public static void register() {
		Subterranea.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(SNOWFLAKE.get(), context -> new SnowflakeRenderer(context, new SnowflakeModel(context.bakeLayer(SBTRModelLayers.SNOWFLAKE))));
		/*
		event.registerEntityRenderer(MOUNTAIN_ILLAGER.get(), context -> new MountainIllagerRenderer(context, new MountainIllagerModel<MountainIllagerEntity>(context.bakeLayer(SBTRModelLayers.MILLAGER), false), 0.5F));
		event.registerEntityRenderer(ANTHROPOLOGIST_ILLAGER.get(), context -> new AnthropologistIllagerRenderer(context, new AnthropologistIllagerModel<AnthropologistIllagerEntity>(context.bakeLayer(SBTRModelLayers.ANTHROPILLAGIST), false), 0.5F));
		*/
	}

	public static void registerSpawns() {
	//	SpawnPlacements.register(MOUNTAIN_ILLAGER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MountainIllagerEntity::mountainIllagerCanSpawn);
	}
	
	@SubscribeEvent
	public static void createAttributes(EntityAttributeCreationEvent event) {
	//	event.put(MOUNTAIN_ILLAGER.get(), MountainIllagerEntity.createAttributes().build());
	//	event.put(ANTHROPOLOGIST_ILLAGER.get(), AnthropologistIllagerEntity.createAttributes().build());
	}
	
	@SuppressWarnings({ "unused" })
	@SubscribeEvent
	public static void registerSpawnEggs(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> itemRegistry = event.getRegistry();
	//	itemRegistry.register(new SpawnEggItem(MOUNTAIN_ILLAGER_ENTITYTYPE, 0x707070, 0x204000, new Item.Properties().tab(CreativeModeTab.TAB_MISC)).setRegistryName(new ResourceLocation(Reference.MODID, "millager_spawn_egg")));
	//	itemRegistry.register(new SpawnEggItem(ANTHROPOLOGIST_ILLAGER_ENTITYTYPE, 0x707070, 0x443300, new Item.Properties().tab(CreativeModeTab.TAB_MISC)).setRegistryName(new ResourceLocation(Reference.MODID, "anthropillagist_spawn_egg")));

	}
	
	
	
}
