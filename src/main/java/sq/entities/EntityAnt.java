package sq.entities;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import sq.enums.EnumAntType;

public abstract class EntityAnt extends Entity
{

	public EntityAnt(World worldIn) {
		super(worldIn);
		// TODO Auto-generated constructor stub
	}

	public EnumAntType getAntType() {
		// TODO Auto-generated method stub
		return EnumAntType.BLACK;
	}

}
