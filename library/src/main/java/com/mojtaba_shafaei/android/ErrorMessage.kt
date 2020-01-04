package com.mojtaba_shafaei.android

import android.content.Context
import android.graphics.ColorFilter
import android.graphics.PorterDuff.Mode
import android.graphics.PorterDuffColorFilter
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.model.KeyPath
import com.airbnb.lottie.value.LottieValueCallback
import com.mojtaba_shafaei.androidErrorMessage.R
import kotlinx.android.synthetic.main.message_error_layout.view.*


class ErrorMessage : ConstraintLayout {
  constructor(context: Context) : super(context) {
    init(context)
  }

  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
    init(context)
  }

  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    init(context)
  }

  private fun init(context: Context) {
    if (isInEditMode) {
      return
    }
    val root = View.inflate(context, R.layout.message_error_layout, this)
    root.isClickable = true
    root.isFocusable = true
  }

  fun setTypeface(typeface: Typeface): ErrorMessage {
    tv_error.typeface = typeface
    btn_action.typeface = typeface

    return this
  }

  fun showMessage(
      message: CharSequence,
      @ColorInt messageTextColor: Int = transparent_black_percent_60,
      actionTitle: CharSequence = "تلاش مجدد",
      @DrawableRes actionIcon: Int = R.drawable.ic_refresh_light_blue_500_24dp,
      @ColorInt actionTextColor: Int = light_blue_500,
      action: Runnable? = null
  ): ErrorMessage {
    visibility = View.VISIBLE
    hideProgressBar()
    iv_icon.visibility = View.GONE
    tv_error.visibility = View.VISIBLE

    when (action) {
      null -> {
        btn_action.visibility = View.GONE
      }
      else -> {
        btn_action.visibility = View.VISIBLE
        btn_action.setOnClickListener { action.run() }
      }
    }

    tv_error.text = message
    tv_error.setTextColor(messageTextColor)

    btn_action.text = actionTitle
    btn_action.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(context, actionIcon), null)
    btn_action.setTextColor(actionTextColor)

    return this
  }

  private fun hideProgressBar() {
    progressBar.pauseAnimation()
    progressBar.visibility = View.GONE
  }

  fun showError(
      message: CharSequence,
      @ColorInt messageTextColor: Int = transparent_black_percent_60,
      @DrawableRes mainIcon: Int = R.drawable.ic_error,
      actionTitle: CharSequence = "تلاش مجدد",
      @DrawableRes actionIcon: Int = R.drawable.ic_refresh_light_blue_500_24dp,
      @ColorInt actionTextColor: Int = light_blue_500,
      action: Runnable? = null
  ): ErrorMessage {
    visibility = View.VISIBLE

    hideProgressBar()
    iv_icon.visibility = View.VISIBLE
    tv_error.visibility = View.VISIBLE

    when (action) {
      null -> {
        btn_action.visibility = View.GONE
      }
      else -> {
        btn_action.visibility = View.VISIBLE
        btn_action.setOnClickListener { action.run() }
      }
    }

    if (!actionTitle.isBlank()) {
      btn_action.text = actionTitle
    }

    val drawableCompat = ContextCompat.getDrawable(context, mainIcon)
    if (drawableCompat != null) {
      iv_icon.setImageDrawable(drawableCompat)
    }

    tv_error.text = message
    tv_error.setTextColor(messageTextColor)

    btn_action.text = actionTitle
    btn_action.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(context, actionIcon), null)
    btn_action.setTextColor(actionTextColor)

    return this
  }

  fun showInternetError(
      message: CharSequence = "مشکلی در اتصال اینترنت بوجود آمده است",
      @ColorInt messageTextColor: Int = transparent_black_percent_60,
      @DrawableRes mainIcon: Int = R.drawable.ic_internet_off,
      actionTitle: CharSequence = "تلاش مجدد",
      @DrawableRes actionIcon: Int = R.drawable.ic_refresh_light_blue_500_24dp,
      @ColorInt actionTextColor: Int = light_blue_500,
      action: Runnable? = null
  ): ErrorMessage {
    visibility = View.VISIBLE
    hideProgressBar()
    iv_icon.visibility = View.VISIBLE
    tv_error.visibility = View.VISIBLE

    when (action) {
      null -> {
        btn_action.visibility = View.GONE
      }
      else -> {
        btn_action.visibility = View.VISIBLE
        btn_action.setOnClickListener { action.run() }
      }
    }

    val drawableCompat = ContextCompat.getDrawable(context, mainIcon)
    if (drawableCompat != null) {
      iv_icon.setImageDrawable(drawableCompat)
    }

    tv_error.text = message
    tv_error.setTextColor(messageTextColor)

    btn_action.text = actionTitle
    btn_action.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(context, actionIcon), null)
    btn_action.setTextColor(actionTextColor)

    return this
  }

  fun showNoData(
      message: String = "مقادیری برای نمایش وجود ندارد",
      @ColorInt messageTextColor: Int = error_text_color,
      @DrawableRes mainIcon: Int = R.drawable.ic_sentiment_neutral_red_a100_128dp,
      actionTitle: CharSequence = "تلاش مجدد",
      @DrawableRes actionIcon: Int = R.drawable.ic_refresh_light_blue_500_24dp,
      @ColorInt actionTextColor: Int = light_blue_500,
      action: Runnable? = null
  ): ErrorMessage {
    visibility = View.VISIBLE

    hideProgressBar()
    iv_icon.visibility = View.VISIBLE
    tv_error.visibility = View.VISIBLE

    when (action) {
      null -> {
        btn_action.visibility = View.GONE
      }
      else -> {
        btn_action.visibility = View.VISIBLE
        btn_action.setOnClickListener { action.run() }
      }
    }

    val drawableCompat = ContextCompat.getDrawable(context, mainIcon)
    if (drawableCompat != null) {
      iv_icon.setImageDrawable(drawableCompat)
    }

    tv_error.text = message
    tv_error.setTextColor(messageTextColor)

    btn_action.text = actionTitle
    btn_action.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(context, actionIcon), null)
    btn_action.setTextColor(actionTextColor)

    return this
  }

  fun showLoading(animationData: AnimationData = AnimationData(R.raw.loading, 1.0f, 0.85f, null)): ErrorMessage {
    visibility = View.VISIBLE
    progressBar.speed = animationData.speed
    progressBar.scale = animationData.scale
    progressBar.setAnimation(animationData.loadingRawResId)
    progressBar.playAnimation()

    val tintColor = animationData.tintColor
    if (tintColor != null) {
      progressBar.addValueCallback<ColorFilter>(KeyPath("**"), LottieProperty.COLOR_FILTER,
          LottieValueCallback<ColorFilter>(PorterDuffColorFilter(tintColor, Mode.SRC_ATOP)))
    }

    progressBar.visibility = View.VISIBLE

    val dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.wh_loading)
    progressBar.layoutParams.width = dimensionPixelSize
    progressBar.layoutParams.height = dimensionPixelSize
    val constraintSet = ConstraintSet()
    constraintSet.clone(l_constraint)
    constraintSet.connect(R.id.progressBar, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
    constraintSet.connect(R.id.progressBar, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
    constraintSet.connect(R.id.progressBar, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
    constraintSet.connect(R.id.progressBar, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
    constraintSet.applyTo(l_constraint)

    iv_icon.visibility = View.GONE
    tv_error.visibility = View.GONE
    btn_action.visibility = View.GONE

    return this
  }

  fun showListLoading(animationData: AnimationData = AnimationData(R.raw.skeleton_frame_loading, 12.0f, .8f, 0x36000000)): ErrorMessage {
    visibility = View.VISIBLE
    progressBar.speed = animationData.speed
    progressBar.scale = animationData.scale
    progressBar.setAnimation(animationData.loadingRawResId)
    progressBar.playAnimation()

    val tintColor = animationData.tintColor
    if (tintColor != null) {
      progressBar.addValueCallback<ColorFilter>(KeyPath("**"), LottieProperty.COLOR_FILTER,
          LottieValueCallback<ColorFilter>(PorterDuffColorFilter(tintColor, Mode.SRC_ATOP)))
    }

    progressBar.visibility = View.VISIBLE


    progressBar.layoutParams.width = 0
    progressBar.layoutParams.height = 0

    val constraintSet = ConstraintSet()
    constraintSet.clone(l_constraint)
    constraintSet.connect(R.id.progressBar, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
    constraintSet.connect(R.id.progressBar, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
    constraintSet.connect(R.id.progressBar, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
    constraintSet.connect(R.id.progressBar, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
    constraintSet.applyTo(l_constraint)

    iv_icon.visibility = View.GONE
    tv_error.visibility = View.GONE
    btn_action.visibility = View.GONE
    return this
  }

  fun hide() {
    hideProgressBar()
    visibility = View.GONE
  }

  @Deprecated("Please Use any of show... methods instead of setVisibility(View.VISIBLE) and use hide() instead of setVisibility(GONE)", level = DeprecationLevel.WARNING)
  override fun setVisibility(visibility: Int) {
    if (visibility == View.GONE || visibility == View.INVISIBLE) {
      hideProgressBar()
    }
    super.setVisibility(visibility)
  }

  fun getActionButton(): View {
    return btn_action
  }
}
