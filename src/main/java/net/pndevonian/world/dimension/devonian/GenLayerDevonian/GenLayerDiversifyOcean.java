package net.pndevonian.world.dimension.devonian.GenLayerDevonian;

import net.lepidodendron.util.EnumBiomeTypeDevonian;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.pndevonian.world.biome.devonian.BiomeDevonianOceanDeep;

public class GenLayerDiversifyOcean extends GenLayer {


    public Biome DEVONIAN_OCEAN = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_ocean_deep"));
    public int DEVONIAN_OCEAN_ID =  Biome.getIdForBiome(DEVONIAN_OCEAN);

    public Biome DEVONIAN_OCEAN_DEAD = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_ocean_dead_reef"));
    public int DEVONIAN_OCEAN_DEAD_ID =  Biome.getIdForBiome(DEVONIAN_OCEAN_DEAD);
    public Biome DEVONIAN_REEF = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_reef"));
    public int DEVONIAN_REEF_ID =  Biome.getIdForBiome(DEVONIAN_REEF);


    private final int OceanBiomes[] = new int[] {
            DEVONIAN_OCEAN_ID,
            DEVONIAN_OCEAN_ID,
            DEVONIAN_OCEAN_ID,
            DEVONIAN_OCEAN_ID,
            DEVONIAN_OCEAN_ID,
            DEVONIAN_OCEAN_DEAD_ID,
            DEVONIAN_REEF_ID
    };

    public GenLayerDiversifyOcean(long seed, GenLayer genlayer) {
        super(seed);
        this.parent = genlayer;
    }

    @Override
    public int[] getInts(int x, int z, int width, int height) {
        return diversify(x, z, width, height);
    }

    private int[] diversify(int x, int z, int width, int height) {
        int input[] = this.parent.getInts(x, z, width, height);
        int output[] = IntCache.getIntCache(width * height);
        EnumBiomeTypeDevonian type;
        for (int zOut = 0; zOut < height; zOut++) {
            for (int xOut = 0; xOut < width; xOut++) {
                int i = xOut + zOut * width;
                int center = input[i];
                initChunkSeed(xOut + x, zOut + z);
                if (nextInt(2) == 0) {
                    if (Biome.getBiome(center) == BiomeDevonianOceanDeep.biome)
                        output[i] = OceanBiomes[nextInt(OceanBiomes.length)];
                    else output[i] = center;
                } else output[i] = center;
            }
        }
        return output;
    }

}