package sq.client.render;

import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sq.client.model.ModelSpiderQueen;
import sq.client.model.layer.LayerHeldItemSpider;

/*
 * Provides the textures for the spider queen model
 */
@SideOnly(Side.CLIENT)
public class RenderSpiderQueenPlayer extends RenderLivingBase<EntityPlayer>
{
	public static ResourceLocation[] SPIDER_QUEEN_TEXTURES; 
	public static ResourceLocation[] ARM_TEXTURES;
	
	private final ModelSpiderQueen	modelBipedMain;
	
    public RenderSpiderQueenPlayer(RenderManager renderManager)
    {
        super(renderManager, new ModelSpiderQueen(), 0.5F);
		modelBipedMain = new ModelSpiderQueen();
		mainModel = modelBipedMain;

		this.addLayer(new LayerHeldItemSpider(this));
		
        SPIDER_QUEEN_TEXTURES = new ResourceLocation[4];
        ARM_TEXTURES = new ResourceLocation[4];

		SPIDER_QUEEN_TEXTURES[0] = new ResourceLocation("spiderqueen:textures/entities/spider-queen-1.png");
		SPIDER_QUEEN_TEXTURES[1] = new ResourceLocation("spiderqueen:textures/entities/spider-queen-2.png");
		SPIDER_QUEEN_TEXTURES[2] = new ResourceLocation("spiderqueen:textures/entities/spider-queen-3.png");
		SPIDER_QUEEN_TEXTURES[3] = new ResourceLocation("spiderqueen:textures/entities/spider-queen-4.png");

		ARM_TEXTURES[0] = new ResourceLocation("spiderqueen:textures/entities/spider-queen-arms-1.png");
		ARM_TEXTURES[1] = new ResourceLocation("spiderqueen:textures/entities/spider-queen-arms-2.png");
		ARM_TEXTURES[2] = new ResourceLocation("spiderqueen:textures/entities/spider-queen-arms-3.png");
		ARM_TEXTURES[3] = new ResourceLocation("spiderqueen:textures/entities/spider-queen-arms-4.png");
    }

	@Override
	public void doRender(EntityPlayer entity, double x, double y, double z, float entityYaw, float partialTicks) 
	{
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPlayer entity) 
	{
		return SPIDER_QUEEN_TEXTURES[0];
	}
	
	@Override
	protected boolean canRenderName(EntityPlayer entity)
	{
		return false;
	}
}
