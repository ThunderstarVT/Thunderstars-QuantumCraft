package net.thunderstar__vt.quantumcraft.util;

import net.minecraft.network.chat.Component;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PhysUtils{
    public enum Unit {
        ELECTRON_VOLT(1000, SI_PREFIXES, "eV"),
        ANGSTROM(1000, SI_PREFIXES, "Å"),
        METER(1000, SI_PREFIXES, "m");

        private final double base;
        private final Map<Integer, String> prefixes;
        private final String unit;

        Unit(double base, Map<Integer, String> prefixes, String unit){
            this.base = base;
            this.prefixes = prefixes;
            this.unit = unit;
        }

        public double getBase() { return base; }
        public Map<Integer, String> getPrefixes() { return prefixes; }
        public String getUnit() { return unit; }
    }

    public static Component formatUnit(double value, Unit unit) {
        String str;

        if (value == 0.0 || !Double.isFinite(value)) {
            str = String.format("%.3f %s", value, unit.getUnit());
        } else {
            int exponent_base = (int) Math.floor(Math.log(Math.abs(value)) / Math.log(unit.getBase()));

            if (unit.getPrefixes().containsKey(exponent_base)) {
                double scaled = value / Math.pow(unit.getBase(), exponent_base);

                String prefix = unit.getPrefixes().get(exponent_base);

                str = String.format("%.3f %s%s", scaled, prefix, unit.getUnit());
            } else {
                int exponent = (int) Math.floor(Math.log10(Math.abs(value)));

                double scaled = value / Math.pow(10, exponent);

                str = String.format("%.3fe+%d %s", scaled, exponent, unit.getUnit());
            }
        }

        return Component.literal(str);
    }

    public static Component formatUnit(double value, Unit unit, char colorCode) {
        String str;

        if (value == 0.0 || !Double.isFinite(value)) {
            str = String.format("%.3f %s", value, unit.getUnit());
        } else {
            int exponent_base = (int) Math.floor(Math.log(Math.abs(value)) / Math.log(unit.getBase()));

            if (unit.getPrefixes().containsKey(exponent_base)) {
                double scaled = value / Math.pow(unit.getBase(), exponent_base);

                String prefix = unit.getPrefixes().get(exponent_base);

                str = String.format("%.3f %s%s", scaled, prefix, unit.getUnit());
            } else {
                int exponent = (int) Math.floor(Math.log10(Math.abs(value)));

                double scaled = value / Math.pow(10, exponent);

                str = String.format("%.3fe+%d %s", scaled, exponent, unit.getUnit());
            }
        }

        return Component.literal("§" + colorCode + str + "§r");
    }


    public static final Map<Integer, String> SI_PREFIXES = new HashMap<>();

    static {
        SI_PREFIXES.put(-10, "q");
        SI_PREFIXES.put(-9, "r");
        SI_PREFIXES.put(-8, "y");
        SI_PREFIXES.put(-7, "z");
        SI_PREFIXES.put(-6, "a");
        SI_PREFIXES.put(-5, "f");
        SI_PREFIXES.put(-4, "p");
        SI_PREFIXES.put(-3, "n");
        SI_PREFIXES.put(-2, "μ");
        SI_PREFIXES.put(-1, "m");
        SI_PREFIXES.put(0, "");
        SI_PREFIXES.put(1, "k");
        SI_PREFIXES.put(2, "M");
        SI_PREFIXES.put(3, "G");
        SI_PREFIXES.put(4, "T");
        SI_PREFIXES.put(5, "P");
        SI_PREFIXES.put(6, "E");
        SI_PREFIXES.put(7, "Z");
        SI_PREFIXES.put(8, "Y");
        SI_PREFIXES.put(9, "R");
        SI_PREFIXES.put(10, "Q");
    }


    // fundamental constants
    public static final double PLANCK = 6.626_070_15e-34;
    public static final double PLANCK_REDUCED = 1.054_571_817e-34;
    public static final double SPEED_OF_LIGHT = 299_792_458;
    public static final double ELEMENTARY_CHARGE = 1.602_176_634e-19;
    public static final double EPSILON_0 = 8.854_187_812_8e-12;


    public static double energyToWavelength(double energy) {
        return (PLANCK * SPEED_OF_LIGHT) / (energy * ELEMENTARY_CHARGE);
    }

    public static Color wavelengthToColor(double wavelength) {
        double num = Math.log(wavelength);

        double r = MathUtils.gaussian(num, -14.2, 0.1);
        double g = MathUtils.gaussian(num, -14.4, 0.1);
        double b = MathUtils.gaussian(num, -14.6, 0.1);

        double max = Math.max(r, Math.max(g, b));
        if (max > 0.0) {
            r /= max;
            g /= max;
            b /= max;
        }

        r *= MathUtils.gaussian(num, -14.4, 0.5);
        g *= MathUtils.gaussian(num, -14.4, 0.5);
        b *= MathUtils.gaussian(num, -14.4, 0.5);

        double gamma = 0.8;

        int R = (int)(255 * Math.pow(r, gamma));
        int G = (int)(255 * Math.pow(g, gamma));
        int B = (int)(255 * Math.pow(b, gamma));

        return new Color(Math.clamp(R, 0, 255), Math.clamp(G, 0, 255), Math.clamp(B, 0, 255));
    }
}
