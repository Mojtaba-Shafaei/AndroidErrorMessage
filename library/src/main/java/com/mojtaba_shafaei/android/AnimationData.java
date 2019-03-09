package com.mojtaba_shafaei.android;

import androidx.annotation.RawRes;

class AnimationData{

@RawRes
int resId;
float scale;
float speed;
Integer tintColor;

AnimationData(@RawRes int resId, float scale, float speed, Integer tintColor){
  this.resId = resId;
  this.scale = scale;
  this.speed = speed;
  this.tintColor = tintColor;
}
}
