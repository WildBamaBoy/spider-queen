package sqr.core.minecraft;

//package sqr.item;
//
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.EnumAction;
//import net.minecraft.item.ItemStack;
//import net.minecraft.potion.PotionEffect;
//import net.minecraft.world.World;
//
//public class ItemFood extends Item
//{
//    public final int field_35430_a = 32;
//
//
//    private final int healAmount;
//    private final float saturationModifier;
//
//
//    private final boolean isWolfsFavoriteMeat;
//
//
//    private boolean alwaysEdible;
//
//
//    private int potionId;
//
//
//    private int potionDuration;
//
//
//    private int potionAmplifier;
//
//
//    private float potionEffectProbability;
//
//    public ItemFood(int par1, int par2, float par3, boolean par4)
//    {
//        super(par1);
//        healAmount = par2;
//        isWolfsFavoriteMeat = par4;
//        saturationModifier = par3;
//    }
//
//    public ItemFood(int par1, int par2, boolean par3)
//    {
//        this(par1, par2, 0.6F, par3);
//    }
//
//    public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
//    {
//
//		par3EntityPlayer.entityDropItem(new ItemStack(Items.string, healAmount, 0), 0); // SNIP
//
//        par1ItemStack.stackSize--;
//        par3EntityPlayer.getFoodStats().addStats(this);
//        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
//
//        if (!par2World.isRemote && potionId > 0 && par2World.rand.nextFloat() < potionEffectProbability)
//        {
//            par3EntityPlayer.addPotionEffect(new PotionEffect(potionId, potionDuration * 20, potionAmplifier));
//        }
//
//        return par1ItemStack;
//    }
//
//
//    public int getMaxItemUseDuration(ItemStack par1ItemStack)
//    {
//        return 32;
//    }
//
//
//    public EnumAction getItemUseAction(ItemStack par1ItemStack)
//    {
//        return EnumAction.eat;
//    }
//
//
//    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
//    {
//
//        // if (par3EntityPlayer.canEat(alwaysEdible)) // SNIP
//        {
//            par3EntityPlayer.setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));
//        }
//
//        return par1ItemStack;
//    }
//
//    public int getHealAmount()
//    {
//        return healAmount;
//    }
//
//
//    public float getSaturationModifier()
//    {
//        return saturationModifier;
//    }
//
//
//    public boolean isWolfsFavoriteMeat()
//    {
//        return isWolfsFavoriteMeat;
//    }
//
//
//    public ItemFood setPotionEffect(int par1, int par2, int par3, float par4)
//    {
//        potionId = par1;
//        potionDuration = par2;
//        potionAmplifier = par3;
//        potionEffectProbability = par4;
//        return this;
//    }
//
//
//    public ItemFood setAlwaysEdible()
//    {
//        alwaysEdible = true;
//        return this;
//    }
//
//
//    public Item setItemName(String par1Str)
//    {
//        return super.setItemName(par1Str);
//    }
// }
