package kz.almurt.paiyz.formula;

/**
 * Created by Mukhtar on 15/04/2017.
 */

public class MonthlyPayment {
    private int Year; // Год
    private int Month; // Месяц
    private double AmountOD; // Сумма ОД
    private double AmountFee; //Сумма процентов
    private double MonthlyPayment; // Месячный платеж

    public MonthlyPayment(){

    }

    public int getYear() {

        return Year;
    }

    public void setYear(int year) {

        Year = year;
    }

    public int getMonth() {
        return Month;
    }

    public void setMonth(int month) {
        Month = month;
    }

    public double getAmountOD() {
        return AmountOD;
    }

    public void setAmountOD(double amountOD) {
        AmountOD = Lib.round_2(amountOD);
    }

    public double getAmountFee() {
        return AmountFee;
    }

    public void setAmountFee(double amountFee) {
        AmountFee = Lib.round_2(amountFee);
    }

    public double getMonthlyPayment() {
        return MonthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        MonthlyPayment = Lib.round_2(monthlyPayment);
    }

}
