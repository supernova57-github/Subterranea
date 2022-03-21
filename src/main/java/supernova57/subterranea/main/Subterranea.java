package supernova57.subterranea.main;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;

import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import supernova57.subterranea.registry.SBTRBlockRegistry;
import supernova57.subterranea.registry.SBTRConfiguredStructureRegistry;
import supernova57.subterranea.registry.SBTREffectRegistry;
import supernova57.subterranea.registry.SBTREntityTypeRegistry;
import supernova57.subterranea.registry.SBTRItemRegistry;
import supernova57.subterranea.registry.SBTRParticleTypeRegistry;
import supernova57.subterranea.registry.SBTRStructureRegistry;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Reference.MODID)
public class Subterranea {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    
	private static Method GETCODEC_METHOD;
    
    //Registries!
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MODID);
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Reference.MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Reference.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Reference.MODID);
    public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, Reference.MODID);
    
    public Subterranea() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        
        // Register the applyDimensionalSpacing method to the Forge event bus.
        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, this::applyDimensionalSpacing);
        
        
        
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        
        SBTRBlockRegistry.register();
        SBTREffectRegistry.register();
        SBTREntityTypeRegistry.register();
        SBTRItemRegistry.register();
        SBTRParticleTypeRegistry.register();
        SBTRStructureRegistry.register();
    }

    private void setup(final FMLCommonSetupEvent event) {
    	
    	event.enqueueWork(() -> {
    		SBTREntityTypeRegistry.registerSpawns();
    		SBTRStructureRegistry.setup();
    		SBTRConfiguredStructureRegistry.register();
    	});
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // Do something that can only be done on the client
    	SBTRBlockRegistry.setRenderTypes();

    }
    
    
    
    //TelepathicGrunt's method! (modified) See his StructureTutorialMod for details!
    @SuppressWarnings({ "unchecked" })
	private void applyDimensionalSpacing(final WorldEvent.Load event) {	
    	
    	if (event.getWorld() instanceof ServerLevel) {
    		ServerLevel serverLevel = (ServerLevel)event.getWorld();
    		
    		Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(serverLevel.getChunkSource().getGenerator().getSettings().structureConfig());
    		HashMap<StructureFeature<?>, HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> STStructureToMultiMap = new HashMap<>();
    		StructureSettings worldStructureConfig = serverLevel.getChunkSource().getGenerator().getSettings();
            
    		ImmutableSet<ResourceKey<Biome>> mountainFortressBiomes = ImmutableSet.<ResourceKey<Biome>>builder()
    				.add(Biomes.MEADOW)
    				.build();
    		
    		mountainFortressBiomes.forEach(biomeKey -> associateBiomeToConfiguredStructure(STStructureToMultiMap, SBTRConfiguredStructureRegistry.CONFIGURED_MOUNTAIN_FORTRESS, biomeKey));
    		
    		if (serverLevel.dimension().equals(Level.OVERWORLD)) {
    			if (serverLevel.getChunkSource().getGenerator() instanceof FlatLevelSource) return;
        		tempMap.putIfAbsent(SBTRStructureRegistry.MOUNTAIN_FORTRESS.get(), StructureSettings.DEFAULTS.get(SBTRStructureRegistry.MOUNTAIN_FORTRESS.get()));
    		}
    		
    		
    		try {
    			if (GETCODEC_METHOD == null) GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_230347_a_");
    			ResourceLocation chunkGenLocation = Registry.CHUNK_GENERATOR.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(serverLevel.getChunkSource().getGenerator()));
    			if (chunkGenLocation != null && chunkGenLocation.getNamespace().equals("terraforged")) return;
    		} catch (Exception e) {
    			LOGGER.error("Unable to check if " + serverLevel.dimension().location() + " is using Terraforged's ChunkGenerator.");
    		}
    		
    		
    		ImmutableMap.Builder<StructureFeature<?>, ImmutableMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> tempStructureToMultiMap = ImmutableMap.builder();
    		worldStructureConfig.configuredStructures.entrySet().stream().filter(entry -> !STStructureToMultiMap.containsKey(entry.getKey())).forEach(tempStructureToMultiMap::put);

    		// Add our structures to the structure map/multimap and set the world to use this combined map/multimap.
    		STStructureToMultiMap.forEach((key, value) -> tempStructureToMultiMap.put(key, ImmutableMultimap.copyOf(value)));

    		worldStructureConfig.configuredStructures = tempStructureToMultiMap.build();

    		
    		//serverLevel.getChunkSource().getGenerator().getSettings().structureConfig = tempMap;
    		
    	}
    }
    
    // Another of TelepathicGrunt's methods! 
	private static void associateBiomeToConfiguredStructure(Map<StructureFeature<?>, HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> STStructureToMultiMap, ConfiguredStructureFeature<?, ?> configuredStructureFeature, ResourceKey<Biome> biomeRegistryKey) {
		STStructureToMultiMap.putIfAbsent(configuredStructureFeature.feature, HashMultimap.create());
		HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> configuredStructureToBiomeMultiMap = STStructureToMultiMap.get(configuredStructureFeature.feature);
		if(configuredStructureToBiomeMultiMap.containsValue(biomeRegistryKey)) {
			LOGGER.error("""
				Detected 2 ConfiguredStructureFeatures that share the same base StructureFeature trying to be added to same biome. One will be prevented from spawning.
				This issue happens with vanilla too and is why a Snowy Village and Plains Village cannot spawn in the same biome because they both use the Village base structure.
				The two conflicting ConfiguredStructures are: {}, {}
				The biome that is attempting to be shared: {}
				""",
				BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(configuredStructureFeature),
				BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(configuredStructureToBiomeMultiMap.entries().stream().filter(e -> e.getValue() == biomeRegistryKey).findFirst().get().getKey()),
				biomeRegistryKey
			);
		} else {
			configuredStructureToBiomeMultiMap.put(configuredStructureFeature, biomeRegistryKey);
		}
	}
    
    
    
    
    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    @SuppressWarnings("deprecation")
	private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(net.minecraftforge.event.server.ServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
    
}
