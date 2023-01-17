package net.pndevonian.world.dimension.devonian.GenLayerDevonian;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerDevonianBeach extends GenLayer
{

    public Biome DEVONIAN_OCEAN = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_ocean_deep"));
    public int DEVONIAN_OCEAN_ID = Biome.getIdForBiome(DEVONIAN_OCEAN);
    public Biome DEVONIAN_OCEAN_SHORE = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_ocean"));
    public int DEVONIAN_OCEAN_SHORE_ID = Biome.getIdForBiome(DEVONIAN_OCEAN_SHORE);
    public Biome DEVONIAN_BRACKISH = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_brackish"));
    public int DEVONIAN_BRACKISH_ID = Biome.getIdForBiome(DEVONIAN_BRACKISH);
    public Biome DEVONIAN_FOREST = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_hills"));
    public int DEVONIAN_FOREST_ID = Biome.getIdForBiome(DEVONIAN_FOREST);
    public Biome DEVONIAN_REEF = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_reef"));
    public int DEVONIAN_REEF_ID = Biome.getIdForBiome(DEVONIAN_REEF);
    public Biome DEVONIAN_DEAD_REEF = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_ocean_dead_reef"));
    public int DEVONIAN_DEAD_REEF_ID = Biome.getIdForBiome(DEVONIAN_DEAD_REEF);


    public Biome DEVONIAN_BEACH = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_beach"));
    public int DEVONIAN_BEACH_ID = Biome.getIdForBiome(DEVONIAN_BEACH);
    public Biome DEVONIAN_BEACH_FOREST = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_forest"));
    public int DEVONIAN_BEACH_FOREST_ID = Biome.getIdForBiome(DEVONIAN_BEACH_FOREST);
    public Biome DEVONIAN_MOUNTAINS = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_mountains"));
    public int DEVONIAN_MOUNTAINS_ID = Biome.getIdForBiome(DEVONIAN_MOUNTAINS);
    
    public GenLayerDevonianBeach(long seed, GenLayer genLayer)
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
                //Biome biome = Biome.getBiome(k);

                if (!hasNoBeach(k))
                {
                    if (!isOcean(k))
                    {
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
                            if (k == DEVONIAN_FOREST_ID) {
                                aint1[j + i * areaWidth] = DEVONIAN_BEACH_FOREST_ID;
                            }
                            else {
                                aint1[j + i * areaWidth] = DEVONIAN_BEACH_ID;
                            }
                        }
                    }
                    else
                    {
                        aint1[j + i * areaWidth] = k;
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
        if (biomeID == DEVONIAN_OCEAN_ID || biomeID == DEVONIAN_OCEAN_SHORE_ID
                || biomeID == DEVONIAN_BRACKISH_ID
                || biomeID == DEVONIAN_REEF_ID
                || biomeID == DEVONIAN_DEAD_REEF_ID) {
            return true;
        }
        return false;
    }

    private boolean hasNoBeach(int biomeID) {
        if (biomeID == DEVONIAN_MOUNTAINS_ID) {
            return true;
        }
        return false;
    }

}
