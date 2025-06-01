import java.io.*;

public class Polynomial {
    double[] coef;
    int[] exp;
   
    public Polynomial() {
        coef = new double[1];
        coef[0] = 0.0;
        exp = new int[1];
        exp[0] = 0;
    }

    
    public Polynomial(double[] coefficients, int[] exponents) {
        coef = new double[coefficients.length];
        for (int i = 0; i < coefficients.length; i++) {
            coef[i] = coefficients[i];
        }
        exp = new int[exponents.length];
        for(int j = 0; j < exponents.length; j++){
            exp[j] = exponents[j];
        }
    }


    public Polynomial (File file) throws IOException{
        BufferedReader input = new BufferedReader(new FileReader(file));
        String expression = input.readLine();
        input.close();
        String[] terms = expression.split("(?=-)|\\+");
        double[] tempCoef = new double[terms.length];
        int[] tempExp = new int[terms.length];
        int count = 0;

        for(String term: terms){
            double coefficient;
            int exponent;

            if (term.contains("x")) {
                String[] parts = term.split("x");
                if (parts[0].isEmpty() || parts[0].equals("+")) {
                    coefficient = 1.0;
                } else if (parts[0].equals("-")) {
                    coefficient = -1.0;
                } else {
                    coefficient = Double.parseDouble(parts[0]);
                }
                exponent = Integer.parseInt(parts[1]);
            } else {
                coefficient = Double.parseDouble(term);
                exponent = 0;
            }

            tempCoef[count] = coefficient;
            tempExp[count] = exponent;
            count++;
        }
        coef = new double[count];
        exp = new int[count];
        for (int i = 0; i < count; i++) {
            coef[i] = tempCoef[i];
            exp[i] = tempExp[i];
        }
    }

   
    public Polynomial add(Polynomial other) {

        int maxLengthCoef = this.coef.length + other.coef.length;
        int maxLengthExp = this.exp.length + other.exp.length;
        double[] tempCoeffs = new double[maxLengthCoef];
        int[] tempExps = new int[maxLengthExp];
        int index = 0;

        for (int i = 0; i < this.coef.length; i++) {
            tempCoeffs[index] = this.coef[i];
            tempExps[index] = this.exp[i];
            index++;
        }

        for (int j = 0; j < other.coef.length; j++) {
            double sc = other.coef[j];
            int id = other.exp[j];


            int k = 0;
            while (k < index && tempExps[k] != id) {
                k++;
            }

            if (k < index) {

                tempCoeffs[k] += sc;
            } else {

                tempCoeffs[index] = sc;
                tempExps[index] = id;
                index++;
            }
        }

        int checks = 0;
        for(int m = 0; m < index; m++){
            if (tempCoeffs[m] != 0.0){
                checks++;
            }
        }

        if(checks == 0){
            return new Polynomial();
        }

        double[] coefficients = new double[checks];
        int[] exponents = new int[checks];
        int pos = 0;
        for (int i = 0; i < index; i++) {
            if (tempCoeffs[i] != 0.0) {
                coefficients[pos] = tempCoeffs[i];
                exponents[pos] = tempExps[i];
                pos++;
            }
        }


        return new Polynomial(coefficients, exponents);
    }


    public Polynomial multiply(Polynomial other){
        int maxSize = this.coef.length * other.coef.length;
        double[] tempCoeffs = new double[maxSize];
        int[] tempExps = new int[maxSize];
        int index = 0;


        for (int i = 0; i < this.coef.length; i++) {
            for (int j = 0; j < other.coef.length; j++) {
                double product = this.coef[i] * other.coef[j];
                int expSum = this.exp[i] + other.exp[j];


                int k = 0;
                while (k < index && tempExps[k] != expSum) {
                    k++;
                }

                if (k < index) {
                    tempCoeffs[k] += product;
                } else {
                    tempCoeffs[index] = product;
                    tempExps[index] = expSum;
                    index++;
                }
            }
        }


        int checks = 0;
        for(int m = 0; m < index; m++){
            if (tempCoeffs[m] != 0.0){
                checks++;
            }
        }

        if(checks == 0){
            return new Polynomial();
        }

        double[] coefficients = new double[checks];
        int[] exponents = new int[checks];
        int pos = 0;
        for (int i = 0; i < index; i++) {
            if (tempCoeffs[i] != 0.0) {
                coefficients[pos] = tempCoeffs[i];
                exponents[pos] = tempExps[i];
                pos++;
            }
        }

        return new Polynomial(coefficients, exponents);
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
        for (int i = 0; i < coef.length; i++) {
            result += coef[i] * power(x, exp[i]);
        }
        return result;
    }

    
    public boolean hasRoot(double x) {
        return evaluate(x) == 0.0;
    }



    public void saveToFile(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        for (int i = 0; i < coef.length; i++) {
            double c = coef[i];
            int e = exp[i];


            if (c > 0 && i != 0) {
                writer.write("+");
            }

            if (e == 0) {
                writer.write(Double.toString(c));
            } else {
                if (c == 1.0) {
                    writer.write("x" + e);
                } else if (c == -1.0) {
                    writer.write("-x" + e);
                } else {
                    writer.write(Double.toString(c) + "x" + e);
                }
            }

        }
        writer.close();

    }



}
