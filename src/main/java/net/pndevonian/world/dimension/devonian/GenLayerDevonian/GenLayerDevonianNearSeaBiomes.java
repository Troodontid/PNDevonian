package net.pndevonian.world.dimension.devonian.GenLayerDevonian;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerDevonianNearSeaBiomes extends GenLayer
{

    public Biome DEVONIAN_OCEAN = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_ocean_deep"));
    public int DEVONIAN_OCEAN_ID =  Biome.getIdForBiome(DEVONIAN_OCEAN);
    public Biome DEVONIAN_OCEAN_SHORE = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_ocean"));
    public int DEVONIAN_OCEAN_SHORE_ID =  Biome.getIdForBiome(DEVONIAN_OCEAN_SHORE);

    public Biome DEVONIAN_FLOODPLAIN = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_floodplain"));
    public int DEVONIAN_FLOODPLAIN_ID =  Biome.getIdForBiome(DEVONIAN_FLOODPLAIN);
    public Biome DEVONIAN_BRACKISH = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_brackish"));
    public int DEVONIAN_BRACKISH_ID =  Biome.getIdForBiome(DEVONIAN_BRACKISH);

    private final int ShoreBiomes[] = new int[] {
            DEVONIAN_FLOODPLAIN_ID,
            DEVONIAN_BRACKISH_ID
    };

    public GenLayerDevonianNearSeaBiomes(long seed, GenLayer genLayer)
    {
        super(seed);
        this.parent = genLayer;
    }

    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {int[] aint = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] aint1 = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaHeight; ++i)
        {
            for (int j = 0; j < areaWidth; ++j)
            {
                this.initChunkSeed((long)(j + areaX), (long)(i + areaY));
                int k = aint[j + 1 + (i + 1) * (areaWidth + 2)];

                if (!isOcean(k))
                {
                    int l1 = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                    int k2 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                    int j3 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                    int i4 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];
                    boolean flag = (isOcean(l1) || isOcean(k2) || isOcean(j3) || isOcean(i4));
                    if (flag && nextInt(4) == 0)
                    {
                        aint1[j + i * areaWidth] = ShoreBiomes[nextInt(ShoreBiomes.length)];
                    }
                    else {
                        aint1[j + i * areaWidth] = k;
                    }
                }
                else {
                    aint1[j + i * areaWidth] = k;
                }
            }
        }

        return aint1;
    }

    public boolean isOcean(int biomeID) {
        if (biomeID == DEVONIAN_OCEAN_SHORE_ID || biomeID == DEVONIAN_OCEAN_ID) {
            return true;
        }
        return false;
    }
    
}
