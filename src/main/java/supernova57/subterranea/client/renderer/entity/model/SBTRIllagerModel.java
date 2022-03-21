package supernova57.subterranea.client.renderer.entity.model;

import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.monster.AbstractIllager;

// Example for future - mobs are now removed.

public abstract class SBTRIllagerModel<T extends AbstractIllager> extends HierarchicalModel<T> implements ArmedModel, HeadedModel {

	public abstract ModelPart getBody();
	
	public abstract ModelPart getArms();
	
	public abstract ModelPart getRightArmCrossed();
	
	public abstract ModelPart getLeftArmCrossed();
	
	public abstract ModelPart getLeftLeg();
	
	public abstract ModelPart getRightLeg();
	
	public abstract ModelPart getRightArm();
	
	public abstract ModelPart getLeftArm();
	
	public abstract void setAllVisible(boolean visible);
	
	public abstract <M extends SBTRIllagerModel<T>> void copyModelPropertiesTo(M armorModel);
	
	public boolean getIsVisible(ModelPart part) {
		return part != null ? part.visible : false;
	}
	
	public void setVisible(ModelPart part, boolean visible) {
		if (part != null) part.visible = visible;
	}

}
