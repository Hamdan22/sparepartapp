package com.example.spareparts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2Home extends AppCompatActivity {

    Button adminpgebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_home);

        adminpgebtn = findViewById(R.id.btnadminpage);

        adminpgebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inn2 = new Intent(MainActivity2Home.this,MainActivity.class);
                startActivity(inn2);
            }
        });
    }
}