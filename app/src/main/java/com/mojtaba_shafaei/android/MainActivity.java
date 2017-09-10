package com.mojtaba_shafaei.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ErrorMessage em;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        em = findViewById(R.id.errorMessage2);
        em.initWithType(ErrorMessage.NO_INTERNET);
        em.setOnClickListenerForButton(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                Toast.makeText(MainActivity.this, "Button clicked", Toast.LENGTH_SHORT).show();
                em.setVisibility(View.GONE);
            }
        });

        findViewById(R.id.btn_menu_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnShowError).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                em.setVisibility(em.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });

        em.setIconTint(ContextCompat.getColorStateList(this,R.color.icon_color));
    }
}
