package sqr.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import radixcore.util.RadixMath;
import sqr.core.minecraft.ModBlocks;

public class WorldGenHugeTree extends WorldGenerator
{
	public WorldGenHugeTree()
	{
		super(false);
		this.field_881_b = new Random();
		this.field_878_e = 0;
		this.field_876_g = 0.61799999999999999D;
		this.field_875_h = 1.0D;
		this.field_874_i = 0.98100000000000001D;
		this.field_873_j = 1.0D;
		this.field_872_k = 1.0D;
		this.field_871_l = 2;
		this.field_870_m = 7;
		this.field_869_n = 4;
	}
	
	void func_521_a()
	{
		this.height = (int) (this.field_878_e * this.field_876_g);
		if (this.height >= this.field_878_e)
		{
			this.height = this.field_878_e - 1;
		}
		int i = (int) (1.3819999999999999D + Math.pow(this.field_872_k * this.field_878_e / 13D, 2D));
		if (i < 1)
		{
			i = 1;
		}
		final int ai[][] = new int[i * this.field_878_e][4];
		int j = this.basePos[1] + this.field_878_e - this.field_869_n;
		int k = 1;
		final int l = this.basePos[1] + this.height;
		int i1 = j - this.basePos[1];
		ai[0][0] = this.basePos[0];
		ai[0][1] = j;
		ai[0][2] = this.basePos[2];
		ai[0][3] = l;
		j--;
		while (i1 >= 0)
		{
			int j1 = 0;
			final float f = this.func_528_a(i1);
			if (f < 0.0F)
			{
				j--;
				i1--;
			}
			else
			{
				final double d = 0.5D;
				for (; j1 < i; j1++)
				{
					final double d1 = this.field_873_j * (f * (this.field_881_b.nextFloat() + 0.32800000000000001D));
					final double d2 = this.field_881_b.nextFloat() * 2D * 3.1415899999999999D;
					final int k1 = MathHelper.floor_double(d1 * Math.sin(d2) + this.basePos[0] + d);
					final int l1 = MathHelper.floor_double(d1 * Math.cos(d2) + this.basePos[2] + d);
					final int ai1[] = { k1, j, l1 };
					final int ai2[] = { k1, j + this.field_869_n, l1 };
					if (this.func_524_a(ai1, ai2) != -1)
					{
						continue;
					}
					final int ai3[] = { this.basePos[0], this.basePos[1], this.basePos[2] };
					final double d3 = Math.sqrt(Math.pow(Math.abs(this.basePos[0] - ai1[0]), 2D) + Math.pow(Math.abs(this.basePos[2] - ai1[2]), 2D));
					final double d4 = d3 * this.field_874_i;
					if (ai1[1] - d4 > l)
					{
						ai3[1] = l;
					}
					else
					{
						ai3[1] = (int) (ai1[1] - d4);
					}
					if (this.func_524_a(ai3, ai1) == -1)
					{
						ai[k][0] = k1;
						ai[k][1] = j;
						ai[k][2] = l1;
						ai[k][3] = ai3[1];
						k++;
					}
				}
				
				j--;
				i1--;
			}
		}
		this.field_868_o = new int[k][4];
		System.arraycopy(ai, 0, this.field_868_o, 0, k);
	}
	
	void func_523_a(int i, int j, int k, float f, byte byte0, int l)
	{
		final int i1 = (int) (f * 0.2D + 0.61799999999999999D);
		final byte byte1 = field_882_a[byte0];
		final byte byte2 = field_882_a[byte0 + 3];
		final int ai[] = { i, j, k };
		final int ai1[] = { 0, 0, 0 };
		int j1 = -i1;
		final int k1 = -i1;
		ai1[byte0] = ai[byte0];
		for (; j1 <= i1; j1++)
		{
			ai1[byte1] = ai[byte1] + j1;
			for (int l1 = -i1; l1 <= i1;)
			{
				final double d = Math.sqrt(Math.pow(Math.abs(j1) + 0.5D, 2D) + Math.pow(Math.abs(l1) + 0.5D, 2D));
				if (d > f)
				{
					l1++;
				}
				else
				{
					ai1[byte2] = ai[byte2] + l1;
					final Block b = this.worldObj.getBlock(ai1[0], ai1[1], ai1[2]);
					if (b != Blocks.air && b != Blocks.leaves && b != Blocks.leaves2)
					{
						l1++;
					}
					else
					{
						this.worldObj.setBlock(ai1[0], ai1[1], ai1[2], Blocks.air);
						l1++;
					}
				}
			}
			
		}
		
	}
	
	float func_528_a(int i)
	{
		if (i < this.field_878_e * 0.29999999999999999D)
		{
			return -1.618F;
		}
		final float f = this.field_878_e / 2.0F;
		final float f1 = this.field_878_e / 2.0F - i;
		float f2;
		if (f1 == 0.0F)
		{
			f2 = f;
		}
		else if (Math.abs(f1) >= f)
		{
			f2 = 0.0F;
		}
		else
		{
			f2 = (float) Math.sqrt(Math.pow(Math.abs(f), 2D) - Math.pow(Math.abs(f1), 2D));
		}
		f2 *= 0.5F;
		return f2;
	}
	
	float func_526_b(int i)
	{
		if (i < 0 || i >= this.field_869_n)
		{
			return -1F;
		}
		return i != 0 && i != this.field_869_n - 1 ? 3F : 2.0F;
	}
	
	void func_520_a(int i, int j, int k)
	{
		int l = j;
		for (final int i1 = j + this.field_869_n; l < i1; l++)
		{
			final float f = this.func_526_b(l - j);
			this.func_523_a(i, l, k, f, (byte) 1, 18);
		}
		
	}
	
	void func_522_a(int ai[], int ai1[], int i)
	{
		final int ai2[] = { 0, 0, 0 };
		byte byte0 = 0;
		int j = 0;
		for (; byte0 < 3; byte0++)
		{
			ai2[byte0] = ai1[byte0] - ai[byte0];
			if (Math.abs(ai2[byte0]) > Math.abs(ai2[j]))
			{
				j = byte0;
			}
		}
		
		if (ai2[j] == 0)
		{
			return;
		}
		final byte byte1 = field_882_a[j];
		final byte byte2 = field_882_a[j + 3];
		byte byte3;
		if (ai2[j] > 0)
		{
			byte3 = 1;
		}
		else
		{
			byte3 = -1;
		}
		final double d = (double) ai2[byte1] / (double) ai2[j];
		final double d1 = (double) ai2[byte2] / (double) ai2[j];
		final int ai3[] = { 0, 0, 0 };
		int k = 0;
		for (final int l = ai2[j] + byte3; k != l; k += byte3)
		{
			ai3[j] = MathHelper.floor_double(ai[j] + k + 0.5D);
			ai3[byte1] = MathHelper.floor_double(ai[byte1] + k * d + 0.5D);
			ai3[byte2] = MathHelper.floor_double(ai[byte2] + k * d1 + 0.5D);
			
			this.worldObj.setBlock(ai3[0], ai3[1], ai3[2], Blocks.air);
		}
		
	}
	
	void func_518_b()
	{
		int i = 0;
		for (final int j = this.field_868_o.length; i < j; i++)
		{
			final int k = this.field_868_o[i][0];
			final int l = this.field_868_o[i][1];
			final int i1 = this.field_868_o[i][2];
			this.func_520_a(k, l, i1);
		}
		
	}
	
	boolean func_527_c(int i)
	{
		return i >= this.field_878_e * 0.20000000000000001D;
	}
	
	void func_529_c()
	{
		final int i = this.basePos[0];
		final int j = this.basePos[1];
		final int k = this.basePos[1] + this.height;
		final int l = this.basePos[2];
		final int ai[] = { i, j, l };
		final int ai1[] = { i, k, l };
		this.func_522_a(ai, ai1, 17);
		if (this.field_871_l == 2)
		{
			ai[0]++;
			ai1[0]++;
			this.func_522_a(ai, ai1, 17);
			ai[2]++;
			ai1[2]++;
			this.func_522_a(ai, ai1, 17);
			ai[0]--;
			ai1[0]--;
			this.func_522_a(ai, ai1, 17);
		}
	}
	
	void beehive()
	{
		final int i = this.basePos[0];
		final int j = this.basePos[1];
		final int k = this.basePos[1] + this.height;
		final int l = this.basePos[2];
		final int ai[] = { i, j, l };
		final int ai1[] = { i, k, l };
		this.hiveDo(ai, ai1, 17);
	}
	
	void hiveDo(int ai[], int ai1[], int i)
	{
		final int ai2[] = { 0, 0, 0 };
		byte byte0 = 0;
		int j = 0;
		for (; byte0 < 3; byte0++)
		{
			ai2[byte0] = ai1[byte0] - ai[byte0];
			if (Math.abs(ai2[byte0]) > Math.abs(ai2[j]))
			{
				j = byte0;
			}
		}
		
		if (ai2[j] == 0)
		{
			return;
		}
		final byte byte1 = field_882_a[j];
		final byte byte2 = field_882_a[j + 3];
		byte byte3;
		if (ai2[j] > 0)
		{
			byte3 = 1;
		}
		else
		{
			byte3 = -1;
		}
		final double d = (double) ai2[byte1] / (double) ai2[j];
		final double d1 = (double) ai2[byte2] / (double) ai2[j];
		final int ai3[] = { 0, 0, 0 };
		int k = 0;
		boolean change = false;
		int px = 0;
		int py = 0;
		int pz = 0;
		int opx = 0;
		int opy = 0;
		int opz = 0;
		
		for (final int l = ai2[j] + byte3; k != l; k += byte3)
		{
			ai3[j] = MathHelper.floor_double(ai[j] + k + 0.5D);
			ai3[byte1] = MathHelper.floor_double(ai[byte1] + k * d + 0.5D);
			ai3[byte2] = MathHelper.floor_double(ai[byte2] + k * d1 + 0.5D);
			// worldObj.setBlock(ai3[0], ai3[1], ai3[2], i);
			final Random nr = new Random();
			final int amt = 5 + nr.nextInt(4);
			if (this.checkForHive(ai3[0] - amt, ai3[1], ai3[2]))
			{
				change = true;
				px = ai3[0] - amt;
				py = ai3[1];
				pz = ai3[2];
				opx = ai3[0];
				opy = ai3[1];
				opz = ai3[2];
			}
			if (this.checkForHive(ai3[0] + amt, ai3[1], ai3[2]))
			{
				change = true;
				px = ai3[0] + amt;
				py = ai3[1];
				pz = ai3[2];
				opx = ai3[0];
				opy = ai3[1];
				opz = ai3[2];
			}
			if (this.checkForHive(ai3[0], ai3[1], ai3[2] - amt))
			{
				change = true;
				px = ai3[0];
				py = ai3[1];
				pz = ai3[2] - amt;
				opx = ai3[0];
				opy = ai3[1];
				opz = ai3[2];
			}
			if (this.checkForHive(ai3[0], ai3[1], ai3[2] + amt))
			{
				change = true;
				px = ai3[0];
				py = ai3[1];
				pz = ai3[2] + amt;
				opx = ai3[0];
				opy = ai3[1];
				opz = ai3[2];
			}
			
		}
		
		if (change == true)
		{
			this.buildHive(px, py, pz, opx, opy, opz);
		}
		
	}
	
	public void buildHive(int i, int j, int k, int oi, int oj, int ok)
	{
		this.worldObj.setBlock(i, j - 1, k, ModBlocks.beehive);
		this.worldObj.setBlock(i, j - 2, k, ModBlocks.beehive);
		this.worldObj.setBlock(i, j - 3, k, ModBlocks.beehive);
		this.worldObj.setBlock(i, j - 4, k, ModBlocks.beehive);
		this.worldObj.setBlock(i, j - 5, k, ModBlocks.beehive);
		this.worldObj.setBlock(i, j - 6, k, ModBlocks.beehive);
		
		for (int x = -7; x < 7; x++)
		{
			for (int y = -7; y < 7; y++)
			{
				for (int z = -7; z < 7; z++)
				{
					if (RadixMath.getDistanceToXYZ(i + x, j + y, k + z, i, j - 3, k) < 6)
					{
						this.worldObj.setBlock(i + x, j + y, k + z, ModBlocks.beehive);
					}
				}
			}
		}
		
		if (oi > i)
		{
			for (int x = i; x < oi; x++)
			{
				this.worldObj.setBlock(x, j, k, Blocks.log);
			}
			return;
		}
		if (i > oi)
		{
			for (int x = oi; x < i; x++)
			{
				this.worldObj.setBlock(x, j, k, Blocks.log);
			}
			return;
		}
		
		if (ok > k)
		{
			for (int z = k; z < ok; z++)
			{
				this.worldObj.setBlock(i, j, z, Blocks.log);
			}
			return;
		}
		if (k > ok)
		{
			for (int z = ok; z < k; z++)
			{
				this.worldObj.setBlock(i, j, z, Blocks.log);
			}
			return;
		}
		
		// worldObj.setBlock(i, j-6, k, 1);
	}
	
	public boolean checkForHive(int i, int j, int k)
	{
		if (this.worldObj.getBlock(i, j, k) == Blocks.leaves || this.worldObj.getBlock(i, j, k) == Blocks.log)
		{
			if (this.worldObj.isAirBlock(i, j - 1, k) && this.worldObj.isAirBlock(i, j - 2, k) && this.worldObj.isAirBlock(i, j - 3, k) && this.worldObj.isAirBlock(i, j - 4, k) && this.worldObj.isAirBlock(i, j - 5, k) && this.worldObj.isAirBlock(i, j - 6, k) && this.worldObj.isAirBlock(i, j - 7, k) && this.worldObj.isAirBlock(i, j - 8, k))
			{
				return true;
			}
		}
		return false;
	}
	
	void func_525_d()
	{
		int i = 0;
		final int j = this.field_868_o.length;
		final int ai[] = { this.basePos[0], this.basePos[1], this.basePos[2] };
		for (; i < j; i++)
		{
			final int ai1[] = this.field_868_o[i];
			final int ai2[] = { ai1[0], ai1[1], ai1[2] };
			ai[1] = ai1[3];
			final int k = ai[1] - this.basePos[1];
			if (this.func_527_c(k))
			{
				this.func_522_a(ai, ai2, 17);
			}
		}
		
	}
	
	int func_524_a(int ai[], int ai1[])
	{
		final int ai2[] = { 0, 0, 0 };
		byte byte0 = 0;
		int i = 0;
		for (; byte0 < 3; byte0++)
		{
			ai2[byte0] = ai1[byte0] - ai[byte0];
			if (Math.abs(ai2[byte0]) > Math.abs(ai2[i]))
			{
				i = byte0;
			}
		}
		
		if (ai2[i] == 0)
		{
			return -1;
		}
		final byte byte1 = field_882_a[i];
		final byte byte2 = field_882_a[i + 3];
		byte byte3;
		if (ai2[i] > 0)
		{
			byte3 = 1;
		}
		else
		{
			byte3 = -1;
		}
		final double d = (double) ai2[byte1] / (double) ai2[i];
		final double d1 = (double) ai2[byte2] / (double) ai2[i];
		final int ai3[] = { 0, 0, 0 };
		int j = 0;
		final int k = ai2[i] + byte3;
		do
		{
			if (j == k)
			{
				break;
			}
			ai3[i] = ai[i] + j;
			ai3[byte1] = MathHelper.floor_double(ai[byte1] + j * d);
			ai3[byte2] = MathHelper.floor_double(ai[byte2] + j * d1);
			final Block l = this.worldObj.getBlock(ai3[0], ai3[1], ai3[2]);
			if (l != Blocks.air && l != Blocks.leaves)
			{
				break;
			}
			j += byte3;
		}
		while (true);
		if (j == k)
		{
			return -1;
		}
		else
		{
			return Math.abs(j);
		}
	}
	
	boolean func_519_e()
	{
		final int ai[] = { this.basePos[0], this.basePos[1], this.basePos[2] };
		final int ai1[] = { this.basePos[0], this.basePos[1] + this.field_878_e - 1, this.basePos[2] };
		final Block i = this.worldObj.getBlock(this.basePos[0], this.basePos[1] - 1, this.basePos[2]);
		if (i != Blocks.grass && i != Blocks.dirt)
		{
			return false;
		}
		final int j = this.func_524_a(ai, ai1);
		if (j == -1)
		{
			return true;
		}
		if (j < 6)
		{
			return false;
		}
		else
		{
			this.field_878_e = j * 2;
			return true;
		}
	}
	
	public void func_517_a(double d, double d1, double d2)
	{
		this.field_870_m = (int) (d * 12D);
		if (d > 0.5D)
		{
			this.field_869_n = 5;
		}
		this.field_873_j = d1;
		this.field_872_k = d2;
	}
	
	@Override
	public boolean generate(World world, Random random, int i, int j, int k)
	{
		if (j > 100)
		{
			return false;
		}
		this.worldObj = world;
		
		boolean flag = true;
		for (int j1 = j; j1 <= j + 1 + i; j1++)
		{
			byte byte0 = 2;
			
			if (j1 == j)
			{
				byte0 = 1;
			}
			
			if (j1 >= j + 1 + i - 2)
			{
				byte0 = 2;
			}
			
			for (int i1 = i - byte0; i1 <= i + byte0 && flag; i1++)
			{
				for (int k1 = k - byte0; k1 <= k + byte0 && flag; k1++)
				{
					if (j1 >= 0 && j1 < 256)
					{
						final Block b = world.getBlock(i1, j1, k1);
						
						if (b != Blocks.air && b != Blocks.leaves && b != Blocks.grass && b != Blocks.dirt && b != Blocks.log && b != Blocks.sapling)
						{
							flag = false;
						}
					}
					else
					{
						flag = false;
					}
				}
			}
		}
		
		if (!flag)
		{
			return false;
		}
		
		final Block b = world.getBlock(i, j - 1, k);
		
		if (b != Blocks.grass && b != Blocks.dirt || j >= 256 - i - 1)
		{
			return false;
		}
		
		world.setBlock(i, j - 1, k, Blocks.dirt);
		world.setBlock(i + 1, j - 1, k, Blocks.dirt);
		world.setBlock(i, j - 1, k + 1, Blocks.dirt);
		world.setBlock(i + 1, j - 1, k + 1, Blocks.dirt);
		
		final long l = random.nextLong();
		this.field_881_b.setSeed(l);
		this.basePos[0] = i;
		this.basePos[1] = j;
		this.basePos[2] = k;
		if (this.field_878_e == 0)
		{
			this.field_878_e = (10 + this.field_881_b.nextInt(this.field_870_m)) * 2;
		}
		if (!this.func_519_e())
		{
			return false;
		}
		else
		{
			this.func_521_a();
			this.func_518_b();
			this.func_529_c();
			this.func_525_d();
			this.beehive();
			return true;
		}
	}
	
	static final byte field_882_a[] = { 2, 0, 0, 1, 2, 1 };
	Random field_881_b;
	World worldObj;
	int basePos[] = { 0, 0, 0 };
	int field_878_e;
	int height;
	double field_876_g;
	double field_875_h;
	double field_874_i;
	double field_873_j;
	double field_872_k;
	int field_871_l;
	int field_870_m;
	int field_869_n;
	int field_868_o[][];
	
}
