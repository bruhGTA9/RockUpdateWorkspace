
package net.mcreator.rockupdate.itemgroup;

import net.mcreator.rockupdate.block.GneissBlockBlock;
import net.mcreator.rockupdate.RockupdateModElements;

@RockupdateModElements.ModElement.Tag
public class RockUpdateItemGroup extends RockupdateModElements.ModElement {
	public RockUpdateItemGroup(RockupdateModElements instance) {
		super(instance, 22);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabrock_update") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(GneissBlockBlock.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
