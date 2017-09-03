package com.mojtaba_shafaei.android;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.IntDef;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ImageViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ErrorMessage extends LinearLayout {
    private final String TAG = "ErrorMessageLibrary";
    private ImageView icon;
    private TextView message;
    private Button button;

    private OnClickListener clickListener;

    public static final int ERROR = 1;
    public static final int MESSAGE = 2;
    public static final int NO_INTERNET = 3;
    public static final int NO_DATA = 4;

    @Retention(RetentionPolicy.SOURCE)
    @Documented
    @IntDef({ERROR, MESSAGE, NO_INTERNET, NO_DATA})
    public @interface ErrorMessageTypeEnum {
    }

    public ErrorMessage(Context context) {
        super(context);
        init(context);
    }

    public ErrorMessage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        readAttrs(context, attrs);
    }

    public ErrorMessage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        readAttrs(context, attrs);
    }

    private void init(Context context) {
        if (isInEditMode()) {
            return;
        }

        View root = inflate(context, R.layout.message_error_layout, this);
        root.setClickable(true);

        root.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        icon = findViewById(R.id.imageViewIcon);
        message = findViewById(R.id.textViewMessage);
        button = findViewById(R.id.btn);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.onClick(view);
                }
            }
        });
    }

    public void setMessageTypeface(Typeface typeface) {
        message.setTypeface(typeface);
        button.setTypeface(typeface);
    }

    private void readAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ErrorMessage,
                0, 0);

        try {
            int typ = a.getInteger(R.styleable.ErrorMessage_em_type, ERROR);
            initWithType(typ);

            if (a.hasValue(R.styleable.ErrorMessage_em_icon)) {
                Drawable iconRes = a.getDrawable(R.styleable.ErrorMessage_em_icon);
                icon.setImageDrawable(iconRes);
            }

            if (a.hasValue(R.styleable.ErrorMessage_em_iconTintColor)) {
                ColorStateList iconColorTint = a.getColorStateList(R.styleable.ErrorMessage_em_iconTintColor);
                setIconTint(iconColorTint);
            }

            if (a.hasValue(R.styleable.ErrorMessage_em_message)) {
                String messageString = a.getString(R.styleable.ErrorMessage_em_message);
                setMessage(messageString);
            }

            @ColorInt int colorInt = a.getColor(R.styleable.ErrorMessage_em_messageTextColor
                    , getResources().getColor(R.color.messageTextColor));

            setMessageTextColor(colorInt);

            if (a.hasValue(R.styleable.ErrorMessage_em_messageTextSize)) {
                final int textSize = a.getDimensionPixelSize(R.styleable.ErrorMessage_em_messageTextSize, getResources().getDimensionPixelSize(R.dimen.textSize));
                message.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            setButtonVisibility(a.getInt(R.styleable.ErrorMessage_em_buttonVisibility, View.GONE));
            setIconVisibility(a.getInt(R.styleable.ErrorMessage_em_iconVisibility, View.VISIBLE));

            setIconSize(a.getDimensionPixelSize(R.styleable.ErrorMessage_em_iconSize, getResources().getDimensionPixelSize(R.dimen.iconSize)));

        } catch (Exception e) {
            Log.e(TAG, "readAttrs: " + e.getMessage());
        } finally {
            a.recycle();
        }
    }

    private void initWithType(int typ) {
        switch (typ) {
            case ERROR:
                icon.setImageResource(R.drawable.ic_error);
                break;

            case MESSAGE:
                icon.setImageResource(R.drawable.ic_done);
                break;

            case NO_INTERNET:
                icon.setImageResource(R.drawable.ic_internet_off);
                message.setText(R.string.no_internet_connection);
                break;

            case NO_DATA:
                icon.setImageResource(R.drawable.ic_sad);
                message.setText("no data found");
                break;
        }
    }

    public void setIconVisibility(int visibility) {
        icon.setVisibility(visibility);
    }

    public void setIconSize(int dimensionPixelSize) {
        LayoutParams layoutParams = (LayoutParams) icon.getLayoutParams();
        layoutParams.width = dimensionPixelSize;
        layoutParams.height = dimensionPixelSize;
        icon.setLayoutParams(layoutParams);
    }

    public void setMessage(String messageString) {
        message.setText(messageString);
    }

    public void setMessage(@StringRes int messageRes) {
        message.setText(messageRes);
    }

    public void setMessageTextColor(@ColorInt int colorInt) {
        message.setTextColor(colorInt);
    }

    public void setMessageTextColorRes(@ColorRes int colorRes) {
        message.setTextColor(ContextCompat.getColor(getContext(), colorRes));
    }

    public void setButtonVisibility(int visibility) {
        button.setVisibility(visibility);
        if (visibility == View.VISIBLE) {
            ViewCompat.setElevation(button, getContext().getResources().getDimensionPixelSize(R.dimen.shadowSize));
        }
    }

    public void setOnClickListenerForButton(OnClickListener l) {
        clickListener = l;
    }

    public void setIconTint(ColorStateList iconColorTint) {
        ImageViewCompat.setImageTintList(icon, iconColorTint);
    }
}
