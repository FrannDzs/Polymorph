/*
 * Copyright (c) 2020 C4
 *
 * This file is part of Polymorph, a mod made for Minecraft.
 *
 * Polymorph is free software: you can redistribute it and/or modify it under the terms of the GNU
 * Lesser General Public License as published by the Free Software Foundation, either version 3 of
 * the License, or any later version.
 *
 * Polymorph is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with Polymorph.
 * If not, see <https://www.gnu.org/licenses/>.
 */

package top.theillusivec4.polymorph.api.type;

import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.List;
import java.util.Set;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public interface IRecipeSelector<I extends IInventory, R extends IRecipe<I>> {

  IPolyProvider<I, R> getProvider();

  void selectRecipe(R recipe);

  void highlightRecipe(String recipe);

  void setRecipes(List<R> recipes, World world, boolean refresh, String selected);

  void setRecipes(Set<String> recipes, World world, boolean refresh, String selected);

  void tick();

  void render(MatrixStack matrixStack, int mouseX, int mouseY, float renderPartialTicks);

  boolean mouseClicked(double mouseX, double mouseY, int button);

  void markUpdatePosition();
}

