public class Main2 {

    static class PiecewiseFunction {
        private double a = 2.8;
        private double b = 0.3;
        private double c = 4.0;
        private double dx = 0.002;
        private double start = 0.0;
        private double end = 2.0;
        private double[] xValues;

        
        public PiecewiseFunction() {
            int size = calculateSteps(start, end, dx);
            xValues = new double[size];
            for (int i = 0; i < size; i++) {
                xValues[i] = Math.round((start + i * dx) * 1000.0) / 1000.0;
            }
        }

        // Обчислення кількості кроків
        public int calculateSteps(double start, double end, double step) {
            if (step <= 0) {
                throw new IllegalArgumentException("Крок має бути додатнім!");
            }
            if (end < start) {
                throw new IllegalArgumentException("Кінець інтервалу має бути більшим за початок!");
            }
            return (int) ((end - start) / step) + 1;
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

        public int getStepCount() { // отримання кількості кроків
            return xValues.length;
        }
    }

    public static void main(String[] args) {
        PiecewiseFunction func = new PiecewiseFunction();

        System.out.println("Кількість кроків: " + func.getStepCount());

        int[] testIndices = {0, 700, 1000};
        func.testIndices(testIndices);
    }
}
