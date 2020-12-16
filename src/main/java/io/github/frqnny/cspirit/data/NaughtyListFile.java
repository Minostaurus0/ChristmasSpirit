package io.github.frqnny.cspirit.data;

import com.google.gson.reflect.TypeToken;
import io.github.frqnny.cspirit.util.FileHelper;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NaughtyListFile {

    public static List<UUID> naughtyList;

    public static void init() {
        naughtyList = FileHelper.readFileOrCreate("NaughtyList", new ArrayList<>(), new TypeToken<List<UUID>>() {
        });
    }

    public static void addToNaughtyList(PlayerEntity player) {
        naughtyList.add(player.getUuid());
        FileHelper.saveToFile("NaughtyList", (ArrayList<UUID>) naughtyList);
    }

    public static void removeFromNaughtyList(PlayerEntity player) {
        naughtyList.remove(player.getUuid());
        FileHelper.saveToFile("NaughtyList", (ArrayList<UUID>) naughtyList);
    }

    public static boolean isOnNaughtyList(PlayerEntity player) {

        if (!player.world.isClient) {

            for (UUID playerID : naughtyList) {

                if (player.getUuid().equals(playerID)) {
                    return true;
                }
            }
        }

        return false;
    }
}
