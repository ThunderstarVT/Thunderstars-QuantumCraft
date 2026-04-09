package net.thunderstar__vt.quantumcraft.util;

import net.minecraft.network.chat.Component;
import net.thunderstar__vt.quantumcraft.object.item.custom.ElementItem;

import java.util.HashMap;
import java.util.Map;

public class ChemUtils {
    public enum Unit {
        ELECTRON_VOLT(1000),
        ANGSTROM(1000);

        double base;

        Unit(double base){
            this.base = base;
        }
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


    public static final double PROTON_MASS = 938_272_089.43;
    public static final double NEUTRON_MASS = 939_565_421.94;
    public static final double ELECTRON_MASS = 510_998.950_69;
    public static final double MUON_MASS = 105_658_375.5;


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

        //TODO: compute size in Å
        double size = 0;

        cachedAtomSize.put(data, size);
        return size;
    }
}
