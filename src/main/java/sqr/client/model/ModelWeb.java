package sqr.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
//Exported java file
//Keep in mind that you still need to fill in some blanks
// - ZeuX
import net.minecraft.entity.Entity;

public class ModelWeb extends ModelBase
{

	public ModelWeb()
	{
		final float scale = 0F;
		this.Front = new ModelRenderer(this,0, 4);
		this.Front.addBox(-3F, -3F, -3F, 6, 6, 6, scale);
		this.Front.setRotationPoint(0F, 21F, 0F);

		this.back = new ModelRenderer(this,0, 0);
		this.back.addBox(-2F, -2F, -1F, 2, 2, 2, scale);
		this.back.setRotationPoint(1F, 22F, 9F);

		this.mid = new ModelRenderer(this,28, 8);
		this.mid.addBox(0F, 0F, 0F, 4, 4, 4, scale);
		this.mid.setRotationPoint(-2F, 19F, 3F);

		this.New_Shape = new ModelRenderer(this,29, 8);
		this.New_Shape.addBox(0F, 0F, 0F, 4, 4, 4, scale);
		this.New_Shape.setRotationPoint(-2F, 20F, -2F);

	}
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		//for animation
		//setRotationAngles(f, f1, f2, f3, f4, f5);
		this.Front.render(f5);
		this.back.render(f5);
		this.mid.render(f5);
		this.New_Shape.render(f5);
	}

	//fields

	ModelRenderer Front;
	ModelRenderer back;
	ModelRenderer mid;
	ModelRenderer New_Shape;

}
