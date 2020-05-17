package calculatorTDD;


public class Calculator {

    public static Double add(Double a, Double b) {
        return a+b;
    }

    public static Double subtract(Double a, Double b) {
        return a-b;
    }

    public static Double pow(Double a, Integer b) {
        return Math.pow(a,b);
    }

    public static Double sqrt(Double a) {
        return Math.sqrt(a);
    }

}
