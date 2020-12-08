package mod.vemerion.vemerrychristmas.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
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

}
