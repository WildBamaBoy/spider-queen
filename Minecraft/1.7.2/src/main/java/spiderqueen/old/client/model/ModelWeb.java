package spiderqueen.old.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
//Exported java file
//Keep in mind that you still need to fill in some blanks
// - ZeuX

public class ModelWeb extends ModelBase
{

public ModelWeb()
{
	float scale = 0F;
	Front = new ModelRenderer(this,0, 4);
	Front.addBox(-3F, -3F, -3F, 6, 6, 6, scale);
	Front.setRotationPoint(0F, 21F, 0F);

	back = new ModelRenderer(this,0, 0);
	back.addBox(-2F, -2F, -1F, 2, 2, 2, scale);
	back.setRotationPoint(1F, 22F, 9F);

	mid = new ModelRenderer(this,28, 8);
	mid.addBox(0F, 0F, 0F, 4, 4, 4, scale);
	mid.setRotationPoint(-2F, 19F, 3F);

	New_Shape = new ModelRenderer(this,29, 8);
	New_Shape.addBox(0F, 0F, 0F, 4, 4, 4, scale);
	New_Shape.setRotationPoint(-2F, 20F, -2F);

}
public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
{
//for animation
//setRotationAngles(f, f1, f2, f3, f4, f5);
	Front.render(f5);
	back.render(f5);
	mid.render(f5);
	New_Shape.render(f5);
}

//fields

	ModelRenderer Front;
	ModelRenderer back;
	ModelRenderer mid;
	ModelRenderer New_Shape;

}
