package mod.vemerion.vemerrychristmas.item;

import mod.vemerion.vemerrychristmas.ModInit;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.ItemTags;

public class SantaHatMaterial implements ArmorMaterial {

	@Override
	public int getDurability(EquipmentSlot slot) {
		return 65;
	}

	@Override
	public int getProtectionAmount(EquipmentSlot slot) {
		return 1;
	}

	@Override
	public int getEnchantability() {
		return 15;
	}

	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.BLOCK_WOOL_PLACE;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.fromTag(ItemTags.WOOL);
	}

	@Override
	public String getName() {
		return ModInit.MODID + "_santa_hat";
	}

	@Override
	public float getToughness() {
		return 0;
	}

	@Override
	public float getKnockbackResistance() {
		return 0;
	}

}
