package net.pndevonian.world.dimension.devonian.GenLayerDevonian;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerDevonianBorderMountains extends GenLayer
{
    public Biome DEVONIAN_SHALLOW_OCEAN = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_ocean"));
    public int DEVONIAN_SHALLOW_OCEAN_ID =  Biome.getIdForBiome(DEVONIAN_SHALLOW_OCEAN);
    public Biome DEVONIAN_MOUNTAINS = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_mountains"));
    public int DEVONIAN_MOUNTAINS_ID =  Biome.getIdForBiome(DEVONIAN_MOUNTAINS);

    public Biome DEVONIAN_SAVANNA = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_savanna"));
    public int DEVONIAN_SAVANNA_ID =  Biome.getIdForBiome(DEVONIAN_SAVANNA);
    public Biome DEVONIAN_SWAMP = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_swamp"));
    public int DEVONIAN_SWAMP_ID =  Biome.getIdForBiome(DEVONIAN_SWAMP);
    public Biome DEVONIAN_FOREST = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_hills"));
    public int DEVONIAN_FOREST_ID =  Biome.getIdForBiome(DEVONIAN_FOREST);

    private final int Biomes[] = new int[] {
            DEVONIAN_SAVANNA_ID,
            DEVONIAN_SWAMP_ID,
            DEVONIAN_FOREST_ID,
            DEVONIAN_SAVANNA_ID,
            DEVONIAN_SWAMP_ID,
            DEVONIAN_FOREST_ID,
            DEVONIAN_SAVANNA_ID,
            DEVONIAN_SWAMP_ID,
            DEVONIAN_FOREST_ID,
            DEVONIAN_SAVANNA_ID,
            DEVONIAN_SWAMP_ID,
            DEVONIAN_FOREST_ID,
            DEVONIAN_MOUNTAINS_ID
    };

    public GenLayerDevonianBorderMountains(long seed, GenLayer genLayer)
    {
        super(seed);
        this.parent = genLayer;
    }

    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] aint = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] aint1 = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaHeight; ++i)
        {
            for (int j = 0; j < areaWidth; ++j)
            {
                this.initChunkSeed((long)(j + areaX), (long)(i + areaY));
                int k = aint[j + 1 + (i + 1) * (areaWidth + 2)];

               if (!isOcean(k)) {
                    int l1 = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                    int k2 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                    int j3 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                    int i4 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];

                    if (!isOcean(l1) && !isOcean(k2) && !isOcean(j3) && !isOcean(i4))
                    {
                        aint1[j + i * areaWidth] = k;
                    }
                    else
                    {
                        aint1[j + i * areaWidth] = Biomes[nextInt(Biomes.length)];
                    }
                }
                else
                {
                    aint1[j + i * areaWidth] = k;
                }

            }
        }

        return aint1;
    }

    private boolean isOcean(int biomeID) {
        if (biomeID == DEVONIAN_SHALLOW_OCEAN_ID) {
            return true;
        }
        return false;
    }


}
