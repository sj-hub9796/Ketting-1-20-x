package org.kettingpowered.ketting.patches;

import io.izzel.arclight.api.PluginPatcher;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
public class ContainerCounter implements PluginPatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger("ContainerCounterPatcher");

    public void handleClass(ClassNode node, ClassRepo classRepo) {
        node.methods.forEach(m -> m.instructions.forEach(i -> {
            if (i instanceof MethodInsnNode min) {
                if (min.owner.equals("net/minecraft/server/level/ServerPlayer") && min.name.equals("nextContainerCounter") && min.desc.equals("()I")) {
                    LOGGER.debug("Patching nextContainerCounter in {}:{}", node.name, m.name);
                    m.instructions.insertBefore(min, new MethodInsnNode(min.getOpcode(), min.owner, "nextContainerCounterInt", min.desc));
                    m.instructions.remove(min);
                }
            }
        }));
    }
}
