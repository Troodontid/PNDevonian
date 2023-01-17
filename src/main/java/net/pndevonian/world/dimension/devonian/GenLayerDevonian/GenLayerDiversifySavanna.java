package net.pndevonian.world.dimension.devonian.GenLayerDevonian;

import net.lepidodendron.util.EnumBiomeTypeDevonian;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.pndevonian.world.biome.devonian.BiomeDevonianForest;
import net.pndevonian.world.biome.devonian.BiomeDevonianSavanna;

public class GenLayerDiversifySavanna extends GenLayer {


    public Biome DEVONIAN_SAVANNA = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_savanna"));
    public int DEVONIAN_SAVANNA_ID =  Biome.getIdForBiome(DEVONIAN_SAVANNA);
    public Biome DEVONIAN_SPRINGS = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_springs"));
    public int DEVONIAN_SPRINGS_ID =  Biome.getIdForBiome(DEVONIAN_SPRINGS);


    private final int Biomes[] = new int[] {
            DEVONIAN_SAVANNA_ID,
            DEVONIAN_SAVANNA_ID,
            DEVONIAN_SPRINGS_ID
    };

    public GenLayerDiversifySavanna(long seed, GenLayer genlayer) {
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
                    if (Biome.getBiome(center) == BiomeDevonianSavanna.biome)
                        output[i] = Biomes[nextInt(Biomes.length)];
                    else output[i] = center;
                } else output[i] = center;
            }
        }
        return output;
    }

}