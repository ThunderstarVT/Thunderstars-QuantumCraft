package net.thunderstar__vt.quantumcraft.util;

import java.util.function.IntToDoubleFunction;

public class MathUtils {
    public static double sum(int start, int end, IntToDoubleFunction function) {
        double total = 0.0;

        for (int i = start; i <= end; i++) {
            total += function.applyAsDouble(i);
        }

        return total;
    }

    public static double product(int start, int end, IntToDoubleFunction function) {
        double total = 1.0;

        for (int i = start; i <= end; i++) {
            total *= function.applyAsDouble(i);
        }

        return total;
    }


    public static double choose(int n, int k) {
        return product(1, k, i -> (double)(n + 1 - i) / i);
    }


    public static double factorial(int n) {
        if (n < 0) return Double.NaN;
        return product(1, n, i -> i);
    }


    public static double laguerrePolynomial(int k, int a, double x) {
        return sum(0, k, m -> (Math.pow(-1, m)/factorial(m)) * choose(k + a, k - m) * Math.pow(x, m));
    }


    public static double gaussian(double x, double mean, double sigma) {
        double d = (x - mean) / sigma;
        return Math.exp(-0.5 * d * d);
    }
}
