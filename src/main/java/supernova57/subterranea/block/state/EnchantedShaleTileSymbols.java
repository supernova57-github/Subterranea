package supernova57.subterranea.block.state;

import net.minecraft.util.StringRepresentable;

public enum EnchantedShaleTileSymbols implements StringRepresentable {
	
	BODY("body"),
	SPIRIT("spirit"),
	SOUL("soul");

	private final String symbolName;
	
	private EnchantedShaleTileSymbols(String symbolname) {
		this.symbolName = symbolname;
	}
	
	@Override
	public String getSerializedName() {
		return this.symbolName;
	}

}
