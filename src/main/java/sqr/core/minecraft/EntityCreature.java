package sqr.core.minecraft;

//package sqr.entity;
//
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.pathfinding.PathEntity;
//import net.minecraft.profiler.Profiler;
//import net.minecraft.util.MathHelper;
//import net.minecraft.world.World;
//
//public abstract class EntityCreature extends EntityLivingBase
//{
//    private PathEntity pathToEntity;
//
//
//    protected Entity entityToAttack;
//
//
//    protected boolean hasAttacked;
//
//
//    protected int fleeingTick;
//
//    public EntityCreature(World par1World)
//    {
//        super(par1World);
//        hasAttacked = false;
//        fleeingTick = 0;
//    }
//
//
//    protected boolean isMovementCeased()
//    {
//        return false;
//    }
//
//    protected void updateEntityActionState()
//    {
//        Profiler.startSection("ai");
//
//        if (fleeingTick > 0)
//        {
//            fleeingTick--;
//        }
//
//        hasAttacked = isMovementCeased();
//        float f = 16F;
//
//        if (entityToAttack == null)
//        {
//            entityToAttack = findPlayerToAttack();
//
//            if (entityToAttack != null)
//            {
//                pathToEntity = worldObj.getPathEntityToEntity(this, entityToAttack, f, true, false, false, true);
//            }
//        }
//        else if (!entityToAttack.isEntityAlive())
//        {
//            entityToAttack = null;
//        }
//        else
//        {
//            float f1 = entityToAttack.getDistanceToEntity(this);
//
//            if (canEntityBeSeen(entityToAttack))
//            {
//                attackEntity(entityToAttack, f1);
//            }
//            else
//            {
//                attackBlockedEntity(entityToAttack, f1);
//            }
//        }
//
//        Profiler.endSection();
//
//        if (!hasAttacked && entityToAttack != null && (pathToEntity == null || rand.nextInt(20) == 0))
//        {
//            pathToEntity = worldObj.getPathEntityToEntity(this, entityToAttack, f, true, false, false, true);
//        }
//        else if (!hasAttacked && (pathToEntity == null && rand.nextInt(180) == 0 || rand.nextInt(120) == 0 || fleeingTick > 0) && entityAge < 100)
//        {
//            updateWanderPath();
//        }
//
//        int i = MathHelper.floor_double(boundingBox.minY + 0.5D);
//        boolean flag = isInWater();
//        boolean flag1 = handleLavaMovement();
//        rotationPitch = 0.0F;
//
//        if (pathToEntity == null || rand.nextInt(100) == 0)
//        {
//            super.updateEntityActionState();
//            pathToEntity = null;
//            return;
//        }
//
//        Profiler.startSection("followpath");
//        Vec3D vec3d = pathToEntity.getCurrentNodeVec3d(this);
//
//        for (double d = width * 2.0F; vec3d != null && vec3d.squareDistanceTo(posX, vec3d.yCoord, posZ) < d * d;)
//        {
//            pathToEntity.incrementPathIndex();
//
//            if (pathToEntity.isFinished())
//            {
//                vec3d = null;
//                pathToEntity = null;
//            }
//            else
//            {
//                vec3d = pathToEntity.getCurrentNodeVec3d(this);
//            }
//        }
//
//        isJumping = false;
//
//        if (vec3d != null)
//        {
//            double d1 = vec3d.xCoord - posX;
//            double d2 = vec3d.zCoord - posZ;
//            double d3 = vec3d.yCoord - (double)i;
//            float f2 = (float)((Math.atan2(d2, d1) * 180D) / Math.PI) - 90F;
//            float f3 = f2 - rotationYaw;
//            moveForward = moveSpeed;
//
//            for (; f3 < -180F; f3 += 360F) { }
//
//            for (; f3 >= 180F; f3 -= 360F) { }
//
//            if (f3 > 30F)
//            {
//                f3 = 30F;
//            }
//
//            if (f3 < -30F)
//            {
//                f3 = -30F;
//            }
//
//            rotationYaw += f3;
//
//            if (hasAttacked && entityToAttack != null)
//            {
//                double d4 = entityToAttack.posX - posX;
//                double d5 = entityToAttack.posZ - posZ;
//                float f5 = rotationYaw;
//                rotationYaw = (float)((Math.atan2(d5, d4) * 180D) / Math.PI) - 90F;
//                float f4 = (((f5 - rotationYaw) + 90F) * (float)Math.PI) / 180F;
//                moveStrafing = -MathHelper.sin(f4) * moveForward * 1.0F;
//                moveForward = MathHelper.cos(f4) * moveForward * 1.0F;
//            }
//
//            if (d3 > 0.0D)
//            {
//                isJumping = true;
//            }
//        }
//
//        if (entityToAttack != null)
//        {
//            faceEntity(entityToAttack, 30F, 30F);
//        }
//
//        if (isCollidedHorizontally && !hasPath())
//        {
//            isJumping = true;
//        }
//
//        if (rand.nextFloat() < 0.8F && (flag || flag1))
//        {
//            isJumping = true;
//        }
//
//		spiderBaitCode(); // SNIP
//        Profiler.endSection();
//    }
//
//
//    protected void updateWanderPath()
//    {
//        Profiler.startSection("stroll");
//        boolean flag = false;
//        int i = -1;
//        int j = -1;
//        int k = -1;
//        float f = -99999F;
//
//        for (int l = 0; l < 10; l++)
//        {
//            int i1 = MathHelper.floor_double((posX + (double)rand.nextInt(13)) - 6D);
//            int j1 = MathHelper.floor_double((posY + (double)rand.nextInt(7)) - 3D);
//            int k1 = MathHelper.floor_double((posZ + (double)rand.nextInt(13)) - 6D);
//            float f1 = getBlockPathWeight(i1, j1, k1);
//
//            if (f1 > f)
//            {
//                f = f1;
//                i = i1;
//                j = j1;
//                k = k1;
//                flag = true;
//            }
//        }
//
//        if (flag)
//        {
//            pathToEntity = worldObj.getEntityPathToXYZ(this, x, y, z, 10F, true, false, false, true);
//        }
//
//		spiderBaitCode(); // SNIP
//        Profiler.endSection();
//    }
//
//
//    protected void attackEntity(Entity entity, float f)
//    {
//    }
//
//
//    protected void attackBlockedEntity(Entity entity, float f)
//    {
//    }
//
//
//    public float getBlockPathWeight(int par1, int par2, int par3)
//    {
//        return 0.0F;
//    }
//
//
//    protected Entity findPlayerToAttack()
//    {
//        return null;
//    }
//
//
//    public boolean getCanSpawnHere()
//    {
//        int i = MathHelper.floor_double(posX);
//        int j = MathHelper.floor_double(boundingBox.minY);
//        int k = MathHelper.floor_double(posZ);
//        return super.getCanSpawnHere() && getBlockPathWeight(x, y, z) >= 0.0F;
//    }
//
//
//    public boolean hasPath()
//    {
//        return pathToEntity != null;
//    }
//
//
//    public void setPathToEntity(PathEntity par1PathEntity)
//    {
//        pathToEntity = par1PathEntity;
//    }
//
//
//    public Entity getEntityToAttack()
//    {
//        return entityToAttack;
//    }
//
//
//    public void setTarget(Entity par1Entity)
//    {
//        entityToAttack = par1Entity;
//    }
//
//
//    protected float getSpeedModifier()
//    {
//        if (isAIEnabled())
//        {
//            return 1.0F;
//        }
//
//        float f = super.getSpeedModifier();
//
//        if (fleeingTick > 0)
//        {
//            f *= 2.0F;
//        }
//
//        return f;
//    }
// }
