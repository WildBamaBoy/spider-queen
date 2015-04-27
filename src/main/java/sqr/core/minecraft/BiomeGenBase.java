package sqr.core.minecraft;
//package sqr.gen;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//import net.minecraft.block.Block;
//import net.minecraft.entity.EnumCreatureType;
//import net.minecraft.util.MathHelper;
//import net.minecraft.world.ColorizerFoliage;
//import net.minecraft.world.ColorizerGrass;
//import net.minecraft.world.World;
//import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
//import net.minecraft.world.biome.BiomeGenBeach;
//import net.minecraft.world.biome.BiomeGenDesert;
//import net.minecraft.world.biome.BiomeGenEnd;
//import net.minecraft.world.biome.BiomeGenForest;
//import net.minecraft.world.biome.BiomeGenHell;
//import net.minecraft.world.biome.BiomeGenHills;
//import net.minecraft.world.biome.BiomeGenJungle;
//import net.minecraft.world.biome.BiomeGenMushroomIsland;
//import net.minecraft.world.biome.BiomeGenOcean;
//import net.minecraft.world.biome.BiomeGenPlains;
//import net.minecraft.world.biome.BiomeGenRiver;
//import net.minecraft.world.biome.BiomeGenSnow;
//import net.minecraft.world.biome.BiomeGenSwamp;
//import net.minecraft.world.biome.BiomeGenTaiga;
//import net.minecraft.world.gen.feature.WorldGenBigTree;
//import net.minecraft.world.gen.feature.WorldGenForest;
//import net.minecraft.world.gen.feature.WorldGenSwamp;
//import net.minecraft.world.gen.feature.WorldGenTallGrass;
//import net.minecraft.world.gen.feature.WorldGenTrees;
//import net.minecraft.world.gen.feature.WorldGenerator;
//
//public abstract class BiomeGenBase
//{
//
//    protected List spawnableNewCreatureList; // SNIP
//
//    public static final BiomeGenBase biomeList[] = new BiomeGenBase[256];
//    public static final BiomeGenBase ocean = (new BiomeGenOcean(0)).setColor(112).setBiomeName("Ocean").setMinMaxHeight(-1F, 0.4F);
//    public static final BiomeGenBase plains = (new BiomeGenPlains(1)).setColor(0x8db360).setBiomeName("Plains").setTemperatureRainfall(0.8F, 0.4F);
//    public static final BiomeGenBase desert = (new BiomeGenDesert(2)).setColor(0xfa9418).setBiomeName("Desert").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.2F);
//    public static final BiomeGenBase extremeHills = (new BiomeGenHills(3)).setColor(0x606060).setBiomeName("Extreme Hills").setMinMaxHeight(0.2F, 1.3F).setTemperatureRainfall(0.2F, 0.3F);
//    public static final BiomeGenBase forest = (new BiomeGenForest(4)).setColor(0x56621).setBiomeName("Forest").func_4124_a(0x4eba31).setTemperatureRainfall(0.7F, 0.8F);
//    public static final BiomeGenBase taiga = (new BiomeGenTaiga(5)).setColor(0xb6659).setBiomeName("Taiga").func_4124_a(0x4eba31).func_50086_b().setTemperatureRainfall(0.05F, 0.8F).setMinMaxHeight(0.1F, 0.4F);
//    public static final BiomeGenBase swampland = (new BiomeGenSwamp(6)).setColor(0x7f9b2).setBiomeName("Swampland").func_4124_a(0x8baf48).setMinMaxHeight(-0.2F, 0.1F).setTemperatureRainfall(0.8F, 0.9F);
//    public static final BiomeGenBase river = (new BiomeGenRiver(7)).setColor(255).setBiomeName("River").setMinMaxHeight(-0.5F, 0.0F);
//    public static final BiomeGenBase hell = (new BiomeGenHell(8)).setColor(0xff0000).setBiomeName("Hell").setDisableRain().setTemperatureRainfall(2.0F, 0.0F);
//
//
//    public static final BiomeGenBase sky = (new BiomeGenEnd(9)).setColor(0x8080ff).setBiomeName("Sky").setDisableRain();
//    public static final BiomeGenBase frozenOcean = (new BiomeGenOcean(10)).setColor(0x9090a0).setBiomeName("FrozenOcean").func_50086_b().setMinMaxHeight(-1F, 0.5F).setTemperatureRainfall(0.0F, 0.5F);
//    public static final BiomeGenBase frozenRiver = (new BiomeGenRiver(11)).setColor(0xa0a0ff).setBiomeName("FrozenRiver").func_50086_b().setMinMaxHeight(-0.5F, 0.0F).setTemperatureRainfall(0.0F, 0.5F);
//    public static final BiomeGenBase icePlains = (new BiomeGenSnow(12)).setColor(0xffffff).setBiomeName("Ice Plains").func_50086_b().setTemperatureRainfall(0.0F, 0.5F);
//    public static final BiomeGenBase iceMountains = (new BiomeGenSnow(13)).setColor(0xa0a0a0).setBiomeName("Ice Mountains").func_50086_b().setMinMaxHeight(0.2F, 1.2F).setTemperatureRainfall(0.0F, 0.5F);
//    public static final BiomeGenBase mushroomIsland = (new BiomeGenMushroomIsland(14)).setColor(0xff00ff).setBiomeName("MushroomIsland").setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(0.2F, 1.0F);
//    public static final BiomeGenBase mushroomIslandShore = (new BiomeGenMushroomIsland(15)).setColor(0xa000ff).setBiomeName("MushroomIslandShore").setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(-1F, 0.1F);
//
//
//    public static final BiomeGenBase beach = (new BiomeGenBeach(16)).setColor(0xfade55).setBiomeName("Beach").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.0F, 0.1F);
//
//
//    public static final BiomeGenBase desertHills = (new BiomeGenDesert(17)).setColor(0xd25f12).setBiomeName("DesertHills").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.2F, 0.7F);
//
//
//    public static final BiomeGenBase forestHills = (new BiomeGenForest(18)).setColor(0x22551c).setBiomeName("ForestHills").func_4124_a(0x4eba31).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.2F, 0.6F);
//
//
//    public static final BiomeGenBase taigaHills = (new BiomeGenTaiga(19)).setColor(0x163933).setBiomeName("TaigaHills").func_50086_b().func_4124_a(0x4eba31).setTemperatureRainfall(0.05F, 0.8F).setMinMaxHeight(0.2F, 0.7F);
//
//
//    public static final BiomeGenBase extremeHillsEdge = (new BiomeGenHills(20)).setColor(0x72789a).setBiomeName("Extreme Hills Edge").setMinMaxHeight(0.2F, 0.8F).setTemperatureRainfall(0.2F, 0.3F);
//
//
//    public static final BiomeGenBase jungle = (new BiomeGenJungle(21)).setColor(0x537b09).setBiomeName("Jungle").func_4124_a(0x537b09).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.2F, 0.4F);
//    public static final BiomeGenBase jungleHills = (new BiomeGenJungle(22)).setColor(0x2c4205).setBiomeName("JungleHills").func_4124_a(0x537b09).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(1.8F, 0.2F);
//    public String biomeName;
//    public int color;
//
//
//    public byte topBlock;
//
//
//    public byte fillerBlock;
//    public int field_6502_q;
//
//
//    public float minHeight;
//
//
//    public float maxHeight;
//
//
//    public float temperature;
//
//
//    public float rainfall;
//
//
//    public int waterColorMultiplier;
//    public BiomeDecorator biomeDecorator;
//
//
//    protected List spawnableMonsterList;
//
//
//    protected List spawnableCreatureList;
//
//
//    protected List spawnableWaterCreatureList;
//
//
//    private boolean enableSnow;
//
//
//    private boolean enableRain;
//
//
//    public final int biomeID;
//    protected WorldGenTrees worldGenTrees;
//    protected WorldGenBigTree worldGenBigTree;
//    protected WorldGenForest worldGenForest;
//    protected WorldGenSwamp worldGenSwamp;
//
//    protected BiomeGenBase(int par1)
//    {
//        topBlock = (byte)Blocks.grass;
//        fillerBlock = (byte)Blocks.dirt;
//        field_6502_q = 0x4ee031;
//        minHeight = 0.1F;
//        maxHeight = 0.3F;
//        temperature = 0.5F;
//        rainfall = 0.5F;
//        waterColorMultiplier = 0xffffff;
//        spawnableMonsterList = new ArrayList();
//        spawnableCreatureList = new ArrayList();
//        spawnableWaterCreatureList = new ArrayList();
//
//
//        enableRain = true;
//        worldGenTrees = new WorldGenTrees(false);
//        worldGenBigTree = new WorldGenBigTree(false);
//        worldGenForest = new WorldGenForest(false);
//        worldGenSwamp = new WorldGenSwamp();
//        biomeID = par1;
//        biomeList[par1] = this;
//        biomeDecorator = createBiomeDecorator();
//        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.src.EntitySheep.class, 12, 4, 4));
//        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityPig.class, 10, 4, 4));
//        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityChicken.class, 10, 4, 4));
//        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityCow.class, 8, 4, 4));
//        spawnableMonsterList.add(new SpawnListEntry(net.minecraft.src.EntitySpider.class, 10, 4, 4));
//        spawnableMonsterList.add(new SpawnListEntry(net.minecraft.src.EntityZombie.class, 10, 4, 4));
//        spawnableMonsterList.add(new SpawnListEntry(net.minecraft.src.EntitySkeleton.class, 10, 4, 4));
//        spawnableMonsterList.add(new SpawnListEntry(net.minecraft.src.EntityCreeper.class, 10, 4, 4));
//        spawnableMonsterList.add(new SpawnListEntry(net.minecraft.src.EntitySlime.class, 10, 4, 4));
//        spawnableMonsterList.add(new SpawnListEntry(net.minecraft.src.EntityEnderman.class, 1, 1, 4));
//        spawnableWaterCreatureList.add(new SpawnListEntry(net.minecraft.src.EntitySquid.class, 10, 4, 4));
//
//
//		// SNIP
//        spawnableNewCreatureList = new ArrayList();
//		spawnableNewCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityRedAnt.class, 11,1,4));
//		spawnableNewCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityFly.class, 12,1,1));
//		spawnableNewCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityWasp.class, 12,1,2));
//		spawnableNewCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityHuman.class, 12,2,4));
//		spawnableNewCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityAnt.class, 11,3,4));
//		spawnableNewCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityMand.class, 12,1,3));
//		spawnableNewCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityBeetle.class, 12,1,3));
//		spawnableNewCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityYuki.class, 6,1,2));
//		spawnableNewCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityGatherer.class, 5,1,1));
//		spawnableNewCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityWarrior.class, 5,1,1));
//		spawnableWaterCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityOctopus.class, 5,1,4));
//
//		// -SNIP
//    }
//
//
//    protected BiomeDecorator createBiomeDecorator()
//    {
//        return new BiomeDecorator(this);
//    }
//
//
//    private BiomeGenBase setTemperatureRainfall(float par1, float par2)
//    {
//        if (par1 > 0.1F && par1 < 0.2F)
//        {
//            throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
//        }
//        else
//        {
//            temperature = par1;
//            rainfall = par2;
//            return this;
//        }
//    }
//
//
//    private BiomeGenBase setMinMaxHeight(float par1, float par2)
//    {
//        minHeight = par1;
//        maxHeight = par2;
//        return this;
//    }
//
//
//    private BiomeGenBase setDisableRain()
//    {
//        enableRain = false;
//        return this;
//    }
//
//
//    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
//    {
//        if (par1Random.nextInt(10) == 0)
//        {
//            return worldGenBigTree;
//        }
//        else
//        {
//            return worldGenTrees;
//        }
//    }
//
//    public WorldGenerator func_48410_b(Random par1Random)
//    {
//        return new WorldGenTallGrass(Blocks.tallGrass, 1);
//    }
//
//    protected BiomeGenBase func_50086_b()
//    {
//        enableSnow = true;
//        return this;
//    }
//
//    protected BiomeGenBase setBiomeName(String par1Str)
//    {
//        biomeName = par1Str;
//        return this;
//    }
//
//    protected BiomeGenBase func_4124_a(int par1)
//    {
//        field_6502_q = par1;
//        return this;
//    }
//
//    protected BiomeGenBase setColor(int par1)
//    {
//        color = par1;
//        return this;
//    }
//
//
//    public int getSkyColorByTemp(float par1)
//    {
//        par1 /= 3F;
//
//        if (par1 < -1F)
//        {
//            par1 = -1F;
//        }
//
//        if (par1 > 1.0F)
//        {
//            par1 = 1.0F;
//        }
//
//        return java.awt.Color.getHSBColor(0.6222222F - par1 * 0.05F, 0.5F + par1 * 0.1F, 1.0F).getRGB();
//    }
//
//
//    public List getSpawnableList(EnumCreatureType par1EnumCreatureType)
//    {
//        if (par1EnumCreatureType == EnumCreatureType.monster)
//        {
//            return spawnableMonsterList;
//        }
//
//        if (par1EnumCreatureType == EnumCreatureType.creature)
//        {
//			// SNIP
//			Random rm = new Random();
//			if(rm.nextInt(2) == 0) { return spawnableNewCreatureList; }
//            return spawnableCreatureList;
//			// -SNIP
//        }
//
//        if (par1EnumCreatureType == EnumCreatureType.waterCreature)
//        {
//            return spawnableWaterCreatureList;
//        }
//        else
//        {
//            return null;
//        }
//    }
//
//
//    public boolean getEnableSnow()
//    {
//        return enableSnow;
//    }
//
//
//    public boolean canSpawnLightningBolt()
//    {
//        if (enableSnow)
//        {
//            return false;
//        }
//        else
//        {
//            return enableRain;
//        }
//    }
//
//
//    public boolean isHighHumidity()
//    {
//        return rainfall > 0.85F;
//    }
//
//
//    public float getSpawningChance()
//    {
//        return 0.1F;
//    }
//
//
//    public final int getIntRainfall()
//    {
//        return (int)(rainfall * 65536F);
//    }
//
//
//    public final int getIntTemperature()
//    {
//        return (int)(temperature * 65536F);
//    }
//
//
//    public final float getFloatRainfall()
//    {
//        return rainfall;
//    }
//
//
//    public final float getFloatTemperature()
//    {
//        return temperature;
//    }
//
//    public void decorate(World par1World, Random par2Random, int par3, int par4)
//    {
//        biomeDecorator.decorate(par1World, par2Random, par3, par4);
//    }
//
//
//    public int getBiomeGrassColor()
//    {
//        double d = MathHelper.clamp_float(getFloatTemperature(), 0.0F, 1.0F);
//        double d1 = MathHelper.clamp_float(getFloatRainfall(), 0.0F, 1.0F);
//        return ColorizerGrass.getGrassColor(d, d1);
//    }
//
//
//    public int getBiomeFoliageColor()
//    {
//        double d = MathHelper.clamp_float(getFloatTemperature(), 0.0F, 1.0F);
//        double d1 = MathHelper.clamp_float(getFloatRainfall(), 0.0F, 1.0F);
//        return ColorizerFoliage.getFoliageColor(d, d1);
//    }
//}
