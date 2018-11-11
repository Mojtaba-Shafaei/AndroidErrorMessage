package com.mojtaba_shafaei.android;

import android.support.annotation.RawRes;

public class AnimationData{

@RawRes
int resId;
float scale;
float speed;
Integer tintColor;

public AnimationData(@RawRes int resId, float scale, float speed, Integer tintColor){
  this.resId = resId;
  this.scale = scale;
  this.speed = speed;
  this.tintColor = tintColor;
}
}
