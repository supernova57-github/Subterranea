package supernova57.subterranea.main;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class Reference {
	
	// Mod info
	
	public static final String MODID = "subterranea";
	
	
	// Useful constants
	
	public static final double ONE_DEGREE_IN_RADIANS = Math.PI / 180.0D;
	
	
	// Resource Locations
	
	public static final ResourceLocation SNOWFLAKE_LOCATION = new ResourceLocation(MODID, "textures/entity/snowflake.png");
	
	
	// Modified Structure Codec (can accept sizes up to 15 instead of just 7). ONLY IF A STRUCTURE HAS DELAYED RECURSION!!!
	
	public static final Codec<JigsawConfiguration> BIG_STRUCTURE_CODEC = RecordCodecBuilder.create(configInstance -> configInstance
		.group(
			StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(JigsawConfiguration::startPool),
			Codec.intRange(0, 15).fieldOf("size").forGetter(JigsawConfiguration::maxDepth)
		)
		.apply(configInstance, JigsawConfiguration::new
	));
	
}
