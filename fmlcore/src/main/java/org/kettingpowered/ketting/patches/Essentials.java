package org.kettingpowered.ketting.patches;

import io.izzel.arclight.api.PluginPatcher;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

import java.util.ArrayList;
import java.util.List;

public class Essentials implements PluginPatcher {

    public void handleClass(ClassNode node, ClassRepo classRepo) {
        if (node.name.equals("com/earth2me/essentials/EssentialsServerListener"))
            node.accept(new ArrayChanger());
    }

    //For some reason, bukkit renames LegacyQueryHandler to LegacyPingHandler, so we have to add an extra check for that
    static class ArrayChanger extends ClassVisitor {

        private final List<String> ignoredSLPECallers = new ArrayList<>();

        protected ArrayChanger() {
            super(Opcodes.ASM9);

            ignoredSLPECallers.add(".LegacyQueryHandler.channelRead("); //Forge version of the class

            //yoinked from https://github.com/EssentialsX/Essentials/blob/2.x/Essentials/src/main/java/com/earth2me/essentials/EssentialsServerListener.java#L19
            ignoredSLPECallers.add(".LegacyPingHandler.channelRead(");
            ignoredSLPECallers.add("de.dytanic.cloudnet.bridge.BukkitBootstrap");
            ignoredSLPECallers.add("de.dytanic.cloudnet.ext.bridge.bukkit.BukkitCloudNetBridgePlugin");
        }

        public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
            if (name.equals("ignoredSLPECallers"))
                return super.visitField(access, name, descriptor, signature, ignoredSLPECallers);

            return super.visitField(access, name, descriptor, signature, value);
        }
    }
}
