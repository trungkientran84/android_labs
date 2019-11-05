package com.kientran.week04;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtCalcHistory;
    private TextView txtResult;

    private CalculatorEngine calcEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_main);

        txtCalcHistory = findViewById(R.id.txt_all);
        txtResult = findViewById(R.id.txt_result);

        calcEngine = new CalculatorEngine();
    }

    public void buttonPressed(View view){

        calcEngine.calc(String.valueOf(((Button) view).getText()));
        txtCalcHistory.setText(calcEngine.getCalcHistory());

        String resultLine = calcEngine.getCalcResult();
        if(resultLine.length() > 8){
            txtResult.setTextSize(50);
        } else {
            txtResult.setTextSize(90);
        }
        txtResult.setText(calcEngine.getCalcResult());
    }
}
