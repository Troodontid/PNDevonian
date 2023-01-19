
package net.pndevonian.world.biome.devonian;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.block.BlockStromatoporoideaReef;
import net.lepidodendron.util.EnumBiomeTypeDevonian;
import net.lepidodendron.world.biome.devonian.BiomeDevonian;
import net.lepidodendron.world.gen.*;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class BiomeDevonianReef extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:devonian_reef")
	public static final BiomeGenCustom biome = null;
	public BiomeDevonianReef(ElementsLepidodendronMod instance) {
		super(instance, 1591);
	}

	@Override
	public void initElements() {
		elements.biomes.add(() -> new BiomeGenCustom());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.OCEAN);
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.WATER);
	}

	static class BiomeGenCustom extends BiomeDevonian {
		public BiomeGenCustom() {
			super(new BiomeProperties("Devonian Barrier Reef").setRainfall(0.9F).setBaseHeight(-0.40F).setHeightVariation(0.175F).setTemperature(2.25F));
			setRegistryName("lepidodendron:devonian_reef");
			topBlock = Blocks.GRAVEL.getDefaultState();
			fillerBlock = Blocks.STONE.getStateFromMeta(0);
			decorator.treesPerChunk = -999;
			decorator.flowersPerChunk = 0;
			decorator.grassPerChunk = 0;
			decorator.mushroomsPerChunk = 0;
			decorator.bigMushroomsPerChunk = 0;
			decorator.reedsPerChunk = 0;
			decorator.cactiPerChunk = 0;
			decorator.sandPatchesPerChunk = 0;
			decorator.gravelPatchesPerChunk = 0;
			this.spawnableMonsterList.clear();
			this.spawnableCreatureList.clear();
			this.spawnableWaterCreatureList.clear();
			this.spawnableCaveCreatureList.clear();
		}

		protected static final WorldGenStromatoliteReefCambrian REEF_GENERATOR_STROMATOLITE = new WorldGenStromatoliteReefCambrian();
		protected static final WorldGenReef REEF_GENERATOR = new WorldGenReef();
		protected static final WorldGenSandNearWater SAND_GENERATOR = new WorldGenSandNearWater();
		protected static final WorldGenSandyDirt SANDY_GENERATOR = new WorldGenSandyDirt();
		protected static final WorldGenPietzschia PIETZSCHIA_GENERATOR = new WorldGenPietzschia();
		protected static final WorldGenKeraphyton KERAPHYTON_GENERATOR = new WorldGenKeraphyton();
		protected static final WorldGenSelaginella SELAGINELLA_GENERATOR = new WorldGenSelaginella();

		public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	    {
	        return null;
	    }

		@Override
	    public void decorate(World worldIn, Random rand, BlockPos pos)
	    {

			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.GRASS))
				for (int i = 0; i < 22; ++i)
				{
					int j = rand.nextInt(16) + 8;
					int k = rand.nextInt(16) + 8;
					SANDY_GENERATOR.generate(worldIn, rand, worldIn.getTopSolidOrLiquidBlock(new BlockPos(pos.getX() + j, 0, pos.getZ() + k)).up());
				}

			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.GRASS))
				for (int i = 0; i < 64; ++i)
				{
					int j = rand.nextInt(16) + 8;
					int k = rand.nextInt(16) + 8;
					SAND_GENERATOR.generate(worldIn, rand, worldIn.getTopSolidOrLiquidBlock(new BlockPos(pos.getX() + j, 0, pos.getZ() + k)).up());
				}

			if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.CLAY)) {
				for (int i = 0; i < 4; ++i) {
					int radius = 6;
					int j;
					int k;
					if (radius < 14) {
						j = 16 + rand.nextInt(16 - radius - 2) - rand.nextInt(16 - radius - 2);
						k = 16 + rand.nextInt(16 - radius - 2) - rand.nextInt(16 - radius - 2);
					}
					else {
						radius = 14;
						j = 16;
						k = 16;
					}
					int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
					BlockPos pos1 = pos.add(j, l, k);
					if (
							(pos1.getY() < worldIn.getSeaLevel())
					) {
						REEF_GENERATOR_STROMATOLITE.generate(worldIn, rand, pos1, radius);
					}
				}
			}

			if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.ROCK))
			for (int i = 0; i < 12; ++i)
			{
				int radius = 12;
				int j;
				int k;
				if (radius < 14) {
					j = 16 + rand.nextInt(16 - radius - 2) - rand.nextInt(16 - radius - 2);
					k = 16 + rand.nextInt(16 - radius - 2) - rand.nextInt(16 - radius - 2);
				}
				else {
					radius = 14;
					j = 16;
					k = 16;
				}
				int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
				BlockPos pos1 = pos.add(j, l, k);
				if (
						(pos1.getY() < worldIn.getSeaLevel())
				) {
					REEF_GENERATOR.generate(worldIn, rand, pos1, radius, BlockStromatoporoideaReef.block.getDefaultState());
				}
			}

			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.GRASS))
			for (int i = 0; i < 8; ++i)
			{
				int j = rand.nextInt(16) + 8;
				int k = rand.nextInt(16) + 8;
				int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
				SELAGINELLA_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
			}


			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.GRASS))
				for (int i = 0; i < 8; ++i)
				{
					int j = rand.nextInt(16) + 8;
					int k = rand.nextInt(16) + 8;
					int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
					PIETZSCHIA_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
				}


			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.GRASS))
				for (int i = 0; i < 8; ++i)
				{
					int j = rand.nextInt(16) + 8;
					int k = rand.nextInt(16) + 8;
					int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
					KERAPHYTON_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
				}


			super.decorate(worldIn, rand, pos);
	    }

		@Override
		public EnumBiomeTypeDevonian getBiomeType() {
			return EnumBiomeTypeDevonian.Ocean;
		}

	}
}
