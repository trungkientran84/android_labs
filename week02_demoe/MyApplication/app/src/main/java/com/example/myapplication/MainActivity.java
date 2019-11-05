package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    public static final String MY_TAG = "aaq";
    Bundle instanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(MY_TAG, "onCreate: ");
        editText = findViewById(R.id.et);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(MY_TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref = this.getPreferences(MODE_PRIVATE);
//        if (pref.contains(getString(R.string.et_text)))
        String text = pref.getString(getString(R.string.et_text),"Hello");
        editText.setText(text);
        Log.i(MY_TAG, "onResume: "+text);

    }

    @Override
    protected void onPause() {
        super.onPause();
        String text = editText.getText().toString();
        SharedPreferences pref = this.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(getString(R.string.et_text), text);
        editor.commit();
        Log.i(MY_TAG, "onPause: "+text);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(MY_TAG, "onStop: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(MY_TAG, "onRestart: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(MY_TAG, "onDestroy: ");
    }

}
