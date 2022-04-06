package com.mrnadimi.androidrecyclerexp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mrnadimi.androidfragment.FragmentUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentUtils.replaceFragment(this ,new TestFragment());
    }
}