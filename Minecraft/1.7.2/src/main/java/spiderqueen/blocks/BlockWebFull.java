/*******************************************************************************
 * BlockWebFull.java
 * Copyright (c) 2014 Radix-Shock Entertainment.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package spiderqueen.blocks;

import net.minecraft.world.IBlockAccess;

public class BlockWebFull extends BlockWeb
{
	public BlockWebFull(boolean isPoison)
	{
		super(isPoison);
	}

	@Override
	public int getRenderType()
	{
		return 1;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int posX, int posY, int posZ)
	{
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}
}
