
package net.mcreator.rockupdate.block;

import org.omg.CORBA.ObjectHolder;

import net.minecraft.block.material.Material;

import net.mcreator.rockupdate.itemgroup.RockUpdateItemGroup;
import net.mcreator.rockupdate.RockupdateModElements;

import java.util.Random;
import java.util.List;
import java.util.Collections;

@RockupdateModElements.ModElement.Tag
public class ChrysoberylOreBlock extends RockupdateModElements.ModElement {
	@ObjectHolder("rockupdate:chrysoberyl_ore")
	public static final Block block = null;
	public ChrysoberylOreBlock(RockupdateModElements instance) {
		super(instance, 32);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(RockUpdateItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1f, 10f).lightValue(0).harvestLevel(2)
					.harvestTool(ToolType.PICKAXE));
			setRegistryName("chrysoberyl_ore");
		}

		@Override
		public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
			return true;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, new OreFeature(OreFeatureConfig::deserialize) {
				@Override
				public boolean place(IWorld world, ChunkGenerator generator, Random rand, BlockPos pos, OreFeatureConfig config) {
					DimensionType dimensionType = world.getDimension().getType();
					boolean dimensionCriteria = false;
					if (dimensionType == DimensionType.OVERWORLD)
						dimensionCriteria = true;
					if (!dimensionCriteria)
						return false;
					return super.place(world, generator, rand, pos, config);
				}
			}.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.create("chrysoberyl_ore", "chrysoberyl_ore", blockAt -> {
				boolean blockCriteria = false;
				if (blockAt.getBlock() == Blocks.STONE.getDefaultState().getBlock())
					blockCriteria = true;
				if (blockAt.getBlock() == GneissBlockBlock.block.getDefaultState().getBlock())
					blockCriteria = true;
				if (blockAt.getBlock() == StonyGneissBlockBlock.block.getDefaultState().getBlock())
					blockCriteria = true;
				if (blockAt.getBlock() == Blocks.ANDESITE.getDefaultState().getBlock())
					blockCriteria = true;
				return blockCriteria;
			}), block.getDefaultState(), 5)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(2, 0, 0, 18))));
		}
	}
}