package io.github.thebusybiscuit.extraheads.utils;

import lombok.experimental.UtilityClass;

import org.bukkit.Bukkit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class MinecraftVersionUtils {

    private static final Pattern VERSION_PATTERN = Pattern.compile("(\\d+)\\.(\\d+)(?:\\.(\\d+))?");

    public static boolean isAtLeast(int major, int minor) {
        return isAtLeast(major, minor, 0);
    }

    public static boolean isAtLeast(int major, int minor, int patch) {
        String currentVersion = Bukkit.getServer().getMinecraftVersion();
        Matcher matcher = VERSION_PATTERN.matcher(currentVersion);

        if (!matcher.find()) {
            return false;
        }

        int currentMajor = Integer.parseInt(matcher.group(1));
        int currentMinor = Integer.parseInt(matcher.group(2));
        int currentPatch = matcher.group(3) != null ? Integer.parseInt(matcher.group(3)) : 0;

        if (currentMajor != major) {
            return currentMajor > major;
        }
        if (currentMinor != minor) {
            return currentMinor > minor;
        }
        return currentPatch >= patch;
    }
}
