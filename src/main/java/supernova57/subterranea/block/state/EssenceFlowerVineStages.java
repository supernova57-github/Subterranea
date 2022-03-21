package supernova57.subterranea.block.state;

import net.minecraft.util.StringRepresentable;

public enum EssenceFlowerVineStages implements StringRepresentable {
	
	IMMATURE("immature", 0),
	SMALL_BUD("small_bud", 4),
	LARGE_BUD("large_bud", 8),
	FLOWER("flower", 13),
	SHRIVELED_FLOWER("shriveled_flower", 6),
	IMMATURE_CAPSULE("immature_capsule", 4),
	MATURE_CAPSULE("mature_capsule", 4),
	MATURE("mature", 0);

	private final String stageName;
	private final int emittedLightLevel;
	
	private EssenceFlowerVineStages(String stageName, int emittedLightLevel) {
		this.stageName = stageName;
		this.emittedLightLevel = emittedLightLevel;
	}
	
	@Override
	public String getSerializedName() {
		return this.stageName;
	}
	
	public int getEmittedLightLevel() {
		return this.emittedLightLevel;
	}

}
