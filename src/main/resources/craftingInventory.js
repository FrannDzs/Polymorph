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

function initializeCoreMod() {
  return {
    'coremodone': {
      'target': {
        'type': 'CLASS',
        'name': 'net.minecraft.inventory.CraftingInventory'
      },
      'transformer': function (classNode) {
        print("Initializing transformation ", classNode.toString());
        var opcodes = Java.type('org.objectweb.asm.Opcodes');
        var MethodInsnNode = Java.type(
            'org.objectweb.asm.tree.MethodInsnNode');
        var methods = classNode.methods;
        var found = 0;

        for (m in methods) {
          var method = methods[m];
          var code = method.instructions;
          var instr = code.toArray();

          if (method.name === "decrStackSize" || method.name
              === "func_70298_a") {
            print("Found method decrStackSize ", method.toString());
            found++;

            for (var i = 0; i < instr.length; i++) {
              var instruction = instr[i];

              if (instruction.getOpcode() === opcodes.IFNE) {
                var inst = instruction.getNext();
                print("Found node ", inst.toString());
                code.insert(inst, new MethodInsnNode(opcodes.INVOKESTATIC,
                    "top/theillusivec4/polymorph/common/PolymorphHooks",
                    "onCraftMatrixChanged", "()V", false));
                break;
              }
            }
          } else if (method.name === "setInventorySlotContents" || method.name
              === "func_70299_a") {
            print("Found method setInventorySlotContents ", method.toString());
            found++;

            if (instr.length > 0) {
              var instruction1 = instr[0];
              print("Found node ", instruction1.toString());
              code.insert(instruction1, new MethodInsnNode(opcodes.INVOKESTATIC,
                  "top/theillusivec4/polymorph/common/PolymorphHooks",
                  "onCraftMatrixChanged", "()V", false));
            }
          }

          if (found >= 2) {
            break;
          }
        }
        return classNode;
      }
    }
  }
}