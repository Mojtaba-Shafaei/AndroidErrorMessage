package com.mojtaba_shafaei.android;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.button.MaterialButton;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import com.mojtaba_shafaei.androidErrorMessage.R;

public class ErrorMessage extends ConstraintLayout{

private final transient String TAG = "ErrorMessage";
private AppCompatImageView mIcon;
private AppCompatTextView mMessage;
private MaterialButton mButton;
private ContentLoadingProgressBar mProgressBar;

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
  setVisibility(VISIBLE);
  mProgressBar.hide();
  mIcon.setVisibility(GONE);
  mMessage.setVisibility(VISIBLE);

  if(runnable == null){
    mButton.setVisibility(GONE);
  } else{
    mButton.setVisibility(VISIBLE);
    mButton.setOnClickListener(v -> runnable.run());
  }

  mMessage.setText(message);

  return this;
}

public ErrorMessage showError(CharSequence error){
  return showError(error, null);
}

public ErrorMessage showError(CharSequence error, Runnable runnable){
  setVisibility(VISIBLE);

  mProgressBar.hide();
  mIcon.setVisibility(VISIBLE);
  mMessage.setVisibility(VISIBLE);
  if(runnable == null){
    mButton.setVisibility(GONE);
  } else{
    mButton.setVisibility(VISIBLE);
    mButton.setOnClickListener(v -> runnable.run());
  }

  mIcon.setImageDrawable(VectorDrawableCompat.create(getResources()
      , R.drawable.ic_error
      , null)
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

  mProgressBar.hide();
  mIcon.setVisibility(VISIBLE);
  mMessage.setVisibility(VISIBLE);

  final VectorDrawableCompat drawableCompat = VectorDrawableCompat.create(getResources()
      , R.drawable.ic_internet_off
      , null);

  if(drawableCompat != null){
    mIcon.setImageDrawable(drawableCompat.getCurrent());
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

  mProgressBar.hide();
  mIcon.setVisibility(VISIBLE);
  mMessage.setVisibility(VISIBLE);

  mIcon.setImageDrawable(VectorDrawableCompat.create(getResources()
      , R.drawable.ic_sentiment_dissatisfied_red_a100_24dp
      , null)
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

  mProgressBar.show();
  mIcon.setVisibility(GONE);
  mMessage.setVisibility(GONE);
  mButton.setVisibility(GONE);

  return this;
}

public void hide(){
  setVisibility(GONE);
}
}
