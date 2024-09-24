package org.kettingpowered.ketting.utils;

import net.minecraft.network.syncher.SynchedEntityData;

import java.util.Set;

public class EntityDataUtils {

    public static <T> boolean classesMatch(SynchedEntityData.DataItem<T> dataitem, T value) {
        if (dataitem.getValue() == null || value == null) return true; //Just skip if one of them is null
        if (exceptionCheck(dataitem.getValue(), value)) return true;
        return dataitem.getValue().getClass().isAssignableFrom(value.getClass());
    }

    private static <T> boolean exceptionCheck(T data, T value) {
        if (data.getClass().getName().equals("kotlin.collections.EmptySet") && value instanceof Set<?>)
            return true; //Special case for cobblemon

        return false;
    }
}
