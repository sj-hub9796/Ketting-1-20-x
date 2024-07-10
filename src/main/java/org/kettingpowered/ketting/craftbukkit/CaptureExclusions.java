package org.kettingpowered.ketting.craftbukkit;

import java.util.HashSet;
import java.util.Set;

/**
 * This class is used to prevent certain items that update a blockentity and directly use that new blockentity, to be excluded from bukkit's blockentity capture system.
 * This essentially ensures that the blockentity is valid and has a valid level
 */
public class CaptureExclusions {

    private static final Set<String> captureExclusions = new HashSet<>();

    static {
        captureExclusions.add("net.p3pp3rf1y.sophisticatedstorage.item.");
    }

    public static boolean isExcluded(Class<?> clazz) {
        return captureExclusions.stream().anyMatch(exclusion -> clazz.getName().startsWith(exclusion));
    }
}
