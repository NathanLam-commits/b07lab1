import java.io.*;
import java.util.ArrayList;

public class Polynomial {
    double[] coefficient;
    int[] exponent;

    public Polynomial() {
        coefficient = new double[]{0};
        exponent = new int[]{0};
    }

    public Polynomial(double[] c, int[] e) {
        int a = c.length;
        coefficient = new double[a];
        exponent = new int[a];

        for(int i = 0 ; i < a ; i++) {
            coefficient[i] = c[i];
            exponent[i] = e[i];
        }
    }

    public Polynomial(String poly) {
        poly = poly.replace("-", "#-");
        poly = poly.replace("+", "#");
        String[] arrStr = poly.split("#");
        String[] splitCE;

        coefficient = new double[arrStr.length];
        exponent = new int[arrStr.length];
        for(int i = 0 ; i < arrStr.length ; i++) {
            splitCE = arrStr[i].split("x");
            coefficient[i] = Double.parseDouble(splitCE[0]);

            if(splitCE.length == 1 && !splitCE[0].equals(arrStr[i]))
                exponent[i] = 1;
            else if(splitCE.length == 1)
                exponent[i] = 0;
            else
                exponent[i] = Integer.parseInt(splitCE[1]);
        }
    }

    private static int findIndex(int input, int[] arr){
        for(int i = 0 ; i < arr.length ; i++) {
            if(arr[i] == input) {
                return i;
            }
        }
        return -1;
    }


    public Polynomial add(Polynomial other) {
        ArrayList<Double> tempd = new ArrayList<>();
        ArrayList<Integer> tempi = new ArrayList<>();

        for(int i = 0 ; i < this.coefficient.length ; i++) {
            int foundInd = Polynomial.findIndex(exponent[i], other.exponent);
            if(foundInd > -1)
                tempd.add(coefficient[i] + other.coefficient[foundInd]);
            else
                tempd.add(coefficient[i]);
            tempi.add(exponent[i]);
        }

        for(int j = 0 ; j < other.coefficient.length ; j++) {
            if(!tempi.contains(other.exponent[j])) {
                 tempi.add(other.exponent[j]);
                 tempd.add(other.coefficient[j]);
            }
        }

        while(tempd.contains(0.0)) {
            tempi.remove(tempd.indexOf(0.0));
            tempd.remove(0.0);
        }

        int[] reti = new int[tempi.size()];
        double[] retd = new double[tempd.size()];
        for(int i = 0 ; i < tempd.size() ; i++) {
            reti[i] = tempi.get(i);
            retd[i] = tempd.get(i);
        }
        return new Polynomial(retd, reti);
    }

    public double evaluate(double x) {
        double sum = 0;
        for(int i = 0; i < coefficient.length ; i++) {
            sum += (Math.pow(x, exponent[i]) * coefficient[i]);
        }
        return sum;
    }

    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }

    public Polynomial multiply(Polynomial other) {
        ArrayList<Double> tempd = new ArrayList<>();
        ArrayList<Integer> tempi = new ArrayList<>();

         for(int i = 0 ; i < coefficient.length ; i++) {
            for(int j = 0 ; j < other.coefficient.length ; j++) {
                int e = exponent[i] + other.exponent[j];
                double c = coefficient[i] * other.coefficient[j];
                int ind = tempi.indexOf(e);
                if(ind > -1)
                    tempd.set(ind, tempd.get(ind) + c);
                else {
                    tempd.add(c);
                    tempi.add(e);
                }
            }
         }

         while(tempd.contains(0.0)) {
             tempi.remove(tempd.indexOf(0.0));
             tempd.remove(0.0);
         }

         int[] reti = new int[tempi.size()];
         double[] retd = new double[tempd.size()];
         for(int i = 0 ; i < tempd.size() ; i++) {
             reti[i] = tempi.get(i);
             retd[i] = tempd.get(i);
         }
         return new Polynomial(retd, reti);
    }

    public void saveToFile(String fileName) {
        File file = new File(fileName);
        try {
            BufferedWriter wr = new BufferedWriter(new FileWriter(file));
            StringBuilder in = new StringBuilder();
            for(int i = 0 ; i < coefficient.length ; i++) {
                if(coefficient[i] > 0.0 && !in.isEmpty())
                    in.append("+");
                if(exponent[i] == 0)
                    in.append(coefficient[i]);
                else if(exponent[i] == 1) {
                    in.append(coefficient[i]).append("x");
                }
                else
                    in.append(coefficient[i]).append("x").append(exponent[i]);
            }
            wr.write(in.toString());
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}