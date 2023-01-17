package net.pndevonian.world.dimension.devonian.GenLayerDevonian;

import net.lepidodendron.util.EnumBiomeTypeDevonian;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.pndevonian.world.biome.devonian.BiomeDevonianSavanna;
import net.pndevonian.world.biome.devonian.BiomeDevonianVale;

public class GenLayerDiversifyVale extends GenLayer {


    public Biome DEVONIAN_VALE = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_vale"));
    public int DEVONIAN_VALE_ID =  Biome.getIdForBiome(DEVONIAN_VALE);
    public Biome DEVONIAN_SPRINGS = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_springs"));
    public int DEVONIAN_SPRINGS_ID =  Biome.getIdForBiome(DEVONIAN_SPRINGS);


    private final int Biomes[] = new int[] {
            DEVONIAN_VALE_ID,
            DEVONIAN_VALE_ID,
            DEVONIAN_SPRINGS_ID
    };

    public GenLayerDiversifyVale(long seed, GenLayer genlayer) {
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
                    if (Biome.getBiome(center) == BiomeDevonianVale.biome)
                        output[i] = Biomes[nextInt(Biomes.length)];
                    else output[i] = center;
                } else output[i] = center;
            }
        }
        return output;
    }

}