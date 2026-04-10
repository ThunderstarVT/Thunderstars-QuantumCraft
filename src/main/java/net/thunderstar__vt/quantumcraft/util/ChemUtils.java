package net.thunderstar__vt.quantumcraft.util;

import net.minecraft.network.chat.Component;
import net.thunderstar__vt.quantumcraft.object.item.custom.ElementItem;
import org.jetbrains.annotations.Debug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChemUtils {
    public enum Unit {
        ELECTRON_VOLT(1000, SI_PREFIXES, "eV"),
        ANGSTROM(1000, SI_PREFIXES, "Å");

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

        if (value == 0.0) {
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


    // measured constants
    public static final double PROTON_MASS = 938_272_089.43;
    public static final double NEUTRON_MASS = 939_565_421.94;
    public static final double ELECTRON_MASS = 510_998.950_69;
    public static final double MUON_MASS = 105_658_375.5;

    // fundamental constants
    public static final double PLANCK_REDUCED = 1.054_571_817e-34;
    public static final double SPEED_OF_LIGHT = 299_792_458;
    public static final double ELEMENTARY_CHARGE = 1.602_176_634e-19;
    public static final double EPSILON_0 = 8.854_187_812_8e-12;

    // conversion constants
    public static final double EV_TO_KG = 1.782_662_695_946e-36;


    private static final Map<ElementItem.AtomData, Double> cachedAtomMass = new HashMap<>();
    public static double computeAtomMass(ElementItem.AtomData data) {
        if (cachedAtomMass.containsKey(data)) return cachedAtomMass.get(data);

        //TODO: compute mass in eV
        double mass = 0;
        if (data.muonic()) {
            mass = PROTON_MASS * data.protons()
                    + NEUTRON_MASS * data.neutrons()
                    + MUON_MASS * data.electrons();
        } else {
            mass = PROTON_MASS * data.protons()
                    + NEUTRON_MASS * data.neutrons()
                    + ELECTRON_MASS * data.electrons();
        }

        cachedAtomMass.put(data, mass);
        return mass;
    }

    private static final Map<ElementItem.AtomData, Double> cachedAtomSize = new HashMap<>();
    public static double computeAtomSize(ElementItem.AtomData data) {
        if (cachedAtomSize.containsKey(data)) return cachedAtomSize.get(data);

        List<ElectronOrbital> orbitals = new ArrayList<>();

        int maxN = 10 + (int)Math.ceil(Math.cbrt(data.electrons())) * 3;
        for (int n = 1; n < maxN; n++) {
            for (int l = 0; l < n; l++) {
                orbitals.add(new ElectronOrbital(n, l));
            }
        }

        orbitals.sort((a, b) -> {
            int sumA = a.n + a.l;
            int sumB = b.n + b.l;

            if (sumA != sumB) return Integer.compare(sumA, sumB);
            return Integer.compare(a.n, b.n);
        });

        int remaining = data.electrons();

        int n_eff = 0;
        int l_eff = 0;

        List<FilledOrbital> filledOrbitals = new ArrayList<>();

        for (ElectronOrbital orbital : orbitals) {
            if (remaining <= 0) break;

            int fill = Math.min(remaining, orbital.capacity);
            remaining -= fill;

            if (orbital.n > n_eff || (orbital.n == n_eff && orbital.l < l_eff)) {
                n_eff = orbital.n;
                l_eff = orbital.l;
            }

            filledOrbitals.add(new FilledOrbital(orbital.n, orbital.l, fill));
        }

        double S = -0.30f;

        for (FilledOrbital orbital : filledOrbitals) {
            if (orbital.n == n_eff) {
                if (orbital.l == 0) {
                    S += 0.30 * orbital.electrons;
                } else {
                    S += 0.35 * orbital.electrons;
                }
            } else if (orbital.n == n_eff - 1) {
                if (orbital.l < 2) {
                    S += 0.85 * orbital.electrons;
                } else {
                    S += 1.00 * orbital.electrons;
                }
            } else if (orbital.n < n_eff - 1) {
                S += 1.00 * orbital.electrons;
            }
        }

        double Z_eff = data.protons() - S;

        double r = (n_eff / Z_eff) * MathUtils.regularizedGammaPFindMedianT(n_eff);

        double mu = ((data.muonic() ? MUON_MASS : ELECTRON_MASS) * (data.protons()*PROTON_MASS + data.neutrons()*NEUTRON_MASS))
                /((data.muonic() ? MUON_MASS : ELECTRON_MASS) + (data.protons()*PROTON_MASS + data.neutrons()*NEUTRON_MASS));

        double magicNumber = 0.875 * Math.pow(1.15, -n_eff);

        double cloudSize = magicNumber * r * ELECTRON_MASS / mu;

        double nucleusSize = 1.6836e-5 * Math.cbrt(data.protons() + data.neutrons());

        double size = Math.max(cloudSize, nucleusSize);

        cachedAtomSize.put(data, size);
        return size;
    }

    public static class ElectronOrbital {
        public final int n, l, capacity;

        public ElectronOrbital(int n, int l) {
            this.n = n;
            this.l = l;
            this.capacity = 2 * (2*l + 1);
        }
    }

    private static class FilledOrbital extends ElectronOrbital {
        public final int electrons;

        public FilledOrbital(int n, int l, int electrons) {
            super(n, l);

            this.electrons = electrons;
        }
    }

    public static int shellCapacity(int shell) {
        return 2*(shell+1)*(shell+1);
    }
}
