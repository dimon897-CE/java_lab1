public class Main3 {

    static class PiecewiseFunction {
        private double a = 2.8;
        private double b = 0.3;
        private double c = 4.0;
        private double dx = 0.002;
        private double start = 0.0;
        private double end = 2.0;

        // приватні масиви аргументів і значень
        private double[] xValues;
        private double[] yValues;

        // конструктор створює масиви x та y
        public PiecewiseFunction() {
            int size = calculateSteps(start, end, dx);
            xValues = new double[size];
            yValues = new double[size];

            for (int i = 0; i < size; i++) {
                double x = Math.round((start + i * dx) * 1000.0) / 1000.0;
                xValues[i] = x;
                yValues[i] = computeY(x);
            }
        }

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

        // Методи доступу до елементів масивів
        public double getX(int index) {
            if (index >= 0 && index < xValues.length) {
                return xValues[index];
            } else {
                throw new IndexOutOfBoundsException("Індекс поза межами масиву x");
            }
        }

        public double getY(int index) {
            if (index >= 0 && index < yValues.length) {
                return yValues[index];
            } else {
                throw new IndexOutOfBoundsException("Індекс поза межами масиву y");
            }
        }

        public int getStepCount() {
            return xValues.length;
        }
    }

    public static void main(String[] args) {
        PiecewiseFunction func = new PiecewiseFunction();

        System.out.println("Кількість кроків: " + func.getStepCount());

        int[] testIndices = {0, 700, 1000};
        for (int index : testIndices) {
            double x = func.getX(index);
            double y = func.getY(index);
            System.out.printf("Індекс %d: x = %.3f, y = %.6f%n", index, x, y);
        }
    }
}
