package sq.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import radixcore.util.RadixMath;
import sq.core.SpiderCore;
import sq.core.minecraft.ModItems;
import sq.entity.EntityHuman;

public enum EnumHumanType 
{
	WARRIOR(1),
	ARCHER(2),
	MINER(3),
	PROSPECTOR(4),
	NOOB(5),
	NOMAD(6),
	FARMER(7);

	private int id;

	EnumHumanType (int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return id;
	}
	
	public static EnumHumanType getAtRandom()
	{
		int index = SpiderCore.rand.nextInt(values().length);
		return values()[index];
	}
	
	public static EnumHumanType byId(int id)
	{
		for (EnumHumanType type : values())
		{
			if (type.id == id)
			{
				return type;
			}
		}

		return null;
	}

	public ItemStack[] getDropsForType(EntityHuman human)
	{
		List<ItemStack> list = new ArrayList<ItemStack>();
		boolean flag = false;
		
		int fortuneLevel = human.getFortuneLevel();
		
		switch (this)
		{
		case ARCHER:
			list.add(new ItemStack(Items.bow, 1));
			list.add(new ItemStack(Items.arrow, RadixMath.getNumberInRange(0, 32)));
			
			//Fall through to warrior, set a flag that will prevent swords from being added
			//to the archer's inventory.
			flag = true;
			
		case WARRIOR:
			list.add(new ItemStack(Items.rotten_flesh, RadixMath.getNumberInRange(0, 6)));
			list.add(new ItemStack(Items.gunpowder, RadixMath.getNumberInRange(0, 3)));
			list.add(new ItemStack(Items.bread, RadixMath.getNumberInRange(0, 2)));
			list.add(new ItemStack(Items.spider_eye, RadixMath.getNumberInRange(0, 3)));
			list.add(new ItemStack(Items.leather, RadixMath.getNumberInRange(0, 4)));
			list.add(new ItemStack(Items.wooden_sword, RadixMath.getNumberInRange(0, 1)));
			list.add(new ItemStack(Items.bone, RadixMath.getNumberInRange(0, 5)));

			if (!flag)
			{
				if (fortuneLevel == 1)
				{
					list.add(new ItemStack(Items.iron_sword, 1));
				}

				else if (fortuneLevel == 2)
				{
					list.add(new ItemStack(Items.diamond_sword, 1));
				}
			}
			
			break;
		
		case MINER:
			list.add(new ItemStack(Items.wooden_shovel, RadixMath.getNumberInRange(0, 1)));
			list.add(new ItemStack(Items.wooden_pickaxe, RadixMath.getNumberInRange(0, 1)));
			list.add(new ItemStack(Items.coal, RadixMath.getNumberInRange(0, 8)));
			list.add(new ItemStack(Blocks.cobblestone, RadixMath.getNumberInRange(0, 64)));
			list.add(new ItemStack(Blocks.dirt, RadixMath.getNumberInRange(0, 32)));
			list.add(new ItemStack(Blocks.torch, RadixMath.getNumberInRange(0, 15)));
			
			if (fortuneLevel == 1)
			{
				list.add(new ItemStack(Items.iron_pickaxe, RadixMath.getNumberInRange(0, 1)));
				list.add(new ItemStack(Blocks.iron_ore, RadixMath.getNumberInRange(0, 16)));
				list.add(new ItemStack(Items.redstone, RadixMath.getNumberInRange(0, 32)));
				list.add(new ItemStack(Blocks.gold_ore, RadixMath.getNumberInRange(0, 5)));
			}
			
			else if (fortuneLevel == 2)
			{
				list.add(new ItemStack(Items.diamond_pickaxe, RadixMath.getNumberInRange(0, 1)));
				list.add(new ItemStack(Items.diamond_shovel, RadixMath.getNumberInRange(0, 1)));
				list.add(new ItemStack(Items.diamond, RadixMath.getNumberInRange(0, 3)));
				list.add(new ItemStack(Items.emerald, RadixMath.getNumberInRange(0, 1)));
			}
			
			break;
			
		case PROSPECTOR:
			list.add(new ItemStack(Items.iron_shovel, RadixMath.getNumberInRange(0, 1)));
			list.add(new ItemStack(Items.iron_pickaxe, RadixMath.getNumberInRange(0, 1)));
			list.add(new ItemStack(Blocks.torch, RadixMath.getNumberInRange(0, 9)));
			list.add(new ItemStack(Items.coal, RadixMath.getNumberInRange(0, 32)));
			list.add(new ItemStack(Items.iron_ingot, RadixMath.getNumberInRange(0, 32)));
			list.add(new ItemStack(Blocks.tnt, RadixMath.getNumberInRange(0, 4)));
			
			if (fortuneLevel == 1)
			{
				list.add(new ItemStack(Items.gold_ingot, RadixMath.getNumberInRange(0, 8)));
			}
			
			else if (fortuneLevel == 2)
			{
				list.add(new ItemStack(Items.diamond, RadixMath.getNumberInRange(0, 7)));
				list.add(new ItemStack(Items.emerald, RadixMath.getNumberInRange(0, 3)));
			}
			
			break;

		case NOOB:
			list.add(new ItemStack(Blocks.log, RadixMath.getNumberInRange(0, 12), 0));
			list.add(new ItemStack(Items.stick, RadixMath.getNumberInRange(0, 4), 0));
			list.add(new ItemStack(Items.wooden_shovel, RadixMath.getNumberInRange(0, 1)));
			list.add(new ItemStack(Items.wooden_sword, RadixMath.getNumberInRange(0, 1)));
			list.add(new ItemStack(Items.porkchop, RadixMath.getNumberInRange(0, 4)));
			list.add(new ItemStack(Items.chicken, RadixMath.getNumberInRange(0, 3)));
			list.add(new ItemStack(Items.feather, RadixMath.getNumberInRange(0, 7)));
			list.add(new ItemStack(Blocks.yellow_flower, RadixMath.getNumberInRange(0, 3)));
			list.add(new ItemStack(Blocks.dirt, RadixMath.getNumberInRange(0, 12)));
			break;
			
		case NOMAD:
			list.add(new ItemStack(Blocks.torch, RadixMath.getNumberInRange(0, 16)));
			list.add(new ItemStack(Blocks.planks, RadixMath.getNumberInRange(0, 32)));
			list.add(new ItemStack(Blocks.glass, RadixMath.getNumberInRange(0, 16)));
			list.add(new ItemStack(Blocks.log, RadixMath.getNumberInRange(0, 16)));
			list.add(new ItemStack(Blocks.cobblestone, RadixMath.getNumberInRange(0, 16)));
			list.add(new ItemStack(Blocks.chest, RadixMath.getNumberInRange(0, 1)));
			list.add(new ItemStack(Blocks.crafting_table, RadixMath.getNumberInRange(0, 1)));
			list.add(new ItemStack(Blocks.furnace, RadixMath.getNumberInRange(0, 2)));
			list.add(new ItemStack(Items.cooked_beef, RadixMath.getNumberInRange(0, 7)));
			list.add(new ItemStack(Items.cooked_chicken, RadixMath.getNumberInRange(0, 7)));
			list.add(new ItemStack(Items.stone_sword, RadixMath.getNumberInRange(0, 1)));
			
			if (fortuneLevel == 1)
			{
				list.add(new ItemStack(Items.compass, RadixMath.getNumberInRange(0, 1)));
				list.add(new ItemStack(Items.clock, RadixMath.getNumberInRange(0, 1)));
				list.add(new ItemStack(Items.book, RadixMath.getNumberInRange(0, 3)));
				list.add(new ItemStack(Blocks.bookshelf, RadixMath.getNumberInRange(0, 2)));
				list.add(new ItemStack(Items.iron_sword, RadixMath.getNumberInRange(0, 1)));
			}
			
			else if (fortuneLevel == 2)
			{
				list.add(new ItemStack(Blocks.enchanting_table, RadixMath.getNumberInRange(0, 1)));
				list.add(new ItemStack(Items.diamond_sword, RadixMath.getNumberInRange(0, 1)));
				list.add(new ItemStack(Items.diamond, RadixMath.getNumberInRange(0, 4)));
				list.add(new ItemStack(Items.emerald, RadixMath.getNumberInRange(0, 12)));
			}
			
			break;
			
		case FARMER:
			list.add(new ItemStack(Items.wheat, RadixMath.getNumberInRange(0, 16)));
			list.add(new ItemStack(Items.wheat_seeds, RadixMath.getNumberInRange(0, 8)));
			list.add(new ItemStack(Items.bread, RadixMath.getNumberInRange(0, 3)));
			list.add(new ItemStack(Items.chicken, RadixMath.getNumberInRange(0, 4)));
			list.add(new ItemStack(Items.wooden_hoe, RadixMath.getNumberInRange(0, 1)));
			
			if (fortuneLevel == 1)
			{
				list.add(new ItemStack(Items.iron_hoe, RadixMath.getNumberInRange(0, 1)));
				list.add(new ItemStack(Items.potato, RadixMath.getNumberInRange(0, 16)));
				list.add(new ItemStack(Items.carrot, RadixMath.getNumberInRange(0, 16)));
			}
			
			else if (fortuneLevel == 2)
			{
				list.add(new ItemStack(Items.diamond_hoe, RadixMath.getNumberInRange(0, 1)));
				list.add(new ItemStack(ModItems.nectar, RadixMath.getNumberInRange(0, 32)));
				list.add(new ItemStack(ModItems.rareFruit, RadixMath.getNumberInRange(0, 3)));
				list.add(new ItemStack(ModItems.mandragoraSeeds, RadixMath.getNumberInRange(0, 3)));
				list.add(new ItemStack(Items.emerald, RadixMath.getNumberInRange(0, 2)));
			}
		}
		
		//Standard drop rate from the original mod.
		Random r = new Random();
		if(r.nextInt(100) < 17) { list.add(new ItemStack(ModItems.skull)); }
		if(r.nextInt(100) < 17) { list.add(new ItemStack(ModItems.heart)); }
		if(r.nextInt(100) < 17) { list.add(new ItemStack(ModItems.brain)); }

		//Remove empty stacks, not sure if trying to drop them would cause issue.
		for (int i = list.size() - 1; i > 0; i--)
		{
			ItemStack stack = list.get(i);
			
			if (stack.stackSize == 0)
			{
				list.remove(i);
			}
		}
			
		return list.toArray(new ItemStack[list.size()]);
	}
}
