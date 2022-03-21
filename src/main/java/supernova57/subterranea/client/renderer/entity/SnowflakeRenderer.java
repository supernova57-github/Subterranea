package supernova57.subterranea.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import supernova57.subterranea.client.renderer.entity.model.SnowflakeModel;
import supernova57.subterranea.entity.projectile.SnowflakeEntity;
import supernova57.subterranea.main.Reference;

public class SnowflakeRenderer extends EntityRenderer<SnowflakeEntity> {
	
	private final SnowflakeModel model;

	public SnowflakeRenderer(EntityRendererProvider.Context context, SnowflakeModel model) {
		super(context);
		this.model = model;
	}
	
	@Override
	public void render(SnowflakeEntity snowflake, float theta, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int light) {
		// Theta indicates the entity's azimuthal rotation.
		super.render(snowflake, theta, partialTicks, matrixStack, buffer, light);
		
		float actualTicks = (float)snowflake.tickCount + partialTicks;
		VertexConsumer translucent = buffer.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(snowflake)));
		
		matrixStack.pushPose();
		matrixStack.translate(0.0D, 0.15F, 0.0D);
		this.model.setupAnim(snowflake, 0.0F, 0.0F, 0.0F, 0.0F, actualTicks);
		matrixStack.scale(1.5F, 1.5F, 1.5F);
		// Last 4 values are RGBA
		this.model.renderToBuffer(matrixStack, translucent, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.5F);
		matrixStack.popPose();		
	}


	@Override
	public ResourceLocation getTextureLocation(SnowflakeEntity snowflake) {
		return Reference.SNOWFLAKE_LOCATION;
	}

}
