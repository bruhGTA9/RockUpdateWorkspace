
package net.mcreator.rockupdate.item;

import org.omg.CORBA.ObjectHolder;

import net.mcreator.rockupdate.itemgroup.RockUpdateItemGroup;
import net.mcreator.rockupdate.RockupdateModElements;

@RockupdateModElements.ModElement.Tag
public class OpalDropItem extends RockupdateModElements.ModElement {
	@ObjectHolder("rockupdate:opal_drop")
	public static final Item block = null;
	public OpalDropItem(RockupdateModElements instance) {
		super(instance, 3);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(RockUpdateItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("opal_drop");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
