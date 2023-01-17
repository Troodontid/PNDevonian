package net.pndevonian.world.dimension.devonian.GenLayerDevonian;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerDevonianVale extends GenLayer
{

    public Biome DEVONIAN_MOUNTAINS = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_mountains"));
    public int DEVONIAN_MOUNTAINS_ID =  Biome.getIdForBiome(DEVONIAN_MOUNTAINS);
    public Biome DEVONIAN_VALE = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_vale"));
    public int DEVONIAN_VALE_ID =  Biome.getIdForBiome(DEVONIAN_VALE);

    private final int HillsBiomes[] = new int[] {
            DEVONIAN_VALE_ID,
            DEVONIAN_VALE_ID,
            DEVONIAN_VALE_ID,
            DEVONIAN_MOUNTAINS_ID
    };

    public GenLayerDevonianVale(long seed, GenLayer genLayer)
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

                if (k == DEVONIAN_MOUNTAINS_ID)
                {
                    int l1 = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                    int k2 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                    int j3 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                    int i4 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];
                    boolean flag = (
                        (l1 == DEVONIAN_MOUNTAINS_ID)
                        && (k2 == DEVONIAN_MOUNTAINS_ID)
                        && (j3 == DEVONIAN_MOUNTAINS_ID)
                        && (i4 == DEVONIAN_MOUNTAINS_ID)
                    );
                    if (flag)
                    {
                        aint1[j + i * areaWidth] = HillsBiomes[nextInt(HillsBiomes.length)];
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
    
}
