package org.kettingpowered.ketting.config;

import org.jetbrains.annotations.NotNull;
import org.kettingpowered.ketting.config.value.Value;
import org.kettingpowered.ketting.config.value.types.BooleanValue;
import org.kettingpowered.ketting.config.value.types.IntValue;
import org.kettingpowered.ketting.core.Ketting;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class KettingConfig extends AbstractConfig {

    private static final KettingConfig INSTANCE;

    static {
        INSTANCE = new KettingConfig("ketting.yml");
    }

    public static KettingConfig getInstance() {
        return INSTANCE;
    }

    public KettingConfig(@NotNull String fileName) {
        super(new String[] {
                "This is the main configuration file for Ketting.",
                "",
                "Site: https://kettingpowered.org/",
                "Github: https://github.com/kettingpowered/",
                "Discord: https://discord.kettingpowered.org/",
                ""
        }, fileName, 1);
        load();
    }

    //Start of config values
    public final BooleanValue PRINT_INJECTIONS = new BooleanValue("debug.print_injections", false, "Print all values injected into Bukkit to the console.");
    public final BooleanValue WARN_ON_UNKNOWN_ENTITY = new BooleanValue("debug.warn_on_unknown_entity", true, "Print a warning to the console when an entity unknown to Bukkit is spawned.");
    public final BooleanValue WARN_ON_NULL_NBT = new BooleanValue("debug.warn_on_null_nbt", true, "Print a warning to the console when something tries to set a null NBT tag.");

    public final BooleanValue OVERWRITE_FORGE_PERMISSIONS = new BooleanValue("forge.overwrite_forge_permissions", false, "--- WARNING - THIS WILL COMPLETELY DISABLE FORGE PERMISSION CHECKS ---  Overwrite Forge permissions with Bukkit permissions, makes it possible to use a permission manager plugin for modded commands. If true, Forge permissions will be set to 'forge.command.MODDEDCOMMAND' where MODDEDCOMMAND is the name of the modded command.");

    public final BooleanValue MERGE_WORLD_SYSTEMS = new BooleanValue("ketting.merge_world_systems", false, "If true, this will attempt to merge both the Forge and Bukkit world system into one, making dimensions exist in the world folder, and Bukkit worlds in their own folder.");
    public final BooleanValue HALT_EXIT = new BooleanValue("ketting.halt.exit", true, "If true, Ketting will call System.exit(255), once it is supposed to regularly shutdown. This is useful, if some plugins or mods keep the server up unintentionally.");
    public final BooleanValue HALT_HALT = new BooleanValue("ketting.halt.halt", false, "If true, Ketting will call Runtime.getRuntime().halt(), once it is supposed to regularly shutdown. THIS SKIPS SHUTDOWN HOOKS. SOME STUFF MIGHT BREAK!");
    public final IntValue HALT_THREADDUMP_SLEEP = new IntValue("ketting.halt.thread_dump.sleep_time", 5000, "If true, Ketting will sleep this many seconds before printing the thread-dump.");
    public final BooleanValue HALT_THREADDUMP_ENABLE = new BooleanValue("ketting.halt.thread_dump.enabled", false, "If true, Ketting will print a Stacktrace of all threads once the server is supposed to regularly shutdown. This option is intended to aid debugging for the option 'ketting.force.halt'.");
    //End of config values
}
