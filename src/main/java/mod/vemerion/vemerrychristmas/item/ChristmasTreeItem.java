package mod.vemerion.vemerrychristmas.item;

import mod.vemerion.vemerrychristmas.ModInit;
import mod.vemerion.vemerrychristmas.entity.ChristmasTreeEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ChristmasTreeItem extends Item {

	public ChristmasTreeItem(Settings settings) {
		super(settings);
	}
	
	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		World world = context.getWorld();
		if (world.isClient) {
			return ActionResult.SUCCESS;
		} else {
			Vec3d pos = context.getHitPos();
			ChristmasTreeEntity tree = new ChristmasTreeEntity(ModInit.CHRISTMAS_TREE_ENTITY, world);
			tree.updatePosition(pos.x, pos.y, pos.z);
			if (world.spawnEntity(tree) && !context.getPlayer().abilities.creativeMode) {
				context.getStack().decrement(1);
			}
			
			return ActionResult.CONSUME;
		}
	}

}
