package com.mojtaba_shafaei.android

import android.content.Context
import android.graphics.ColorFilter
import android.graphics.PorterDuff.Mode
import android.graphics.PorterDuffColorFilter
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.model.KeyPath
import com.airbnb.lottie.value.LottieValueCallback
import com.mojtaba_shafaei.androidErrorMessage.R
import kotlinx.android.synthetic.main.message_error_layout.view.*


class ErrorMessage @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var mAnimationData: AnimationData? = null
    private var mListAnimationData: AnimationData? = null

    init {
        init(context)
    }

    private fun init(context: Context) {
        if (isInEditMode) {
            return
        }

        val root = View.inflate(context, R.layout.message_error_layout, this)
        root.isClickable = true
        root.isFocusable = true

        ///////////////////////////////////////////////////////////
        mAnimationData = AnimationData(R.raw.loading, 1.0f, 0.85f, null)
        mListAnimationData = AnimationData(R.raw.skeleton_frame_loading, 12.0f, .8f, 0x36000000)
    }

    fun setTypeface(typeface: Typeface?): ErrorMessage {
        if (typeface != null) {
            tv_error.typeface = typeface
            btn_action!!.typeface = typeface
        }

        return this
    }

    @JvmOverloads
    fun showMessage(message: CharSequence, runnable: Runnable? = null): ErrorMessage {
        return showMessage(message, null, runnable)
    }

    fun showMessage(message: CharSequence, btnActionTitle: CharSequence?, runnable: Runnable?): ErrorMessage {
        visibility = View.VISIBLE
        hideProgressBar()
        iv_icon.visibility = View.GONE
        tv_error.visibility = View.VISIBLE

        if (runnable == null) {
            btn_action.visibility = View.GONE
        } else {
            btn_action.visibility = View.VISIBLE
            btn_action.setOnClickListener { runnable.run() }
        }
        if (btnActionTitle != null && btnActionTitle.isNotEmpty()) {
            btn_action.text = btnActionTitle
        }
        tv_error.text = message

        return this
    }

    private fun hideProgressBar() {
        progressBar.pauseAnimation()
        progressBar.visibility = View.GONE
    }

    fun showError(error: CharSequence, runnable: Runnable): ErrorMessage {
        return showError(error, null, runnable)
    }

    @JvmOverloads
    fun showError(error: CharSequence, btnActionTitle: CharSequence? = null, runnable: Runnable? = null): ErrorMessage {
        visibility = View.VISIBLE

        hideProgressBar()
        iv_icon.visibility = View.VISIBLE
        tv_error.visibility = View.VISIBLE
        if (runnable == null) {
            btn_action.visibility = View.GONE
        } else {
            btn_action.visibility = View.VISIBLE
            btn_action.setOnClickListener { runnable.run() }
        }

        if (btnActionTitle != null && btnActionTitle.isNotEmpty()) {
            btn_action.text = btnActionTitle
        }
        iv_icon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_error)
        )

        tv_error.text = error

        return this
    }


    /**
     * @param message String of of error description
     * @param actionOfButton The action that runs if user click on retryButton.<br></br> if `null` passed, so retry button will be hidden.
     */
    @JvmOverloads
    fun showInternetError(message: String?, actionOfButton: Runnable? = null): ErrorMessage {
        visibility = View.VISIBLE

        hideProgressBar()
        iv_icon.visibility = View.VISIBLE
        tv_error.visibility = View.VISIBLE

        val drawableCompat = ContextCompat.getDrawable(context, R.drawable.ic_internet_off)

        if (drawableCompat != null) {
            iv_icon.setImageDrawable(drawableCompat)
        }

        if (message != null)
            tv_error.text = message
        else
            tv_error.setText(R.string.no_internet_connection)

        if (actionOfButton == null) {
            btn_action.visibility = View.GONE
        } else {
            btn_action.visibility = View.VISIBLE
            btn_action.setOnClickListener { actionOfButton.run() }
        }

        return this
    }

    /**
     * @param actionOfButton The action that runs if user click on retryButton.<br></br> if `null` passed, so retry button will be hidden.
     */
    @JvmOverloads
    fun showInternetError(actionOfButton: Runnable? = null): ErrorMessage {
        return showInternetError(null, actionOfButton)
    }

    @JvmOverloads
    fun showNoData(actionOfButton: Runnable? = null): ErrorMessage {
        return showNoData(null, actionOfButton)
    }

    @JvmOverloads
    fun showNoData(message: String?, actionOfButton: Runnable? = null): ErrorMessage {
        visibility = View.VISIBLE

        hideProgressBar()
        iv_icon.visibility = View.VISIBLE
        tv_error.visibility = View.VISIBLE

        iv_icon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_sentiment_neutral_red_a100_128dp))

        if (message == null)
            tv_error.setText(R.string.no_data)
        else
            tv_error.text = message

        if (actionOfButton == null) {
            btn_action.visibility = View.GONE
        } else {
            btn_action.visibility = View.VISIBLE
            btn_action.setOnClickListener { actionOfButton.run() }
        }

        return this
    }

    fun showLoading(): ErrorMessage {
        visibility = View.VISIBLE
        progressBar.speed = mAnimationData!!.speed
        progressBar.scale = mAnimationData!!.scale
        progressBar.setAnimation(mAnimationData!!.resId)
        progressBar.playAnimation()

        if (mAnimationData!!.tintColor != null) {
            progressBar.addValueCallback<ColorFilter>(KeyPath("**"), LottieProperty.COLOR_FILTER,
                    LottieValueCallback<ColorFilter>(PorterDuffColorFilter(mAnimationData!!.tintColor, Mode.SRC_ATOP)))
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

    fun showListLoading(): ErrorMessage {
        visibility = View.VISIBLE
        progressBar.speed = mListAnimationData!!.speed
        progressBar.scale = mListAnimationData!!.scale
        progressBar.setAnimation(mListAnimationData!!.resId)
        progressBar.playAnimation()

        if (mListAnimationData!!.tintColor != null) {
            progressBar.addValueCallback<ColorFilter>(KeyPath("**"), LottieProperty.COLOR_FILTER,
                    LottieValueCallback<ColorFilter>(PorterDuffColorFilter(mListAnimationData!!.tintColor, Mode.SRC_ATOP)))
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

    @Deprecated("Please Use any of show... methods instead of setVisibility(View.VISIBLE) and use hide() instead of setVisibility(GONE)")
    override fun setVisibility(visibility: Int) {
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            hideProgressBar()
        }
        super.setVisibility(visibility)
    }
}
