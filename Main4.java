public class Main4 {

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

        // методи доступу до елементів масивів
        public double getX(int index) { return xValues[index]; }
        public double getY(int index) { return yValues[index]; }

        // знаходить індекс найбільшого елемента масиву y
        public int getIndexOfMaxY() {
            int maxIndex = 0;
            for (int i = 1; i < yValues.length; i++) {
                if (yValues[i] > yValues[maxIndex]) {
                    maxIndex = i;
                }
            }
            return maxIndex;
        }

        // знаходить індекс найменшого елемента масиву y
        public int getIndexOfMinY() {
            int minIndex = 0;
            for (int i = 1; i < yValues.length; i++) {
                if (yValues[i] < yValues[minIndex]) {
                    minIndex = i;
                }
            }
            return minIndex;
        }

        // сума всіх елементів масиву y
        public double sumY() {
            double sum = 0;
            for (double y : yValues) {
                sum += y;
            }
            return sum;
        }

        // середнє арифметичне масиву y
        public double averageY() {
            return sumY() / yValues.length;
        }
    }

    public static void main(String[] args) {
        PiecewiseFunction func = new PiecewiseFunction();

        System.out.println("Кількість кроків: " + func.calculateSteps(0.0, 2.0, 0.002));

        int maxIndex = func.getIndexOfMaxY();
        int minIndex = func.getIndexOfMinY();

        System.out.printf("Максимум: індекс %d, x = %.3f, y = %.6f%n",
                maxIndex, func.getX(maxIndex), func.getY(maxIndex));

        System.out.printf("Мінімум: індекс %d, x = %.3f, y = %.6f%n",
                minIndex, func.getX(minIndex), func.getY(minIndex));

        System.out.printf("Сума всіх y: %.6f%n", func.sumY());
        System.out.printf("Середнє арифметичне y: %.6f%n", func.averageY());
    }
}