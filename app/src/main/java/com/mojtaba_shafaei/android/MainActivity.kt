package com.mojtaba_shafaei.android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    btn_close.setOnClickListener { em.state = ErrorMessage.State.hidden() }

    btnShowInternetError.setOnClickListener { em.state = ErrorMessage.State.internetError { Log.e("TAG", "internet error fired") } }
    btnShowError.setOnClickListener { em.state = ErrorMessage.State.error(message = getString(R.string.error_String)).copy(messageTextColor = 0xFFFF0000.toInt()) }
    btnShowNoDataError.setOnClickListener { em.state = ErrorMessage.State.noData() }
    btnShowLoading.setOnClickListener { em.state = ErrorMessage.State.loading() }
    btnShowMessage.setOnClickListener { em.state = ErrorMessage.State.message(message = "این یک پیغام تستی است", action = { Log.d("TAG", "action Message fired") }) }
  }
}
