package mod.vemerion.vemerrychristmas;

import mod.vemerion.vemerrychristmas.renderer.ChristmasTreeRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class ClientModInit implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.INSTANCE.register(ModInit.CHRISTMAS_TREE_ENTITY, (dispatcher, context) -> {
			return new ChristmasTreeRenderer(dispatcher);
		});
	}
}
