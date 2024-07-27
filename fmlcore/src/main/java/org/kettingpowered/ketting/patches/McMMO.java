package org.kettingpowered.ketting.patches;

import io.izzel.arclight.api.PluginPatcher;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class McMMO implements PluginPatcher {

    private static final String[] BOSS_CLASSES = new String[] {};

    private static final String[] BOSS_SUPERCLASSES = new String[] {};

    private static final String[] BOSS_INTERFACES = new String[] {
            "com.aetherteam.aether.entity.AetherBossMob"
    };

    private static final Logger LOGGER = LoggerFactory.getLogger("mcMMOPatcher");

    public void handleClass(ClassNode node, ClassRepo classRepo) {
        if (node.name.equals("com/gmail/nossr50/util/MobHealthbarUtils"))
            handleAetherBosses(node);
    }

    private void handleAetherBosses(ClassNode node) {
        node.methods.forEach(m -> {
            if (m.name.equals("isBoss") && m.desc.equals("(Lorg/bukkit/entity/LivingEntity;)Z"))
                handleIsBoss(m);
        });
    }

    private void handleIsBoss(MethodNode method) {
        LOGGER.debug("Patching MobHealthbarUtils.isBoss");

        final String craftLivingEntityLocation = "org/bukkit/craftbukkit/v1_20_R1/entity/CraftLivingEntity";
        final String livingEntityLocation = "net/minecraft/world/entity/LivingEntity";

        InsnList list = new InsnList();
        //load first function param and try casting
        list.add(new VarInsnNode(Opcodes.ALOAD, 0));
        list.add(new TypeInsnNode(Opcodes.CHECKCAST, craftLivingEntityLocation));

        //get CraftLivingEntity.getHandle()
        list.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, craftLivingEntityLocation, "getHandle", "()L" + livingEntityLocation + ";"));

        //get the class name
        list.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, livingEntityLocation, "getClass", "()Ljava/lang/Class;"));
        list.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;"));

        //call the function below
        list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(getClass()), "isCustomBoss", "(Ljava/lang/String;)Z"));

        //return true if isCustomBoss returns true
        LabelNode label = new LabelNode();
        list.add(new JumpInsnNode(Opcodes.IFEQ, label));
        list.add(new InsnNode(Opcodes.ICONST_1));
        list.add(new InsnNode(Opcodes.IRETURN));

        //add it before the switch
        list.add(label);
        list.add(new FrameNode(Opcodes.F_SAME, 0, null, 0, null));

        method.instructions.insert(list);
    }

    @SuppressWarnings("unused")
    public static boolean isCustomBoss(String className) {
        //check if the class name matches
        if (Arrays.asList(BOSS_CLASSES).contains(className)) return true;
        
        try {
            Class<?> entityClass = Class.forName(className);

            //check if the superclass matches
            for (String superclassName : BOSS_SUPERCLASSES)
                if (entityClass.getSuperclass().getName().equals(superclassName)) return true;

            //check if any interfaces match
            for (Class<?> intf : entityClass.getInterfaces())
                if (Arrays.asList(BOSS_INTERFACES).contains(intf.getName())) return true;
        } catch (ClassNotFoundException e) {
            LOGGER.warn("Failed to find class {}", className);
        }
        return false;
    }
}
