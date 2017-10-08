package kz.almurt.paiyz.formula;

/**
 * Created by mukhtar on 10/6/17.
 */

public class Lib {

    public static String formatValue(String addPayment) {
        String value = "";
        int j = 0;
        while (j < addPayment.length()) {
            if ("1234567890".contains(String.valueOf(addPayment.charAt(j)))) {
                value = value + addPayment.charAt(j);
            } else if (j == addPayment.length() - 3 || j == addPayment.length() - 2) {
                value = value + ".";
            }
            j++;
        }
        if (value.equals("")) {
            return "0.0";
        }
        return value;
    }

    public static double round_2(double inSoma){
        return Math.round(inSoma*100.0)/100.0;
    }

}
