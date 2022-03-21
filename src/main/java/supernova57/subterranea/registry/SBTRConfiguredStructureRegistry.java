package supernova57.subterranea.registry;

import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.PlainVillagePools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import supernova57.subterranea.main.Reference;

// Credit to TelepathicGrunt's StructureTutorialMod!
public class SBTRConfiguredStructureRegistry {

	public static final ConfiguredStructureFeature<?, ?> CONFIGURED_MOUNTAIN_FORTRESS = SBTRStructureRegistry.MOUNTAIN_FORTRESS.get().configured(new JigsawConfiguration(() -> PlainVillagePools.START, 0));
	
	public static void register() {
		Registry<ConfiguredStructureFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;
		Registry.register(registry, new ResourceLocation(Reference.MODID, "configured_mountain_fortress"), CONFIGURED_MOUNTAIN_FORTRESS);
	}
	
}
