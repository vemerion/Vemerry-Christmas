package mod.vemerion.vemerrychristmas.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import mod.vemerion.vemerrychristmas.ModInit;
import mod.vemerion.vemerrychristmas.model.SantaHatModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

@Mixin(ArmorFeatureRenderer.class)
public abstract class ArmorFeatureRendererMixin<T extends LivingEntity, M extends BipedEntityModel<T>, A extends BipedEntityModel<T>> extends FeatureRenderer<T, M> {

	public ArmorFeatureRendererMixin(FeatureRendererContext<T, M> context) {
		super(context);
	}

	private static final SantaHatModel<?> SANTA_HAT_MODEL = new SantaHatModel<>();
	private static final Identifier TEXTURE = new Identifier(ModInit.MODID, "textures/armors/santa_hat.png");

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Inject(method = "renderArmor(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/entity/EquipmentSlot;ILnet/minecraft/client/render/entity/model/BipedEntityModel;)V", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
	private void renderArmor(MatrixStack matrices, VertexConsumerProvider vertexConsumers, LivingEntity livingEntity,
			EquipmentSlot equipmentSlot, int i, BipedEntityModel<?> bipedEntityModel, CallbackInfo ci) {
		ItemStack itemStack = livingEntity.getEquippedStack(equipmentSlot);
		if (itemStack.getItem() == ModInit.SANTA_HAT_ITEM) {
			SANTA_HAT_MODEL.head.copyPositionAndRotation(bipedEntityModel.head);
			SANTA_HAT_MODEL.sneaking = bipedEntityModel.sneaking;
			SANTA_HAT_MODEL.child = bipedEntityModel.child;
			SANTA_HAT_MODEL.riding = bipedEntityModel.riding;
			((BipedEntityModel) getContextModel()).setAttributes(SANTA_HAT_MODEL);
			VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers,
					RenderLayer.getArmorCutoutNoCull(TEXTURE), false, false);
			SANTA_HAT_MODEL.render(matrices, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1.0F);
			ci.cancel();
		}
	}

}
