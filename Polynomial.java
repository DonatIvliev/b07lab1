public class Polynomial {
    double[] pol;

   
    public Polynomial() {
        pol = new double[1];
        pol[0] = 0.0;
    }

    
    public Polynomial(double[] coefficients) {
        pol = new double[coefficients.length];
        for (int i = 0; i < coefficients.length; i++) {
            pol[i] = coefficients[i];
        }
    }

   
    public Polynomial add(Polynomial other) {
        double[] result = new double[pol.length];

        for (int i = 0; i < pol.length; i++) {
            result[i] = this.pol[i] + other.pol[i];
        }

        return new Polynomial(result);
    }

    
    private double power(double base, int exponent) {
        double res = 1.0;
        for (int i = 0; i < exponent; i++) {
            res *= base;
        }
        return res;
    }

    
    public double evaluate(double x) {
        double result = 0.0;
        for (int i = 0; i < pol.length; i++) {
            result += pol[i] * power(x, i);
        }
        return result;
    }

    
    public boolean hasRoot(double x) {
        return evaluate(x) == 0.0;
    }
}
