package kz.almurt.paiyz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class BasActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Spinner vSrCalcKind;
    EditText vEdtSoma;
    EditText vEdtPaiyz;
    EditText vEdtPeriod;
    EditText vEdtYears;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vEdtSoma     = (EditText)findViewById(R.id.edtAmount);
        vEdtPaiyz     = (EditText)findViewById(R.id.edtPercent);
        vEdtPeriod  = (EditText)findViewById(R.id.edtPeriod);
        vEdtYears   = (EditText)findViewById(R.id.edtYears);
        vSrCalcKind = (Spinner)findViewById(R.id.sprKindCalc); // Оприделим Spinner в переменную vSrCalcKind

        // Заполним Spinner
        ArrayAdapter<CharSequence> arrAdr = ArrayAdapter.createFromResource(this, R.array.vCalcKind_strarr, android.R.layout.simple_spinner_item);
        arrAdr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vSrCalcKind.setAdapter(arrAdr);
        ////


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vEdtSoma    .getText().length() == 0 || (vEdtPeriod.getText().length() == 0 && vEdtYears.getText().length() == 0) || vEdtPaiyz.getText().length() == 0){
                    Snackbar.make(view, R.string.err_no_all_fialds, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else{
                    Intent vIntNatijeActivity = new Intent(BasActivity.this, NatijeActivity.class);

                    vIntNatijeActivity.putExtra("Soma", Double.parseDouble(vEdtSoma.getText().toString()));
                    vIntNatijeActivity.putExtra("Paiyz", Double.parseDouble(vEdtPaiyz.getText().toString()));
                    vIntNatijeActivity.putExtra("Metod", (int)vSrCalcKind.getSelectedItemId());

                    if (vEdtPeriod.getText().toString().isEmpty()){
                        vIntNatijeActivity.putExtra("Months",  0);
                    }
                    else{
                        vIntNatijeActivity.putExtra("Months", Integer.parseInt(vEdtPeriod.getText().toString()));
                    }

                    if (vEdtYears.getText().toString().isEmpty()){
                        vIntNatijeActivity.putExtra("Years", 0);
                    }
                    else{
                        vIntNatijeActivity.putExtra("Years", Integer.parseInt(vEdtYears.getText().toString()));
                    }


                    startActivity(vIntNatijeActivity);

                }
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {


        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
