package mod.vemerion.vemerrychristmas.item;

import mod.vemerion.vemerrychristmas.ModInit;
import mod.vemerion.vemerrychristmas.entity.ChristmasTreeEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ChristmasTreeItem extends Item {

	public ChristmasTreeItem(Settings settings) {
		super(settings);
	}

	// Based on ArmorStandItem.useOnBlock()
	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		Direction direction = context.getSide();
		if (direction == Direction.DOWN) {
			return ActionResult.FAIL;
		} else {
			World world = context.getWorld();
			ItemPlacementContext itemPlacementContext = new ItemPlacementContext(context);
			BlockPos blockPos = itemPlacementContext.getBlockPos();
			ItemStack stack = context.getStack();
			Vec3d pos = Vec3d.ofBottomCenter(blockPos);
			PlayerEntity player = context.getPlayer();
			Box box = ModInit.CHRISTMAS_TREE_ENTITY.getDimensions().method_30231(pos.getX(), pos.getY(), pos.getZ());
			if (world.isSpaceEmpty(null, box, e -> true)) {
				if (!world.isClient) {
					ServerWorld serverWorld = (ServerWorld) world;
					ChristmasTreeEntity tree = ModInit.CHRISTMAS_TREE_ENTITY.create(serverWorld, null, null, player,
							blockPos, SpawnReason.SPAWN_EGG, true, true);
					world.spawnEntity(tree);
				}
				if (!player.abilities.creativeMode)
					stack.decrement(1);
				return ActionResult.success(world.isClient);
			} else {
				return ActionResult.FAIL;
			}
		}
	}

}
