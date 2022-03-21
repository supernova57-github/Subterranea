package supernova57.subterranea.client.renderer.entity.model;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import supernova57.subterranea.entity.projectile.SnowflakeEntity;
import supernova57.subterranea.main.Reference;

public class SnowflakeModel extends HierarchicalModel<SnowflakeEntity> {
	
	private final ModelPart modelRoot;
	private final ModelPart crystal;
	
	private static final String CRYSTAL = "crystal";
	
	public SnowflakeModel(ModelPart modelRoot) {
		this.modelRoot = modelRoot;
		this.crystal = this.modelRoot.getChild(CRYSTAL);
	}
	
	public static LayerDefinition createMainLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();
		root.addOrReplaceChild(CRYSTAL, CubeListBuilder.create()
				.texOffs(0, 0)
				.addBox(-2.5F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F)
				.addBox(-0.5F, -2.5F, -0.5F, 1.0F, 5.0F, 1.0F)
				.addBox(-0.5F, -0.5F, -2.5F, 1.0F, 1.0F, 5.0F), PartPose.ZERO);

		return LayerDefinition.create(mesh, 16, 16);
	}

	public void setupAnim(SnowflakeEntity snowflake, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float time) {
		this.crystal.xRot = 10 * time * (float)Reference.ONE_DEGREE_IN_RADIANS;
		this.crystal.zRot = 20 * time * (float)Reference.ONE_DEGREE_IN_RADIANS;
	}
	
	public ModelPart root() {
		return this.modelRoot;
	}

}
