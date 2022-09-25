public class Polynomial {
    double[] coefficient;

    public Polynomial() {
        coefficient = new double[]{0};
    }

    public Polynomial(double [] c) {
        int a = c.length;
        coefficient = new double[a];
        for(int i = 0; i < a; i++) {
            coefficient[i] = c[i];
        }
    }

    public Polynomial add(Polynomial other) {
        int min = Math.min(coefficient.length, other.coefficient.length);
        int max = Math.max(coefficient.length, other.coefficient.length);
        boolean is_longer = false;

        if (max == coefficient.length) {
            is_longer = true;
        }

        Polynomial r = new Polynomial();
        double[] a = new double[max];
        for(int i = 0 ; i < min ; i++) {
            a[i] = coefficient[i] + other.coefficient[i];
        }

        for(int i = min; i < max ; i++) {
            if (is_longer) {
                a[i] = coefficient[i];
            }
            else {
                a[i] = other.coefficient[i];
            }
        }
        Polynomial ret = new Polynomial(a);
        return ret;
    }

    public double evaluate(double x) {
        double sum = 0;
        for(int i = 0; i < coefficient.length ; i++) {
            sum += (Math.pow(x, i) * coefficient[i]);
        }
        return sum;
    }

    public boolean hasRoot(double x) {
        if(evaluate(x) == 0) {
            return true;
        }
        return false;
    }
}