package kz.almurt.paiyz.formula;

/**
 * Created by Mukhtar on 15/04/2017.
 */

public class Loan {
    private double PaymentAmount;    // Общая сумма платежей
    private double MntlyPay;         // Месячный платеж
    private String MntlyPayStr;      // Месячный платеж строкой
    private int    CalcMethod;       // Метод расчета
    private MonthlyPayment[] Graf;   // График погажения

    // Методы расчета
    static final int  ANNUITY = 0;        // Аннуитетный
    static final int  DIFFERENTIATED = 1; // Дифференцированный (равные доли)


    public Loan (double inSumm, double inPercent, int inYears, int inMonth, int inMethod){
        //TODO LoanCalc
        int vPeriod;
        double vPercent = inPercent/100;
        int i = 0;

        if (inYears > 0){
            vPeriod = (inYears*12)+inMonth; // Срок в месяцах
        }
        else{
            vPeriod = inMonth;
        }
        Graf = new MonthlyPayment[vPeriod]; // График погажения
        CalcMethod = inMethod;

        if (inMethod==ANNUITY) {
            double vMultiplier = 1+vPercent * 1/12; // Множитель;
            PaymentAmount = inSumm*(vPercent*vPeriod)/12*(1+(1/(Math.pow(1+(vPercent/12), vPeriod)-1))); // Полная сумма выплат с процентами
            MntlyPay   = PaymentAmount /vPeriod; // Месячный плаеж
            // Расчитаем первый платеж
            Graf[0] = new MonthlyPayment();
            Graf[0].setAmountOD(MntlyPay * Math.pow(1+vPercent*1/12, vPeriod*(-1))); // ОД
            Graf[0].setAmountFee(MntlyPay - Graf[0].getAmountOD()); // Проценты
            Graf[0].setMonthlyPayment(MntlyPay);


            // Все последующие платежи в цикле через множитель vMultiplier
            i = 1;
            Graf[0].setMonth(i);
            Graf[0].setYear(i);

            while (i != vPeriod){
                Graf[i] = new MonthlyPayment();
                Graf[i].setAmountOD(vMultiplier * Graf[i-1].getAmountOD());
                Graf[i].setAmountFee(MntlyPay - Graf[i].getAmountOD());
                Graf[i].setMonthlyPayment(MntlyPay);
                Graf[i].setMonth(i+1);

                if (i > 0) {
                    if ((float) Graf[i].getMonth() / 12 > Graf[i - 1].getYear()) {
                        Graf[i].setYear(Graf[i - 1].getYear() + 1);
                    } else {
                        Graf[i].setYear(Graf[i - 1].getYear());
                    }
                }
                else{
                    Graf[i].setYear(1);
                }

                ++i;
            }
            MntlyPayStr = Double.toString(getMntlyPay());
        }
        else if (inMethod==DIFFERENTIATED){
            double vAmountOD = inSumm/vPeriod;
            double vPaymentAmount = 0;
            int    j = 0;
            while (i != vPeriod) {
                j = i;
                ++i;
                Graf[j] = new MonthlyPayment();
                Graf[j].setAmountOD(vAmountOD);
                Graf[j].setMonthlyPayment(vAmountOD * (1 + vPercent / 12 * (vPeriod + 1 - i)));
                Graf[j].setAmountFee(Graf[j].getMonthlyPayment()-Graf[j].getAmountOD());
                vPaymentAmount += Graf[j].getMonthlyPayment();
                Graf[j].setMonth(i);
                if (j > 0) {
                    if ((float) Graf[j].getMonth() / 12 > Graf[j - 1].getYear()) {
                        Graf[j].setYear(Graf[j - 1].getYear() + 1);
                    } else {
                        Graf[j].setYear(Graf[j - 1].getYear());
                    }
                }
                else{
                    Graf[j].setYear(1);
                }
            }
            PaymentAmount = vPaymentAmount;
            MntlyPayStr   = Double.toString(Graf[0].getMonthlyPayment())+"..."+Double.toString(Graf[j].getMonthlyPayment());
        }

    }

    public double getPaymentAmount() {
        return PaymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        PaymentAmount = paymentAmount;
    }

    public double getMntlyPay() {
        return Lib.round_2(MntlyPay);
    }

    public void setMntlyPay(double mntlyPay) {
        MntlyPay = mntlyPay;
    }

    public MonthlyPayment[] getGraf() {
        return Graf;
    }

    public void setGraf(MonthlyPayment[] graf) {
        Graf = graf;
    }

    public int getCalcMethod() {
        return CalcMethod;
    }

    public void setCalcMethod(int calcMethod) {
        CalcMethod = calcMethod;
    }

    public void setMntlyPayStr(String mntlyPayStr){
        MntlyPayStr = mntlyPayStr;
    }

    public String getMntlyPayStr() {
        return MntlyPayStr;
    }
}
