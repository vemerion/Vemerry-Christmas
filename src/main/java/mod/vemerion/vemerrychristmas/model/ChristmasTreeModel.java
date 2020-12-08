package mod.vemerion.vemerrychristmas.model;

import com.google.common.collect.ImmutableList;

import mod.vemerion.vemerrychristmas.entity.ChristmasTreeEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

/**
 * Created using Tabula 8.0.0
 */
public class ChristmasTreeModel extends EntityModel<ChristmasTreeEntity> {
    public ModelPart tree1;
    public ModelPart tree2;
    public ModelPart tree3;
    public ModelPart tree4;

    public ChristmasTreeModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.tree1 = new ModelPart(this, 0, 0);
        this.tree1.setPivot(0.0F, 0.0F, 0.0F);
        this.tree1.addCuboid(-12.0F, -24.0F, 0.0F, 24.0F, 48.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.tree3 = new ModelPart(this, 0, 0);
        this.tree3.setPivot(0.0F, 0.0F, 0.0F);
        this.tree3.addCuboid(-12.0F, -24.0F, 0.0F, 24.0F, 48.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tree3, 0.0F, 0.7853981633974483F, 0.0F);
        this.tree2 = new ModelPart(this, 0, 0);
        this.tree2.setPivot(0.0F, 0.0F, 0.0F);
        this.tree2.addCuboid(-12.0F, -24.0F, 0.0F, 24.0F, 48.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tree2, 0.0F, 1.5707963267948966F, 0.0F);
        this.tree4 = new ModelPart(this, 0, 0);
        this.tree4.setPivot(0.0F, 0.0F, 0.0F);
        this.tree4.addCuboid(-12.0F, -24.0F, 0.0F, 24.0F, 48.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tree4, 0.0F, -0.7853981633974483F, 0.0F);
    }

    @Override
    public void render(MatrixStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.tree1, this.tree3, this.tree2, this.tree4).forEach((modelRenderer) -> { 
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

	@Override
	public void setAngles(ChristmasTreeEntity entity, float limbAngle, float limbDistance, float animationProgress,
			float headYaw, float headPitch) {
		
	}
}
