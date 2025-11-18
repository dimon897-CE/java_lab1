package labs.Zagorodyanskiy;
/**
* @author Zagorodyanskiy D 2-CE-24
* @version 25.0.1
* The PiecewiseFunction class represents a piecewise-defined function
* over the interval [0; 2]
*/
public class PiecewiseFunction {

        private double a = 2.8;
        private double b = 0.3;
        private double c = 4.0;
        private double dx = 0.002;
        private double start = 0.0;
        private double end = 2.0;

        private double[] xValues;
        private double[] yValues;
		
		/**
		* Create a PiecewiseFunction object
		* Initializes arrays of x and y values by computing
		* the function values over the specified interval
		*/
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
		
		/**
		*Calculates the number of steps between start and end
		* @param start the beginning of the interval
		* @param end the end of the interval
		* @param step the step size
		* @return the number of points in the interval
		*/
        public int calculateSteps(double start, double end, double step) {
            return (int) ((end - start) / step) + 1;
        }

		/**
		* Computes the value of the function for a given x
		* <ul>
		*	<li>if x &lt; 1.4: y = a * x^2 + b * x + c</li>
		*	<li>if x == 1.4: y = a / x + sqrt(x^2 + 1)</li>
		*	<li>if x &gt; 1.4: y = (a + b * x) / sqrt(x^2 + 1)</li>
		* </ul>
		*
		* @param x the input arg
		* @return the computed func value
		*/
        public double computeY(double x) {
            if (x < 1.4) {
                return a * x * x + b * x + c;
            } else if (x == 1.4) {
                return a / x + Math.sqrt(x * x + 1);
            } else {
                return (a + b * x) / Math.sqrt(x * x + 1);
            }
        }
		
		/**
		* Returns the x at the specific index
		*
		* @param index in the xValues array
		* @return y-value
		*/
        public double getX(int index) { return xValues[index]; }
		
		/**
		* Returns the y at the specific index
		*
		* @param index in the yValues array
		* @return x-value
		*/
        public double getY(int index) { return yValues[index]; }

        /**
		* Finds the index of the max y in the array
		*
		* @return the index of the max el
		*/
        public int getIndexOfMaxY() {
            int maxIndex = 0;
            for (int i = 1; i < yValues.length; i++) {
                if (yValues[i] > yValues[maxIndex]) {
                    maxIndex = i;
                }
            }
            return maxIndex;
        }

        /**
		* Finds the index of the min y in the array
		*
		* @return the index of the min el
		*/
        public int getIndexOfMinY() {
            int minIndex = 0;
            for (int i = 1; i < yValues.length; i++) {
                if (yValues[i] < yValues[minIndex]) {
                    minIndex = i;
                }
            }
            return minIndex;
        }

	/**
	*Entry point of the program
	* Creates a PiecewiseFunction object
	* Prints max and min el-s with their idc
	*
	* @param args command-line arguments (not used)
	*/
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