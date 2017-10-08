package kz.almurt.paiyz;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.TextView;
import kz.almurt.paiyz.faces.GroupStr;
import kz.almurt.paiyz.faces.NesieListAdapter;
import kz.almurt.paiyz.formula.Loan;
import kz.almurt.paiyz.formula.MonthlyPayment;

public class NatijeActivity extends AppCompatActivity {
    SparseArray<GroupStr> groups = new SparseArray<GroupStr>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_natije);
        ActionBar vBar = getSupportActionBar();

        vBar.setHomeButtonEnabled(true);
        vBar.setDisplayHomeAsUpEnabled(true);

        TextView tvNatije = (TextView) findViewById(R.id.txtNatije);
        ExpandableListView lvNatije = (ExpandableListView) findViewById(R.id.lstNatije);
        NesieListAdapter vListAdapter = new NesieListAdapter(this, groups);

        Intent vIntent = getIntent();

        double vAmount  = 0;
        double vPercent = 0;
        int vMonths     = 0;
        int vYears      = 0;
        int vKindCalc   = 0;

        vAmount   = vIntent.getDoubleExtra("Soma",  vAmount);
        vPercent  = vIntent.getDoubleExtra("Paiyz", vPercent);
        vMonths   = vIntent.getIntExtra("Months", vMonths);
        vYears    = vIntent.getIntExtra("Years",  vYears);
        vKindCalc = vIntent.getIntExtra("Metod", vKindCalc);

        Loan vLoan = new Loan(vAmount, vPercent, vYears, vMonths, vKindCalc);
        MonthlyPayment[] vMPGraf = new MonthlyPayment[vLoan.getGraf().length];

        System.arraycopy(vLoan.getGraf(), 0, vMPGraf, 0, vLoan.getGraf().length);

        createData(vMPGraf);
        lvNatije.setAdapter(vListAdapter);
        lvNatije.expandGroup(0); // Раскроем первую группу

        tvNatije.setText("Общая сумма платежей:  " + String.format("%.2f", vLoan.getPaymentAmount()) + '\n'
                        +"Месячный платеж: " + vLoan.getMntlyPayStr() /*String.format("%.2f", vLoan.getMntlyPay())*/ +'\n');
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                // TODO startActivity(new Intent(this, MainFinCalcActivity.class));
                return true;
            /*case R.id.action_settings:
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void createData(MonthlyPayment[] pMonthlyPayment){
        int idx = 0;
        for (int y = 0; (y < pMonthlyPayment[pMonthlyPayment.length-1].getYear()); y++){
            GroupStr group = new GroupStr(Integer.toString(pMonthlyPayment[idx].getYear())+"-й год");
            for (int x = 0; ((x < 12) & (idx < pMonthlyPayment.length)); x++){
                group.children.add(Integer.toString(pMonthlyPayment[idx].getMonth())
                                   +". Платеж "+Double.toString(pMonthlyPayment[idx].getMonthlyPayment())
                                   + " (долг " + Double.toString(pMonthlyPayment[idx].getAmountOD())
                                   + " проценты "+Double.toString(pMonthlyPayment[idx].getAmountFee())+")"
                                  );
                idx++;
            }
            groups.append(y, group);
        }
    }
}
