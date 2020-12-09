package mod.vemerion.vemerrychristmas.entity;

import mod.vemerion.vemerrychristmas.ModInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ChristmasTreeEntity extends Entity {

	public ChristmasTreeEntity(EntityType<? extends ChristmasTreeEntity> type, World world) {
		super(type, world);
	}

	@Override
	protected void initDataTracker() {

	}

	@Override
	protected void readCustomDataFromTag(CompoundTag tag) {

	}

	@Override
	protected void writeCustomDataToTag(CompoundTag tag) {

	}

	@Override
	public Packet<?> createSpawnPacket() {
		return new EntitySpawnS2CPacket(this);
	}

	@Override
	public boolean damage(DamageSource source, float amount) {
		if (isInvulnerableTo(source)) {
			return false;
		} else {
			if (world.isClient)
				return true;

			Block.dropStack(this.world, this.getBlockPos(), new ItemStack(ModInit.CHRISTMAS_TREE_ITEM));
			remove();
			BlockState state = world.getBlockState(getBlockPos());
			if (state.getBlock() == ModInit.CHRISTMAS_TREE_LIGHT_BLOCK)
				world.setBlockState(getBlockPos(), Blocks.AIR.getDefaultState());
			return true;
		}
	}

	@Override
	public PistonBehavior getPistonBehavior() {
		return PistonBehavior.IGNORE;
	}

	@Override
	protected boolean canClimb() {
		return false;
	}

	// Determines if the entity can be interacted with
	@Override
	public boolean collides() {
		return true;
	}

	@Override
	public ActionResult interact(PlayerEntity player, Hand hand) {
		if (!world.isClient) {
			BlockState state = world.getBlockState(getBlockPos());
			if (state.getBlock() == ModInit.CHRISTMAS_TREE_LIGHT_BLOCK)
				world.setBlockState(getBlockPos(), Blocks.AIR.getDefaultState());
			else
				world.setBlockState(getBlockPos(), ModInit.CHRISTMAS_TREE_LIGHT_BLOCK.getDefaultState());
		}
		return ActionResult.CONSUME;
	}

	@Override
	public ActionResult interactAt(PlayerEntity player, Vec3d hitPos, Hand hand) {
		return ActionResult.PASS;
	}

}
