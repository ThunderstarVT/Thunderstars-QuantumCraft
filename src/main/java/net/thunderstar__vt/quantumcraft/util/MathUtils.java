package net.thunderstar__vt.quantumcraft.util;

public class MathUtils {
    public static double regularizedGammaP(int n, double t) {
        double sum = 1.0;
        double term = 1.0;

        for (int k = 1; k < n; k++) {
            term *= t / k;
            sum += term;
        }

        return 1.0 - Math.exp(-t) * sum;
    }

    public static double regularizedGammaPDF(int n, double t) {
        double term = 1.0;

        for (int k = 1; k < n; k++) {
            term *= t / k;
        }

        return term * Math.exp(-t);
    }

    public static double regularizedGammaPFindMedianT(int n) {
        double t = Math.max(1e-6, n - 1.0/3.0);

        for (int i = 0; i < 20; i++) {
            double P = regularizedGammaP(n, t);
            double dP = regularizedGammaPDF(n, t);

            double F = P - 0.5;

            double step = F / dP;
            t -= step;

            if (t <= 0) {
                t = 1e-6;
            }

            if (Math.abs(step) < 1e-12) {
                break;
            }
        }

        return t;
    }


}
