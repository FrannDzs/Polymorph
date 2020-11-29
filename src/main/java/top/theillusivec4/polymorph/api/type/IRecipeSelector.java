package top.theillusivec4.polymorph.api.type;

import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.List;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public interface IRecipeSelector<I extends IInventory, R extends IRecipe<I>> {

  void tick();

  void selectRecipe(R recipe);

  void setRecipes(List<R> recipes, World world, boolean refresh);

  void render(MatrixStack matrixStack, int mouseX, int mouseY, float renderPartialTicks);

  boolean mouseClicked(double mouseX, double mouseY, int button);

  void markUpdatePosition();
}
