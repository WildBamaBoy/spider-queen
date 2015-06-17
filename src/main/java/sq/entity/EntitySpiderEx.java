package sq.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;

public class EntitySpiderEx extends EntityCreature implements IWebClimber
{
	public EntitySpiderEx(World world) 
	{
		super(world);
	}
}
