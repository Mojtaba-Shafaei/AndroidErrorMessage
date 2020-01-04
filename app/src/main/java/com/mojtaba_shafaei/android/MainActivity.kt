package com.mojtaba_shafaei.android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    btn_close.setOnClickListener { em.hide() }

    btnShowInternetError.setOnClickListener { em.showInternetError(action = Runnable { Log.d("TAG", "action fired") }) }
    btnShowError.setOnClickListener { em.showError(getString(R.string.error_String)) }
    btnShowNoDataError.setOnClickListener { em.showNoData() }
    btnShowLoading.setOnClickListener { em.showLoading() }
    btnShowMessage.setOnClickListener { em.showMessage(message = "این یک پیغام تستی است", action = Runnable { finish() }) }
    btnShowListLoading.setOnClickListener { em.showListLoading() }
  }
}
