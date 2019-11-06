package com.kientran.myrecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.kientran.myrecyclerview.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements LaptopFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Toast.makeText(this, "Clicked : " + item.toString() , Toast.LENGTH_SHORT).show();
    }
}
