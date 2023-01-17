package net.pndevonian.world.dimension.devonian.GenLayerDevonian;

//import net.lepidodendron.world.biome.devonian.
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerDevonianBiomes extends GenLayer {

    public Biome DEVONIAN_OCEAN_SHORE = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_ocean"));
    public int DEVONIAN_OCEAN_SHORE_ID =  Biome.getIdForBiome(DEVONIAN_OCEAN_SHORE);
    public Biome DEVONIAN_MOUNTAINS = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_mountains"));
    public int DEVONIAN_MOUNTAINS_ID =  Biome.getIdForBiome(DEVONIAN_MOUNTAINS);

    private final int Biomes[] = new int[] {
            DEVONIAN_OCEAN_SHORE_ID,
            DEVONIAN_OCEAN_SHORE_ID,
            DEVONIAN_MOUNTAINS_ID
    };

    public GenLayerDevonianBiomes(long seed) {
        super(seed);
    }

    @Override
    public int[] getInts(int x, int z, int width, int height) {
        int dest[] = IntCache.getIntCache(width * height);
        for (int dz = 0; dz < height; dz++) {
            for (int dx = 0; dx < width; dx++) {
                initChunkSeed(dx + x, dz + z);
                dest[dx + dz * width] = Biomes[nextInt(Biomes.length)];;
            }
        }
        return dest;
    }
}