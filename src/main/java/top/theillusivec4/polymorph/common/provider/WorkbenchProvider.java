/*
 * Copyright (c) 2020 C4
 *
 * This file is part of Polymorph, a mod made for Minecraft.
 *
 * Polymorph is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Polymorph is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Polymorph.  If not, see <https://www.gnu.org/licenses/>.
 */

package top.theillusivec4.polymorph.common.provider;

import java.lang.reflect.Field;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import top.theillusivec4.polymorph.Polymorph;
import top.theillusivec4.polymorph.api.PolymorphApi.IProvider;

public class WorkbenchProvider implements IProvider<WorkbenchContainer> {

  private static final Field CRAFT_MATRIX = ObfuscationReflectionHelper
      .findField(WorkbenchContainer.class, "field_75162_e");

  @Override
  public CraftingInventory getCraftingMatrix(WorkbenchContainer container) {
    CraftingInventory craftingInventory = null;

    try {
      craftingInventory = (CraftingInventory) CRAFT_MATRIX.get(container);
    } catch (IllegalAccessException e) {
      Polymorph.LOGGER
          .error("Whoops, something went wrong while trying to retrieve the crafting matrix!");
    }
    return craftingInventory;
  }

  @Override
  public Slot getOutputSlot(WorkbenchContainer container) {
    return container.getSlot(container.getOutputSlot());
  }

  @Override
  public int getXOffset() {
    return 36;
  }

  @Override
  public int getYOffset() {
    return -72;
  }
}