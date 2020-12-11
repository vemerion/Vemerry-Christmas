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

public class ChristmasPresentBlockEntity extends BlockEntity {

	ItemStack inventory = ItemStack.EMPTY;

	public ChristmasPresentBlockEntity() {
		super(ModInit.CHRISTMAS_PRESENT_BLOCK_ENTITY);
	}

	public void fill(PlayerEntity player, Hand hand) {
		if (!world.isClient) {
			if (inventory.isEmpty()) {
				ItemStack playerStack = player.getStackInHand(hand);
				if (!playerStack.isEmpty()) {
					player.setStackInHand(hand, ItemStack.EMPTY);
					inventory = playerStack.copy();
					if (world.getBlockState(getPos()).contains(ChristmasStockingBlock.FULL))
						world.setBlockState(getPos(), getCachedState().with(ChristmasStockingBlock.FULL, true), 3);
				}
			}
		}
	}

	public void drop() {
		if (!world.isClient) {
			ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), inventory);
			inventory = ItemStack.EMPTY;
			if (world.getBlockState(getPos()).contains(ChristmasStockingBlock.FULL))
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
