package com.kientran.lab01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onClick(View view ){
        answer = findViewById(R.id.answer);
        switch (view.getId()){
            case R.id.bt_yes:
                answer.setText(R.string.answer_yes);
                break;
            case R.id.bt_no:
                answer.setText(R.string.answer_no);
                break;
            default:
                break;

        }

    }
}
