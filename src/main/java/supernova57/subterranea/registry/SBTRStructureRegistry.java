package supernova57.subterranea.registry;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import supernova57.subterranea.main.Subterranea;
import supernova57.subterranea.world.gen.structure.MountainFortressFeature;


// Credit to TelepathicGrunt's StructureTutorialMod for getting this set up!
public class SBTRStructureRegistry {
	
	public static final RegistryObject<StructureFeature<JigsawConfiguration>> MOUNTAIN_FORTRESS = Subterranea.STRUCTURES.register("mountain_fortress", () -> new MountainFortressFeature(JigsawConfiguration.CODEC));
	
	public static void setup() {
		setupMapSpacingAndLand(MOUNTAIN_FORTRESS.get(), new StructureFeatureConfiguration(20, 12, 12535326));
	}
	
	public static void register() {
		Subterranea.STRUCTURES.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	private static <S extends StructureFeature<?>> void setupMapSpacingAndLand (S structure, StructureFeatureConfiguration config) {
		
		StructureFeature.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);
		
		StructureSettings.DEFAULTS = ImmutableMap
				.<StructureFeature<?>, StructureFeatureConfiguration>builder()
				.putAll(StructureSettings.DEFAULTS)
				.put(structure, config)
				.build();
		
		BuiltinRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
			Map<StructureFeature<?>, StructureFeatureConfiguration> map = settings.getValue().structureSettings().structureConfig();
			
			if (map instanceof ImmutableMap) {
				Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(map);
				tempMap.put(structure, config);
				settings.getValue().structureSettings().structureConfig = tempMap;
			} else {
				map.put(structure, config);
			}
			
		});
		
		
	}

}
