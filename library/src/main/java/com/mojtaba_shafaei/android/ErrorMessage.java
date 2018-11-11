package com.mojtaba_shafaei.android;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.button.MaterialButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.mojtaba_shafaei.androidErrorMessage.R;

public class ErrorMessage extends ConstraintLayout{

private final transient String TAG = "ErrorMessage";
private AppCompatImageView mIcon;
private AppCompatTextView mMessage;
private MaterialButton mButton;
private LottieAnimationView mProgressBar;

private AnimationData mAnimationData;

public ErrorMessage(Context context){
  this(context, null);
}

public ErrorMessage(Context context, AttributeSet attrs){
  this(context, attrs, 0);
}

public ErrorMessage(Context context, AttributeSet attrs, int defStyleAttr){
  super(context, attrs, defStyleAttr);
  init(context);
}

private void init(Context context){
  if(isInEditMode()){
    return;
  }

  View root = inflate(context, R.layout.message_error_layout, this);
  root.setClickable(true);
  root.setFocusable(true);
  /*root.setOnTouchListener(new OnTouchListener(){
    @Override
    public boolean onTouch(View v, MotionEvent event){
      return true;
    }
  });
*/
  mIcon = findViewById(R.id.iv_icon);
  mMessage = findViewById(R.id.tv_error);
  mButton = findViewById(R.id.btn_action);
  mProgressBar = findViewById(R.id.progressBar);
///////////////////////////////////////////////////////////
  mAnimationData = new AnimationData(R.raw.loading_dots, getResources().getBoolean(R.bool.is_tablet) ? 4.0f : 2.0f, 2.0f, 0xFF039BE5);
}

public ErrorMessage setTypeface(Typeface typeface){
  if(typeface != null){
    mMessage.setTypeface(typeface);
    mButton.setTypeface(typeface);
  }

  return this;
}

public ErrorMessage showMessage(CharSequence message){
  return showMessage(message, null);
}

public ErrorMessage showMessage(CharSequence message, Runnable runnable){
  return showMessage(message, null, runnable);
}

public ErrorMessage showMessage(CharSequence message, CharSequence btnActionTitle, Runnable runnable){
  setVisibility(VISIBLE);
  hideProgressBar();
  mIcon.setVisibility(GONE);
  mMessage.setVisibility(VISIBLE);

  if(runnable == null){
    mButton.setVisibility(GONE);
  } else{
    mButton.setVisibility(VISIBLE);
    mButton.setOnClickListener(v -> runnable.run());
  }
  if(btnActionTitle != null && btnActionTitle.length() > 0){
    mButton.setText(btnActionTitle);
  }
  mMessage.setText(message);

  return this;
}

private void hideProgressBar(){
  mProgressBar.pauseAnimation();
  mProgressBar.setVisibility(GONE);
}

public ErrorMessage showError(CharSequence error){
  return showError(error, null, null);
}

public ErrorMessage showError(CharSequence error, Runnable runnable){
  return showError(error, null, runnable);
}

public ErrorMessage showError(CharSequence error, CharSequence btnActionTitle, Runnable runnable){
  setVisibility(VISIBLE);

  hideProgressBar();
  mIcon.setVisibility(VISIBLE);
  mMessage.setVisibility(VISIBLE);
  if(runnable == null){
    mButton.setVisibility(GONE);
  } else{
    mButton.setVisibility(VISIBLE);
    mButton.setOnClickListener(v -> runnable.run());
  }

  if(btnActionTitle != null && btnActionTitle.length() > 0){
    mButton.setText(btnActionTitle);
  }
  mIcon.setImageDrawable(ContextCompat.getDrawable(getContext()
      , R.drawable.ic_error)
  );

  mMessage.setText(error);

  return this;
}

public ErrorMessage showInternetError(){
  return showInternetError(null);
}

/**
 * @param actionOfButton The action that runs if user click on retryButton.<br/> if {@code null} passed, so retry button will be hidden.
 */
public ErrorMessage showInternetError(@Nullable Runnable actionOfButton){
  setVisibility(VISIBLE);

  hideProgressBar();
  mIcon.setVisibility(VISIBLE);
  mMessage.setVisibility(VISIBLE);

  final Drawable drawableCompat = ContextCompat.getDrawable(getContext()
      , R.drawable.ic_internet_off);

  if(drawableCompat != null){
    mIcon.setImageDrawable(drawableCompat);
  }

  mMessage.setText(R.string.no_internet_connection);
  if(actionOfButton == null){
    mButton.setVisibility(GONE);
  } else{
    mButton.setVisibility(VISIBLE);
    mButton.setOnClickListener(v -> actionOfButton.run());
  }

  return this;
}

public ErrorMessage showNoData(){
  return showNoData(null);
}

public ErrorMessage showNoData(@Nullable Runnable actionOfButton){
  setVisibility(VISIBLE);

  hideProgressBar();
  mIcon.setVisibility(VISIBLE);
  mMessage.setVisibility(VISIBLE);

  mIcon.setImageDrawable(ContextCompat.getDrawable(getContext()
      , R.drawable.ic_sentiment_neutral_red_a100_128dp)
  );

  mMessage.setText(R.string.no_data);
  if(actionOfButton == null){
    mButton.setVisibility(GONE);
  } else{
    mButton.setVisibility(VISIBLE);
    mButton.setOnClickListener(v -> actionOfButton.run());
  }

  return this;
}

public ErrorMessage showLoading(){
  setVisibility(VISIBLE);
  mProgressBar.setSpeed(mAnimationData.speed);
  mProgressBar.setScale(mAnimationData.scale);
  mProgressBar.setAnimation(mAnimationData.resId);
  mProgressBar.playAnimation();

  if(mAnimationData.tintColor != null){
    mProgressBar.addValueCallback(new KeyPath("**"), LottieProperty.COLOR_FILTER,
                                  new LottieValueCallback<>(new PorterDuffColorFilter(mAnimationData.tintColor, Mode.SRC_ATOP)));
  }

  mProgressBar.setVisibility(VISIBLE);
  mIcon.setVisibility(GONE);
  mMessage.setVisibility(GONE);
  mButton.setVisibility(GONE);

  return this;
}

public ErrorMessage setLoadingData(AnimationData animationData){
  mAnimationData = animationData;
  return this;
}

public ErrorMessage showListLoading(){
  setLoadingData(new AnimationData(R.raw.skeleton_frame_loading, 10.0f, .8f, 0x36000000));
  showLoading();
  return this;
}

public void hide(){
  hideProgressBar();
  setVisibility(GONE);
}
}
