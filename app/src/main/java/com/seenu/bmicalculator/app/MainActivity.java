package com.seenu.bmicalculator.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class MainActivity extends ActionBarActivity implements TextWatcher {
    private EditText wtEt;
    private EditText htEt;
    private TextView resultTv;

    private double wt = 0.0;
    private double ht = 0.0;
    private double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wtEt = (EditText)findViewById(R.id.editText);
        htEt = (EditText)findViewById(R.id.editText2);
        resultTv = (TextView)findViewById(R.id.textView2);

        wtEt.addTextChangedListener(this);
        htEt.addTextChangedListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        String wtStr = wtEt.getText().toString();
        String htStr = htEt.getText().toString();

        if(wtStr.equals(""))
            wt = 0.0;
        else
            wt = Double.parseDouble(wtStr);

        if(htStr.equals(""))
            ht = 0.0;
        else
            ht = Double.parseDouble(htStr);

        result = wt/(ht*ht);
     //   result = round(result,2);
    }

    @Override
    public void afterTextChanged(Editable editable) {

        String resultStr = String.format("%.2f", result);
        System.out.println(resultStr);
        if(resultStr.equals("Infinity"))
            resultTv.setText("");
        else
            resultTv.setText(resultStr);
    }

    private double round(double value, int places){

        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
