package net.thunderstar__vt.quantumcraft.util;

import net.minecraft.network.chat.Component;
import net.thunderstar__vt.quantumcraft.object.item.custom.ElementItem;
import net.thunderstar__vt.quantumcraft.object.item.custom.ParticleItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuantUtils {
    // conversion constants
    public static final double EV_TO_KG = 1.782_662_695_946e-36;


    private static final Map<ElementItem.AtomData, Double> cachedAtomMass = new HashMap<>();
    public static double computeAtomMass(ElementItem.AtomData data) {
        if (cachedAtomMass.containsKey(data)) return cachedAtomMass.get(data);

        double leptonMass = (data.muonic() ? ParticleItem.ParticleType.MUON.mass : ParticleItem.ParticleType.ELECTRON.mass);

        double bindingEnergyNuclear = getNuclearBindingEnergy(data.protons(), data.neutrons());
        double bindingEnergyLeptonic = getLeptonicBindingEnergy(data.protons(), data.neutrons(), data.electrons(), leptonMass);

        double mass = Math.abs(data.protons()) * ParticleItem.ParticleType.PROTON.mass
                + Math.abs(data.neutrons()) * ParticleItem.ParticleType.NEUTRON.mass
                + Math.abs(data.electrons()) * leptonMass
                - bindingEnergyNuclear
                - bindingEnergyLeptonic;

        cachedAtomMass.put(data, mass);
        return mass;
    }

    private static double getNuclearBindingEnergy(int Z, int N) {
        double A = Math.abs(Z + N);

        double a_V = 15_800_000;
        double a_S = 18_300_000;
        double a_K = 1_000_000;
        double a_C = 714_000;
        double a_A = 23_200_000;
        double a_As = 11_000_000;
        double a_P = 12_000_000;

        double W = 30_000_000;

        double pairingTerm;
        if (A % 2 == 1) {
            pairingTerm = 0;
        } else if (Z % 2 == 1) {
            pairingTerm = -a_P * Math.sqrt(A);
        } else {
            pairingTerm = a_P * Math.sqrt(A);
        }

        return a_V * A
                - a_S * Math.cbrt(A * A)
                - a_K * Math.cbrt(A)
                - a_C * Z * (Z-1) / Math.cbrt(A)
                - a_A * (N-Z) * (N-Z) / A
                - a_As * (N-Z) * (N-Z) / Math.cbrt(A * A * A * A)
                - W * Math.abs(N - Z) / A
                + pairingTerm;
    }

    private static double getLeptonicBindingEnergy(int Z, int N, int L, double leptonMass) {
        List<FilledOrbital> filledOrbitals = getFilledOrbitals(L);

        double E = 0.0;

        double fineStruct = (PhysUtils.ELEMENTARY_CHARGE * PhysUtils.ELEMENTARY_CHARGE)
                / (4 * Math.PI * PhysUtils.EPSILON_0 * PhysUtils.PLANCK_REDUCED * PhysUtils.SPEED_OF_LIGHT);

        double nucleusMass = Math.abs(Z) * ParticleItem.ParticleType.PROTON.mass + Math.abs(N) * ParticleItem.ParticleType.NEUTRON.mass;

        double mu = (leptonMass * nucleusMass) / (leptonMass + nucleusMass);

        for (FilledOrbital orbital : filledOrbitals) {
            double Z_eff = getZeff(Z, orbital.n, orbital.l, filledOrbitals);
            E += -((mu * fineStruct * fineStruct) / 2.0)
                    * ((Z_eff * Z_eff) / (orbital.n * orbital.n)) * orbital.electrons;
        }

        return E;
    }


    private static final Map<ElementItem.AtomData, Double> cachedAtomSize = new HashMap<>();
    public static double computeAtomSize(ElementItem.AtomData data) {
        if (cachedAtomSize.containsKey(data)) return cachedAtomSize.get(data);

        if (data.protons() * data.electrons() < 0) {
            cachedAtomSize.put(data, Double.POSITIVE_INFINITY);
            return Double.POSITIVE_INFINITY;
        }

        List<FilledOrbital> filledOrbitals = getFilledOrbitals(data.electrons());

        double r = 0.0;

        for (FilledOrbital orbital : filledOrbitals) {
            double Z_eff_o = getZeff(Math.abs(data.protons()), orbital.n, orbital.l, filledOrbitals);
            double r_o = (3 * orbital.n * orbital.n - orbital.l * (orbital.l + 1)) / (3 * Z_eff_o);

            if (r_o > r) {
                r = r_o;
            }
        }

        double leptonMass = (data.muonic() ? ParticleItem.ParticleType.MUON.mass : ParticleItem.ParticleType.ELECTRON.mass);

        double v = Math.abs(data.neutrons()) * ParticleItem.ParticleType.NEUTRON.mass;
        double v1 = Math.abs(data.protons()) * ParticleItem.ParticleType.PROTON.mass + v;

        double mu = (leptonMass * v1)
                /(leptonMass + v1);

        double bohrRadius = (4 * Math.PI * PhysUtils.EPSILON_0 * PhysUtils.PLANCK_REDUCED * PhysUtils.PLANCK_REDUCED)
                / (PhysUtils.ELEMENTARY_CHARGE * PhysUtils.ELEMENTARY_CHARGE * EV_TO_KG * leptonMass);

        double cloudSize = 1e10 * bohrRadius * r * leptonMass / mu;

        double nucleusSize = 1.6836e-5 * Math.cbrt(Math.abs(data.protons()) + Math.abs(data.neutrons()));

        double size = Math.max(cloudSize, nucleusSize);

        cachedAtomSize.put(data, size);
        return size;
    }


    public static double getZeff(int Z, int n, int l, List<FilledOrbital> filledOrbitals) {
        return getSlaterZeff(Z, n, l, filledOrbitals);
    }


    public static double getSlaterZeff(int Z, int n, int l, List<FilledOrbital> filledOrbitals) {
        double S = (n == 1) ? -0.30 : -0.35;

        for (FilledOrbital orbital : filledOrbitals) {
            if (orbital.n == n) {
                if ((l < 2 && orbital.l < 2) || orbital.l == l) {
                    if (n == 1) {
                        S += 0.30 * orbital.electrons;
                    } else {
                        S += 0.35 * orbital.electrons;
                    }
                } else if (orbital.l < l) {
                    S += 1.00 * orbital.electrons;
                }
            } else if (orbital.n == n-1) {
                if (orbital.l < 2) {
                    S += 0.85 * orbital.electrons;
                } else {
                    S += 1.00 * orbital.electrons;
                }
            } else if (orbital.n < n-1) {
                S += 1.00 * orbital.electrons;
            }
        }

        return Z - S;
    }


    public static double electronWaveFunctionRadial(double r, double Z, int n, int l) {
        return Math.sqrt((2*Z / n) * MathUtils.factorial(n-l-1)/(2*n*MathUtils.factorial(n+l)))
                * Math.exp(-Z*r / n)
                * Math.pow(2*Z*r / n, l)
                * MathUtils.laguerrePolynomial(n-l-1, 2*l+1, 2*Z*r / n);
    }


    public static class ElectronOrbital {
        public final int n, l, capacity;

        public ElectronOrbital(int n, int l) {
            this.n = n;
            this.l = l;
            this.capacity = 2 * (2*l + 1);
        }
    }

    public static class FilledOrbital extends ElectronOrbital {
        public final int electrons;

        public FilledOrbital(int n, int l, int electrons) {
            super(n, l);

            this.electrons = electrons;
        }
    }


    public static List<FilledOrbital> getFilledOrbitals(int electrons) {
        List<ElectronOrbital> orbitals = new ArrayList<>();

        int maxN = 10 + (int)Math.ceil(Math.cbrt(Math.abs(electrons))) * 3;
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

        int remaining = Math.abs(electrons);

        List<FilledOrbital> filledOrbitals = new ArrayList<>();

        for (ElectronOrbital orbital : orbitals) {
            if (remaining <= 0) break;

            int fill = Math.min(remaining, orbital.capacity);
            remaining -= fill;

            filledOrbitals.add(new FilledOrbital(orbital.n, orbital.l, fill));
        }

        return filledOrbitals;
    }


    public static int shellCapacity(int shell) {
        return 2*(shell+1)*(shell+1);
    }
}
