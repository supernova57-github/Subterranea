package supernova57.subterranea.registry;

import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import supernova57.subterranea.main.Subterranea;
import supernova57.subterranea.world.gen.structure.MountainFortressFeature;


public class SBTRStructureRegistry {
	
	public static final RegistryObject<StructureFeature<JigsawConfiguration>> MOUNTAIN_FORTRESS = Subterranea.STRUCTURES.register("mountain_fortress", () -> new MountainFortressFeature(JigsawConfiguration.CODEC));
	
	public static void register() {
		Subterranea.STRUCTURES.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
}
