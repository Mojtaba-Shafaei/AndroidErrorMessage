package com.mojtaba_shafaei.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

ErrorMessage em;

@Override
protected void onCreate(Bundle savedInstanceState){
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  em = findViewById(R.id.errorMessage);
  em.hide();

  findViewById(R.id.btn_menu_more).setOnClickListener(view -> InfoActivity.startActivity(this));

  findViewById(R.id.btnShowError)
      .setOnClickListener(view ->
                              em.showNoData(

                              ));
}
}
