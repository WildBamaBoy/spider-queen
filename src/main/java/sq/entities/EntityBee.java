package sq.entities;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import sq.enums.EnumBeeType;

public abstract class EntityBee extends Entity
{

	public EntityBee(World worldIn) {
		super(worldIn);
		// TODO Auto-generated constructor stub
	}

	public EnumBeeType getBeeType() {
		// TODO Auto-generated method stub
		return EnumBeeType.WARRIOR;
	}

}
