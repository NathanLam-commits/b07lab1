public class Driver {
    public static void main(String [] args) {
        Polynomial p = new Polynomial("3-3x+4x2+5x5");
        double[] c = {1.0, 3.0, -5.0, 1.0};
        int[] i = {0, 1, 2, 3};
        Polynomial e = new Polynomial(c, i);

        p.saveToFile("test.txt");
        e.saveToFile("tester.txt");

        Polynomial ep = p.add(e);
        ep.saveToFile("bruh.txt");

        ep = p.multiply(e);
        ep.saveToFile("bork.txt");


        System.out.println(e.evaluate(3));
        System.out.println(e.hasRoot(1));
        System.out.println(e.hasRoot(2));


    }
}
