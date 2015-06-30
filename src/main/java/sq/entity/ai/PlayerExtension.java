package sq.entity.ai;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import sq.entity.EntityWebslinger;

public class PlayerExtension implements IExtendedEntityProperties
{
	public static final String					ID	= "SQPlayerExtension";
	public EntityWebslinger						webEntity;
	private final EntityPlayer					player;
	public int slingerCooldown;

	public PlayerExtension(EntityPlayer player)
	{
		this.player = player;
	}

	@Override
	public void saveNBTData(NBTTagCompound nbt)
	{
	}

	@Override
	public void loadNBTData(NBTTagCompound nbt)
	{
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
}