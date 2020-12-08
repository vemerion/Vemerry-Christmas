package mod.vemerion.vemerrychristmas.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import mod.vemerion.vemerrychristmas.ModInit;
import mod.vemerion.vemerrychristmas.entity.ChristmasTreeEntity;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.math.Vec3d;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin {

	@Shadow
	private ClientWorld world;

	@Inject(method = "onEntitySpawn(Lnet/minecraft/network/packet/s2c/play/EntitySpawnS2CPacket;)V", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/network/packet/s2c/play/EntitySpawnS2CPacket;getEntityTypeId()Lnet/minecraft/entity/EntityType;"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
	private void onEntitySpawn(EntitySpawnS2CPacket packet, CallbackInfo ci, double x, double y, double z,
			EntityType<?> type) {
		Entity entity = null;
		if (type == ModInit.CHRISTMAS_TREE_ENTITY) {
			entity = new ChristmasTreeEntity(ModInit.CHRISTMAS_TREE_ENTITY, world);
		}

		if (entity != null) {
			int entityId = packet.getId();
			entity.setVelocity(Vec3d.ZERO);
			entity.updatePosition(x, y, z);
			entity.updateTrackedPosition(x, y, z);
			entity.pitch = packet.getPitch() * 360 / 256.0f;
			entity.yaw = packet.getYaw() * 360 / 256.0f;
			entity.setEntityId(entityId);
			entity.setUuid(packet.getUuid());
			world.addEntity(entityId, entity);
			ci.cancel();
		}
	}

}