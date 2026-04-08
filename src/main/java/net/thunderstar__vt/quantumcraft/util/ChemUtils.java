package net.thunderstar__vt.quantumcraft.util;

import net.minecraft.network.chat.Component;

public class ChemUtils {
    public enum Unit {
        ELECTRON_VOLT
    }

    public Component formatUnit(double value, Unit unit) {
        switch (unit) {
            case ELECTRON_VOLT -> {
                return Component.literal("eV");
            }
            default -> {
                return Component.literal("Null");
            }
        }
    }


    public double computeIsotopeMass(int protons, int neutrons, int electrons) {
        return 0;
    }
}
