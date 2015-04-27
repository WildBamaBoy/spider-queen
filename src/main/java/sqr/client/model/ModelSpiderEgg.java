package sqr.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
//Exported java file
//Keep in mind that you still need to fill in some blanks
// - ZeuX
import net.minecraft.entity.Entity;

public class ModelSpiderEgg extends ModelBase
{

	public ModelSpiderEgg()
	{
		final float scale = 0F;
		this.New_Shape = new ModelRenderer(this,0, 4);
		this.New_Shape.addBox(-3F, -3F, -3F, 6, 6, 6, scale);
		this.New_Shape.setRotationPoint(0F, 1F, 0F);

		this.New_Shape1 = new ModelRenderer(this,0, 0);
		this.New_Shape1.addBox(-2F, -2F, -1F, 2, 2, 2, scale);
		this.New_Shape1.setRotationPoint(1F, 2F, 0F);

		this.New_Shape2 = new ModelRenderer(this,28, 8);
		this.New_Shape2.addBox(0F, 0F, 0F, 4, 4, 4, scale);
		this.New_Shape2.setRotationPoint(-2F, -1F, -2F);

	}
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		//for animation
		//setRotationAngles(f, f1, f2, f3, f4, f5);
		this.New_Shape.render(f5);
		this.New_Shape1.render(f5);
		this.New_Shape2.render(f5);
	}

	//fields

	ModelRenderer New_Shape;
	ModelRenderer New_Shape1;
	ModelRenderer New_Shape2;

}
