package net.pndevonian.world.dimension.devonian.GenLayerDevonian;

import net.lepidodendron.util.EnumBiomeTypeDevonian;
import net.lepidodendron.world.biome.devonian.BiomeDevonian;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerDevonianRiverMix extends GenLayer
{
    private final GenLayer biomePatternGeneratorChain;
    private final GenLayer riverPatternGeneratorChain;

    //Creeks to use:
    public Biome DEVONIAN_CREEK_COAST = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_creek_coastal"));
    public int DEVONIAN_CREEK_COAST_ID = Biome.getIdForBiome(DEVONIAN_CREEK_COAST);
    public Biome DEVONIAN_CREEK_FLOODPLAIN = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_creek_floodplain"));
    public int DEVONIAN_CREEK_FLOODPLAIN_ID =  Biome.getIdForBiome(DEVONIAN_CREEK_FLOODPLAIN);
    public Biome DEVONIAN_CREEK_FOREST = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_creek_forest"));
    public int DEVONIAN_CREEK_FOREST_ID =  Biome.getIdForBiome(DEVONIAN_CREEK_FOREST);
    public Biome DEVONIAN_CREEK_GILBOA = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_creek_gilboa"));
    public int DEVONIAN_CREEK_GILBOA_ID =  Biome.getIdForBiome(DEVONIAN_CREEK_GILBOA);
    public Biome DEVONIAN_CREEK_MEADOW = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_creek_meadow"));
    public int DEVONIAN_CREEK_MEADOW_ID =  Biome.getIdForBiome(DEVONIAN_CREEK_MEADOW);
    public Biome DEVONIAN_CREEK_SWAMP = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_creek_swamp"));
    public int DEVONIAN_CREEK_SWAMP_ID =  Biome.getIdForBiome(DEVONIAN_CREEK_SWAMP);
    public Biome DEVONIAN_CREEK_BRACKISH = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_creek_brackish"));
    public int DEVONIAN_CREEK_BRACKISH_ID =  Biome.getIdForBiome(DEVONIAN_CREEK_BRACKISH);

    //Biomes to exclude for rivers:
    public Biome DEVONIAN_SINKHOLE = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_hypersaline_sinkhole"));
    public int DEVONIAN_SINKHOLE_ID =  Biome.getIdForBiome(DEVONIAN_SINKHOLE);
    public Biome DEVONIAN_SINKHOLE_EDGE = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_hypersaline_sinkhole_transition"));
    public int DEVONIAN_SINKHOLE_EDGE_ID =  Biome.getIdForBiome(DEVONIAN_SINKHOLE_EDGE);
    public Biome DEVONIAN_MOUNTAINS = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_mountains"));
    public int DEVONIAN_MOUNTAINS_ID =  Biome.getIdForBiome(DEVONIAN_MOUNTAINS);
    public Biome DEVONIAN_OCEAN_SHORE = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_ocean"));
    public int DEVONIAN_OCEAN_SHORE_ID =  Biome.getIdForBiome(DEVONIAN_OCEAN_SHORE);
    public Biome DEVONIAN_DEAD_REEF = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_ocean_dead_reef"));
    public int DEVONIAN_DEAD_REEF_ID =  Biome.getIdForBiome(DEVONIAN_DEAD_REEF);
    public Biome DEVONIAN_OCEAN = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_ocean_deep"));
    public int DEVONIAN_OCEAN_ID =  Biome.getIdForBiome(DEVONIAN_OCEAN);
    public Biome DEVONIAN_REEF = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_reef"));
    public int DEVONIAN_REEF_ID =  Biome.getIdForBiome(DEVONIAN_REEF);
    public Biome DEVONIAN_SPIKES = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_spikes"));
    public int DEVONIAN_SPIKES_ID =  Biome.getIdForBiome(DEVONIAN_SPIKES);
    public Biome DEVONIAN_SPRINGS = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_springs"));
    public int DEVONIAN_SPRINGS_ID =  Biome.getIdForBiome(DEVONIAN_SPRINGS);
    public Biome DEVONIAN_REEF_EDGE = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_reef_transition"));
    public int DEVONIAN_REEF_EDGE_ID =  Biome.getIdForBiome(DEVONIAN_REEF_EDGE);

    public GenLayerDevonianRiverMix(long p_i2129_1_, GenLayer p_i2129_3_, GenLayer p_i2129_4_)
    {
        super(p_i2129_1_);
        this.biomePatternGeneratorChain = p_i2129_3_;
        this.riverPatternGeneratorChain = p_i2129_4_;
    }

    public void initWorldGenSeed(long seed)
    {
        this.biomePatternGeneratorChain.initWorldGenSeed(seed);
        this.riverPatternGeneratorChain.initWorldGenSeed(seed);
        super.initWorldGenSeed(seed);
    }

    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] aint = this.biomePatternGeneratorChain.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] aint1 = this.riverPatternGeneratorChain.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] aint2 = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaWidth * areaHeight; ++i)
        {
            if (aint1[i] == Biome.getIdForBiome(Biomes.RIVER))
            {
                //Exclude rivers here:
                if (aint[i] == DEVONIAN_SINKHOLE_ID
                        || aint[i] == DEVONIAN_OCEAN_SHORE_ID
                        || aint[i] == DEVONIAN_SINKHOLE_EDGE_ID
                        || aint[i] == DEVONIAN_MOUNTAINS_ID
                        || aint[i] == DEVONIAN_REEF_ID
                        || aint[i] == DEVONIAN_REEF_EDGE_ID
                        || aint[i] == DEVONIAN_DEAD_REEF_ID
                        || aint[i] == DEVONIAN_OCEAN_ID
                        || aint[i] == DEVONIAN_SPIKES_ID
                        || aint[i] == DEVONIAN_SPRINGS_ID
                )
                {
                    aint2[i] = aint[i];
                }
                else {
                    //Add the rivers we want:
                    Biome biome = Biome.getBiome(aint[i]);
                    if (biome instanceof BiomeDevonian) {
                        BiomeDevonian biomeDevonian = (BiomeDevonian) biome;
                        if (biomeDevonian.getBiomeType() == EnumBiomeTypeDevonian.Forest
                                || biomeDevonian.getBiomeType() == EnumBiomeTypeDevonian.Vale
                                || biomeDevonian.getBiomeType() == EnumBiomeTypeDevonian.Savanna) {
                            aint2[i] = DEVONIAN_CREEK_FOREST_ID;
                        }
                        else if (biomeDevonian.getBiomeType() == EnumBiomeTypeDevonian.Brackish) {
                            aint2[i] = DEVONIAN_CREEK_BRACKISH_ID;
                        }
                        else if (biomeDevonian.getBiomeType() == EnumBiomeTypeDevonian.Ocean) {
                            aint2[i] = DEVONIAN_CREEK_COAST_ID;
                        }
                        else if (biomeDevonian.getBiomeType() == EnumBiomeTypeDevonian.Swamp) {
                            aint2[i] = DEVONIAN_CREEK_SWAMP_ID;
                        }
                        else if (biomeDevonian.getBiomeType() == EnumBiomeTypeDevonian.Meadow) {
                            aint2[i] = DEVONIAN_CREEK_MEADOW_ID;
                        }
                        else if (biomeDevonian.getBiomeType() == EnumBiomeTypeDevonian.Floodplain) {
                            aint2[i] = DEVONIAN_CREEK_FLOODPLAIN_ID;
                        }
                        else if (biomeDevonian.getBiomeType() == EnumBiomeTypeDevonian.Gilboa) {
                            aint2[i] = DEVONIAN_CREEK_GILBOA_ID;
                        }
                        else {
                            aint2[i] = aint[i];
                        }
                    }
                    else {
                        aint2[i] = aint[i];
                    }
                }
            }
            else
            {
                aint2[i] = aint[i];
            }

        }

        return aint2;
    }
}
