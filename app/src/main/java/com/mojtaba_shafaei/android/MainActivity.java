package com.mojtaba_shafaei.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

ErrorMessage em;

@Override
protected void onCreate(Bundle savedInstanceState){
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  em = findViewById(R.id.errorMessage);
  em.hide();

  findViewById(R.id.btn_menu_more).setOnClickListener(view -> InfoActivity.startActivity(this));

  findViewById(R.id.btnShowError).setOnClickListener(view -> em.showNoData());
  findViewById(R.id.btnShowLoading).setOnClickListener(view -> em.showLoading());
  findViewById(R.id.btnShowListLoading).setOnClickListener(view -> {
    em.showListLoading();
  });

  findViewById(R.id.root).setOnClickListener(view -> em.hide());
  findViewById(R.id.root).setOnLongClickListener(view -> {
    em.setLoadingData(new AnimationData(R.raw.circle_red_button, 2.50f, .6f, 0x20000000));
    em.showLoading();
    return false;
  });
}
}
