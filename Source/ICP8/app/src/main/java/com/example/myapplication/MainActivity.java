package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid = false;
                TextView et1 = findViewById(R.id.editText1);
                EditText et2 = findViewById(R.id.editText2);
                String s1 = et1.getText().toString();
                String s2 = et2.getText().toString();
                if(s1.equals("admin") && s2.equals("1234"))
                    valid = true;

                if (valid){
                    Intent I = new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(I);
                }
                else{
                    Context context = getApplicationContext();
                    CharSequence text = "Wrong input!";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });



    }
}
