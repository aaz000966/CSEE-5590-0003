package com.example.vijaya.myorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView mTextview = (TextView) findViewById(R.id.textView2);

        mTextview.setText(getIntent().getStringExtra("message"));
    }
}
