package mod.vemerion.vemerrychristmas.model;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

/**
 * Created using Tabula 8.0.0
 */
public class SantaHatModel<T extends LivingEntity> extends BipedEntityModel<T> {
	public ModelPart hat1;
	public ModelPart hat2;
	public ModelPart hat3;
	public ModelPart hat4;
	public ModelPart hat5;
	public ModelPart hat6;

	public SantaHatModel() {
		super(0.5f);
		this.textureWidth = 64;
		this.textureHeight = 128;
		this.hat5 = new ModelPart(this, 48, 101);
		this.hat5.setPivot(5.0F, 0.0F, 0.0F);
		this.hat5.addCuboid(0.0F, -2.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(hat5, 0.0F, 0.0F, 0.46914448828868976F);
		this.hat2 = new ModelPart(this, 0, 105);
		this.hat2.setPivot(0.0F, -3.0F, 0.0F);
		this.hat2.addCuboid(-4.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, 0.0F, 0.0F);
		this.hat4 = new ModelPart(this, 24, 99);
		this.hat4.setPivot(0.0F, -1.0F, 0.0F);
		this.hat4.addCuboid(-2.0F, -2.0F, -2.0F, 8.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(hat4, 0.0F, 0.0F, 0.46914448828868976F);
		this.hat3 = new ModelPart(this, 0, 97);
		this.hat3.setPivot(0.0F, -2.0F, 0.0F);
		this.hat3.addCuboid(-3.0F, -2.0F, -3.0F, 6.0F, 2.0F, 6.0F, 0.0F, 0.0F, 0.0F);
		this.hat1 = new ModelPart(this, 0, 115);
		this.hat1.setPivot(0.0F, -6.0F, 0.0F);
		this.hat1.addCuboid(-5.0F, -3.0F, -5.0F, 10.0F, 3.0F, 10.0F, 0.0F, 0.0F, 0.0F);
		this.hat6 = new ModelPart(this, 40, 122);
		this.hat6.setPivot(3.0F, -1.0F, 0.0F);
		this.hat6.addCuboid(0.0F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(hat6, 0.0F, 0.0F, 0.4241149949188166F);
		this.hat4.addChild(this.hat5);
		this.hat1.addChild(this.hat2);
		this.hat3.addChild(this.hat4);
		this.hat2.addChild(this.hat3);
		this.hat5.addChild(this.hat6);
		this.head.addChild(hat1);
	}

	@Override
	public void render(MatrixStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		ImmutableList.of(head).forEach((modelRenderer) -> {
			modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		});
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.pitch = x;
		modelRenderer.yaw = y;
		modelRenderer.roll = z;
	}
}
