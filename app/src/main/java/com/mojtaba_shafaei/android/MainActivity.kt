package com.mojtaba_shafaei.android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mojtaba_shafaei.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
 private lateinit var binding: ActivityMainBinding
 
 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  binding = ActivityMainBinding.inflate(layoutInflater)
  val view = binding.root
  setContentView(view)
  
  binding.btnClose.setOnClickListener { binding.em.state = ErrorMessage.State.hidden() }
  binding.btnShowInternetError.setOnClickListener { binding.em.state = ErrorMessage.State.internetError { Log.e("TAG", "internet error fired") } }
  binding.btnShowError.setOnClickListener { binding.em.state = ErrorMessage.State.error(message = getString(R.string.error_String)).copy(messageTextColor = 0xFFFF0000.toInt()) }
  binding.btnShowNoDataError.setOnClickListener { binding.em.state = ErrorMessage.State.noData() }
  binding.btnShowLoading.setOnClickListener { binding.em.state = ErrorMessage.State.loading() }
  binding.btnShowMessage.setOnClickListener { binding.em.state = ErrorMessage.State.message(message = "این یک پیغام تستی است", action = { Log.d("TAG", "action Message fired") }) }
 }
}
