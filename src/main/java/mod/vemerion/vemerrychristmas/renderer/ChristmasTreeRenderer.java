package mod.vemerion.vemerrychristmas.renderer;

import mod.vemerion.vemerrychristmas.ModInit;
import mod.vemerion.vemerrychristmas.entity.ChristmasTreeEntity;
import mod.vemerion.vemerrychristmas.model.ChristmasTreeModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class ChristmasTreeRenderer extends EntityRenderer<ChristmasTreeEntity> {

	private static final Identifier TEXTURE = new Identifier(ModInit.MODID, "textures/entities/christmas_tree.png");
	private static final ChristmasTreeModel MODEL = new ChristmasTreeModel();

	public ChristmasTreeRenderer(EntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(ChristmasTreeEntity entity, float yaw, float tickDelta, MatrixStack matrices,
			VertexConsumerProvider vertexConsumers, int light) {
		super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
		matrices.push();
		matrices.scale(-1, -1, 1);
		matrices.translate(0, -1.5, 0);
		VertexConsumer vertexConsumer = vertexConsumers.getBuffer(MODEL.getLayer(getTexture(entity)));
		MODEL.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
		matrices.pop();
	}

	@Override
	public Identifier getTexture(ChristmasTreeEntity entity) {
		return TEXTURE;
	}

}
