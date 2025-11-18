public class Main {
    public static void main(String[] args) {
        PiecewiseFunction func = new PiecewiseFunction();
        int[] testIndices = {0, 700, 1000}; // x = 0.0, 1.4, 2.0
        func.testIndices(testIndices);
    }
}

class PiecewiseFunction {
    private double a = 2.8;
    private double b = 0.3;
    private double c = 4.0;
    private double dx = 0.002;
    private double[] xValues; // масив значень [0;2]

    public PiecewiseFunction() {
        int size = (int) ((2.0 - 0.0) / dx) + 1;
        xValues = new double[size];
        for (int i = 0; i < size; i++) {
            xValues[i] = Math.round((i * dx) * 1000.0) / 1000.0; // округл до 3 знаків після коми
        }
    }

    public double computeY(double x) {
        if (x < 1.4) {
            return a * x * x + b * x + c;
        } else if (x == 1.4) {
            return a / x + Math.sqrt(x * x + 1);
        } else {
            return (a + b * x) / Math.sqrt(x * x + 1);
        }
    }

    public void testIndices(int[] indices) {
        for (int index : indices) {
            if (index >= 0 && index < xValues.length) {
                double x = xValues[index];
                double y = computeY(x);
                System.out.printf("Індекс %d: x = %.3f, y = %.6f%n", index, x, y);
            } else {
                System.out.printf("Індекс %d: поза межами масиву%n", index);
            }
        }
    }
}
