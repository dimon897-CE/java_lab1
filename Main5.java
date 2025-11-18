public class Main5 {

    static class PiecewiseFunction {
        private double a = 2.8;
        private double b = 0.3;
        private double c = 4.0;
        private double dx = 0.002;
        private double start = 0.0;
        private double end = 2.0;

        private double[] xValues;
        private double[] yValues;

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

        public double getX(int index) { return xValues[index]; }
        public double getY(int index) { return yValues[index]; }

        // індекс найбільшого елемента
        public int getIndexOfMaxY() {
            int maxIndex = 0;
            for (int i = 1; i < yValues.length; i++) {
                if (yValues[i] > yValues[maxIndex]) {
                    maxIndex = i;
                }
            }
            return maxIndex;
        }

        // індекс найменшого елемента
        public int getIndexOfMinY() {
            int minIndex = 0;
            for (int i = 1; i < yValues.length; i++) {
                if (yValues[i] < yValues[minIndex]) {
                    minIndex = i;
                }
            }
            return minIndex;
        }
    }

    public static void main(String[] args) {
        PiecewiseFunction func = new PiecewiseFunction();

        int maxIndex = func.getIndexOfMaxY();
        int minIndex = func.getIndexOfMinY();

        System.out.printf("Найбільший елемент: індекс %d, x = %.3f, y = %.6f%n",
                maxIndex, func.getX(maxIndex), func.getY(maxIndex));

        System.out.printf("Найменший елемент: індекс %d, x = %.3f, y = %.6f%n",
                minIndex, func.getX(minIndex), func.getY(minIndex));
    }
}
