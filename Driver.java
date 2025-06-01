public class Driver {
    public static void main(String[] args) {

        Polynomial p = new Polynomial();
        System.out.println("p(3) = " + p.evaluate(3));  // Expect 0.


        double[] c1 = {6, 5};           // Coefficients
        int[] e1 = {3, 0};              // Exponents
        Polynomial p1 = new Polynomial(c1, e1);

        // Test constructor with arrays: p2 = -2x^3 - 9x^0
        double[] c2 = {-2, -9};
        int[] e2 = {3, 0};
        Polynomial p2 = new Polynomial(c2, e2);

        // Add: s = p1 + p2 = (6 - 2)x^3 + (5 - 9) = 4x^3 - 4
        Polynomial s = p1.add(p2);

        // Evaluate s(0.1)
        System.out.println("s(0.1) = " + s.evaluate(0.1));  

        // Check if 1 is a root: s(1) = 4 - 4 = 0 → root!
        if (s.hasRoot(1)) {
            System.out.println("1 is a root of s");
        } else {
            System.out.println("1 is not a root of s");
        }

        // Additional test: Multiply p1 * p2
        Polynomial m = p1.multiply(p2);
        System.out.println("m(1) = " + m.evaluate(1));  // Evaluate product at x = 1
    }
}
