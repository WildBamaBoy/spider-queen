package sq.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import sq.entity.creature.EntityWebslinger;

/**
 * Stores extra data about the player it is attached to.
 */
public class PlayerExtension implements IExtendedEntityProperties
{
	public static final String					ID	= "SQPlayerExtension";
	
	public EntityWebslinger						webEntity;
	private final EntityPlayer					player;
	public int slingerCooldown;
	private int monstersKilled;
	
	public PlayerExtension(EntityPlayer player)
	{
		this.player = player;
	}

	@Override
	public void saveNBTData(NBTTagCompound nbt)
	{
		nbt.setInteger("monstersKilled", monstersKilled);
	}

	@Override
	public void loadNBTData(NBTTagCompound nbt)
	{
		nbt.getInteger("monstersKilled");
	}

	@Override
	public void init(Entity entity, World world)
	{
	}

	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(PlayerExtension.ID, new PlayerExtension(player));
	}

	public EntityPlayer getPlayer()
	{
		return player;
	}
	
	public void tick()
	{
		if (slingerCooldown > 0)
		{
			slingerCooldown--;
		}
	}
	
	public static PlayerExtension get(EntityPlayer player)
	{
		return (PlayerExtension) player.getExtendedProperties(ID);
	}

	public int getMonstersKilled() 
	{
		return monstersKilled;
	}
	
	public void setMonstersKilled(int value)
	{
		monstersKilled = value;
	}
}