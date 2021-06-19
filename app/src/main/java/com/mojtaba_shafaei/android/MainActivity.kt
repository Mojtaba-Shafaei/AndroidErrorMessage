package com.mojtaba_shafaei.android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mojtaba_shafaei.android.ErrorMessage.State
import com.mojtaba_shafaei.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
 private lateinit var binding: ActivityMainBinding
 
 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  binding = ActivityMainBinding.inflate(layoutInflater)
  val view = binding.root
  setContentView(view)

  binding.btnSimpleShowInternetError.setOnClickListener { binding.em.state = State.internetError() { Log.e("TAG", "internet error fired") } }
  binding.btnShowInternetError.setOnClickListener { binding.em.state = State.internetError(message =binding.btnShowInternetError.tag as String? , actionTitle = "My Action...") { Log.e("TAG", "internet error 2 fired") } }

  binding.btnClose.setOnClickListener { binding.em.state = State.hidden() }

  binding.btnShowError.setOnClickListener { binding.em.state = State.error(message = getString(R.string.error_String)).copy(messageTextColor = 0xFFFF0000.toInt()) }
  binding.btnShowError2.setOnClickListener { binding.em.state = State.error(message = "Hey Developer. This is a Green error!", actionTitle = "Action Title", action = {
   Log.e("TAG", "btnShowError2 fired")
  })
          .copy(messageTextColor = 0xFF00FF00.toInt()) }// the copy method is in the parent class of State

  binding.btnShowNoDataError.setOnClickListener { binding.em.state = State.noData() }
  binding.btnShowNoDataError2.setOnClickListener { binding.em.state = State.noData(message = "Not Found...", actionTitle = "Try Again♣", action = { Log.d("TAG", "No Data2 fired")})
          .copy(mainIcon = android.R.drawable.stat_notify_error)}

  binding.btnShowLoading.setOnClickListener { binding.em.state = State.loading() }

  binding.btnShowMessage.setOnClickListener { binding.em.state = State.message(message = "Hey Developer, This is a message that you can change it.",
          action = { Log.d("TAG", "action Message fired") }) }
 }
}
