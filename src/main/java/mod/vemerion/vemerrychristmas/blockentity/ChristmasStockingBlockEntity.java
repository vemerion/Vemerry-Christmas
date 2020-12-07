package mod.vemerion.vemerrychristmas.blockentity;

import mod.vemerion.vemerrychristmas.ModInit;
import mod.vemerion.vemerrychristmas.block.ChristmasStockingBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;

public class ChristmasStockingBlockEntity extends BlockEntity {

	ItemStack inventory = ItemStack.EMPTY;

	public ChristmasStockingBlockEntity() {
		super(ModInit.CHRISTMAS_STOCKING_BLOCK_ENTITY);
	}

	public void use(PlayerEntity player, Hand hand) {
		if (!world.isClient) {
			if (inventory.isEmpty()) {
				ItemStack playerStack = player.getStackInHand(hand);
				if (!playerStack.isEmpty()) {
					player.setStackInHand(hand, ItemStack.EMPTY);
					inventory = playerStack.copy();
					world.setBlockState(getPos(), getCachedState().with(ChristmasStockingBlock.FULL, true), 3);
				}
			} else {
				drop();
			}
		}
	}

	public void drop() {
		if (!world.isClient) {
			ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), inventory);
			inventory = ItemStack.EMPTY;
			world.setBlockState(getPos(), getCachedState().with(ChristmasStockingBlock.FULL, false), 3);
		}
	}

	@Override
	public CompoundTag toTag(CompoundTag tag) {
		super.toTag(tag);

		tag.put("inventory", inventory.toTag(new CompoundTag()));

		return tag;
	}

	@Override
	public void fromTag(BlockState state, CompoundTag tag) {
		super.fromTag(state, tag);

		inventory = ItemStack.fromTag(tag.getCompound("inventory"));
	}

}
