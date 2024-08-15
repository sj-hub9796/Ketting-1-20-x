package org.kettingpowered.ketting.patches;

import io.izzel.arclight.api.PluginPatcher;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class redirects climateSettings (f_47437_) field calls to the Forge getModifiedClimateSettings method as climateSettings needs to stay private due to field_to_method.js#11
 */
@SuppressWarnings("unused")
public class ClimateSettings implements PluginPatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger("ClimateSettingsPatcher");

    public void handleClass(ClassNode node, ClassRepo classRepo) {
        node.methods.forEach(m -> m.instructions.forEach(i -> {
            if (i instanceof FieldInsnNode fin) {
                if (fin.owner.equals("net/minecraft/world/level/biome/Biome") && fin.name.equals("f_47437_") && fin.desc.equals("Lnet/minecraft/world/level/biome/Biome$ClimateSettings;")) {
                    LOGGER.debug("Patching climateSettings call in {}:{}", node.name, m.name);
                    m.instructions.insertBefore(fin, new MethodInsnNode(Opcodes.INVOKEVIRTUAL, fin.owner, "getModifiedClimateSettings", "()" + fin.desc));
                    m.instructions.remove(fin);
                }
            }
        }));
    }
}
