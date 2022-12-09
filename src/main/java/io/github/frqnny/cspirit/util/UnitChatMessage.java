package io.github.frqnny.cspirit.util;

import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class UnitChatMessage {

    private final String unitName;
    private final Entity[] players;

    public UnitChatMessage(String unitName, Entity... players) {
        this.unitName = unitName;
        this.players = players;
    }

    public void printMessage(Formatting format, String message) {

        for (Entity player : players) {

            Text componentString = Text.literal(getUnitName() + (format + message));
            player.sendMessage(componentString);//, Util.NIL_UUID);
        }
    }

    private String getUnitName() {
        return "[" + unitName + "] ";
    }
}
