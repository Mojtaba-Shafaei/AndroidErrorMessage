package com.mojtaba_shafaei.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity{

public static void startActivity(Activity activity){
  Intent intent = new Intent(activity, InfoActivity.class);
  activity.startActivity(intent);
}

@Override
protected void onCreate(Bundle savedInstanceState){
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_info);

  ((TextView) findViewById(R.id.version)).setText(String.format("version: %s", BuildConfig.VERSION_NAME));
}
}
